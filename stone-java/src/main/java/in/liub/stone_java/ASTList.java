package in.liub.stone_java;

import java.util.Iterator;
import java.util.List;

public class ASTList extends ASTree {
    private List<ASTree> children;

    public ASTList(List<ASTree> children) {
        this.children = children;
    }

    @Override
    public int numChildren() {
        return children.size();
    }

    @Override
    public ASTree child(int index) {
        return children.get(index);
    }

    @Override
    public Iterator<ASTree> children() {
        return children.iterator();
    }
}
