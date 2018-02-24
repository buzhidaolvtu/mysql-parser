package distribute.framework.dsql;

import com.antlr.grammarsv4.mysql.MySqlLexer;
import com.antlr.grammarsv4.mysql.MySqlParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

public class DSQL {

    public static void main(String[] args) {
        try {
            String file = "/Users/boxfish/workspace/dsql/examples/dml_select.sql";
            String file2 = "sql-scripts/select.sql";
            AntlrCaseInsensitiveFileStream antlrCaseInsensitiveFileStream = new AntlrCaseInsensitiveFileStream(file2, "UTF-8", CaseInsensitiveType.UPPER);
            CharStream charStream = CharStreams.fromFileName(file);
            charStream = antlrCaseInsensitiveFileStream;
            MySqlLexer lexer = new MySqlLexer(charStream);
            TokenStream tokenStream = new CommonTokenStream(lexer);
            MySqlParser parser = new MySqlParser(tokenStream);
            MyListener listener = new MyListener();
            parser.addParseListener(listener);
            parser.select_statement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
