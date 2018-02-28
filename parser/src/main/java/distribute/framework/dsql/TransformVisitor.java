package distribute.framework.dsql;

import com.antlr.grammarsv4.mysql.MySqlParser;
import com.antlr.grammarsv4.mysql.MySqlParserBaseVisitor;
import distribute.framework.ast.*;
import org.antlr.v4.runtime.tree.Tree;

import java.util.List;
import java.util.Stack;


public class TransformVisitor extends MySqlParserBaseVisitor {

    private Stack<AstNode> stack = new Stack<>();

    public TransformVisitor(AstNode root) {
        stack.push(root);
    }

    //expression
    @Override
    public Object visitIsExpression(MySqlParser.IsExpressionContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeIs(parent);
        stack.push(node);
        Object o = super.visitIsExpression(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitAndExpression(MySqlParser.AndExpressionContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeAnd(parent);
        stack.push(node);
        Object o = super.visitAndExpression(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitXorExpression(MySqlParser.XorExpressionContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeXor(parent);
        stack.push(node);
        Object o = super.visitXorExpression(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitNotExpression(MySqlParser.NotExpressionContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeNot(parent);
        stack.push(node);
        Object o = super.visitNotExpression(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitOrExpression(MySqlParser.OrExpressionContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeOr(parent);
        stack.push(node);
        Object o = super.visitOrExpression(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitSimpleExpression(MySqlParser.SimpleExpressionContext ctx) {
        return super.visitSimpleExpression(ctx);
    }

    //BooleanPrimary


    @Override
    public Object visitComparisonBooleanPrimary(MySqlParser.ComparisonBooleanPrimaryContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeComp(parent);
        stack.push(node);
        Object o = super.visitComparisonBooleanPrimary(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitIsNullBooleanPrimary(MySqlParser.IsNullBooleanPrimaryContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeIs(parent);
        stack.push(node);
        Object o = super.visitIsNullBooleanPrimary(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitComparisonBooleanPrimary2(MySqlParser.ComparisonBooleanPrimary2Context ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeComp(parent);
        stack.push(node);
        Object o = super.visitComparisonBooleanPrimary2(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitConsistentEqualBooleanPrimary(MySqlParser.ConsistentEqualBooleanPrimaryContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeComp(parent);
        stack.push(node);
        Object o = super.visitConsistentEqualBooleanPrimary(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitPredicateBooleanPrimary(MySqlParser.PredicateBooleanPrimaryContext ctx) {
        return super.visitPredicateBooleanPrimary(ctx);
    }

    //Predicate
    @Override
    public Object visitInSubqueryPredicate(MySqlParser.InSubqueryPredicateContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeIn(parent);
        stack.push(node);
        Object o = super.visitInSubqueryPredicate(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitInPredicate(MySqlParser.InPredicateContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeIn(parent);
        stack.push(node);
        Object o = super.visitInPredicate(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitBetweenPredicate(MySqlParser.BetweenPredicateContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeBetween(parent);
        stack.push(node);
        Object o = super.visitBetweenPredicate(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitSoundsLikePredicate(MySqlParser.SoundsLikePredicateContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeLIke(parent);
        stack.push(node);
        Object o = super.visitSoundsLikePredicate(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitLikePredicate(MySqlParser.LikePredicateContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeLIke(parent);
        stack.push(node);
        Object o = super.visitLikePredicate(ctx);
        stack.pop();
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
        AstNode parent = stack.peek();
        AstNode node = new AstNodeBitOr(parent);
        stack.push(node);
        Object o = super.visitOrBitExpr(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitModBitExpr(MySqlParser.ModBitExprContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeMath(parent);
        stack.push(node);
        Object o = super.visitModBitExpr(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitShiftLeftBitExpr(MySqlParser.ShiftLeftBitExprContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeShift(parent);
        stack.push(node);
        Object o = super.visitShiftLeftBitExpr(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitAndBitExpr(MySqlParser.AndBitExprContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeAnd(parent);
        stack.push(node);
        Object o = super.visitAndBitExpr(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitShiftRightBitExpr(MySqlParser.ShiftRightBitExprContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeShift(parent);
        stack.push(node);
        Object o = super.visitShiftRightBitExpr(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitMultiplyBitExpr(MySqlParser.MultiplyBitExprContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeMath(parent);
        stack.push(node);
        Object o = super.visitMultiplyBitExpr(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitDivideBitExpr(MySqlParser.DivideBitExprContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeMath(parent);
        stack.push(node);
        Object o = super.visitDivideBitExpr(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitSimpleBitExpr(MySqlParser.SimpleBitExprContext ctx) {
        return super.visitSimpleBitExpr(ctx);
    }

    @Override
    public Object visitPlusBitExpr(MySqlParser.PlusBitExprContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeMath(parent);
        stack.push(node);
        Object o = super.visitPlusBitExpr(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitMinusBitExpr(MySqlParser.MinusBitExprContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeMath(parent);
        stack.push(node);
        Object o = super.visitMinusBitExpr(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitXorBitExpr(MySqlParser.XorBitExprContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeBitXor(parent);
        stack.push(node);
        Object o = super.visitXorBitExpr(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitBitInvertSimpleExpr(MySqlParser.BitInvertSimpleExprContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeBitInversion(parent);
        stack.push(node);
        Object o = super.visitBitInvertSimpleExpr(ctx);
        stack.pop();
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
        AstNode parent = stack.peek();
        new AstNodeColumnExpr(parent);
        return super.visitColumnSimpleExpr(ctx);
    }

    @Override
    public Object visitRowSimpleExpr(MySqlParser.RowSimpleExprContext ctx) {
        //TODO
        return super.visitRowSimpleExpr(ctx);
    }

    @Override
    public Object visitHignNotSimpleExpr(MySqlParser.HignNotSimpleExprContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeNot(parent);
        stack.push(node);
        Object o = super.visitHignNotSimpleExpr(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitConstantSimpleExpr(MySqlParser.ConstantSimpleExprContext ctx) {
        AstNode parent = stack.peek();
        new AstNodeConstantExpr(parent);
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
        AstNode parent = stack.peek();
        AstNode node = new AstNodePositive(parent);
        stack.push(node);
        Object o = super.visitPositiveSimpleExpr(ctx);
        stack.pop();
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
        AstNode parent = stack.peek();
        new AstNodeFunctionExpr(parent);
        return super.visitFunctionSimpleExpr(ctx);
    }

    @Override
    public Object visitNegtiveSimpleExpr(MySqlParser.NegtiveSimpleExprContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeNegtive(parent);
        stack.push(node);
        Object o = super.visitNegtiveSimpleExpr(ctx);
        stack.pop();
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
        AstNode parent = stack.peek();
        AstNode node = new AstNodeTables(parent);
        stack.push(node);
        Object o = super.visitTable_sources(ctx);
        stack.pop();
        return o;
    }

    //todo join
    @Override
    public Object visitTable_source(MySqlParser.Table_sourceContext ctx) {
        List<MySqlParser.Join_partContext> join_partContexts = ctx.join_part();
        if (join_partContexts == null || join_partContexts.size() == 0) {
            return super.visitTable_source(ctx);
        }
        AstNode parent = stack.peek();
        int size = join_partContexts.size();
        ctx.table_source_item().accept(this);
        for (int i = 0; i < size; i++) {
            MySqlParser.Join_partContext join_partContext = join_partContexts.get(i);
            this.visit(join_partContext);
            join_partContext.accept(this);
            AstNode child = parent.getChild(parent.getChildCount() - 1);
        }
        return null;
    }

    @Override
    public Object visitAtomTableItem(MySqlParser.AtomTableItemContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeSimpleTable(parent);
        stack.push(node);
        Object o = super.visitAtomTableItem(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitSubqueryTableItem(MySqlParser.SubqueryTableItemContext ctx) {
        AstNode parent = stack.peek();
        AstNode node = new AstNodeSubqueryTable(parent);
        stack.push(node);
        Object o = super.visitSubqueryTableItem(ctx);
        stack.pop();
        return o;
    }

    @Override
    public Object visitTableSourcesItem(MySqlParser.TableSourcesItemContext ctx) {
        return super.visitTableSourcesItem(ctx);
    }

    @Override
    public Object visitInnerJoin(MySqlParser.InnerJoinContext ctx) {
        AstNode node = new AstNodeJoin(null, "inner");
        stack.push(node);
        Object o = super.visitInnerJoin(ctx);
        stack.pop();
        return node;
    }

    @Override
    public Object visitStraightJoin(MySqlParser.StraightJoinContext ctx) {
        AstNode node = new AstNodeJoin(null, "straight");
        stack.push(node);
        Object o = super.visitStraightJoin(ctx);
        stack.pop();
        return node;
    }

    @Override
    public Object visitOuterJoin(MySqlParser.OuterJoinContext ctx) {
        AstNode node = new AstNodeJoin(null, ctx.LEFT() != null ? ctx.LEFT().getText() : ctx.RIGHT().getText());
        stack.push(node);
        Object o = super.visitOuterJoin(ctx);
        stack.pop();
        return node;
    }

    @Override
    public Object visitNaturalJoin(MySqlParser.NaturalJoinContext ctx) {
        AstNode node = new AstNodeJoin(null, "natural");
        stack.push(node);
        Object o = super.visitNaturalJoin(ctx);
        stack.pop();
        return node;
    }
}
