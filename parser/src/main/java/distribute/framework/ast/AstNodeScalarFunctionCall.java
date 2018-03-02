package distribute.framework.ast;

public class AstNodeScalarFunctionCall extends AstNodeFunctionCall {
    public AstNodeScalarFunctionCall(AstNode parent) {
        super(parent);
    }

    public AstNodeScalarFunctionCall(AstNode parent, String name) {
        super(parent, name);
    }
}
