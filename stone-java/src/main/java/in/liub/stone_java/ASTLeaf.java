package in.liub.stone_java;

import java.util.ArrayList;
import java.util.Iterator;

public class ASTLeaf extends ASTree {
    @Override
    public int numChildren() {
        return 0;
    }

    @Override
    public ASTree child(int index) {
        throw new IndexOutOfBoundsException("no child in ASTLeaf");
    }

    private static ArrayList<ASTree> empty = new ArrayList<>();
    @Override
    public Iterator<ASTree> children() {
        return empty.iterator();
    }
}
