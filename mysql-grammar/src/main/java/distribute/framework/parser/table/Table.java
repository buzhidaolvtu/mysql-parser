package distribute.framework.parser.table;

import distribute.framework.parser.expression.ColumnExpression;

import java.util.List;

public class Table {

    //没有元信息
    private TableDefinition tableDefinition;

    //只有value信息，没有元信息。
    private List<Row> rows;

    public List<Row> getAllColumns(){
        return null;
    }

}
