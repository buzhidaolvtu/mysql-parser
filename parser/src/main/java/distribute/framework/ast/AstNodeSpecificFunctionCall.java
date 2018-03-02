package distribute.framework.ast;

public class AstNodeSpecificFunctionCall extends AstNodeFunctionCall {
    public AstNodeSpecificFunctionCall(AstNode parent) {
        super(parent);
    }

    public AstNodeSpecificFunctionCall(AstNode parent, String name) {
        super(parent, name);
    }
}
