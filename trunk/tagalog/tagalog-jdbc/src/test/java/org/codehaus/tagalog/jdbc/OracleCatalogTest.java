/*
 * $Id: OracleCatalogTest.java,v 1.10 2004-09-22 15:54:18 mhw Exp $
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
 * @version $Revision: 1.10 $
 */
public final class OracleCatalogTest extends AbstractCatalogTest {
    private static final String PLEXUS_CONF = "OracleSQLTest.xml";

    public InputStream getCustomConfiguration() throws Exception {
        return getResourceAsStream(PLEXUS_CONF);
    }
}
