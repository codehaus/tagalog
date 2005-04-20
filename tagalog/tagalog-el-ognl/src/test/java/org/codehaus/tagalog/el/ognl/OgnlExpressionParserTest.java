/*
 * $Id: OgnlExpressionParserTest.java,v 1.3 2005-04-20 15:57:01 mhw Exp $
 */

package org.codehaus.tagalog.el.ognl;

import java.util.Map;

import junit.framework.TestCase;
import ognl.OgnlException;

import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.el.ExpressionEvaluationException;
import org.codehaus.tagalog.el.ExpressionParseException;
import org.codehaus.tagalog.el.SimpleExpressionParser;

/**
 * Tests for {@link org.codehaus.tagalog.el.ognl.OgnlExpressionParser}
 * and {@link org.codehaus.tagalog.el.ognl.OgnlExpression}.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class OgnlExpressionParserTest extends TestCase {

    private SimpleExpressionParser parser;

    private Map context;

    protected void setUp() throws Exception {
        super.setUp();

        parser = new OgnlExpressionParser();

        context = new java.util.HashMap();
        context.put("one", new Integer(1));
        context.put("two", new Integer(2));
        context.put("three", new Integer(3));

        Person me = new Person();
        me.setUserId("mhw");
        me.setFirstName("Mark");
        me.setLastName("Wilkinson");
        context.put("me", me);
    }

    private Object evaluateOgnl(String text) throws Exception {
        Expression exp = parser.parse(text);
        return exp.evaluate(context);
    }

    public void testExceptionHandling() throws Exception {
        try {
            evaluateOgnl("some error");
            fail();
        } catch (ExpressionParseException e) {
            assertTrue(e.getCause() instanceof OgnlException);
        }

        try {
            evaluateOgnl("me.nonexistent");
            fail();
        } catch (ExpressionEvaluationException e) {
            assertTrue(e.getCause() instanceof OgnlException);
        }
    }

    private void assertNumericEquals(int expected, String text)
        throws Exception
    {
        Object result;
        Number number;

        result = evaluateOgnl(text);
        assertTrue(result instanceof Number);
        number = (Number) result;
        assertEquals(expected, number.intValue());
    }

    public void testNumeric() throws Exception {
        assertNumericEquals(2, "two");
        assertNumericEquals(7, "two * three + one");
    }

    private void assertStringEquals(String expected, String text)
        throws Exception
    {
        Object result;

        result = evaluateOgnl(text);
        assertTrue(result instanceof String);
        assertEquals(expected, result);
    }

    public void testString() throws Exception {
        assertStringEquals("mhw", "me.userId");

        assertStringEquals("Mark Wilkinson",
                           "me.firstName + ' ' + me.lastName");
    }

    private void assertBooleanEquals(boolean expected, String text)
        throws Exception
    {
        Object result;
        Boolean boole;

        result = evaluateOgnl(text);
        assertTrue(result instanceof Boolean);
        boole = (Boolean) result;
        assertEquals(expected, boole.booleanValue());
    }

    public void testBoolean() throws Exception {
        assertBooleanEquals(true, "me.userId == 'mhw'");

        assertBooleanEquals(false,
                           "me.firstName.length() == me.lastName.length()");
    }
}
