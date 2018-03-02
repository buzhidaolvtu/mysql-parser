package distribute.framework.parser.expression;

import distribute.framework.parser.index.KeyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColumnExpression {

    private final static Logger logger = LoggerFactory.getLogger(ColumnExpression.class);

    public String databaseName;
    public String tableName;
    public String columnName;

    @Override
    public String toString() {
        String column = "";
        if (databaseName != null) {
            column += databaseName + ".";
        }
        if (tableName != null) {
            column += tableName + ".";
        }
        if (columnName != null) {
            column += columnName;
        }
        return column;
    }
}
