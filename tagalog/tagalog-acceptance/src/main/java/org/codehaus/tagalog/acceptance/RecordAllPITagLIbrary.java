/*
 * $Id: RecordAllPITagLIbrary.java,v 1.1 2005-04-26 14:37:48 mhw Exp $
 */

package org.codehaus.tagalog.acceptance;

import org.codehaus.tagalog.AbstractTagLibrary;
import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.pi.RecordAllPIHandler;

/**
 * RecordMostRecentPITagLIbrary
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class RecordAllPITagLIbrary extends AbstractTagLibrary {
    public static final TagBinding WILDCARD = new TagBinding("*", RecordAllPIHandler.class);

    public RecordAllPITagLIbrary() {
        registerTagBinding(WILDCARD);
    }
}
