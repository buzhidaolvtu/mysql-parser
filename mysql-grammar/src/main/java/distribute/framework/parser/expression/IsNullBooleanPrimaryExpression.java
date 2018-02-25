package distribute.framework.parser.expression;

import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.parser.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IsNullBooleanPrimaryExpression {

    private final static Logger logger = LoggerFactory.getLogger(IsNullBooleanPrimaryExpression.class);

    public Value evaluate(MySqlParser.Boolean_primaryContext column) {
        logger.info("IsNullBooleanPrimaryExpression:{}", column.getText());
        return null;
    }
}
