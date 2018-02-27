package distribute.framework.parser.operator;

import distribute.framework.parser.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Operator {

    private final static Logger logger = LoggerFactory.getLogger(Operator.class);

    public static Value evaluate(OpType opType, Value v1, Value v2) {
        logger.info("evaluate,op:{},v1:{},v2:{}", opType, v1, v2);
        switch (opType) {
            case AND:
            case OR:
            case XOR:

            case EQ:
            case GT:
            case LT:
            case LTE:
            case GTE:
            case NEQ:
            case NULLEQ:

            case SHIFTLEFT:
            case SHIFTRIGHT:
            case BIT_AND:
            case BIT_XOR:
            case BIT_OR:
            case BIT_INVERT:

            case MULTIPLY:
            case DIVIDE:
            case MOD:
            case PLUS:
            case MINUS:

            case POSITIVE:
            case NEGTIVE:
        }
        return null;
    }

    public static Value evaluate(OpType opType, Value v1) {
        switch (opType) {
            case NOT:
            case BIT_INVERT:
            case POSITIVE:
            case NEGTIVE:
        }
        return null;
    }

}
