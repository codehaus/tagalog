/*
 * $Id: CatalogTag.java,v 1.1 2004-02-11 19:03:21 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc.tagalog;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;

import com.fintricity.jdbc.Catalog;
import com.fintricity.jdbc.Proc;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public final class CatalogTag extends AbstractTag {
    Catalog catalog;

    public void begin(String elementName, Attributes attributes) {
        catalog = (Catalog) getContext().get("catalog");
    }

    public void child(Object child) {
        catalog.addProc((Proc) child);
    }

    public boolean recycle() {
        catalog = null;
        return true;
    }
}
