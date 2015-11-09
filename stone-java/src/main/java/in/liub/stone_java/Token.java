package in.liub.stone_java;

public abstract class Token {
    public static final Token EOF = new Token() {
        @Override
        public String getText() {
            return "EOF";
        }
    };

    public boolean isNumber() {
        return false;
    }

    public int getNumber() {
        throw new RuntimeException("Unexpected");
    }

    public boolean isIdentifier() {
        return false;
    }

    public abstract String getText();
}
