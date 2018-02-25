package distribute.framework.parser.expression;

import com.antlr.grammarsv4.mysql.MySqlParser;
import distribute.framework.parser.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColumnExpression {

    private final static Logger logger = LoggerFactory.getLogger(ColumnExpression.class);

    public Value evaluate(MySqlParser.Full_column_nameContext context) {
        String[] split = context.getText().split("\\.");
        if(split.length == 1){
            logger.info("column:{}",split[0]);
        }
        if(split.length == 2){
            logger.info("table:{},column:{}",split[0],split[1]);
        }
        if(split.length == 3){
            logger.info("database:{},table:{},column:{}",split[0],split[1],split[2]);
        }
        return null;
    }
}
