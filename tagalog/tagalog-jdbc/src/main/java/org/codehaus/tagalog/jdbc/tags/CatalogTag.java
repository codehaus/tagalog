/*
 * $Id: CatalogTag.java,v 1.2 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc.tags;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;

import org.codehaus.tagalog.jdbc.Catalog;
import org.codehaus.tagalog.jdbc.Proc;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
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
