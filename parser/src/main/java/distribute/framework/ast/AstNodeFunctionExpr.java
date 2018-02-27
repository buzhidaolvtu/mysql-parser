package distribute.framework.ast;

public class AstNodeFunctionExpr extends AstNode{
    public AstNodeFunctionExpr(AstNode parent) {
        super(parent);
        this.name = "Function";
    }
}
