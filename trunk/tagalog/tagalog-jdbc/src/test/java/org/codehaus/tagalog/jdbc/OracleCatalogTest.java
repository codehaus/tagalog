/*
 * $Id: OracleCatalogTest.java,v 1.9 2004-02-25 20:40:29 mhw Exp $
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
 * @version $Revision: 1.9 $
 */
public final class OracleCatalogTest extends AbstractCatalogTest {
    private static final String PLEXUS_CONF = "OracleSQLTest.xml";

    public OracleCatalogTest(String testName) {
        super(testName);
    }

    public InputStream getCustomConfiguration() throws Exception {
        return getResourceAsStream(PLEXUS_CONF);
    }
}
