package in.liub.stone_java;

import java.util.Iterator;

public abstract class ASTree {
    public abstract int numChildren();
    public abstract ASTree child(int index);
    public abstract Iterator<ASTree> children();
}
