/*
 * $Id: CatalogTagLibrary.java,v 1.5 2005-04-07 15:52:59 mhw Exp $
 */

package org.codehaus.tagalog.jdbc.tags;

import org.codehaus.tagalog.AbstractTagLibrary;
import org.codehaus.tagalog.TagBinding;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.5 $
 */
public final class CatalogTagLibrary extends AbstractTagLibrary {
    public static final String NS_URI
        = "http://tagalog.codehaus.org/ns/jdbc-catalog";

    public static final TagBinding CATALOG        = new TagBinding("catalog",
                                                                   CatalogTag.class);
    public static final TagBinding PROC           = new TagBinding("proc",
                                                                   ProcTag.class);
    public static final TagBinding SEQUENCE       = new TagBinding("sequence",
                                                                   SequenceTag.class);
    public static final TagBinding DIALECT_CHOICE = new TagBinding("dialect-choice",
                                                                   DialectChoiceTag.class);
    public static final TagBinding STMT           = new TagBinding("stmt",
                                                                   StatementTag.class);
    public static final TagBinding QUERY          = new TagBinding("query",
                                                                   StatementTag.class);
    public static final TagBinding UPDATE         = new TagBinding("update",
                                                                   StatementTag.class);

    public CatalogTagLibrary() {
        registerTagBinding(CATALOG);
        registerTagBinding(PROC);
        registerTagBinding(SEQUENCE);
        registerTagBinding(DIALECT_CHOICE);
        registerTagBinding(STMT);
        registerTagBinding(QUERY);
        registerTagBinding(UPDATE);
    }
}
