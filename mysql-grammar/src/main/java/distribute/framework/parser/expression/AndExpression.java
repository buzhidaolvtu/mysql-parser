package distribute.framework.parser.expression;

import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.parser.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AndExpression {

    private final static Logger logger = LoggerFactory.getLogger(AndExpression.class);

    public Value evaluate(MySqlParser.ExpressionContext e1, MySqlParser.ExpressionContext e2){
        return null;
    }
}
