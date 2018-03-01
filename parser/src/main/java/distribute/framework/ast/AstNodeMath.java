package distribute.framework.ast;

//*, /, DIV, %, MOD
//-, +
public class AstNodeMath extends AstNode{
    public AstNodeMath(AstNode parent) {
        super(parent,"Math");
    }
}
