package in.liub.stone_java;

import java.io.StringReader;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LexerTest {
    @Test
    public void emptyInput() {
        Lexer lexer = new Lexer(new StringReader(""));
        assertThat(lexer.read(), is(Token.EOF));
    }
}
