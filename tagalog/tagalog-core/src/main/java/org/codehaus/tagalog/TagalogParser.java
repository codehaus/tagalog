/*
 * $Id: TagalogParser.java,v 1.1 2004-02-10 18:56:05 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * <code>TagalogParser</code> defines the common interface for all Tagalog
 * parsers. This amounts to a single method to trigger the actual parse.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public interface TagalogParser {
    void parse() throws Exception;
}
