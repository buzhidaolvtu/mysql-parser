package distribute.framework.parser.table;

public class TableDefinition {

    private String tableName;
    private String[] columnNames;
    private ColumnDefinition[] columnDefinitions;
    private IndexOrKeyDefinition[] indexOrKeyDefinitions;
    private TableOption[] tableOptions;//todo
    private PartitionOptions partitionOptions;//todo

}
