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
  
## reference  
[运算符优先级与结合顺序](https://theantlrguy.atlassian.net/wiki/spaces/ANTLR3/pages/2687077/Operator+precedence+parser)
* 不同结合顺序一定有不同的优先级
* 相同优先级一定有相同的结合顺序（其实是上面命题的等价表示）