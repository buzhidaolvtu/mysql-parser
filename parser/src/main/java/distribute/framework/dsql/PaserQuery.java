package distribute.framework.dsql;

import com.antlr.grammarsv4.mysql.MySqlLexer;
import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.ast.AstNode;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class PaserQuery extends ParserDML {

    public PaserQuery() {
    }

    public static void main(String[] args) {
        try {
            String file = "parser/examples/query.sql";
            AntlrCaseInsensitiveFileStream antlrCaseInsensitiveFileStream = new AntlrCaseInsensitiveFileStream(file, "UTF-8", CaseInsensitiveType.UPPER);
            CharStream charStream = CharStreams.fromFileName(file);
            charStream = antlrCaseInsensitiveFileStream;
            MySqlLexer lexer = new MySqlLexer(charStream);
            TokenStream tokenStream = new CommonTokenStream(lexer);
            MySqlParser parser = new MySqlParser(tokenStream);
            ParseTree tree = parser.select_statement();

            AstNode root = new AstNode("root");
            TransformVisitor transformVisitor = new TransformVisitor(root);
            transformVisitor.visit(tree);
            System.out.println(root.toStringTree());
            TreeViewer viewer = new TreeViewer(null, root);
            viewer.open();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
