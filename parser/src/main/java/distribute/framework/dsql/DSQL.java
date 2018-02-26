package distribute.framework.dsql;

import com.antlr.grammarsv4.mysql.MySqlLexer;
import com.antlr.grammarsv4.mysql.MySqlParser;
import org.antlr.v4.runtime.*;

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
            MyVisitor visitor = new MyVisitor();
            MySqlParser.ExpressionContext expressionContext = (MySqlParser.ExpressionContext)visitor.visit(expression);
//            System.out.println(expressionContext.value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
