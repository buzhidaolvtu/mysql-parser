package distribute.framework.dsql;

import com.antlr.grammarsv4.mysql.MySqlLexer;
import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.ast.AstNode;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

public class ParserFunctionCall {
    public static void main(String[] args) {
        try {
            String file = "parser/examples/function_call.sql";
            AntlrCaseInsensitiveFileStream antlrCaseInsensitiveFileStream = new AntlrCaseInsensitiveFileStream(file, "UTF-8", CaseInsensitiveType.UPPER);
            CharStream charStream = CharStreams.fromFileName(file);
            charStream = antlrCaseInsensitiveFileStream;
            MySqlLexer lexer = new MySqlLexer(charStream);
            TokenStream tokenStream = new CommonTokenStream(lexer);
            MySqlParser parser = new MySqlParser(tokenStream);
            MySqlParser.Function_callContext function_callContext = parser.function_call();

//            Trees.inspect(table_sourcesContext, parser);

            AstNode root = new AstNode("root");
            TransformVisitor transformVisitor = new TransformVisitor(root);
            transformVisitor.visit(function_callContext);
            System.out.println(root.toStringTree());
            TreeViewer viewer = new TreeViewer(null, root);
            viewer.open();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
