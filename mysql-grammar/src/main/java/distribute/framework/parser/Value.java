package distribute.framework.parser;

import distribute.framework.parser.datatype.Type;

/**
 * Variable or the result of expression
 */
public class Value {

    public final static Value NOT_NULL = new Value(null);
    public final static Value NULL = new Value(null);

    private Type type;
    private Object value;


    public Value(Type type){
        this(type,null);
    }

    public Value(Type type, Object value) {
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
