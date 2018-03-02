package distribute.framework.ast;

public class AstNodeConstantExpr extends AstNode{
    public AstNodeConstantExpr(AstNode parent) {
        super(parent,"Constant");
    }

    public AstNodeConstantExpr(AstNode parent, String name) {
        super(parent, name);
    }
}
