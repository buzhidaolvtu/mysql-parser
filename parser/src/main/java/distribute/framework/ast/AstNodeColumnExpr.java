package distribute.framework.ast;

import distribute.framework.parser.expression.ColumnExpression;

public class AstNodeColumnExpr extends AstNode {
    public AstNodeColumnExpr(AstNode parent) {
        super(parent,"Column");
    }

    public AstNodeColumnExpr(AstNode parent, ColumnExpression columnExpression) {
        super(parent,columnExpression.toString());
    }


}
