/*
 * $Id: TagalogParseException.java,v 1.6 2005-05-18 10:28:40 krisb Exp $
 */

package org.codehaus.tagalog;

/**
 * <code>TagalogParseException</code> is used to wrap any exceptions
 * thrown by the underlying XML parser.
 * <p>
 * Cause is handled internally by this class to support pre J2SE 1.4.
 *
 * @see TagException
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.6 $
 */
public class TagalogParseException extends Exception {
    private final Throwable cause;

    public TagalogParseException(Throwable cause) {
        super();
        this.cause = cause;
    }

    /**
     * Returns the cause of this TagError.
     * @return the cause of this TagError
     * @see java.lang.Throwable#getCause()
     */
    public final Throwable getCause() {
        return cause;
    }
}
