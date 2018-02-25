package distribute.framework.parser.expression;

import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.parser.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConstantExpression {

    private final static Logger logger = LoggerFactory.getLogger(ConstantExpression.class);

    public Value evaluate(MySqlParser.ConstantContext context){
        int ruleIndex = context.getRuleIndex();
        String text = context.getText();
        logger.info("constant:{}",text);
        context.getRuleContext();
        switch (ruleIndex){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:

        }
        return null;
    }
}
