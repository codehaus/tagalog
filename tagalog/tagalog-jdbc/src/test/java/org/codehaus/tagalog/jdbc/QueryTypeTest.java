/*
 * $Id: QueryTypeTest.java,v 1.2 2004-01-30 17:48:58 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import junit.framework.TestCase;

/**
 * Simple tests for the {@link QueryType} class.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
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
            QueryType qt = QueryType.fromString(null);
            fail("fromString should throw exception");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            QueryType qt = QueryType.fromString("bogus");
            fail("fromString should throw exception");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

}
