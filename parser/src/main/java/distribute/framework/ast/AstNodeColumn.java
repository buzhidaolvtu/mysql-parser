package distribute.framework.ast;

import distribute.framework.parser.expression.ColumnExpression;

public class AstNodeColumn extends AstNode {
    public AstNodeColumn(AstNode parent) {
        super(parent,"Column");
    }

    public AstNodeColumn(AstNode parent,ColumnExpression columnExpression) {
        super(parent,columnExpression.toString());
    }


}
