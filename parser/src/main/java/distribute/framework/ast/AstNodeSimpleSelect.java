package distribute.framework.ast;

public class AstNodeSimpleSelect extends AstNode{

    public AstNode source;
    public AstNode filter;
    public AstNode map;

    public AstNodeSimpleSelect(String name) {
        super(name);
    }

    public AstNodeSimpleSelect(AstNode parent, String name) {
        super(parent, name);
    }
}
