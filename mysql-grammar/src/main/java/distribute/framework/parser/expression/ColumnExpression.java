package distribute.framework.parser.expression;

import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.parser.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColumnExpression {

    private final static Logger logger = LoggerFactory.getLogger(ColumnExpression.class);

    public Value evaluate(MySqlParser.Full_column_nameContext context) {

        return null;
    }
}
