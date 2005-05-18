/*
 * $Id: RecordMostRecentPITagLIbrary.java,v 1.3 2005-05-18 11:26:58 krisb Exp $
 */

package org.codehaus.tagalog.acceptance;

import org.codehaus.tagalog.AbstractTagLibrary;
import org.codehaus.tagalog.TagBinding;

/**
 * RecordMostRecentPITagLIbrary
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public final class RecordMostRecentPITagLIbrary extends AbstractTagLibrary {
    public static final TagBinding WILDCARD =
            new TagBinding("*", RecordMostRecentPIHandler.class);

    public RecordMostRecentPITagLIbrary() {
        registerTagBinding(WILDCARD);
    }
}
