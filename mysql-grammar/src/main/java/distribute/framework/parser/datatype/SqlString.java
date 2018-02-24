package distribute.framework.parser.datatype;

public class SqlString extends DataTypeImpl{

    @Override
    public boolean isString() {
        return true;
    }

    @Override
    public SqlString string() {
        return this;
    }

}
