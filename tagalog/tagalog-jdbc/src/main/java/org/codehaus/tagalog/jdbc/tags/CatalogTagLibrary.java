/*
 * $Id: CatalogTagLibrary.java,v 1.2 2004-02-26 12:32:26 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc.tagalog;

import org.codehaus.tagalog.AbstractTagLibrary;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public final class CatalogTagLibrary extends AbstractTagLibrary {
    public static final String NS_URI
        = "http://eng.fintricity.com/ns/jdbc-catalog";

    public CatalogTagLibrary() {
        registerTag("catalog", CatalogTag.class);
        registerTag("proc", ProcTag.class);
        registerTag("sequence", SequenceTag.class);
        registerTag("dialect-choice", DialectChoiceTag.class);
        registerTag("stmt", StatementTag.class);
        registerTag("query", StatementTag.class);
    }

}
