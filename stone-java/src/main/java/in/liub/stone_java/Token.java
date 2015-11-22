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

    public boolean isString() { return false; }

    public abstract String getText();

    @Override
    public String toString() {
        return getText();
    }

    public static Token numberOf(int num) {
        return new NumToken(num);
    }

    public static Token identifierOf(String identifier) {
        return new IdToken(identifier);
    }

    public static Token stringOf(String text) {
        return new StrToken(text);
    }

    private static class NumToken extends Token {
        private int value;

        public NumToken(int num) {
            value = num;
        }

        @Override
        public boolean isNumber() {
            return true;
        }

        @Override
        public int getNumber() {
            return value;
        }

        @Override
        public String getText() {
            return String.valueOf(value);
        }
    }

    private static class IdToken extends Token {
        private final String text;

        public IdToken(String id) {
            text = id;
        }

        @Override
        public boolean isIdentifier() {
            return true;
        }

        @Override
        public String getText() {
            return text;
        }
    }

    private static class StrToken extends Token {
        private final String text;

        public StrToken(String text) {
            this.text = text;
        }

        @Override
        public boolean isString() {
            return true;
        }

        @Override
        public String getText() {
            return text;
        }
    }
}
