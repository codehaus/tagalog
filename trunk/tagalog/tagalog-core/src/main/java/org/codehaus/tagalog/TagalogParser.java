/*
 * $Id: TagalogParser.java,v 1.10 2005-05-17 12:54:13 krisb Exp $
 */

package org.codehaus.tagalog;

import java.util.Map;

/**
 * The common interface for all Tagalog parsers.
 * The interface has two clients: it is used by Tagalog client code
 * that is parsing documents, and it is also available to {@link Tag}
 * implementations through the {@link Tag#setParser} method.
 * We refer to these two parts of the interface as the <i>client interface</i>
 * and the <i>tag interface</i>.
 * Each method provided by the interface is intended for use by either
 * the client or a tag, as indicated in the method
 * documentation.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.10 $
 */
public interface TagalogParser {
    /**
     * Perform the parse, using an empty {@link java.util.Map} to hold the
     * parse context.
     * <p>
     * Part of the client interface.
     *
     * @return The object returned by the {@link Tag#end} method of the
     * root element.
     *
     * @throws TagalogParseException if there was a serious problem that
     * prevented the content from being parsed.
     */
    Object parse() throws TagalogParseException;

    /**
     * Perform the parse, using the supplied {@link java.util.Map} to hold the
     * parse context.
     * <p>
     * Part of the client interface.
     *
     * @param context A map used to hold shared information during the
     * parse.
     *
     * @return The object returned by the {@link Tag#end} method of the
     * root element.
     *
     * @throws TagalogParseException if there was a serious problem that
     * prevented the content from being parsed.
     */
    Object parse(Map context) throws TagalogParseException;

    /**
     * Return the parse errors that were collected during the parse. These
     * are the errors that are raised by tags during the normal parsing of
     * the content. They are tagged with the location in the source file
     * where the error occurred.
     * <p>
     * Part of the client interface.
     *
     * @return An array containing the parse errors, possibly empty.
     */
    ParseError[] parseErrors();

    /**
     * Return the location of the tag that is being parsed in the source
     * document.
     * <p>
     * Part of the tag interface.
     *
     * @return Location of the current tag.
     */
    Location getLocation();
    
    /**
     * Adds the supplied message to the list of parse errors at the current
     * parse location.
     * <p>
     * Part of the tag interface.
     * 
     * @param message the message to add for an error.
     */
    void addError(String message);
    
}
