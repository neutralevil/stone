package in.liub.stone_java;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

public class ASTListTest {
    private ASTList list;

    @Before
    public void setUp() {
        list = new ASTList(Arrays.asList(
                new ASTLeaf(Token.identifierOf("i")),
                new ASTLeaf(Token.identifierOf("=")),
                new ASTLeaf(Token.numberOf(768))));
    }

    @Test
    public void hasAtLeastOneChild() {
        assertTrue(list.numChildren() >= 1);
    }

    @Test
    public void canEnumerateChildren() {
        int num = list.numChildren();
        ASTree[] children1 = new ASTree[num];
        ASTree[] children2 = new ASTree[num];

        for (int i = 0; i < num; ++i)
            children1[i] = list.child(i);

        Iterator<ASTree> it = list.children();
        int i = 0;
        while (it.hasNext())
            children2[i++] = it.next();
    }
}

