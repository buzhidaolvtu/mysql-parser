package distribute.framework.ast;

public class AstNodeConstant extends AstNode{
    public AstNodeConstant(AstNode parent) {
        super(parent,"Constant");
    }

    public AstNodeConstant(AstNode parent, String name) {
        super(parent, name);
    }
}
