package distribute.framework.parser.expression;

import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.parser.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConstantExpression {

    private final static Logger logger = LoggerFactory.getLogger(ConstantExpression.class);

    public Value evaluate(MySqlParser.ConstantContext context){

        String text = context.getText();
        logger.info("constant:{}",text);

        return null;
    }
}
