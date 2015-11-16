package in.liub.stone_java;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private static Pattern pattern = Pattern.compile(
            "\\s*(" +
            "(?<number>[0-9]+)" +
            "|(?:\"(?<string>(\\\\n|\\\\\\\\|\\\\\"|[^\"])*)\")" +
            "|(?<id>[a-z_A-Z][a-z_A-Z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})" +
            ")");

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

        Matcher matcher = pattern.matcher(line);
        if (matcher.lookingAt()) {
            String m = matcher.group(1);
            if (matcher.group("number") != null)
                return new NumToken(Integer.parseInt(m));
            else if (matcher.group("id") != null)
                return new IdToken(m);
            else if (matcher.group("string") != null)
                return new StrToken(escaped(matcher.group("string")));
        }
        return Token.EOF;
    }

    private String escaped(String src) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < src.length(); ++i) {
            char c = src.charAt(i);
            if (c == '\\' && i + 1 < src.length()) {
                char c2 = src.charAt(i + 1);
                if (c2 == '\\' || c2 == '"') {
                    c = c2;
                    ++i;
                } else if (c2 == 'n') {
                    c = '\n';
                    ++i;
                }
            }
            sb.append(c);
        }
        return sb.toString();
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

class StrToken extends Token {
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
