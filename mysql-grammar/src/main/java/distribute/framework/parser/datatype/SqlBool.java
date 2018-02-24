package distribute.framework.parser.datatype;


public class SqlBool extends DataTypeImpl{

    @Override
    public boolean isBool() {
        return true;
    }

    @Override
    public SqlBool bool() {
        return this;
    }
}
