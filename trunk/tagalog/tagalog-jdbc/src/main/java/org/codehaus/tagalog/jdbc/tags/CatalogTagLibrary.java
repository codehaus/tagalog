/*
 * $Id: CatalogTagLibrary.java,v 1.3 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc.tags;

import org.codehaus.tagalog.AbstractTagLibrary;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public final class CatalogTagLibrary extends AbstractTagLibrary {
    public static final String NS_URI
        = "http://tagalog.codehaus.org/ns/jdbc-catalog";

    public CatalogTagLibrary() {
        registerTag("catalog", CatalogTag.class);
        registerTag("proc", ProcTag.class);
        registerTag("sequence", SequenceTag.class);
        registerTag("dialect-choice", DialectChoiceTag.class);
        registerTag("stmt", StatementTag.class);
        registerTag("query", StatementTag.class);
    }

}
