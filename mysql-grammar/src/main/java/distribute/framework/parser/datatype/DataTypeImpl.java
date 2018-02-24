package distribute.framework.parser.datatype;

public class DataTypeImpl implements DataType{

    @Override
    public boolean isString() {
        return false;
    }

    @Override
    public SqlString string() {
        return null;
    }

    @Override
    public boolean isNumber() {
        return false;
    }

    @Override
    public SqlNumber number() {
        return null;
    }
}
