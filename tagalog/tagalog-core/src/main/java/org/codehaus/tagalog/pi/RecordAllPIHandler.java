/*
 * $Id: RecordAllPIHandler.java,v 1.3 2004-11-17 14:20:34 krisb Exp $
 */

package org.codehaus.tagalog.pi;

import java.util.List;
import java.util.Map;

/**
 * A processing instruction handler that records all the data provided
 * to each processing instruction target in a document.
 * Each processing instruction target results in a context mapping from a
 * {@link String} to a {@link List} of {@link String} data values.
 * Processing instruction data is added to end of the list,
 * so each list contains processing instruction data in the order it was
 * encountered in the document.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @author Kristopher Brown
 * @version $Revision: 1.3 $
 */
public class RecordAllPIHandler extends AbstractPIHandler 
        implements PIHandler {

    /**
     * Creates an instance of {@link RecordAllPIHandler}, using the supplied 
     * <code>piContextKey</code> to store the pi context in the parser context.
     * @param piContextKey the key to store the pi context in the parser context
     */
    public RecordAllPIHandler(String piContextKey) {
        super(piContextKey);
    }

    /**
     * @see org.codehaus.tagalog.pi.PIHandler#processingInstruction(java.lang.String, java.lang.String, java.util.Map)
     */
    public void processingInstruction(String target, String data, Map context) {
        Map piContext = getOrCreatePIContextMap(context);
        List dataList;

        dataList = (List) piContext.get(target);
        if (dataList == null) {
            dataList = new java.util.LinkedList();
            piContext.put(target, dataList);
        }
        dataList.add(data);
    }
    
}
