/*
 * $Id: ConverterManagerTest.java,v 1.2 2005-03-01 10:33:52 mhw Exp $
 */

package org.codehaus.tagalog.conv;

import junit.framework.TestCase;

/**
 * Tests for {@link ConverterManager}.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class ConverterManagerTest extends TestCase {

    public void testConverterManager() {
        ConverterManager m = new ConverterManager();

        assertNull(m.getConverter(String.class));

        m.registerConverter(String.class, new MockConverter());
        assertNotNull(m.getConverter(String.class));

        try {
            m.registerConverter(String.class, null);
            fail();
        } catch (NullPointerException e) {
            // expected
        }

        try {
            m.registerConverter(null, new MockConverter());
            fail();
        } catch (NullPointerException e) {
            // expected
        }
    }

    public void testCanonicalisation() {
        assertCanonical(String.class, String.class);
        assertCanonical(Void.class, Void.class);
        assertCanonical(Void.class, Void.TYPE);
        assertCanonical(Boolean.class, Boolean.class);
        assertCanonical(Boolean.class, Boolean.TYPE);
        assertCanonical(Byte.class, Byte.class);
        assertCanonical(Byte.class, Byte.TYPE);
        assertCanonical(Character.class, Character.class);
        assertCanonical(Character.class, Character.TYPE);
        assertCanonical(Short.class, Short.class);
        assertCanonical(Short.class, Short.TYPE);
        assertCanonical(Integer.class, Integer.class);
        assertCanonical(Integer.class, Integer.TYPE);
        assertCanonical(Long.class, Long.class);
        assertCanonical(Long.class, Long.TYPE);
        assertCanonical(Float.class, Float.class);
        assertCanonical(Float.class, Float.TYPE);
        assertCanonical(Double.class, Double.class);
        assertCanonical(Double.class, Double.TYPE);

        try {
            ConverterManager.canonicalClass(null);
            fail();
        } catch (NullPointerException e) {
            // expected
        }
    }

    private void assertCanonical(Class expected, Class argument) {
        assertEquals(expected, ConverterManager.canonicalClass(argument));
    }
}
