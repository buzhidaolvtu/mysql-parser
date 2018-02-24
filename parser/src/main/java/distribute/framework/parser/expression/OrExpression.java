package distribute.framework.parser.expression;

import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.parser.Value;
import org.antlr.v4.runtime.ParserRuleContext;

public class OrExpression {
    public Value evaluate(MySqlParser.ExpressionContext e1, MySqlParser.ExpressionContext e2){
        return null;
    }
}
