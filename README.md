# mysql-parser

## 问题
* 运算优先级怎么解决
* 运算的结合顺序是怎样指定的

## 应用(实现中...)
* 客户端解析SQL并分解成小SQL，可以在分库分表时使用
* 可视化抽象语法树，利于分析
* mybatis集成，数据自动智能生成，单体测试更方便，支持语法mybatis语法：#{var},${var}
* 语法检查，不再需要连接mysql

## demo
### ast tree
![语法推导树](https://github.com/buzhidaolvtu/mysql-parser/blob/master/demo/syntax%20derivation%20parse%20tree.png)
![抽象语法树](https://github.com/buzhidaolvtu/mysql-parser/blob/master/demo/simpfied%20ast%20tree.png)
  
## reference  
[运算符优先级与结合顺序](https://theantlrguy.atlassian.net/wiki/spaces/ANTLR3/pages/2687077/Operator+precedence+parser)