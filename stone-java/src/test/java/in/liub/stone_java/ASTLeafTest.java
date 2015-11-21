package in.liub.stone_java;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ASTLeafTest {
    private ASTLeaf leaf;

    @Before
    public void setUp() {
        this.leaf = new ASTLeaf();
    }

    @After
    public void tearDown() {
        this.leaf = null;
    }
    @Test
    public void leafIsNoChild() {
        assertThat(leaf.numChildren(), is(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void canNotGetChildFromALeafNode() {
        leaf.child(0);
    }

    @Test
    public void canNotIterateChildrenFromALeafNode() {
        Iterator<ASTree> children = leaf.children();
        assertThat(children.hasNext(), is(not(true)));
    }
}
