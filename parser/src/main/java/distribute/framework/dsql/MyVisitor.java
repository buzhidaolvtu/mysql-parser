package distribute.framework.dsql;


import com.antlr.grammarsv4.mysql.MySqlParser;
import com.antlr.grammarsv4.mysql.MySqlParserBaseVisitor;
import distribute.framework.parser.Value;
import distribute.framework.parser.datatype.ValueType;
import distribute.framework.parser.operator.OpType;
import distribute.framework.parser.operator.Operator;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class MyVisitor extends MySqlParserBaseVisitor<ParserRuleContext> {

    private final static Logger logger = LoggerFactory.getLogger(MyVisitor.class);

    @Override
    public ParserRuleContext visitOrExpression(MySqlParser.OrExpressionContext ctx) {
        MySqlParser.ExpressionContext e1 = ctx.e1;
        e1.accept(this);
        MySqlParser.ExpressionContext e2 = ctx.e2;
        ctx.value = Operator.evaluate(OpType.OR, e1.value, e2.value);
        return ctx;
    }

    //todo
    @Override
    public ParserRuleContext visitXorExpression(MySqlParser.XorExpressionContext ctx) {
        MySqlParser.ExpressionContext e1 = ctx.e1;
        e1.accept(this);
        MySqlParser.ExpressionContext e2 = ctx.e2;
        e2.accept(this);
        ctx.value = Operator.evaluate(OpType.XOR, e1.value, e2.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitAndExpression(MySqlParser.AndExpressionContext ctx) {
        MySqlParser.ExpressionContext e1 = ctx.e1;
        MySqlParser.ExpressionContext e2 = ctx.e2;
        e1.accept(this);
        e2.accept(this);
        ctx.value = Operator.evaluate(OpType.AND, e1.value, e2.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitNotExpression(MySqlParser.NotExpressionContext ctx) {
        MySqlParser.ExpressionContext e1 = ctx.e1;
        e1.accept(this);
        ctx.value = Operator.evaluate(OpType.NOT, e1.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitIsExpression(MySqlParser.IsExpressionContext ctx) {
        MySqlParser.Boolean_primaryContext e1 = ctx.e1;
        e1.accept(this);
        if (ctx.TRUE() != null) {
            ctx.value = e1.value.isTrue();
        } else if (ctx.FALSE() != null) {
            ctx.value = e1.value.isFalse();
        } else if (ctx.UNKNOWN() != null) {
            ctx.value = e1.value.isUnknown();
        }
        if (ctx.NOT() != null) {
            ctx.value = Operator.evaluate(OpType.NOT, ctx.value);
        }
        return ctx;
    }

    @Override
    public ParserRuleContext visitSimpleExpression(MySqlParser.SimpleExpressionContext ctx) {
        MySqlParser.Boolean_primaryContext e1 = ctx.e1;
        e1.accept(this);
        ctx.value = e1.value;
        return ctx;
    }

    @Override
    public ParserRuleContext visitIsNullBooleanPrimary(MySqlParser.IsNullBooleanPrimaryContext ctx) {
        MySqlParser.Boolean_primaryContext e1 = ctx.e1;
        e1.accept(this);
        if (ctx.null_notnull().NOTNULL() != null) {
            ctx.value = e1.value.isNotNull();
        } else {
            ctx.value = e1.value.isNull();
        }
        return ctx;
    }

    @Override
    public ParserRuleContext visitConsistentEqualBooleanPrimary(MySqlParser.ConsistentEqualBooleanPrimaryContext ctx) {
        MySqlParser.Boolean_primaryContext e1 = ctx.e1;
        MySqlParser.PredicateContext e2 = ctx.e2;
        e1.accept(this);
        e2.accept(this);
        ctx.value = Operator.evaluate(OpType.NULLEQ, e1.value, e2.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitComparisonBooleanPrimary(MySqlParser.ComparisonBooleanPrimaryContext ctx) {
        MySqlParser.Boolean_primaryContext e1 = ctx.e1;
        MySqlParser.PredicateContext e2 = ctx.e2;
        e1.accept(this);
        e2.accept(this);
        OpType opType = OpType.get(ctx.comparison_operator().getText());
        ctx.value = Operator.evaluate(opType, e1.value, e2.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitComparisonBooleanPrimary2(MySqlParser.ComparisonBooleanPrimary2Context ctx) {
        //todo
        return super.visitComparisonBooleanPrimary2(ctx);
    }

    @Override
    public ParserRuleContext visitPredicateBooleanPrimary(MySqlParser.PredicateBooleanPrimaryContext ctx) {
        MySqlParser.PredicateContext e1 = ctx.e1;
        e1.accept(this);
        ctx.value = e1.value;
        return ctx;
    }

    @Override
    public ParserRuleContext visitInSubqueryPredicate(MySqlParser.InSubqueryPredicateContext ctx) {
        //todo
        return super.visitInSubqueryPredicate(ctx);
    }

    @Override
    public ParserRuleContext visitInPredicate(MySqlParser.InPredicateContext ctx) {
        //todo
        return super.visitInPredicate(ctx);
    }

    @Override
    public ParserRuleContext visitBetweenPredicate(MySqlParser.BetweenPredicateContext ctx) {
        //todo
        return super.visitBetweenPredicate(ctx);
    }

    @Override
    public ParserRuleContext visitSoundsLikePredicate(MySqlParser.SoundsLikePredicateContext ctx) {
        //todo
        return super.visitSoundsLikePredicate(ctx);
    }

    @Override
    public ParserRuleContext visitLikePredicate(MySqlParser.LikePredicateContext ctx) {
        //todo
        return super.visitLikePredicate(ctx);
    }

    @Override
    public ParserRuleContext visitRegexpPredicate(MySqlParser.RegexpPredicateContext ctx) {
        //todo
        return super.visitRegexpPredicate(ctx);
    }

    @Override
    public ParserRuleContext visitSimplePredicate(MySqlParser.SimplePredicateContext ctx) {
        MySqlParser.Bit_exprContext e1 = ctx.e1;
        e1.accept(this);
        ctx.value = e1.value;
        return ctx;
    }

    @Override
    public ParserRuleContext visitOrBitExpr(MySqlParser.OrBitExprContext ctx) {
        MySqlParser.Bit_exprContext e1 = ctx.e1;
        MySqlParser.Bit_exprContext e2 = ctx.e2;
        e1.accept(this);
        e2.accept(this);
        ctx.value = Operator.evaluate(OpType.BIT_OR, e1.value, e2.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitAndBitExpr(MySqlParser.AndBitExprContext ctx) {
        MySqlParser.Bit_exprContext e1 = ctx.e1;
        MySqlParser.Bit_exprContext e2 = ctx.e2;
        e1.accept(this);
        e2.accept(this);
        ctx.value = Operator.evaluate(OpType.BIT_AND, e1.value, e2.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitShiftLeftBitExpr(MySqlParser.ShiftLeftBitExprContext ctx) {
        MySqlParser.Bit_exprContext e1 = ctx.e1;
        MySqlParser.Bit_exprContext e2 = ctx.e2;
        e1.accept(this);
        e2.accept(this);
        ctx.value = Operator.evaluate(OpType.SHIFTLEFT, e1.value, e2.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitShiftRightBitExpr(MySqlParser.ShiftRightBitExprContext ctx) {
        MySqlParser.Bit_exprContext e1 = ctx.e1;
        MySqlParser.Bit_exprContext e2 = ctx.e2;
        e1.accept(this);
        e2.accept(this);
        ctx.value = Operator.evaluate(OpType.SHIFTRIGHT, e1.value, e2.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitPlusBitExpr(MySqlParser.PlusBitExprContext ctx) {
        MySqlParser.Bit_exprContext e1 = ctx.e1;
        MySqlParser.Bit_exprContext e2 = ctx.e2;
        e1.accept(this);
        e2.accept(this);
        ctx.value = Operator.evaluate(OpType.PLUS, e1.value, e2.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitMinusBitExpr(MySqlParser.MinusBitExprContext ctx) {
        MySqlParser.Bit_exprContext e1 = ctx.e1;
        MySqlParser.Bit_exprContext e2 = ctx.e2;
        e1.accept(this);
        e2.accept(this);
        ctx.value = Operator.evaluate(OpType.MINUS, e1.value, e2.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitMultiplyBitExpr(MySqlParser.MultiplyBitExprContext ctx) {
        MySqlParser.Bit_exprContext e1 = ctx.e1;
        MySqlParser.Bit_exprContext e2 = ctx.e2;
        e1.accept(this);
        e2.accept(this);
        ctx.value = Operator.evaluate(OpType.MULTIPLY, e1.value, e2.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitDivideBitExpr(MySqlParser.DivideBitExprContext ctx) {
        MySqlParser.Bit_exprContext e1 = ctx.e1;
        MySqlParser.Bit_exprContext e2 = ctx.e2;
        e1.accept(this);
        e2.accept(this);
        ctx.value = Operator.evaluate(OpType.DIVIDE, e1.value, e2.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitModBitExpr(MySqlParser.ModBitExprContext ctx) {
        MySqlParser.Bit_exprContext e1 = ctx.e1;
        MySqlParser.Bit_exprContext e2 = ctx.e2;
        e1.accept(this);
        e2.accept(this);
        ctx.value = Operator.evaluate(OpType.MOD, e1.value, e2.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitXorBitExpr(MySqlParser.XorBitExprContext ctx) {
        MySqlParser.Bit_exprContext e1 = ctx.e1;
        MySqlParser.Bit_exprContext e2 = ctx.e2;
        e1.accept(this);
        e2.accept(this);
        ctx.value = Operator.evaluate(OpType.BIT_XOR, e1.value, e2.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitSimpleBitExpr(MySqlParser.SimpleBitExprContext ctx) {
        MySqlParser.Simple_exprContext e1 = ctx.e1;
        e1.accept(this);
        ctx.value = e1.value;
        return ctx;
    }

    @Override
    public ParserRuleContext visitConstantSimpleExpr(MySqlParser.ConstantSimpleExprContext ctx) {
        MySqlParser.ConstantContext e1 = ctx.e1;
        e1.accept(this);
        ctx.value = e1.value;
        return ctx;
    }

    @Override
    public ParserRuleContext visitColumnSimpleExpr(MySqlParser.ColumnSimpleExprContext ctx) {
        MySqlParser.Full_column_nameContext e1 = ctx.e1;
        e1.accept(this);
        ctx.value = null;//todo
        return ctx;
    }

    @Override
    public ParserRuleContext visitFunctionSimpleExpr(MySqlParser.FunctionSimpleExprContext ctx) {
        //todo
        MySqlParser.Function_callContext e1 = ctx.e1;
        //parse function
        return ctx;
    }

    @Override
    public ParserRuleContext visitCollateSimpleExpr(MySqlParser.CollateSimpleExprContext ctx) {
        //todo
        return ctx;
    }

    @Override
    public ParserRuleContext visitVariableSimpleExpr(MySqlParser.VariableSimpleExprContext ctx) {
        //todo
        return ctx;
    }

    @Override
    public ParserRuleContext visitConcatSimpleExpr(MySqlParser.ConcatSimpleExprContext ctx) {
        //todo
        return ctx;
    }

    @Override
    public ParserRuleContext visitPositiveSimpleExpr(MySqlParser.PositiveSimpleExprContext ctx) {
        MySqlParser.Simple_exprContext e1 = ctx.e1;
        e1.accept(this);
        ctx.value = e1.value;//todo what if e1.value is boolean?
        return ctx;
    }

    @Override
    public ParserRuleContext visitNegtiveSimpleExpr(MySqlParser.NegtiveSimpleExprContext ctx) {
        MySqlParser.Simple_exprContext e1 = ctx.e1;
        e1.accept(this);
        ctx.value = e1.value;//todo what if e1.value is boolean?
        return ctx;
    }

    @Override
    public ParserRuleContext visitBitInvertSimpleExpr(MySqlParser.BitInvertSimpleExprContext ctx) {
        MySqlParser.Simple_exprContext e1 = ctx.e1;
        e1.accept(this);
        ctx.value = Operator.evaluate(OpType.BIT_INVERT, e1.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitHignNotSimpleExpr(MySqlParser.HignNotSimpleExprContext ctx) {
        MySqlParser.Simple_exprContext e1 = ctx.e1;
        e1.accept(this);
        ctx.value = Operator.evaluate(OpType.NOT, e1.value);
        return ctx;
    }

    @Override
    public ParserRuleContext visitBinarySimpleExpr(MySqlParser.BinarySimpleExprContext ctx) {
        //todo
        return ctx;
    }

    @Override
    public ParserRuleContext visitExprListSimpleExpr(MySqlParser.ExprListSimpleExprContext ctx) {
        MySqlParser.Expression_listContext e = ctx.e;
        e.accept(this);
        List<Value> values = e.expression().stream().map(item -> item.value).collect(Collectors.toList());
        return ctx;
    }

    @Override
    public ParserRuleContext visitRowSimpleExpr(MySqlParser.RowSimpleExprContext ctx) {
        //todo
        return ctx;
    }

    @Override
    public ParserRuleContext visitSubquerySimpleExpr(MySqlParser.SubquerySimpleExprContext ctx) {
        //todo
        return ctx;
    }

    @Override
    public ParserRuleContext visitExistsSimpleExpr(MySqlParser.ExistsSimpleExprContext ctx) {
        //todo
        return ctx;
    }

    @Override
    public ParserRuleContext visitIntervalSimpleExpr(MySqlParser.IntervalSimpleExprContext ctx) {
        //todo
        return ctx;
    }

    @Override
    public ParserRuleContext visitConstant(MySqlParser.ConstantContext ctx) {
        String text = ctx.getText();
        logger.info("constant:{}", text);

        switch (ctx.pn) {
            case 1://string_literal
                text = text.replaceAll("'", "");
                ctx.value = new Value(ValueType.STRING, text);
                break;
            case 2://decimal_literal
                ctx.value = new Value(ValueType.NUMBER, Integer.parseInt(text));
                break;
            case 3://hexadecimal_literal
                text = text.replaceAll("0x", "");
                text = String.valueOf(Integer.parseInt(text, 16));
                break;
            case 4://boolean_literal
                if (text.equals("TRUE")) {
                    ctx.value = new Value(ValueType.BOOLEAN, true);
                } else {
                    ctx.value = new Value(ValueType.BOOLEAN, false);
                }
                break;
            case 5://REAL_LITERAL
                ctx.value = new Value(ValueType.NUMBER, Double.parseDouble(text));
                break;
            case 6://BIT_STRING
                //todo
                break;
            case 7://NOTNULL
                ctx.value = new Value(ValueType.NOTNULL);
                break;
            case 8://(NULL_LITERAL | NULL_SPEC_LITERAL)
                ctx.value = new Value(ValueType.NULL);
                break;
        }
        return ctx;
    }

    @Override
    public ParserRuleContext visitFull_column_name(MySqlParser.Full_column_nameContext ctx) {
        String[] split = ctx.getText().split("\\.");
        if (split.length == 1) {
            logger.info("column:{}", split[0]);
        }
        if (split.length == 2) {
            logger.info("table:{},column:{}", split[0], split[1]);
        }
        if (split.length == 3) {
            logger.info("database:{},table:{},column:{}", split[0], split[1], split[2]);
        }
        return ctx;
    }
}
