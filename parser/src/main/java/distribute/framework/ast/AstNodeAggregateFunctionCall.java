package distribute.framework.ast;

public class AstNodeAggregateFunctionCall extends AstNodeFunctionCall{
    public AstNodeAggregateFunctionCall(AstNode parent) {
        super(parent);
    }

    public AstNodeAggregateFunctionCall(AstNode parent, String name) {
        super(parent, name);
    }
}
