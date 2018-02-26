package distribute.framework.parser.expression;

import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.parser.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XorExpression {

    private final static Logger logger = LoggerFactory.getLogger(XorExpression.class);

    public Value evaluate(MySqlParser.ExpressionContext e1, MySqlParser.ExpressionContext e2){
        logger.info("XorExpression evaluate,e1:{},e2:{}",e1.getText(),e2.getText());

        return null;
    }
}
