/*
 * $Id: RecordAllPITagLibrary.java,v 1.1 2005-05-18 13:05:19 mhw Exp $
 */

package org.codehaus.tagalog.acceptance;

import org.codehaus.tagalog.AbstractTagLibrary;
import org.codehaus.tagalog.TagBinding;

/**
 * RecordMostRecentPITagLibrary
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class RecordAllPITagLibrary extends AbstractTagLibrary {
    public static final TagBinding WILDCARD =
            new TagBinding("*", RecordAllPIHandler.class);

    public RecordAllPITagLibrary() {
        registerTagBinding(WILDCARD);
    }
}
