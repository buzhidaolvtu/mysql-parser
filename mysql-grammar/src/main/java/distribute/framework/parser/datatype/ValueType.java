package distribute.framework.parser.datatype;

public enum ValueType {

    STRING,
    NUMBER,
    BOOLEAN,
    BIT_STRING,

    NOTNULL,
    NULL,

    //special
    LIST,
    COLUMN,
    FUNCTION
}