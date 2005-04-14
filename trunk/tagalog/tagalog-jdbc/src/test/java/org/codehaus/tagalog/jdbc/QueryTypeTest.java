/*
 * $Id: QueryTypeTest.java,v 1.4 2005-04-14 13:51:11 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import junit.framework.TestCase;

/**
 * Simple tests for the {@link QueryType} class.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.4 $
 */
public class QueryTypeTest extends TestCase {

    private static final String[] types = {
        "zero", "zero-or-one", "one", "zero-or-more", "one-or-more"
    };

    public void testToString() {
        QueryType qt;

        for (int i = 0; i < types.length; i++) {
            qt = QueryType.fromString(types[i]);
            assertEquals(types[i], qt.toString());
        }
    }

    public void testGetMinimumRowCount() {
        assertEquals(0, QueryType.fromString("zero").getMinimumRowCount());
        assertEquals(0, QueryType.fromString("zero-or-one").getMinimumRowCount());
        assertEquals(1, QueryType.fromString("one").getMinimumRowCount());
        assertEquals(0, QueryType.fromString("zero-or-more").getMinimumRowCount());
        assertEquals(1, QueryType.fromString("one-or-more").getMinimumRowCount());
    }

    public void testFromString() {
        try {
            QueryType.fromString(null);
            fail("fromString should throw exception");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            QueryType.fromString("bogus");
            fail("fromString should throw exception");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

}
