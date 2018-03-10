package distribute.framework.dsql;

import com.antlr.grammarsv4.mysql.MySqlLexer;
import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.ast.AstNode;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

public class ParserDFA {
    public static void main(String[] args) {
        try {
            String file = "parser/examples/expression3.sql";
            AntlrCaseInsensitiveFileStream antlrCaseInsensitiveFileStream = new AntlrCaseInsensitiveFileStream(file, "UTF-8", CaseInsensitiveType.UPPER);
            CharStream charStream = antlrCaseInsensitiveFileStream;
            MySqlLexer lexer = new MySqlLexer(charStream);
            TokenStream tokenStream = new CommonTokenStream(lexer);
            MySqlParser parser = new MySqlParser(tokenStream);
            parser.addErrorListener(new MysqlSyntaxErrorListener());
            MySqlParser.ExpressionContext expression = parser.expression();
            AstNode root = new AstNode("root");
            TransformVisitor transformVisitor = new TransformVisitor(root);
            transformVisitor.visit(expression);//不要调用accept方法，因为visit方法被重写了
            TreeViewer viewer = new TreeViewer(null, root);
            viewer.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
