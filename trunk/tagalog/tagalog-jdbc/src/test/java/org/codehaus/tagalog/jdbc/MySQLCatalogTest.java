/*
 * $Id: MySQLCatalogTest.java,v 1.8 2004-02-25 20:40:29 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.io.InputStream;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.8 $
 */
public final class MySQLCatalogTest extends AbstractCatalogTest {
    private static final String PLEXUS_CONF = "MySQLTest.xml";

    public MySQLCatalogTest(String testName) {
        super(testName);
    }

    public InputStream getCustomConfiguration() throws Exception {
        return getResourceAsStream(PLEXUS_CONF);
    }
}
