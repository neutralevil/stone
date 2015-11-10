package in.liub.stone_java;

import java.io.StringReader;
import java.util.ArrayList;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LexerTest {
    @Test
    public void emptyInput() {
        assertThat(tokens(""), theOnlyItem(Token.EOF));
    }

    @Test
    public void singleNumber() {
        Lexer lexer = new Lexer(new StringReader("7531"));
        Token t = lexer.read();

        assertTrue(t.isNumber());
        assertEquals(7531, t.getNumber());
    }

    @Test
    public void singleIdentifier() {
        Lexer lexer = new Lexer(new StringReader("test"));
        Token t = lexer.read();

        assertTrue(t.isIdentifier());
        assertEquals("test", t.getText());
    }

    private ArrayList<Token> tokens(String input) {
        return tokens(input, false);
    }

    private ArrayList<Token> tokensExceptEOF(String input) {
        return tokens(input, true);
    }

    private ArrayList<Token> tokens(String input, boolean ignoreEOF) {
        ArrayList<Token> ret = new ArrayList<>();
        Token t;
        Lexer lexer = new Lexer(new StringReader(input));
        do {
            t = lexer.read();
            if (t == Token.EOF && ignoreEOF)
                continue;
            ret.add(t);
        } while (t != Token.EOF);

        return ret;
    }

    private static TheOnlyItem theOnlyItem(Token token) {
        return new TheOnlyItem(equalTo(token));
    }

}

class TheOnlyItem extends TypeSafeDiagnosingMatcher<ArrayList<Token>> {
    private final Matcher<Token> matcher;

    public TheOnlyItem(Matcher<Token> matcher) {
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(ArrayList<Token> item,
                                    Description mismatchDescription) {
        boolean match = item.size() == 1 && matcher.matches(item.get(0));
        if (!match)
            matcher.describeMismatch(item, mismatchDescription);
        return match;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is ")
                .appendDescriptionOf(matcher);
    }
}
