expr:
    expr OR expr
  | expr || expr
  | expr XOR expr
  | expr AND expr
  | expr '&' '&' expr
  | NOT expr
  | '!' expr
  | boolean_primary IS NOT? (TRUE | FALSE | UNKNOWN)
  | boolean_primary

boolean_primary:
    boolean_primary IS NOT? NULL
  | boolean_primary '<' '=' '>' predicate
  | boolean_primary comparison_operator predicate
  | boolean_primary comparison_operator (ALL | ANY) '(' subquery ')'
  | predicate

comparison_operator: '=' | '>' '=' | '>' | '<' '=' | '<' | '<' '>' | '!' '='

predicate:
    bit_expr NOT? IN '(' subquery ')'
  | bit_expr NOT? IN '(' expr (',' expr)* ')'
  | bit_expr NOT? BETWEEN bit_expr AND predicate
  | bit_expr SOUNDS LIKE bit_expr
  | bit_expr NOT? LIKE simple_expr (ESCAPE simple_expr)?
  | bit_expr NOT? REGEXP bit_expr
  | bit_expr

bit_expr:
    bit_expr '|' bit_expr
  | bit_expr '&' bit_expr
  | bit_expr '<' '<' bit_expr
  | bit_expr '>' '>' bit_expr
  | bit_expr '+' bit_expr
  | bit_expr '-' bit_expr
  | bit_expr '*' bit_expr
  | bit_expr '/' bit_expr
  | bit_expr DIV bit_expr
  | bit_expr MOD bit_expr
  | bit_expr '%' bit_expr
  | bit_expr '^' bit_expr
  | bit_expr '+' interval_expr
  | bit_expr '-' interval_expr
  | simple_expr

simple_expr:
    literal
  | identifier
  | function_call
  | simple_expr COLLATE collation_name
  | param_marker
  | variable
  | simple_expr '|' '|' simple_expr
  | '+' simple_expr
  | '-' simple_expr
  | '~' simple_expr
  | '!' simple_expr
  | BINARY simple_expr
  | '(' expr (',' expr)* ')'
  | ROW '(' expr, expr (',' expr)* ')'
  | '(' subquery ')'
  | EXISTS '(' subquery ')'
  | '{' identifier expr '}'
  | match_expr
  | case_expr
  | interval_expr