-- WHERE Clause Optimization
https://dev.mysql.com/doc/refman/5.7/en/where-optimization.html
https://dev.mysql.com/doc/internals/en/optimizer-constant-propagation.html

Removal of unnecessary parentheses:
   ((a AND b) AND c OR (((a AND b) AND (c AND d))))
-> (a AND b AND c) OR (a AND b AND c AND d)

Constant folding:
   (a<b AND b=c) AND a=5
-> b>5 AND b=c AND a=5

Constant condition removal (needed because of constant folding):
   (B>=5 AND B=5) OR (B=6 AND 5=5) OR (B=7 AND 5=6)
-> B=5 OR B=6

Constant expressions used by indexes are evaluated only once.

COUNT(*) on a single table without a WHERE is retrieved directly from the table information for MyISAM and MEMORY tables.
This is also done for any NOT NULL expression when used with only one table.

Early detection of invalid constant expressions.
MySQL quickly detects that some SELECT statements are impossible and returns no rows.

HAVING is merged with WHERE if you do not use GROUP BY or aggregate functions (COUNT(), MIN(), and so on).

For each table in a join, a simpler WHERE is constructed to get a fast WHERE evaluation for the table and also to skip rows as soon as possible.

All constant tables are read first before any other tables in the query. A constant table is any of the following:
1.An empty table or a table with one row.
2.A table that is used with a WHERE clause on a PRIMARY KEY or a UNIQUE index, where all index parts are compared to constant expressions and are defined as NOT NULL.
All of the following tables are used as constant tables:
1.SELECT * FROM t WHERE primary_key=1;
2.SELECT * FROM t1,t2
  WHERE t1.primary_key=1 AND t2.primary_key=t1.id;

The best join combination for joining the tables is found by trying all possibilities.
If all columns in ORDER BY and GROUP BY clauses come from the same table, that table is preferred first when joining.

If there is an ORDER BY clause and a different GROUP BY clause,
or if the ORDER BY or GROUP BY contains columns from tables other than the first table in the join queue,
a temporary table is created.

If you use the SQL_SMALL_RESULT modifier, MySQL uses an in-memory temporary table.

Each table index is queried, and the best index is used unless the optimizer believes that it is more efficient to use a table scan.
At one time, a scan was used based on whether the best index spanned more than 30% of the table,
but a fixed percentage no longer determines the choice between using an index or a scan.
The optimizer now is more complex and bases its estimate on additional factors such as table size, number of rows, and I/O block size.

In some cases, MySQL can read rows from the index without even consulting the data file.
If all columns used from the index are numeric, only the index tree is used to resolve the query.

Before each row is output, those that do not match the HAVING clause are skipped.

Some examples of queries that are very fast:
SELECT COUNT(*) FROM tbl_name;

SELECT MIN(key_part1),MAX(key_part1) FROM tbl_name;

SELECT MAX(key_part2) FROM tbl_name
  WHERE key_part1=constant;

SELECT ... FROM tbl_name
  ORDER BY key_part1,key_part2,... LIMIT 10;

SELECT ... FROM tbl_name
  ORDER BY key_part1 DESC, key_part2 DESC, ... LIMIT 10;

MySQL resolves the following queries using only the index tree, assuming that the indexed columns are numeric:
SELECT key_part1,key_part2 FROM tbl_name WHERE key_part1=val;

SELECT COUNT(*) FROM tbl_name
  WHERE key_part1=val1 AND key_part2=val2;

SELECT key_part2 FROM tbl_name GROUP BY key_part1;

The following queries use indexing to retrieve the rows in sorted order without a separate sorting pass:
SELECT ... FROM tbl_name
  ORDER BY key_part1,key_part2,... ;

SELECT ... FROM tbl_name
  ORDER BY key_part1 DESC, key_part2 DESC, ... ;