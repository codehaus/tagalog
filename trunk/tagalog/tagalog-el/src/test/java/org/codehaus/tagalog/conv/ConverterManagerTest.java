/*
 * $Id: ConverterManagerTest.java,v 1.1 2004-12-20 19:02:49 mhw Exp $
 */

package org.codehaus.tagalog.conv;

import junit.framework.TestCase;

/**
 * Tests for {@link ConverterManager}.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
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
}
