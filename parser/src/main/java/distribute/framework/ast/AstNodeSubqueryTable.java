package distribute.framework.ast;

public class AstNodeSubqueryTable extends AstNode {
    public AstNodeSubqueryTable(AstNode parent) {
        super(parent);
        this.name = "subquery";
    }
}
