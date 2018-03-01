package distribute.framework.ast;

public class AstNodeJoin extends AstNode {

    private AstNode leftPlaceholder;
    private AstNode rightPlaceholder;
    private AstNode onPlaceholder;

    public AstNodeJoin(AstNode parent, String direction) {
        super(parent,direction + " join");
        leftPlaceholder = new AstNode("left placeholder");
        addChild(leftPlaceholder);
        rightPlaceholder = new AstNode("right placeholder");
        addChild(rightPlaceholder);
        onPlaceholder = new AstNode("on placeholder");
    }

    public AstNode getLeftPlaceholder() {
        return leftPlaceholder;
    }

    public AstNode getRightPlaceholder() {
        return rightPlaceholder;
    }

    public void placeOn() {
        addChild(onPlaceholder);
    }

    public AstNode getOnPlaceholder() {
        return onPlaceholder;
    }

    public void setLeft(AstNode left) {
        leftPlaceholder.addChild(left);
    }
}
