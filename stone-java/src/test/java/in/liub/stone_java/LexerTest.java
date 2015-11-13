package in.liub.stone_java;

import java.io.StringReader;
import java.util.ArrayList;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class LexerTest {
    @Test
    public void emptyInput() {
        assertThat(tokens(""), is(theOnly(Token.EOF)));
    }

    @Test
    public void singleNumber() {
        assertThat(tokensExceptEOF("7531"), is(theOnly(number(7531))));
    }

    @Test
    public void singleIdentifier() {
        assertThat(tokensExceptEOF("test"), is(theOnly(identifier("test"))));
    }

    @Test
    public void simpleString() {
        assertThat(tokensExceptEOF("\"a string\""),
                   is(theOnly(string("a string"))));
    }

    @Test
    public void stringWithEscape() {
        assertThat(tokensExceptEOF("\"\\nA\\\\B\\\"\""),
                   is(theOnly(string("\nA\\B\""))));
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

    private static TheOnlyItem theOnly(Token token) {
        return new TheOnlyItem(equalTo(token));
    }

    private static TheOnlyItem theOnly(Matcher<Token> matcher) {
        return new TheOnlyItem(matcher);
    }

    private static Matcher<Token> number(int num) {
        return new TypeSafeDiagnosingMatcher<Token>() {
            @Override
            protected boolean matchesSafely(Token item,
                                            Description mismatchDescription) {
                return item.isNumber() && item.getNumber() == num;
            }

            @Override
            public void describeTo(Description description) {
                description.appendValue(num);
            }
        };
    }

    private static Matcher<Token> identifier(String text) {
        return new TypeSafeDiagnosingMatcher<Token>() {
            @Override
            protected boolean matchesSafely(Token item, Description mismatchDescription) {
                return item.isIdentifier() && item.getText().equals(text);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(text);
            }
        };
    }

    private static Matcher<Token> string(String str) {
        return new TypeSafeDiagnosingMatcher<Token>() {
            @Override
            protected boolean matchesSafely(Token item, Description mismatchDescription) {
                return item.isString() && item.getText().equals(str);
            }

            @Override
            public void describeTo(Description description) {
                description.appendValue(str);
            }
        };
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
        description.appendDescriptionOf(matcher);
    }
}
