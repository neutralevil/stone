package in.liub.stone_java;

import java.io.StringReader;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LexerTest {
    @Test
    public void emptyInput() {
        Lexer lexer = new Lexer(new StringReader(""));
        assertThat(lexer.read(), is(Token.EOF));
    }

    @Test
    public void singleNumber() {
        Lexer lexer = new Lexer(new StringReader("7531"));
        Token t = lexer.read();

        assertTrue(t.isNumber());
        assertEquals(7531, t.getNumber());
    }
}
