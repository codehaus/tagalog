/*
 * $Id: ConverterTest.java,v 1.1 2004-12-20 19:02:49 mhw Exp $
 */

package org.codehaus.tagalog.conv;

import junit.framework.TestCase;

/**
 * Tests for the converter framework.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class ConverterTest extends TestCase {
    private ConverterManager mgr;

    protected void setUp() {
        mgr = ConverterManager.INSTANCE;
    }

    public void testBoolean() throws ConverterException {
        Converter c = mgr.getConverter(Boolean.class);

        assertNotNull(c);

        assertEquals(Boolean.TRUE, c.convert("true"));
        assertEquals(Boolean.TRUE, c.convert("on"));
        assertEquals(Boolean.TRUE, c.convert("yes"));
        assertEquals(Boolean.TRUE, c.convert("  true  "));

        assertEquals(Boolean.FALSE, c.convert("false"));
        assertEquals(Boolean.FALSE, c.convert("off"));
        assertEquals(Boolean.FALSE, c.convert("no"));
        assertEquals(Boolean.FALSE, c.convert("  false  "));

        assertNull(c.convert(null));
        assertNull(c.convert(""));
        assertNull(c.convert("    "));

        try {
            c.convert("foo");
            fail();
        } catch (ConverterException e) {
            // expected
        }

        try {
            c.convert("123");
            fail();
        } catch (ConverterException e) {
            // expected
        }
    }

    public void testInteger() throws ConverterException {
        Converter c = mgr.getConverter(Integer.class);

        assertNotNull(c);

        Integer i = new Integer(42);

        assertEquals(i, c.convert("42"));
        assertEquals(i, c.convert(" 42 "));

        assertEquals(new Integer(-42), c.convert("-42"));
        assertEquals(new Integer(0), c.convert("0"));

        assertNull(c.convert(null));
        assertNull(c.convert(""));
        assertNull(c.convert("    "));

        try {
            c.convert("foo");
            fail();
        } catch (ConverterException e) {
            // expected
        }

        try {
            c.convert("123four");
            fail();
        } catch (ConverterException e) {
            // expected
        }

        assertEquals(new Integer(2147483647), c.convert("2147483647"));

        // too big for an int
        try {
            c.convert("2147483648");
            fail();
        } catch (ConverterException e) {
            // expected
        }

        assertEquals(new Integer(-2147483648), c.convert("-2147483648"));

        // too small for an int
        try {
            c.convert("-2147483649");
            fail();
        } catch (ConverterException e) {
            // expected
        }
    }

}
