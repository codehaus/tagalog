/*
 * $Id: PropertySetterTest.java,v 1.2 2005-03-30 11:41:01 mhw Exp $
 */

package org.codehaus.tagalog.conv;

import java.net.URL;

import junit.framework.TestCase;

/**
 * PropertySetterTest
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class PropertySetterTest extends TestCase {

    public void testConstructor() {
        PropertySetter p = new PropertySetter(Bean.class);
        assertEquals(Bean.class, p.getTargetClass());

        try {
            new PropertySetter(null);
            fail();
        } catch (NullPointerException e) {
            // expected behaviour
        }
    }

    public void testPropertyIntrospection() {
        PropertySetter p = new PropertySetter(Bean.class);

        assertEquals(Boolean.TYPE, p.getPropertyClass("booleanValue"));
        assertEquals(Byte.TYPE, p.getPropertyClass("byteValue"));
        assertEquals(Short.TYPE, p.getPropertyClass("shortValue"));
        assertEquals(Integer.TYPE, p.getPropertyClass("integerValue"));
        assertEquals(Long.TYPE, p.getPropertyClass("longValue"));
        assertEquals(Character.TYPE, p.getPropertyClass("charValue"));
        assertEquals(URL.class, p.getPropertyClass("URLValue"));

        p = new PropertySetter(ChildBean.class);

        assertEquals(Boolean.TYPE, p.getPropertyClass("booleanValue"));
        assertEquals(Byte.TYPE, p.getPropertyClass("byteValue"));
        assertEquals(Short.TYPE, p.getPropertyClass("shortValue"));
        assertEquals(Integer.TYPE, p.getPropertyClass("integerValue"));
        assertEquals(Long.TYPE, p.getPropertyClass("longValue"));
        assertEquals(Character.TYPE, p.getPropertyClass("charValue"));
        assertEquals(URL.class, p.getPropertyClass("URLValue"));

        p = new PropertySetter(DuplicatePropertyBean.class);

        assertEquals(String.class, p.getPropertyClass("intValue"));
    }

    public void testNonExistentProperty() throws Exception {
        PropertySetter p = new PropertySetter(Bean.class);
        Bean b = new Bean();

        try {
            p.setProperty(b, "randomProperty", "hello");
            fail();
        } catch (NoSuchPropertyException e) {
            assertEquals(e.getPropertyName(), "randomProperty");
        }
    }

    public void testSetBooleanProperty() throws Exception {
        PropertySetter p = new PropertySetter(Bean.class);
        Bean b = new Bean();

        p.setProperty(b, "booleanValue", "true");
        assertEquals(true, b.getBooleanValue());
        p.setProperty(b, "booleanValue", "on");
        assertEquals(true, b.getBooleanValue());
        p.setProperty(b, "booleanValue", "yes");
        assertEquals(true, b.getBooleanValue());

        p.setProperty(b, "booleanValue", "false");
        assertEquals(false, b.getBooleanValue());
        p.setProperty(b, "booleanValue", "off");
        assertEquals(false, b.getBooleanValue());
        p.setProperty(b, "booleanValue", "no");
        assertEquals(false, b.getBooleanValue());

        try {
            p.setProperty(b, "booleanValue", "bogus");
            fail();
        } catch(ConverterException e) {
            // expected
        }

        p.setProperty(b, "booleanObjectValue", "true");
        assertEquals(true, b.getBooleanValue());
        p.setProperty(b, "booleanObjectValue", "on");
        assertEquals(true, b.getBooleanValue());
        p.setProperty(b, "booleanObjectValue", "yes");
        assertEquals(true, b.getBooleanValue());

        p.setProperty(b, "booleanObjectValue", "false");
        assertEquals(false, b.getBooleanValue());
        p.setProperty(b, "booleanObjectValue", "off");
        assertEquals(false, b.getBooleanValue());
        p.setProperty(b, "booleanObjectValue", "no");
        assertEquals(false, b.getBooleanValue());

        try {
            p.setProperty(b, "booleanObjectValue", "bogus");
            fail();
        } catch(ConverterException e) {
            // expected
        }
    }

    public void testSetByteProperty() throws Exception {
        PropertySetter p = new PropertySetter(Bean.class);
        Bean b = new Bean();

        p.setProperty(b, "byteValue", "42");
        assertEquals(42, b.getByteValue());
        p.setProperty(b, "byteValue", "-42");
        assertEquals(-42, b.getByteValue());

        try {
            p.setProperty(b, "byteValue", "bogus");
            fail();
        } catch(ConverterException e) {
            // expected
        }

        p.setProperty(b, "byteObjectValue", "42");
        assertEquals(42, b.getByteValue());
        p.setProperty(b, "byteObjectValue", "-42");
        assertEquals(-42, b.getByteValue());

        try {
            p.setProperty(b, "byteObjectValue", "bogus");
            fail();
        } catch(ConverterException e) {
            // expected
        }
    }

    public void testSetShortProperty() throws Exception {
        PropertySetter p = new PropertySetter(Bean.class);
        Bean b = new Bean();

        p.setProperty(b, "shortValue", "42");
        assertEquals(42, b.getShortValue());
        p.setProperty(b, "shortValue", "-42");
        assertEquals(-42, b.getShortValue());

        try {
            p.setProperty(b, "shortValue", "bogus");
            fail();
        } catch(ConverterException e) {
            // expected
        }

        p.setProperty(b, "shortObjectValue", "42");
        assertEquals(42, b.getShortValue());
        p.setProperty(b, "shortObjectValue", "-42");
        assertEquals(-42, b.getShortValue());

        try {
            p.setProperty(b, "shortObjectValue", "bogus");
            fail();
        } catch(ConverterException e) {
            // expected
        }
    }

    public void testSetIntegerProperty() throws Exception {
        PropertySetter p = new PropertySetter(Bean.class);
        Bean b = new Bean();

        p.setProperty(b, "integerValue", "42");
        assertEquals(42, b.getIntegerValue());
        p.setProperty(b, "integerValue", "-42");
        assertEquals(-42, b.getIntegerValue());

        try {
            p.setProperty(b, "integerValue", "bogus");
            fail();
        } catch(ConverterException e) {
            // expected
        }

        p.setProperty(b, "integerObjectValue", "42");
        assertEquals(42, b.getIntegerValue());
        p.setProperty(b, "integerObjectValue", "-42");
        assertEquals(-42, b.getIntegerValue());

        try {
            p.setProperty(b, "integerObjectValue", "bogus");
            fail();
        } catch(ConverterException e) {
            // expected
        }
    }

    public void testSetLongProperty() throws Exception {
        PropertySetter p = new PropertySetter(Bean.class);
        Bean b = new Bean();

        p.setProperty(b, "longValue", "42");
        assertEquals(42, b.getLongValue());
        p.setProperty(b, "longValue", "-42");
        assertEquals(-42, b.getLongValue());

        try {
            p.setProperty(b, "longValue", "bogus");
            fail();
        } catch(ConverterException e) {
            // expected
        }

        p.setProperty(b, "longObjectValue", "42");
        assertEquals(42, b.getLongValue());
        p.setProperty(b, "longObjectValue", "-42");
        assertEquals(-42, b.getLongValue());

        try {
            p.setProperty(b, "longObjectValue", "bogus");
            fail();
        } catch(ConverterException e) {
            // expected
        }
    }

    public void testSetChildIntegerProperty() throws Exception {
        PropertySetter p = new PropertySetter(ChildBean.class);
        ChildBean b = new ChildBean();

        p.setProperty(b, "childIntegerValue", "42");
        assertEquals(42, b.getChildIntegerValue());
        p.setProperty(b, "integerValue", "42");
        assertEquals(84, b.getIntegerValue());
    }

    public void testSetDuplicateProperty() throws Exception {
        PropertySetter p = new PropertySetter(DuplicatePropertyBean.class);
        DuplicatePropertyBean b = new DuplicatePropertyBean();

        p.setProperty(b, "intValue", "1234");
        assertEquals(4, b.getIntValue());
    }
}
