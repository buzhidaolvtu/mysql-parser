package distribute.framework.parser;

import distribute.framework.parser.datatype.ValueType;

public class Value {

    private ValueType type;
    private Object value;

    public Value(ValueType type){
        this(type,null);
    }

    public Value(ValueType type, Object value) {
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
