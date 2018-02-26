package distribute.framework.parser.datatype;

public interface DataType {

    SqlString String = new SqlString();
    SqlNumber Number = new SqlNumber();
    SqlBool Bool = new SqlBool();

    boolean isString();

    SqlString string();

    boolean isNumber();

    SqlNumber number();

    boolean isBool();

    SqlBool bool();
}
