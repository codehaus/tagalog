/*
 * $Id: BracketedExpressionParserTest.java,v 1.2 2004-10-28 13:35:17 mhw Exp $
 */

package org.codehaus.tagalog.el;

import java.util.Map;

import junit.framework.TestCase;

/**
 * BracketedExpressionParserTest
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class BracketedExpressionParserTest extends TestCase {
    private BracketedExpressionParser parser;

    private Map context = new java.util.HashMap();

    protected void setUp() throws Exception {
        super.setUp();
        parser = (BracketedExpressionParser)
                                ParseController.DEFAULT.findByName(ParseController.COMPOUND);

        context.put("foo", "foo");
        context.put("bar", "bar");
        context.put("$", "dollar");
        context.put("{", "open");
        context.put("}", "close");
        context.put("\\", "back slash");
    }

    public void testMetaAndStartChars() {
        BracketedExpressionParser p;

        p = new BracketedExpressionParser(ParseController.DEFAULT, '{', '}');
        p.addExpressionParser('$', ParseController.STANDARD);
        assertEquals("\\{}$", p.getMetaChars());
        assertEquals("$", p.getStartChars());

        p.addExpressionParser('?', "fred");
        assertTrue(p.getMetaChars().startsWith("\\{}"));
        assertEquals(5, p.getMetaChars().length());
        assertEquals(2, p.getStartChars().length());
        assertTrue(p.getStartChars().indexOf('$') != -1);
        assertTrue(p.getStartChars().indexOf('?') != -1);
    }

    void assertEvalEqual(String expected, String expression)
        throws ExpressionParseException, ExpressionEvaluationException
    {
        Expression e = parser.parse(expression);
        assertEquals(expected, e.evaluate(context));
    }

    public void testParse() throws Exception {
        assertEvalEqual("hello world", "hello world");
        assertEvalEqual("hello foo", "hello ${foo}");
        assertEvalEqual("hello foo world", "hello ${foo} world");
        assertEvalEqual("hello foowofoorld", "hello ${foo}wo${foo}rld");
        assertEvalEqual("foo hello wofoorld", "${foo} hello wo${foo}rld");

        // check meta characters can be used in expressions
        assertEvalEqual("dollar", "${$}");
        assertEvalEqual("open", "${{}");
        assertEvalEqual("close", "${\\}}");
        assertEvalEqual("back slash", "${\\\\}");

        // check meta characters can be used as literals
        assertEvalEqual("${foo}", "$\\{foo}");
        assertEvalEqual("${foo}", "\\${foo}");
        assertEvalEqual("${foo}", "\\$\\{foo\\}");
        assertEvalEqual("fred \\ hello", "fred \\ hello");
        assertEvalEqual("${{}", "$\\{{}");

        // check Ant compatability
        assertEvalEqual("foo$", "foo$$");
        assertEvalEqual("foo$fred", "foo$$fred");
        assertEvalEqual("foo$", "foo$");
        assertEvalEqual("foo$fred", "foo$fred");
        assertEvalEqual("hello ${foo}", "hello $${foo}");

        // check corner cases
        assertEvalEqual("", "");
        assertEvalEqual("\\", "\\");
        assertEvalEqual("abc\\", "abc\\");
        assertEvalEqual("$", "$");
        assertEvalEqual("abc$", "abc$");

        // check the returned objects are optimal
        assertTrue(parser.parse("") instanceof ConstantExpression);
        assertTrue(parser.parse("hello") instanceof ConstantExpression);
        assertTrue(parser.parse("${foo}") instanceof ContextValueExpression);
        assertTrue(parser.parse(" ${foo}") instanceof ConcatenationExpression);

        // check error reporting
        try {
            parser.parse("hello ${foo");
            fail("no parse error");
        } catch (ExpressionParseException e) {
            assertEquals("could not find expression close character '}' in 'hello ${foo'", e.getMessage());
        }
        try {
            parser.parse("hello ${foo\\} there");
            fail("no parse error");
        } catch (ExpressionParseException e) {
            assertEquals("could not find expression close character '}' in 'hello ${foo\\} there'", e.getMessage());
        }
    }

    public void testCOWBuffer() {
        BracketedExpressionParser.COWBuffer buf;
        String source = "123 abc def";

        buf = new BracketedExpressionParser.COWBuffer(source, 0);
        assertEquals("", buf.getSpan());

        assertEquals('1', buf.getChar());
        buf.put('1');
        assertEquals('2', buf.getChar());
        buf.put('2');
        assertEquals('3', buf.getChar());
        buf.put('3');
        assertEquals(' ', buf.getChar());
        assertEquals("123", buf.getSpan());

        assertEquals('a', buf.getChar());
        buf.put('a');
        assertEquals('b', buf.getChar());
        buf.put('b');
        assertEquals('c', buf.getChar());
        buf.put('c');
        assertEquals(' ', buf.getChar());
        buf.put('d');
        assertEquals("abcd", buf.getSpan());

        assertEquals('d', buf.getChar());
        buf.put('d');
        assertEquals('e', buf.getChar());
        buf.put('e');
        assertEquals('f', buf.getChar());
        buf.put('f');
        assertEquals(-1, buf.getChar());
        buf.put('o');
        buf.put('o');
        assertEquals("defoo", buf.getSpan());
    }
}
