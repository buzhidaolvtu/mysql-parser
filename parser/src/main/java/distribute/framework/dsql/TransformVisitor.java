package distribute.framework.dsql;

import com.antlr.grammarsv4.mysql.MySqlParser;
import com.antlr.grammarsv4.mysql.MySqlParserBaseVisitor;
import distribute.framework.ast.*;
import distribute.framework.parser.Value;
import distribute.framework.parser.datatype.Type;
import distribute.framework.parser.expression.ColumnExpression;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;

import java.util.List;


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

    //select_statement
    @Override
    public Object visitSimpleSelect(MySqlParser.SimpleSelectContext ctx) {
        AstNodeSimpleSelect node = new AstNodeSimpleSelect(current, "simple select");
        resetCurrent(node);
        MySqlParser.Query_specificationContext query_specificationContext = ctx.query_specification();

        MySqlParser.Select_listContext select_listContext = query_specificationContext.select_list();
        AstNode map = new AstNode(node, "map");
        node.map = map;
        resetCurrent(map);
        visit(select_listContext);

        MySqlParser.From_clauseContext from_clauseContext = query_specificationContext.from_clause();
        if (from_clauseContext.FROM() != null) {
            AstNode source = new AstNode(node, "source");
            node.source = source;
            resetCurrent(source);
            MySqlParser.Table_sourcesContext table_sourcesContext = from_clauseContext.table_sources();
            visit(table_sourcesContext);
        }

        if (from_clauseContext.WHERE() != null) {
            AstNode filter = new AstNode(node, "filter");
            node.filter = filter;
            resetCurrent(filter);
            MySqlParser.ExpressionContext expression = from_clauseContext.expression(0);
            visit(expression);
        }

        return node;
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
    public Object visitMath1BitExpr(MySqlParser.Math1BitExprContext ctx) {
        AstNode node = new AstNodeMath(current);
        resetCurrent(node);
        return super.visitMath1BitExpr(ctx);
    }

    @Override
    public Object visitMath2BitExpr(MySqlParser.Math2BitExprContext ctx) {
        AstNode node = new AstNodeMath(current);
        resetCurrent(node);
        return super.visitMath2BitExpr(ctx);
    }

    @Override
    public Object visitShiftBitExpr(MySqlParser.ShiftBitExprContext ctx) {
        AstNode node = new AstNodeShift(current);
        resetCurrent(node);
        return super.visitShiftBitExpr(ctx);
    }

    @Override
    public Object visitAndBitExpr(MySqlParser.AndBitExprContext ctx) {

        AstNode node = new AstNodeAnd(current);
        resetCurrent(node);
        Object o = super.visitAndBitExpr(ctx);

        return o;
    }


    @Override
    public Object visitSimpleBitExpr(MySqlParser.SimpleBitExprContext ctx) {
        return super.visitSimpleBitExpr(ctx);
    }

    @Override
    public Object visitXorBitExpr(MySqlParser.XorBitExprContext ctx) {

        AstNode node = new AstNodeBitXor(current);
        resetCurrent(node);
        Object o = super.visitXorBitExpr(ctx);

        return o;
    }

    @Override
    public Object visitOrBitExpr(MySqlParser.OrBitExprContext ctx) {
        AstNode node = new AstNodeBitOr(current);
        resetCurrent(node);
        Object o = super.visitOrBitExpr(ctx);

        return o;
    }


    //SimpleExpr
    @Override
    public Object visitBitInvertSimpleExpr(MySqlParser.BitInvertSimpleExprContext ctx) {

        AstNode node = new AstNodeBitInversion(current);
        resetCurrent(node);
        Object o = super.visitBitInvertSimpleExpr(ctx);

        return o;
    }

    @Override
    public Object visitDefaultSimpleExpr(MySqlParser.DefaultSimpleExprContext ctx) {
        //TODO
        return super.visitDefaultSimpleExpr(ctx);
    }

    @Override
    public Object visitVariableSimpleExpr(MySqlParser.VariableSimpleExprContext ctx) {
        MySqlParser.Mysql_variableContext mysql_variableContext = ctx.mysql_variable();
        MySqlParser.Mybatis_varContext mybatis_varContext = mysql_variableContext.mybatis_var();
        if (mybatis_varContext != null) {
            String text = mybatis_varContext.id_().getText();
            new AstNode(current, text);
        }
        return super.visitVariableSimpleExpr(ctx);
    }

    @Override
    public Object visitColumnSimpleExpr(MySqlParser.ColumnSimpleExprContext ctx) {
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

    @Override
    public ParserRuleContext visitConstant(MySqlParser.ConstantContext ctx) {
        String text = ctx.getText();
        new AstNodeConstantExpr(current, text);
        switch (ctx.pn) {
            case 1://string_literal
                text = text.replaceAll("'", "");
                ctx.value = new Value(Type.VARCHAR, text);
                break;
            case 2://decimal_literal
                ctx.value = new Value(Type.BIGINT, Integer.parseInt(text));
                break;
            case 3://hexadecimal_literal
                text = text.replaceAll("0x", "");
                text = String.valueOf(Integer.parseInt(text, 16));
                break;
            case 4://boolean_literal
                if (text.equals("TRUE")) {
                    ctx.value = new Value(Type.TINYINT, true);
                } else {
                    ctx.value = new Value(Type.TINYINT, false);
                }
                break;
            case 5://REAL_LITERAL
                ctx.value = new Value(Type.DOUBLE, Double.parseDouble(text));
                break;
            case 6://BIT_STRING
                //todo
                break;
            case 7://NOTNULL
                ctx.value = Value.NOT_NULL;
                break;
            case 8://(NULL_LITERAL | NULL_SPEC_LITERAL)
                ctx.value = Value.NULL;
                break;
        }
        return ctx;
    }

    @Override
    public ParserRuleContext visitFull_column_name(MySqlParser.Full_column_nameContext ctx) {
        ColumnExpression columnExpression = new ColumnExpression();
        String[] split = ctx.getText().split("\\.");
        if (split.length == 1) {
            columnExpression.columnName = split[0];
        }
        if (split.length == 2) {
            columnExpression.tableName = split[0];
            columnExpression.columnName = split[1];
        }
        if (split.length == 3) {
            columnExpression.databaseName = split[0];
            columnExpression.tableName = split[1];
            columnExpression.columnName = split[2];
        }
        new AstNodeColumnExpr(current, columnExpression);
        return ctx;
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
     *
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

    //select list

    @Override
    public Object visitSelect_list(MySqlParser.Select_listContext ctx) {
        AstNode node = new AstNodeSelectList(current);
        resetCurrent(node);
        Object o = super.visitSelect_list(ctx);
        return o;
    }

    @Override
    public Object visitSellistelAllCol(MySqlParser.SellistelAllColContext ctx) {
        AstNode node = new AstNodeStarColumn(current);
        return node;
    }

    @Override
    public Object visitSellistelCol(MySqlParser.SellistelColContext ctx) {
        String alias = null;
        MySqlParser.Id_Context id_context = ctx.id_();
        if (id_context != null) {
            alias = id_context.getText();
        }
        AstNode node = new AstNodeSelectListItem(current, alias == null ? ctx.getText() : alias);
        resetCurrent(node);
        visit(ctx.full_column_name());
        return node;
    }

    @Override
    public Object visitSellistelFunc(MySqlParser.SellistelFuncContext ctx) {
        String alias = null;
        MySqlParser.Id_Context id_context = ctx.id_();
        if (id_context != null) {
            alias = id_context.getText();
        }
        AstNode node = new AstNodeSelectListItem(current, alias == null ? ctx.getText() : alias);
        resetCurrent(node);
        visit(ctx.function_call());
        return node;
    }

    @Override
    public Object visitSellistelExpr(MySqlParser.SellistelExprContext ctx) {
        String alias = null;
        MySqlParser.Id_Context id_context = ctx.id_();
        if (id_context != null) {
            alias = id_context.getText();
        }
        AstNode node = new AstNodeSelectListItem(current, alias == null ? ctx.getText() : alias);
        resetCurrent(node);
        Object o = super.visitSellistelExpr(ctx);
        return node;
    }

    //funtion call
    @Override
    public Object visitAggregateFunctionCall(MySqlParser.AggregateFunctionCallContext ctx) {
        AstNode node = new AstNodeAggregateFunctionCall(current, "AggregateFunctionCall");
        resetCurrent(node);
        return super.visitAggregateFunctionCall(ctx);
    }

    @Override
    public Object visitScalarFunctionCall(MySqlParser.ScalarFunctionCallContext ctx) {
        MySqlParser.Scalar_function_nameContext scalar_function_nameContext = ctx.scalar_function_name();
        String functionName = scalar_function_nameContext.getText();
        AstNode node = new AstNodeScalarFunctionCall(current, functionName);
        resetCurrent(node);
        visit(ctx.function_args());
        return node;
    }

    @Override
    public Object visitSpecificFunctionCall(MySqlParser.SpecificFunctionCallContext ctx) {
        return super.visitSpecificFunctionCall(ctx);
    }

    @Override
    public Object visitUdfFunctionCall(MySqlParser.UdfFunctionCallContext ctx) {
        //todo
        return super.visitUdfFunctionCall(ctx);
    }

    @Override
    public Object visitSimpleSpecificFCall(MySqlParser.SimpleSpecificFCallContext ctx) {
        return new AstNodeSpecificFunctionCall(current, ctx.getText());
    }

    @Override
    public Object visitConvertDataTypeFCall(MySqlParser.ConvertDataTypeFCallContext ctx) {
        AstNode node = new AstNodeSpecificFunctionCall(current, "ConvertDataType");
        resetCurrent(node);
        return super.visitConvertDataTypeFCall(ctx);
    }

    @Override
    public Object visitValuesFCall(MySqlParser.ValuesFCallContext ctx) {
        //todo
        AstNode node = new AstNodeSpecificFunctionCall(current, "Values");
        resetCurrent(node);
        return super.visitValuesFCall(ctx);
    }

    @Override
    public Object visitCaseFCall(MySqlParser.CaseFCallContext ctx) {
        //todo
        AstNode node = new AstNodeSpecificFunctionCall(current, "Case");
        resetCurrent(node);
        return super.visitCaseFCall(ctx);
    }

    @Override
    public Object visitCharFCall(MySqlParser.CharFCallContext ctx) {
        AstNode node = new AstNodeSpecificFunctionCall(current, "Char");
        resetCurrent(node);
        return super.visitCharFCall(ctx);
    }

    @Override
    public Object visitPositionFCall(MySqlParser.PositionFCallContext ctx) {
        AstNode node = new AstNodeSpecificFunctionCall(current, "Position");
        resetCurrent(node);
        return super.visitPositionFCall(ctx);
    }

    @Override
    public Object visitSubstrFCall(MySqlParser.SubstrFCallContext ctx) {
        AstNode node = new AstNodeSpecificFunctionCall(current, "Substr");
        resetCurrent(node);
        return super.visitSubstrFCall(ctx);
    }

    @Override
    public Object visitTrimFCall(MySqlParser.TrimFCallContext ctx) {
        AstNode node = new AstNodeSpecificFunctionCall(current, "Trim");
        resetCurrent(node);
        return super.visitTrimFCall(ctx);
    }

    @Override
    public Object visitWeightFCall(MySqlParser.WeightFCallContext ctx) {
        AstNode node = new AstNodeSpecificFunctionCall(current, "Weight");
        resetCurrent(node);
        return super.visitWeightFCall(ctx);
    }

    @Override
    public Object visitExtractFCall(MySqlParser.ExtractFCallContext ctx) {
        AstNode node = new AstNodeSpecificFunctionCall(current, "Extract");
        resetCurrent(node);
        return super.visitExtractFCall(ctx);
    }

    @Override
    public Object visitGetFormatFCall(MySqlParser.GetFormatFCallContext ctx) {
        AstNode node = new AstNodeSpecificFunctionCall(current, "GetFormat");
        resetCurrent(node);
        return super.visitGetFormatFCall(ctx);
    }
    //end
}
