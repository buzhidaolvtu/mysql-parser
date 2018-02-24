package distribute.framework.parser.expression;

import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.parser.Value;
import distribute.framework.parser.datatype.DataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotExpression {

    private final static Logger logger = LoggerFactory.getLogger(NotExpression.class);

    public Value evaluate(MySqlParser.ExpressionContext e1){
        logger.info("NotExpression evaluate,e1:{}",e1.getText());

        if(e1 == null){
            return new Value(DataType.Bool,DataType.FALSE);
        }
        return new Value(DataType.Bool,DataType.TRUE);
    }
}