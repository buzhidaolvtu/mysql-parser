package distribute.framework.ast;

public class AstNodeUdfFunctionCall extends AstNodeFunctionCall {
    public AstNodeUdfFunctionCall(AstNode parent) {
        super(parent);
    }

    public AstNodeUdfFunctionCall(AstNode parent, String name) {
        super(parent, name);
    }
}
