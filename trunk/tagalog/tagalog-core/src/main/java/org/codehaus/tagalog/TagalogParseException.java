/*
 * $Id: TagalogParseException.java,v 1.7 2005-06-07 16:39:28 krisb Exp $
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
 * @version $Revision: 1.7 $
 */
public class TagalogParseException extends Exception {
    private final Throwable cause;

    public TagalogParseException(Throwable cause) {
        super();
        this.cause = cause;
    }

    /**
     * Returns the cause of this TagalogParseException.
     * @return the cause of this TagalogParseException
     * @see java.lang.Throwable#getCause()
     */
    public final Throwable getCause() {
        return cause;
    }
}
