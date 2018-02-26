package distribute.framework.parser.expression;

import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.parser.Value;
import distribute.framework.parser.datatype.DataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleExpression {


    private final static Logger logger = LoggerFactory.getLogger(SimpleExpression.class);

    public Value evaluate(MySqlParser.Boolean_primaryContext e1) {
        return null;
    }
}
