/*
 * $Id: RecordMostRecentPIHandler.java,v 1.3 2004-11-17 14:20:34 krisb Exp $
 */

package org.codehaus.tagalog.pi;

import java.util.Map;

/**
 * A processing instruction handler that records the most recent data
 * provided to each processing instruction target in a document.
 * Each processing instruction target results in a context mapping from a
 * {@link PIHashKey} to the most recent {@link String} data value.
 * Subsequent processing instructions with the same target will
 * replace earlier ones.
 * 
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @author Kristopher Brown
 * @version $Revision: 1.3 $
 */
public class RecordMostRecentPIHandler extends AbstractPIHandler
        implements PIHandler {
    
    /**
     * Creates an instance of {@link RecordMostRecentPIHandler}, using the 
     * supplied <code>piContextKey</code> to store the pi context in the parser
     * context.
     * @param piContextKey the key to store the pi context in the parser context
     */
    public RecordMostRecentPIHandler(String piContextKey) {
        super(piContextKey);
    }

    /**
     * @see org.codehaus.tagalog.pi.PIHandler#processingInstruction(java.lang.String, java.lang.String, java.util.Map)
     */
    public void processingInstruction(String target, String data, Map context) {
        Map piContext = getOrCreatePIContextMap(context);
        piContext.put(target, data);
    }
}
