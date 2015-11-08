package in.liub.stone_java;

import java.io.Reader;

public class Lexer {
    public Lexer(Reader reader) {
    }

    public Token read() {
        return Token.EOF;
    }
}
