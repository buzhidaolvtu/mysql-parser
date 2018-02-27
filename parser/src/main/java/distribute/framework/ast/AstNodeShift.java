package distribute.framework.ast;

public class AstNodeShift extends AstNode {
    public AstNodeShift(AstNode parent) {
        super(parent);
        this.name = "Shift";
    }
}
