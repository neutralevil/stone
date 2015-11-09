package in.liub.stone_java;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

public class Lexer {
    private final LineNumberReader reader;

    public Lexer(Reader reader) {
        this.reader = new LineNumberReader(reader);
    }

    public Token read() {
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Unexpected", e);
        }

        try {
            return new NumToken(Integer.parseInt(line));
        } catch (NumberFormatException e) {
            return Token.EOF;
        }
    }
}

class NumToken extends Token {
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
}
