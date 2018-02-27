package distribute.framework.ast;

public class AstNodeVariableExpr extends AstNode{
    public AstNodeVariableExpr(AstNode parent) {
        super(parent);
        this.name = "Variable";
    }
}
