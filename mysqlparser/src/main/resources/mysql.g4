//    Expressions, predicates

// Simplified approach for expression
expression
    : (NOT | '!') expression                             #notExpression
   | expression logical_operator expression                 #logicalExpression
   | predicate IS NOT? (TRUE | FALSE | UNKNOWN)             #isExpression
   | predicate                                        #predicateExpression
   ;

predicate
    : predicate NOT? IN '(' (subquery | expression_list) ')'      #inPredicate
   | predicate IS null_notnull                              #isNullPredicate
   | predicate comparison_operator predicate                #binaryComparasionPredicate
   | predicate
     comparison_operator (ALL | ANY | SOME) '(' subquery ')'      #subqueryComparasionPredicate
   | predicate NOT? BETWEEN predicate AND predicate            #betweenPredicate
   | predicate SOUNDS LIKE predicate                        #soundsLikePredicate
   | predicate NOT? LIKE predicate (ESCAPE string_literal)?    #likePredicate
   | predicate NOT? (REGEXP|RLIKE) predicate                #regexpPredicate
   | (LOCAL_ID VAR_ASSIGN)? expression_atom                 #expressionAtomPredicate
   ;


// Add in ASTVisitor null_notnull in constant
expression_atom
    : DEFAULT                                         #defaultExpressionAtom
   | constant                                         #constantExpressionAtom
   | full_column_name                                    #fullColumnNameExpressionAtom
   | function_call                                    #functionCallExpressionAtom
   | mysql_variable                                   #mysqlVariableExpressionAtom
   | unary_operator expression_atom                      #unaryExpressionAtom
   | BINARY expression_atom                              #binaryExpressionAtom
   | '(' expression ')'                                  #nestedExpressionAtom
   | EXISTS? '(' subquery ')'                            #existsExpessionAtom
   | INTERVAL expression interval_type                      #intervalExpressionAtom
   | expression_atom bit_operator expression_atom              #bitExpressionAtom
   | expression_atom math_operator expression_atom             #mathExpressionAtom
   ;

unary_operator
    : '!' | '~' | '+' | '-' | NOT
   ;

comparison_operator
    : '=' | '>' | '<' | '<' '=' | '>' '='
   | '<' '>' | '!' '=' | '<' '=' '>'
   ;

logical_operator
    : AND | '&' '&' | OR | '|' '|'
   ;

bit_operator
    : '<' '<' | '>' '>' | '&' | XOR | '^' | '|'
   ;

math_operator
    : '*' | '/' | '%' | DIV | MOD | '+' | '-'
   ;