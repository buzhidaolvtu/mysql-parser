package distribute.framework.ast;

public class AstNodeFunctionCall extends AstNode{
    public AstNodeFunctionCall(AstNode parent) {
        super(parent,"Function");
    }

    public AstNodeFunctionCall(AstNode parent, String name) {
        super(parent, "function:"+name);
    }
}
