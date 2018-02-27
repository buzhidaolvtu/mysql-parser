package distribute.framework.parser.table;

import distribute.framework.parser.index.IndexType;

public class IndexOrKeyDefinition {

    public enum IndexDirection {
        ASC, DESC;
    }

    public static class IndexColName {
        private String colName;
        private IndexType length;
        private IndexDirection indexDirection;

    }

    private boolean isUniqueKey;
    private boolean isPrimaryKey;
    private boolean isKey;
    private boolean isForeignKey;
    private String indexName;
    private IndexType indexType;
    private IndexColName[] IndexColName;
    private Integer keyBlockSize;
    private String parserName;
    private String comment;


}
