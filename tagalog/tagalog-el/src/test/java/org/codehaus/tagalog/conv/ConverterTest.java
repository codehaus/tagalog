/*
 * $Id: ConverterTest.java,v 1.4 2005-06-10 12:40:31 krisb Exp $
 */

package org.codehaus.tagalog.conv;

import junit.framework.TestCase;

/**
 * Tests for the converter framework.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.4 $
 */
public class ConverterTest extends TestCase {
    private ConverterManager mgr;

    protected void setUp() {
        mgr = ConverterManager.INSTANCE;
    }

    public void testBoolean() throws ConversionException {
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
        } catch (ConversionException e) {
            // expected
        }

        try {
            c.convert("123");
            fail();
        } catch (ConversionException e) {
            // expected
        }
    }

    public void testByte() throws ConversionException {
        Converter c = mgr.getConverter(Byte.class);

        assertNotNull(c);

        Byte b = new Byte((byte) 42);

        assertEquals(b, c.convert("42"));
        assertEquals(b, c.convert(" 42 "));

        assertEquals(new Byte((byte) -42), c.convert("-42"));
        assertEquals(new Byte((byte) 0), c.convert("0"));

        assertNull(c.convert(null));
        assertNull(c.convert(""));
        assertNull(c.convert("    "));

        try {
            c.convert("foo");
            fail();
        } catch (ConversionException e) {
            // expected
        }

        try {
            c.convert("123four");
            fail();
        } catch (ConversionException e) {
            // expected
        }

        assertEquals(new Byte((byte) 127), c.convert("127"));

        // too big for a byte
        try {
            c.convert("128");
            fail();
        } catch (ConversionException e) {
            // expected
        }

        assertEquals(new Byte((byte) -128), c.convert("-128"));

        // too small for a byte
        try {
            c.convert("-129");
            fail();
        } catch (ConversionException e) {
            // expected
        }
    }

    public void testShort() throws ConversionException {
        Converter c = mgr.getConverter(Short.class);

        assertNotNull(c);

        Short s = new Short((short) 42);

        assertEquals(s, c.convert("42"));
        assertEquals(s, c.convert(" 42 "));

        assertEquals(new Short((short) -42), c.convert("-42"));
        assertEquals(new Short((short) 0), c.convert("0"));

        assertNull(c.convert(null));
        assertNull(c.convert(""));
        assertNull(c.convert("    "));

        try {
            c.convert("foo");
            fail();
        } catch (ConversionException e) {
            // expected
        }

        try {
            c.convert("123four");
            fail();
        } catch (ConversionException e) {
            // expected
        }

        assertEquals(new Short((short) 32767), c.convert("32767"));

        // too big for a short
        try {
            c.convert("32768");
            fail();
        } catch (ConversionException e) {
            // expected
        }

        assertEquals(new Short((short) -32768), c.convert("-32768"));

        // too small for a short
        try {
            c.convert("-32769");
            fail();
        } catch (ConversionException e) {
            // expected
        }
    }

    public void testInteger() throws ConversionException {
        Converter c = mgr.getConverter(Integer.class);

        assertNotNull(c);

        Integer i = new Integer(42);

        assertEquals(i, c.convert("42"));
        assertEquals(i, c.convert(" 42 "));
        assertEquals(i, c.convert("00042"));
        assertEquals(i, c.convert("+42"));

        assertEquals(new Integer(-42), c.convert("-42"));
        assertEquals(new Integer(0), c.convert("0"));

        assertNull(c.convert(null));
        assertNull(c.convert(""));
        assertNull(c.convert("    "));

        try {
            c.convert("foo");
            fail();
        } catch (ConversionException e) {
            // expected
        }

        try {
            c.convert("123four");
            fail();
        } catch (ConversionException e) {
            // expected
        }

        try {
            c.convert("123-456");
            fail();
        } catch (ConversionException e) {
            // expected
        }

        try {
            c.convert("-123+456");
            fail();
        } catch (ConversionException e) {
            // expected
        }

        assertEquals(new Integer(2147483647), c.convert("2147483647"));

        // too big for an int
        try {
            c.convert("2147483648");
            fail();
        } catch (ConversionException e) {
            // expected
        }

        assertEquals(new Integer(-2147483648), c.convert("-2147483648"));

        // too small for an int
        try {
            c.convert("-2147483649");
            fail();
        } catch (ConversionException e) {
            // expected
        }
    }

    public void testLong() throws ConversionException {
        Converter c = mgr.getConverter(Long.class);

        assertNotNull(c);

        Long l = new Long(42);

        assertEquals(l, c.convert("42"));
        assertEquals(l, c.convert(" 42 "));

        assertEquals(new Long(-42), c.convert("-42"));
        assertEquals(new Long(0), c.convert("0"));

        assertNull(c.convert(null));
        assertNull(c.convert(""));
        assertNull(c.convert("    "));

        try {
            c.convert("foo");
            fail();
        } catch (ConversionException e) {
            // expected
        }

        try {
            c.convert("123four");
            fail();
        } catch (ConversionException e) {
            // expected
        }

        assertEquals(new Long(9223372036854775807L),
                     c.convert("9223372036854775807"));

        // too big for an int
        try {
            c.convert("9223372036854775808");
            fail();
        } catch (ConversionException e) {
            // expected
        }

        assertEquals(new Long(-9223372036854775808L),
                     c.convert("-9223372036854775808"));

        // too small for an int
        try {
            c.convert("-9223372036854775809");
            fail();
        } catch (ConversionException e) {
            // expected
        }
    }
}
