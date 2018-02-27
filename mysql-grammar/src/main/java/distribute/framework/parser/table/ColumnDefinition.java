package distribute.framework.parser.table;

import distribute.framework.parser.datatype.DataType;

/**
column_definition:
        data_type [NOT NULL | NULL] [DEFAULT default_value]
        [AUTO_INCREMENT] [UNIQUE [KEY]] [[PRIMARY] KEY]
        [COMMENT 'string']
        [COLUMN_FORMAT {FIXED|DYNAMIC|DEFAULT}]
        [STORAGE {DISK|MEMORY|DEFAULT}]
        [reference_definition]
        | data_type [GENERATED ALWAYS] AS (expression)
        [VIRTUAL | STORED] [NOT NULL | NULL]
        [UNIQUE [KEY]] [[PRIMARY] KEY]
        [COMMENT 'string']
 not support [Generated Columns]
 Generated Columns:
 https://dev.mysql.com/doc/refman/5.7/en/create-table-generated-columns.html
 */
public class ColumnDefinition {

    public enum ColumnFormat{
        FIXED,DYNAMIC,DEFAULT;
    }

    public enum Storage {
        DISK,MEMORY,DEFAULT
    }

    private DataType dataType;
    private boolean isNullable;
    private boolean isNotNullable;
    private Object defaultValue;
    private boolean isAutoIncrement;
    private boolean isUniqueKey;
    private boolean isPrimaryKey;
    private boolean isKey;
    private String comment;
    private ColumnFormat columnFormat;
    private Storage storage;
    private ReferenceDefinition referenceDefinition;

}
