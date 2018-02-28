package distribute.framework.dsql;

import com.antlr.grammarsv4.mysql.MySqlLexer;
import com.antlr.grammarsv4.mysql.MySqlParser;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

public class AnalysisFromTable {

    public static void main(String[] args) {
        try {
            String file = "parser/examples/table_sources.sql";
            AntlrCaseInsensitiveFileStream antlrCaseInsensitiveFileStream = new AntlrCaseInsensitiveFileStream(file, "UTF-8", CaseInsensitiveType.UPPER);
            CharStream charStream = CharStreams.fromFileName(file);
            charStream = antlrCaseInsensitiveFileStream;
            MySqlLexer lexer = new MySqlLexer(charStream);
            TokenStream tokenStream = new CommonTokenStream(lexer);
            MySqlParser parser = new MySqlParser(tokenStream);
            MySqlParser.Table_sourcesContext table_sourcesContext = parser.table_sources();

            Trees.inspect(table_sourcesContext, parser);

//            AstNode root = new AstNode(null);
//            TransformVisitor transformVisitor = new TransformVisitor(root);
//            transformVisitor.visit(table_sourcesContext);
//            TreeViewer viewer = new TreeViewer(null, root);
//            viewer.open();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
