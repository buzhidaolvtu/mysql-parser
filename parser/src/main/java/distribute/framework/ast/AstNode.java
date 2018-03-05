package distribute.framework.ast;

import distribute.framework.parser.Value;
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

    public AstNode(String name) {
        this(null, name);
    }

    public AstNode(AstNode parent, String name) {
        this.name = name != null ? name.replaceAll(" ", "•") : name;
        this.parent = parent;
        if (parent != null) {
            parent.addChild(this);
        }
    }

    public void addChild(AstNode node) {
        children.add(node);
    }

    public void addChild(AstNode node, int pos) {
        children.add(pos, node);
    }

    public void removeChild(AstNode node) {
        children.remove(node);
    }

    public void removeChild(AstNode node, int pos) {
        children.remove(pos);
    }

    public void setParent(AstNode parent) {
        this.parent = parent;
        if (parent != null) {
            parent.addChild(this);
        }
    }

    @Override
    public AstNode getParent() {
        return parent;
    }

    @Override
    public Object getPayload() {
        return name;
    }

    @Override
    public AstNode getChild(int i) {
        return children.get(i);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    public Value evaluate() {
        return null;
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
