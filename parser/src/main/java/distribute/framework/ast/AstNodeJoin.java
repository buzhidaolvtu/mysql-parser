package distribute.framework.ast;

public class AstNodeJoin extends AstNode {

    public AstNodeJoin(AstNode parent, String direction) {
        super(parent);
        this.name = direction + " join";
    }
}
