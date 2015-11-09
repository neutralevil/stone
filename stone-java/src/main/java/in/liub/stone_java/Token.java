package in.liub.stone_java;

public class Token {
    public static final Token EOF = new Token();

    public boolean isNumber() {
        return false;
    }

    public int getNumber() {
        throw new RuntimeException("Unexpected");
    }
}
