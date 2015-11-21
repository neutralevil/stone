package in.liub.stone_java;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ASTLeafTest {
    @Test
    public void leafIsNoChild() {
        ASTLeaf leaf = new ASTLeaf();
        assertThat(leaf.numChildren(), is(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void canNotGetChildFromALeafNode() {
        ASTLeaf leaf = new ASTLeaf();
        leaf.child(0);
    }

    @Test
    public void canNotIterateChildrenFromALeafNode() {
        ASTLeaf leaf = new ASTLeaf();
        Iterator<ASTree> children = leaf.children();
        assertThat(children.hasNext(), is(not(true)));
    }
}
