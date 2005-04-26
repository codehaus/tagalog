/*
 * $Id: RecordAllPITagLIbrary.java,v 1.2 2005-04-26 14:40:55 mhw Exp $
 */

package org.codehaus.tagalog.acceptance;

import org.codehaus.tagalog.AbstractTagLibrary;
import org.codehaus.tagalog.TagBinding;

/**
 * RecordMostRecentPITagLIbrary
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class RecordAllPITagLIbrary extends AbstractTagLibrary {
    public static final TagBinding WILDCARD = new TagBinding("*", RecordAllPIHandler.class);

    public RecordAllPITagLIbrary() {
        registerTagBinding(WILDCARD);
    }
}
