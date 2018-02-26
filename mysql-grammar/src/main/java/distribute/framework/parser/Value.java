package distribute.framework.parser;

import distribute.framework.parser.datatype.DataType;

public class Value {

    public final static Value TRUE = new Value(DataType.Bool);
    public final static Value FALSE = new Value(DataType.Bool);

    private DataType type;
    private Object value;

    public Value(DataType type){
        this(type,null);
    }

    public Value(DataType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Value isTrue(){
        return this;//TODO
    }

    public Value isFalse(){
        return this;//TODO
    }

    public Value isUnknown(){
        return this;//TODO
    }

    public Value isNull(){
        return this;//todo
    }

    public Value isNotNull(){
        return this;//todo
    }


    @Override
    public String toString() {
        return "Value{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}
