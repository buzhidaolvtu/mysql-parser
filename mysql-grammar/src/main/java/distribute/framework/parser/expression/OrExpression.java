package distribute.framework.parser.expression;

import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.parser.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrExpression {

    private final static Logger logger = LoggerFactory.getLogger(OrExpression.class);

    public Value evaluate(MySqlParser.ExpressionContext e1, MySqlParser.ExpressionContext e2){
        logger.info("OrExpression evaluate,e1:{},e2:{}",e1.getText(),e2.getText());

        return null;
    }
}
