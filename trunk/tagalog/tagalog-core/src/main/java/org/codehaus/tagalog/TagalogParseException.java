/*
 * $Id: TagalogParseException.java,v 1.5 2005-05-17 13:22:46 krisb Exp $
 */

package org.codehaus.tagalog;

/**
 * <code>TagalogParseException</code> is used to wrap any exceptions
 * thrown by the underlying XML parser.
 *
 * @see TagException
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.5 $
 */
public class TagalogParseException extends Exception {
    public TagalogParseException(Throwable cause) {
        super(cause);
    }
}
