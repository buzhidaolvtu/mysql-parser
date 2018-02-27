package distribute.framework.dsql;

import com.antlr.grammarsv4.mysql.MySqlLexer;
import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.ast.AstNode;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

public class DSQL {

    public static void main(String[] args) {
        try {
            String file = "parser/examples/expression.sql";
            AntlrCaseInsensitiveFileStream antlrCaseInsensitiveFileStream = new AntlrCaseInsensitiveFileStream(file, "UTF-8", CaseInsensitiveType.UPPER);
            CharStream charStream = CharStreams.fromFileName(file);
            charStream = antlrCaseInsensitiveFileStream;
            MySqlLexer lexer = new MySqlLexer(charStream);
            TokenStream tokenStream = new CommonTokenStream(lexer);
            MySqlParser parser = new MySqlParser(tokenStream);
            MySqlParser.ExpressionContext expression = parser.expression();
//            MyVisitor visitor = new MyVisitor();
//            MySqlParser.ExpressionContext expressionContext = (MySqlParser.ExpressionContext)visitor.visit(expression);
//            System.out.println(expressionContext.value);
            AstNode root = new AstNode(null);
            TransformVisitor transformVisitor = new TransformVisitor(root);
            transformVisitor.visit(expression);
            TreeViewer viewer = new TreeViewer(null, root);
            viewer.open();
//            Trees.inspect(expression, parser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
