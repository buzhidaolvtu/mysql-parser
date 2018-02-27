package distribute.framework.ast;

public class AstNodeLIke extends AstNode {
    public AstNodeLIke(AstNode parent) {
        super(parent);
        this.name = "Like";
    }
}
