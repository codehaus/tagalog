/*
 * $Id: RecordAllPIHandler.java,v 1.2 2004-04-11 17:31:51 mhw Exp $
 */

package org.codehaus.tagalog.pi;

import java.util.List;
import java.util.Map;

/**
 * A processing instruction handler that records all the data provided
 * to each processing instruction target in a document.
 * Each processing instruction target results in a context mapping from a
 * {@link PIHashKey} to a {@link List} of {@link String} data values.
 * Processing instruction data is added to end of the list,
 * so each list contains processing instruction data in the order it was
 * encountered in the document.
 *
 * <p>
 * The approach of using <code>PIHashKey</code> values to distinguish
 * processing instructions from other data in the context <code>Map</code>
 * requires that the context is held in a <code>Map</code>
 * that supports keys of different types, such as {@link java.util.HashMap}.
 * It does not work correctly with {@link java.util.TreeMap} because the keys
 * are not comparable to each other.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class RecordAllPIHandler implements PIHandler {
    public static final RecordAllPIHandler INSTANCE = new RecordAllPIHandler();

    /**
     * Protected constructor to prevent instantiation but allow
     * extension.
     */
    protected RecordAllPIHandler() {
    }

    public void processingInstruction(String target, String data, Map context) {
        PIHashKey piKey = new PIHashKey(target);
        List dataList;

        dataList = (List) context.get(piKey);
        if (dataList == null) {
            dataList = new java.util.LinkedList();
            context.put(piKey, dataList);
        }
        dataList.add(data);
    }
}
