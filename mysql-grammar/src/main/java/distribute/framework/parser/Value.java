package distribute.framework.parser;

import distribute.framework.parser.datatype.DataType;

public class Value {
    private DataType type;
    private Object value;

    public Value(DataType type, Object value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Value{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}
