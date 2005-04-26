/*
 * $Id: AbstractPIHandler.java,v 1.1 2005-04-26 14:40:55 mhw Exp $
 */

package org.codehaus.tagalog.acceptance;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.tagalog.AbstractNodeHandler;
import org.codehaus.tagalog.PIHandler;

/**
 * An abstract implementation of {@link PIHandler} that handles the storage and
 * access of the PIs into a map stored in the parser context under the
 * <code>piContextKey</code> supplied to the constructor.
 *
 * @author Kristopher Brown
 * @version $Revision: 1.1 $ $Date: 2005-04-26 14:40:55 $
 */
public abstract class AbstractPIHandler
    extends AbstractNodeHandler
    implements PIHandler
{
    /**
     * The key under which the piContext is held in the parser context
     */
    public static final String piContextKey = "processing-instructions";

    /**
     * Returns a context suitable for storing PIs in.  If a {@link Map} is
     * present under the <code>piContextKey</code> then that {@link Map} is
     * used, otherwise a {@link HashMap} is created and stored in the parser
     * context and used instead. If another {@link Object} other than a
     * {@link Map} is stored in the parser context then an
     * {@link IllegalStateException} is thrown.
     *
     * @return a context suitable for storing PIs in
     * @throws IllegalStateException if the object stored under
     *         <code>piContextKey</code> is not a {@link Map}
     */
    protected final Map getOrCreatePIContextMap() {
        Map context = getContext();
        Object value = context.get(piContextKey);
        if (value != null &&
                ! (value instanceof Map)) {
            throw new IllegalStateException("invalid object for pi context" +
                    " - must be an implementation of Map");
        }

        Map piContext = (Map) value;
        if (piContext == null) {
            piContext = new HashMap();
            context.put(piContextKey, piContext);
        }
        return piContext;
    }
}
