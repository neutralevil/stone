package in.liub.stone_java;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private static Pattern pattern = Pattern.compile(
            "\\s*((?://.*)|" +
            "(?<num>[0-9]+)" +
            "|(?:\"(?<str>(\\\\n|\\\\\\\\|\\\\\"|[^\"])*)\")" +
            "|(?<id>[a-z_A-Z][a-z_A-Z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})" +
            ")");

    private final LineNumberReader reader;
    private ArrayList<Token> queue = new ArrayList<>();
    private boolean hasMoreLine = true;

    public Lexer(Reader reader) {
        this.reader = new LineNumberReader(reader);
    }

    public Token read() {
        if (fillQueue(0))
            return queue.remove(0);
        return Token.EOF;
    }

    public Token peek(int i) {
        if (fillQueue(i))
            return queue.get(i);
        return Token.EOF;
    }

    private boolean fillQueue(int index) {
        while (index >= queue.size() && hasMoreLine)
            readLine();

        return (index < queue.size());
    }

    private void readLine() {
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Unexpected", e);
        }

        if (line == null) {
            hasMoreLine = false;
            return;
        }

        Matcher matcher = pattern.matcher(line);
        while (matcher.find())
            addToken(matcher);
    }

    private void addToken(Matcher matcher) {
        String m = matcher.group(1);
        if (m != null) {
            if (matcher.group("num") != null)
                queue.add(Token.numberOf(Integer.parseInt(m)));
            else if (matcher.group("id") != null)
                queue.add(Token.identifierOf(m));
            else if (matcher.group("str") != null)
                queue.add(Token.stringOf(escaped(matcher.group("str"))));
        }
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
