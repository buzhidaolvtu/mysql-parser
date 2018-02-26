package distribute.framework.parser.operator;

import java.util.Arrays;

public enum OpType {
    //logical
    AND, OR, NOT, XOR,
    //compare
    // '=' | '>' | '<' | '<' '=' | '>' '=' | '<' '>' | '!' '=' | '<' '=' '>'
    EQ("="), GT(">"), LT("<"), LTE("<="), GTE(">="), NEQ("<>", "!="), NULLEQ("<=>"),
    //bitwise
    SHIFTLEFT, SHIFTRIGHT, BIT_AND, BIT_XOR, BIT_OR, BIT_INVERT,
    //math
    MULTIPLY, DIVIDE, MOD, PLUS, MINUS,
    //'+' '-'
    POSITIVE, NEGTIVE;

    private String[] op;

    OpType() {

    }

    OpType(String... op) {
        this.op = op;
    }

    public static OpType get(String text) {
        return Arrays.asList(values()).stream().filter(item -> item.op != null && Arrays.asList(item.op).contains(text)).findFirst().get();
    }
}