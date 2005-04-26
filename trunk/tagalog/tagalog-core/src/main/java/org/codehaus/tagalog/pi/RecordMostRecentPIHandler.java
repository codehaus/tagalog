/*
 * $Id: RecordMostRecentPIHandler.java,v 1.6 2005-04-26 14:28:31 mhw Exp $
 */

package org.codehaus.tagalog.pi;

import java.util.Map;

import org.codehaus.tagalog.PIHandler;

/**
 * A processing instruction handler that records the most recent data
 * provided to each processing instruction target in a document.
 * Each processing instruction target results in a context mapping from the
 * {@link String} target to the most recent {@link String} data value.
 * Subsequent processing instructions with the same target will
 * replace earlier ones.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @author Kristopher Brown
 * @version $Revision: 1.6 $
 */
public class RecordMostRecentPIHandler
    extends AbstractPIHandler
    implements PIHandler
{
    /**
     * @see org.codehaus.tagalog.PIHandler#processingInstruction(java.lang.String, java.lang.String)
     */
    public void processingInstruction(String target, String data) {
        Map piContext = getOrCreatePIContextMap();
        piContext.put(target, data);
    }
}
