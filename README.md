# mysql-parser

## 问题
* 运算优先级怎么解决
* 运算的结合顺序是怎样指定的

## 特征(实现中...)
* 语法检查，语法优化
* 可视化抽象语法树
* 支持语法mybatis语法:#{var},${var}
* 客户端解析SQL并分解成小SQL，可以在分库分表时使用

## demo
### 语法推导树
![语法推导树](https://github.com/buzhidaolvtu/mysql-parser/blob/master/demo/syntax%20derivation%20parse%20tree.png)
### 抽象语法树
![抽象语法树](https://github.com/buzhidaolvtu/mysql-parser/blob/master/demo/simpfied%20ast%20tree.png)
### table语法树
![table语法树](https://github.com/buzhidaolvtu/mysql-parser/blob/master/demo/table%20ast%20tree.png)
### query tree
![query tree](https://github.com/buzhidaolvtu/mysql-parser/blob/master/demo/query.png)

## understanding
* select -> map  
* from   -> source  
* where  -> filter<br>
  类似于流式处理 source.stream().filter().map();
  
## 应用
* mybatis集成，数据自动智能生成，单体测试更方便

## 限制
to be continued...

## 优化
<p width="100%">Logical Optimizations
   The logical optimization phase applies standard rule-based optimizations to the logical plan. (Cost-based optimization is performed by generating multiple plans using rules, and then computing their costs.) These include constant folding, predicate pushdown, projection pruning, null propagation, Boolean expression simplification, and other rules. In general, we have found it extremely simple to add rules for a wide variety of situations. For example, when we added the fixed-precision DECIMAL type to Spark SQL, we wanted to optimize aggregations such as sums and averages on DECIMALs with small precisions; it took 12 lines of code to write a rule that finds such decimals in SUM and AVG expressions, and casts them to unscaled 64-bit LONGs, does the aggregation on that, then converts the result back. A simplified version of this rule that only optimizes SUM expressions is reproduced below:
<p>
<a href="https://databricks.com/blog/2015/04/13/deep-dive-into-spark-sqls-catalyst-optimizer.html">Logical Optimizations</a>
  
## reference  
[运算符优先级与结合顺序](https://theantlrguy.atlassian.net/wiki/spaces/ANTLR3/pages/2687077/Operator+precedence+parser)
* 不同结合顺序一定有不同的优先级
* 相同优先级一定有相同的结合顺序（其实是上面命题的等价表示）