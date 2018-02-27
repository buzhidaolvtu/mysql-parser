package distribute.framework.ast;

import org.antlr.v4.runtime.misc.Utils;
import org.antlr.v4.runtime.tree.Tree;

import java.util.ArrayList;
import java.util.List;


/**
 * AstNode本身并不体现优先级，优先级是由tree的结构决定
 */
public class AstNode implements Tree {

    private AstNode parent;

    protected String name;

    protected List<AstNode> children = new ArrayList<>();

    public AstNode(AstNode parent) {
        this.name = "";
        this.parent = parent;
    }

    public void addNode(AstNode node) {
        children.add(node);
    }

    @Override
    public Tree getParent() {
        return parent;
    }

    @Override
    public Object getPayload() {
        return null;
    }

    @Override
    public Tree getChild(int i) {
        return children.get(i);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public String toStringTree() {
        String s = name;
        if (getChildCount() == 0) return s;
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        buf.append(s);
        buf.append(' ');
        for (int i = 0; i < getChildCount(); i++) {
            if (i > 0) buf.append(' ');
            buf.append(getChild(i).toStringTree());
        }
        buf.append(")");
        return buf.toString();
    }
}
