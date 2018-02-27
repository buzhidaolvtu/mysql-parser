package distribute.framework.parser.operator;

import java.util.Arrays;

public enum OpType {
    //logical
    AND, OR, XOR,
    //compare
    // '=' | '>' | '<' | '<' '=' | '>' '=' | '<' '>' | '!' '=' | '<' '=' '>'
    EQ("="), GT(">"), LT("<"), LTE("<="), GTE(">="), NEQ("<>", "!="), NULLEQ("<=>"),
    //bitwise
    SHIFTLEFT, SHIFTRIGHT, BIT_AND, BIT_XOR, BIT_OR,
    //math
    MULTIPLY, DIVIDE, MOD, PLUS, MINUS,


    //unary operator
    //logical
    NOT,
    //bitwise
    BIT_INVERT,
    //'+' '-'
    POSITIVE, NEGTIVE;

    private String[] op;

    OpType() {

    }

    OpType(String... op) {
        this.op = op;
    }

    //todo 每次都实时获得，需要优化
    public static OpType get(String text) {
        return Arrays.asList(values()).stream().filter(item -> item.op != null && Arrays.asList(item.op).contains(text)).findFirst().get();
    }
}