/*
 * $Id: ConcatenationExpressionTest.java,v 1.1 2004-10-28 16:06:18 mhw Exp $
 */

package org.codehaus.tagalog.el;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

/**
 * Test {@link ConcatenationExpression}.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class ConcatenationExpressionTest extends TestCase {
    private static final Map EMPTY_MAP = new java.util.TreeMap();

    private static final List EMPTY_LIST = new java.util.ArrayList();

    private ConcatenationExpression exp;

    public void testConstructionFromArray() throws Exception {
        try {
            exp = new ConcatenationExpression((Expression[]) null);
            fail("should have thrown NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        exp = new ConcatenationExpression(new Expression[0]);
        assertEquals("", exp.evaluate(EMPTY_MAP));

        exp = new ConcatenationExpression(new Expression[] {
                new ConstantExpression("hello"),
                new ConstantExpression(", "),
                new ConstantExpression("world"),
        });
        assertEquals("hello, world", exp.evaluate(EMPTY_MAP));
    }

    public void testConstructionFromList() throws Exception {
        try {
            exp = new ConcatenationExpression((List) null);
            fail("should have thrown NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        exp = new ConcatenationExpression(EMPTY_LIST);
        assertEquals("", exp.evaluate(EMPTY_MAP));

        exp = new ConcatenationExpression(Arrays.asList(new Expression[] {
                new ConstantExpression("hello"),
                new ConstantExpression(", "),
                new ConstantExpression("world"),
        }));
        assertEquals("hello, world", exp.evaluate(EMPTY_MAP));
    }
}
