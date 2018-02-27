package distribute.framework.ast;

public class AstNodeConstantExpr extends AstNode{
    public AstNodeConstantExpr(AstNode parent) {
        super(parent);
        this.name = "Constant";
    }
}
