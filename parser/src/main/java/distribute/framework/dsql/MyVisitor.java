package distribute.framework.dsql;


import com.antlr.grammarsv4.mysql.MySqlParserBaseVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MyVisitor extends MySqlParserBaseVisitor {

    @Override
    public Object visitTerminal(TerminalNode node) {
        System.out.println(node.getText());
        return super.visitTerminal(node);
    }
}
