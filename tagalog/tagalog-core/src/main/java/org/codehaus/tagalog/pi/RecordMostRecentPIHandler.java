/*
 * $Id: RecordMostRecentPIHandler.java,v 1.2 2004-04-11 17:31:51 mhw Exp $
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
public class RecordMostRecentPIHandler implements PIHandler {
    public static final RecordMostRecentPIHandler INSTANCE
        = new RecordMostRecentPIHandler();

    /**
     * Protected constructor to prevent instantiation but allow
     * extension.
     */
    protected RecordMostRecentPIHandler() {
    }

    public void processingInstruction(String target, String data, Map context) {
        context.put(new PIHashKey(target), data);
    }
}
