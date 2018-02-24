package distribute.framework.parser.expression;

import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.parser.Value;
import distribute.framework.parser.datatype.DataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleExpression {


    private final static Logger logger = LoggerFactory.getLogger(SimpleExpression.class);

    public Value evaluate(MySqlParser.Boolean_primaryContext e1){
        logger.info("SimpleExpression evaluate,e1:{}",e1.getText());

        if(e1 == null){
            return new Value(DataType.Bool,DataType.FALSE);
        }
        return new Value(DataType.Bool,DataType.TRUE);
    }
}