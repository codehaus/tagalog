/*
 * $Id: TagalogParseException.java,v 1.2 2004-02-20 18:36:05 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * TagalogParseException
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class TagalogParseException extends Exception {
    public TagalogParseException() {
        super();
    }

    public TagalogParseException(String message) {
        super(message);
    }

    public TagalogParseException(Throwable cause) {
        super(cause);
    }

    public TagalogParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
