/*
 * $Id: TagalogParser.java,v 1.2 2004-02-11 12:42:59 mhw Exp $
 */

package org.codehaus.tagalog;

import java.util.Map;

/**
 * <code>TagalogParser</code> defines the common interface for all Tagalog
 * parsers. This amounts to a couple of methods to trigger the actual parse.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public interface TagalogParser {
    /**
     * Perform the parse, using an empty <code>Map</code> to hold the
     * parse context.
     *
     * @return The object returned by the {@link Tag#end()} method of the
     * root element.
     *
     * @throws TagalogParseException if there is some problem parsing the
     * content.
     */
    Object parse() throws TagalogParseException;

    /**
     * Perform the parse, using the supplied <code>Map</code> to hold the
     * parse context.
     *
     * @param context A map used to hold shared information during the
     * parse.
     *
     * @return The object returned by the {@link Tag#end()} method of the
     * root element.
     *
     * @throws TagalogParseException if there is some problem parsing the
     * content.
     */
    Object parse(Map context) throws TagalogParseException;
}
