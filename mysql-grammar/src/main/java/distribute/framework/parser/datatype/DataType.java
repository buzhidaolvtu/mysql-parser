package distribute.framework.parser.datatype;

public interface DataType {

    boolean isString();
    SqlString string();

    boolean isNumber();
    SqlNumber number();
}
