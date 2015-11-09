package in.liub.stone_java;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private static Pattern pattern = Pattern.compile(
            "[a-z_A-Z][a-z_A-Z0-9]*");

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

        if (line == null)
            return Token.EOF;

        try {
            return new NumToken(Integer.parseInt(line));
        } catch (NumberFormatException e) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.lookingAt())
                return new IdToken(matcher.group());
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

    @Override
    public String getText() {
        return String.valueOf(value);
    }
}

class IdToken extends Token {
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
