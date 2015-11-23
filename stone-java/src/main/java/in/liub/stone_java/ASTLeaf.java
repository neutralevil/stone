package in.liub.stone_java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ASTLeaf extends ASTree {
    private final Token token;

    public ASTLeaf(Token token) {
        this.token = token;
    }

    @Override
    public int numChildren() {
        return 0;
    }

    @Override
    public ASTree child(int index) {
        throw new IndexOutOfBoundsException("no child in ASTLeaf");
    }

    @Override
    public Iterator<ASTree> children() {
        return Collections.emptyIterator();
    }

    public Token token() {
        return this.token;
    }
}
