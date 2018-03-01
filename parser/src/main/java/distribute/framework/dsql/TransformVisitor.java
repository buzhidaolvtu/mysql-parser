package distribute.framework.dsql;

import com.antlr.grammarsv4.mysql.MySqlParser;
import com.antlr.grammarsv4.mysql.MySqlParserBaseVisitor;
import distribute.framework.ast.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;

import java.util.List;
import java.util.Stack;


public class TransformVisitor extends MySqlParserBaseVisitor {

    private AstNode current;

    public TransformVisitor(AstNode root) {
        this.current = root;
    }

    private void resetCurrent(AstNode node) {
        current = node;
    }


    @Override
    public Object visit(ParseTree tree) {
        AstNode _cur = current;
        Object visit = super.visit(tree);
        current = _cur;
        return visit;
    }

    @Override
    public Object visitChildren(RuleNode node) {
        Object result = defaultResult();
        int n = node.getChildCount();
        for (int i = 0; i < n; i++) {
            if (!shouldVisitNextChild(node, result)) {
                break;
            }

            ParseTree c = node.getChild(i);
            AstNode _cur = current;
            Object childResult = c.accept(this);
            current = _cur;
            result = aggregateResult(result, childResult);
        }

        return result;
    }

    //expression
    @Override
    public Object visitIsExpression(MySqlParser.IsExpressionContext ctx) {
        AstNode node = new AstNodeIs(current);
        resetCurrent(node);
        Object o = super.visitIsExpression(ctx);
        return o;
    }

    @Override
    public Object visitAndExpression(MySqlParser.AndExpressionContext ctx) {
        AstNode node = new AstNodeAnd(current);
        resetCurrent(node);
        Object o = super.visitAndExpression(ctx);
        return o;
    }

    @Override
    public Object visitXorExpression(MySqlParser.XorExpressionContext ctx) {
        AstNode node = new AstNodeXor(current);
        resetCurrent(node);
        Object o = super.visitXorExpression(ctx);

        return o;
    }

    @Override
    public Object visitNotExpression(MySqlParser.NotExpressionContext ctx) {

        AstNode node = new AstNodeNot(current);
        resetCurrent(node);
        Object o = super.visitNotExpression(ctx);

        return o;
    }

    @Override
    public Object visitOrExpression(MySqlParser.OrExpressionContext ctx) {
        AstNode node = new AstNodeOr(current);
        resetCurrent(node);
        Object o = super.visitOrExpression(ctx);

        return o;
    }

    @Override
    public Object visitSimpleExpression(MySqlParser.SimpleExpressionContext ctx) {
        return super.visitSimpleExpression(ctx);
    }

    //BooleanPrimary


    @Override
    public Object visitComparisonBooleanPrimary(MySqlParser.ComparisonBooleanPrimaryContext ctx) {
        AstNode node = new AstNodeComp(current);
        resetCurrent(node);
        Object o = super.visitComparisonBooleanPrimary(ctx);

        return o;
    }

    @Override
    public Object visitIsNullBooleanPrimary(MySqlParser.IsNullBooleanPrimaryContext ctx) {
        AstNode node = new AstNodeIs(current);
        resetCurrent(node);
        Object o = super.visitIsNullBooleanPrimary(ctx);

        return o;
    }

    @Override
    public Object visitComparisonBooleanPrimary2(MySqlParser.ComparisonBooleanPrimary2Context ctx) {

        AstNode node = new AstNodeComp(current);
        resetCurrent(node);
        Object o = super.visitComparisonBooleanPrimary2(ctx);

        return o;
    }

    @Override
    public Object visitConsistentEqualBooleanPrimary(MySqlParser.ConsistentEqualBooleanPrimaryContext ctx) {
        AstNode node = new AstNodeComp(current);
        resetCurrent(node);
        Object o = super.visitConsistentEqualBooleanPrimary(ctx);

        return o;
    }

    @Override
    public Object visitPredicateBooleanPrimary(MySqlParser.PredicateBooleanPrimaryContext ctx) {
        return super.visitPredicateBooleanPrimary(ctx);
    }

    //Predicate
    @Override
    public Object visitInSubqueryPredicate(MySqlParser.InSubqueryPredicateContext ctx) {
        AstNode node = new AstNodeIn(current);
        resetCurrent(node);
        Object o = super.visitInSubqueryPredicate(ctx);

        return o;
    }

    @Override
    public Object visitInPredicate(MySqlParser.InPredicateContext ctx) {
        AstNode node = new AstNodeIn(current);
        resetCurrent(node);
        Object o = super.visitInPredicate(ctx);

        return o;
    }

    @Override
    public Object visitBetweenPredicate(MySqlParser.BetweenPredicateContext ctx) {
        AstNode node = new AstNodeBetween(current);
        resetCurrent(node);
        Object o = super.visitBetweenPredicate(ctx);

        return o;
    }

    @Override
    public Object visitSoundsLikePredicate(MySqlParser.SoundsLikePredicateContext ctx) {
        AstNode node = new AstNodeLIke(current);
        resetCurrent(node);
        Object o = super.visitSoundsLikePredicate(ctx);

        return o;
    }

    @Override
    public Object visitLikePredicate(MySqlParser.LikePredicateContext ctx) {
        AstNode node = new AstNodeLIke(current);
        resetCurrent(node);
        Object o = super.visitLikePredicate(ctx);

        return o;
    }

    @Override
    public Object visitRegexpPredicate(MySqlParser.RegexpPredicateContext ctx) {
        //todo
        return super.visitRegexpPredicate(ctx);
    }

    @Override
    public Object visitSimplePredicate(MySqlParser.SimplePredicateContext ctx) {
        return super.visitSimplePredicate(ctx);
    }

    //BitExpr

    @Override
    public Object visitOrBitExpr(MySqlParser.OrBitExprContext ctx) {
        AstNode node = new AstNodeBitOr(current);
        resetCurrent(node);
        Object o = super.visitOrBitExpr(ctx);

        return o;
    }

    @Override
    public Object visitModBitExpr(MySqlParser.ModBitExprContext ctx) {
        AstNode node = new AstNodeMath(current);
        resetCurrent(node);
        Object o = super.visitModBitExpr(ctx);

        return o;
    }

    @Override
    public Object visitShiftLeftBitExpr(MySqlParser.ShiftLeftBitExprContext ctx) {
        AstNode node = new AstNodeShift(current);
        resetCurrent(node);
        Object o = super.visitShiftLeftBitExpr(ctx);

        return o;
    }

    @Override
    public Object visitAndBitExpr(MySqlParser.AndBitExprContext ctx) {

        AstNode node = new AstNodeAnd(current);
        resetCurrent(node);
        Object o = super.visitAndBitExpr(ctx);

        return o;
    }

    @Override
    public Object visitShiftRightBitExpr(MySqlParser.ShiftRightBitExprContext ctx) {

        AstNode node = new AstNodeShift(current);
        resetCurrent(node);
        Object o = super.visitShiftRightBitExpr(ctx);

        return o;
    }

    @Override
    public Object visitMultiplyBitExpr(MySqlParser.MultiplyBitExprContext ctx) {

        AstNode node = new AstNodeMath(current);
        resetCurrent(node);
        Object o = super.visitMultiplyBitExpr(ctx);

        return o;
    }

    @Override
    public Object visitDivideBitExpr(MySqlParser.DivideBitExprContext ctx) {

        AstNode node = new AstNodeMath(current);
        resetCurrent(node);
        Object o = super.visitDivideBitExpr(ctx);

        return o;
    }

    @Override
    public Object visitSimpleBitExpr(MySqlParser.SimpleBitExprContext ctx) {
        return super.visitSimpleBitExpr(ctx);
    }

    @Override
    public Object visitPlusBitExpr(MySqlParser.PlusBitExprContext ctx) {

        AstNode node = new AstNodeMath(current);
        resetCurrent(node);
        Object o = super.visitPlusBitExpr(ctx);

        return o;
    }

    @Override
    public Object visitMinusBitExpr(MySqlParser.MinusBitExprContext ctx) {

        AstNode node = new AstNodeMath(current);
        resetCurrent(node);
        Object o = super.visitMinusBitExpr(ctx);

        return o;
    }

    @Override
    public Object visitXorBitExpr(MySqlParser.XorBitExprContext ctx) {

        AstNode node = new AstNodeBitXor(current);
        resetCurrent(node);
        Object o = super.visitXorBitExpr(ctx);

        return o;
    }

    @Override
    public Object visitBitInvertSimpleExpr(MySqlParser.BitInvertSimpleExprContext ctx) {

        AstNode node = new AstNodeBitInversion(current);
        resetCurrent(node);
        Object o = super.visitBitInvertSimpleExpr(ctx);

        return o;
    }

    //SimpleExpr

    @Override
    public Object visitDefaultSimpleExpr(MySqlParser.DefaultSimpleExprContext ctx) {
        //TODO
        return super.visitDefaultSimpleExpr(ctx);
    }

    @Override
    public Object visitVariableSimpleExpr(MySqlParser.VariableSimpleExprContext ctx) {
        //TODO
        return super.visitVariableSimpleExpr(ctx);
    }

    @Override
    public Object visitColumnSimpleExpr(MySqlParser.ColumnSimpleExprContext ctx) {

        new AstNodeColumnExpr(current);
        return super.visitColumnSimpleExpr(ctx);
    }

    @Override
    public Object visitRowSimpleExpr(MySqlParser.RowSimpleExprContext ctx) {
        //TODO
        return super.visitRowSimpleExpr(ctx);
    }

    @Override
    public Object visitHignNotSimpleExpr(MySqlParser.HignNotSimpleExprContext ctx) {

        AstNode node = new AstNodeNot(current);
        resetCurrent(node);
        Object o = super.visitHignNotSimpleExpr(ctx);

        return o;
    }

    @Override
    public Object visitConstantSimpleExpr(MySqlParser.ConstantSimpleExprContext ctx) {
        new AstNodeConstantExpr(current);
        return super.visitConstantSimpleExpr(ctx);
    }

    @Override
    public Object visitCollateSimpleExpr(MySqlParser.CollateSimpleExprContext ctx) {
        //todo
        return super.visitCollateSimpleExpr(ctx);
    }

    @Override
    public Object visitIntervalSimpleExpr(MySqlParser.IntervalSimpleExprContext ctx) {
        //todo
        return super.visitIntervalSimpleExpr(ctx);
    }

    @Override
    public Object visitExistsSimpleExpr(MySqlParser.ExistsSimpleExprContext ctx) {
        //todo
        return super.visitExistsSimpleExpr(ctx);
    }

    @Override
    public Object visitPositiveSimpleExpr(MySqlParser.PositiveSimpleExprContext ctx) {

        AstNode node = new AstNodePositive(current);
        resetCurrent(node);
        Object o = super.visitPositiveSimpleExpr(ctx);

        return o;
    }

    @Override
    public Object visitConcatSimpleExpr(MySqlParser.ConcatSimpleExprContext ctx) {
        //todo
        return super.visitConcatSimpleExpr(ctx);
    }

    @Override
    public Object visitExprListSimpleExpr(MySqlParser.ExprListSimpleExprContext ctx) {
        //????????????todo
        return super.visitExprListSimpleExpr(ctx);
    }

    @Override
    public Object visitFunctionSimpleExpr(MySqlParser.FunctionSimpleExprContext ctx) {

        new AstNodeFunctionExpr(current);
        return super.visitFunctionSimpleExpr(ctx);
    }

    @Override
    public Object visitNegtiveSimpleExpr(MySqlParser.NegtiveSimpleExprContext ctx) {

        AstNode node = new AstNodeNegtive(current);
        resetCurrent(node);
        Object o = super.visitNegtiveSimpleExpr(ctx);

        return o;
    }

    @Override
    public Object visitBinarySimpleExpr(MySqlParser.BinarySimpleExprContext ctx) {
        //todo
        return super.visitBinarySimpleExpr(ctx);
    }

    @Override
    public Object visitSubquerySimpleExpr(MySqlParser.SubquerySimpleExprContext ctx) {
        //todo
        return super.visitSubquerySimpleExpr(ctx);
    }

    //table_sources
    @Override
    public Object visitTable_sources(MySqlParser.Table_sourcesContext ctx) {
        List<MySqlParser.Table_sourceContext> table_sourceContexts = ctx.table_source();
        if (table_sourceContexts.size() == 1) {
            return super.visitTable_sources(ctx);
        }

        AstNode node = new AstNodeTables(current);
        resetCurrent(node);
        Object o = super.visitTable_sources(ctx);
        return o;
    }

    //todo join

    /**
     * 遍历的有问题，需要再思考怎样写更青春
     * @param ctx
     * @return
     */
    @Override
    public Object visitTable_source(MySqlParser.Table_sourceContext ctx) {
        List<MySqlParser.Join_partContext> join_partContexts = ctx.join_part();
        if (join_partContexts == null || join_partContexts.size() == 0) {
            return super.visitTable_source(ctx);
        }

        AstNode current = this.current;

        int size = join_partContexts.size();
        AstNode leftChildOfJoin = null;
        for (int i = 0; i < size; i++) {
            MySqlParser.Join_partContext join_partContext = join_partContexts.get(i);
            AstNodeJoin joinNode = (AstNodeJoin) this.visit(join_partContext);
            if (i == 0) {
                MySqlParser.Table_source_itemContext table_source_itemContext = ctx.table_source_item();
                resetCurrent(joinNode.getLeftPlaceholder());
                this.visit(table_source_itemContext);
                leftChildOfJoin = joinNode;
            } else {
                joinNode.setLeft(leftChildOfJoin);
                leftChildOfJoin = joinNode;
            }
        }
        current.addChild(leftChildOfJoin);
        return null;
    }

    @Override
    public Object visitAtomTableItem(MySqlParser.AtomTableItemContext ctx) {
        AstNode node = new AstNodeSimpleTable(current);
        resetCurrent(node);
        Object o = super.visitAtomTableItem(ctx);
        return o;
    }

    @Override
    public Object visitSubqueryTableItem(MySqlParser.SubqueryTableItemContext ctx) {
        AstNode node = new AstNodeSubqueryTable(current);
        resetCurrent(node);
        Object o = super.visitSubqueryTableItem(ctx);
        return o;
    }

    @Override
    public Object visitTableSourcesItem(MySqlParser.TableSourcesItemContext ctx) {
        return super.visitTableSourcesItem(ctx);
    }

    @Override
    public Object visitInnerJoin(MySqlParser.InnerJoinContext ctx) {
        AstNodeJoin node = new AstNodeJoin(null, "inner");
        resetCurrent(node.getRightPlaceholder());
        this.visit(ctx.table_source_item());
        if (ctx.expression() != null) {
            node.placeOn();
            resetCurrent(node.getOnPlaceholder());
            this.visit(ctx.expression());
        }
        return node;
    }

    @Override
    public Object visitStraightJoin(MySqlParser.StraightJoinContext ctx) {
        AstNodeJoin node = new AstNodeJoin(null, "straight");
        resetCurrent(node);
        Object o = super.visitStraightJoin(ctx);
        return node;
    }

    @Override
    public Object visitOuterJoin(MySqlParser.OuterJoinContext ctx) {
        AstNodeJoin node = new AstNodeJoin(null, ctx.LEFT() != null ? ctx.LEFT().getText() : ctx.RIGHT().getText());
        resetCurrent(node.getRightPlaceholder());
        this.visit(ctx.table_source_item());
        node.placeOn();
        resetCurrent(node.getOnPlaceholder());
        this.visit(ctx.expression());
        return node;
    }

    @Override
    public Object visitNaturalJoin(MySqlParser.NaturalJoinContext ctx) {
        AstNodeJoin node = new AstNodeJoin(null, "natural");
        resetCurrent(node);
        Object o = super.visitNaturalJoin(ctx);
        return node;
    }
}
