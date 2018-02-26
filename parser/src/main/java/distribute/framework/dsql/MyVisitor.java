package distribute.framework.dsql;


import com.antlr.grammarsv4.mysql.MySqlParser;
import com.antlr.grammarsv4.mysql.MySqlParserBaseVisitor;
import distribute.framework.parser.Value;
import distribute.framework.parser.datatype.DataType;
import distribute.framework.parser.expression.ColumnExpression;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyVisitor extends MySqlParserBaseVisitor<ParserRuleContext> {

    private final static Logger logger = LoggerFactory.getLogger(MyVisitor.class);

    @Override
    public ParserRuleContext visitOrExpression(MySqlParser.OrExpressionContext ctx) {
        MySqlParser.ExpressionContext e1 = ctx.e1;
        MySqlParser.ExpressionContext e2 = ctx.e2;
//        MySqlParser.ExpressionContext left = (MySqlParser.ExpressionContext) e1.accept(this);
//        if (left != null && left.value != null) {
//            ctx.value = Value.TRUE;//true
//        } else {
//            MySqlParser.ExpressionContext right = (MySqlParser.ExpressionContext) e2.accept(this);
//            if (right != null && right.value != null) {
//                ctx.value = Value.TRUE;//true
//            } else {
//                ctx.value = Value.FALSE;
//            }
//        }
        visitChildren(ctx);
        return ctx;
    }

    @Override
    public ParserRuleContext visitConstantSimpleExpr(MySqlParser.ConstantSimpleExprContext ctx) {
        MySqlParser.ConstantContext e1 = ctx.e1;
        e1.accept(this);
        return ctx;
    }

    @Override
    public ParserRuleContext visitColumnSimpleExpr(MySqlParser.ColumnSimpleExprContext ctx) {
        ctx.e1.accept(this);
        return ctx;
    }

    @Override
    public ParserRuleContext visitConstant(MySqlParser.ConstantContext ctx) {
        String text = ctx.getText();

        switch(ctx.pn){
            case 1://string_literal
                text = text.replaceAll("'","");
                break;
            case 2://decimal_literal
                break;
            case 3://hexadecimal_literal
                text = text.replaceAll("0x", "");
                text = String.valueOf(Integer.parseInt(text,16));
                break;
            case 4://boolean_literal
            case 5://REAL_LITERAL
            case 6://BIT_STRING
            case 7://NOTNULL
            case 8://(NULL_LITERAL | NULL_SPEC_LITERAL)
            default:

        }
        logger.info("constant:{}",text);
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
