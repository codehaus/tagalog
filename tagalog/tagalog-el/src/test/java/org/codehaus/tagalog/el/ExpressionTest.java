/*
 * $Id: ExpressionTest.java,v 1.1 2005-04-20 11:10:12 mhw Exp $
 */

package org.codehaus.tagalog.el;

import java.util.HashMap;

import junit.framework.TestCase;

/**
 * ExpressionTest
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class ExpressionTest extends TestCase {

    public void testNullExpression() throws Exception {
        assertNull(Expression.NULL.evaluate(new HashMap()));
    }

    public void testEmptyStringExpression() throws Exception {
        assertEquals("", Expression.EMPTY_STRING.evaluate(new HashMap()));
    }
}
