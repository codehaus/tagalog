/*
 * $Id: RecordAllPIHandler.java,v 1.1 2005-04-26 14:40:55 mhw Exp $
 */

package org.codehaus.tagalog.acceptance;

import java.util.List;
import java.util.Map;

import org.codehaus.tagalog.PIHandler;

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
 * @version $Revision: 1.1 $
 */
public class RecordAllPIHandler
    extends AbstractPIHandler
    implements PIHandler
{
    /**
     * @see org.codehaus.tagalog.PIHandler#processingInstruction(java.lang.String, java.lang.String)
     */
    public void processingInstruction(String target, String data) {
        Map piContext = getOrCreatePIContextMap();
        List dataList;

        dataList = (List) piContext.get(target);
        if (dataList == null) {
            dataList = new java.util.LinkedList();
            piContext.put(target, dataList);
        }
        dataList.add(data);
    }
}
