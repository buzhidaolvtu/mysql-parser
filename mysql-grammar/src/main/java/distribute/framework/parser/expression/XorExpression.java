package distribute.framework.parser.expression;

import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.parser.Value;
import distribute.framework.parser.datatype.DataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XorExpression {

    private final static Logger logger = LoggerFactory.getLogger(XorExpression.class);

    public Value evaluate(MySqlParser.ExpressionContext e1, MySqlParser.ExpressionContext e2){
        logger.info("XorExpression evaluate");

        if(e1 == null || e2 == null){
            return new Value(DataType.Bool,DataType.FALSE);
        }
        return new Value(DataType.Bool,DataType.TRUE);
    }
}
