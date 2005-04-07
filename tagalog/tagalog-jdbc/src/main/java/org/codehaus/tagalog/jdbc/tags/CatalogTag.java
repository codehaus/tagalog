/*
 * $Id: CatalogTag.java,v 1.3 2005-04-07 15:52:57 mhw Exp $
 */

package org.codehaus.tagalog.jdbc.tags;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagBinding;

import org.codehaus.tagalog.jdbc.Catalog;
import org.codehaus.tagalog.jdbc.Proc;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public final class CatalogTag extends AbstractTag {
    Catalog catalog;

    public void begin(String elementName, Attributes attributes) {
        catalog = (Catalog) getContext().get("catalog");
    }

    public void child(TagBinding childType, Object child) {
        catalog.addProc((Proc) child);
    }

    public boolean recycle() {
        catalog = null;
        return true;
    }
}
