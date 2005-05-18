/*
 * $Id: ParseError.java,v 1.4 2005-05-18 13:45:10 krisb Exp $
 */

package org.codehaus.tagalog;

/**
 * A <code>ParseError</code> collects information about some non-fatal error
 * encountered while parsing content. Typically these errors are raised by
 * implementations of {@link Tag} as they validate and process the content.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.4 $
 */
public final class ParseError {
    public static final ParseError[] EMPTY_ARRAY = new ParseError[0];

    private final String message;

    private final Location location;

    private final Throwable cause;

    ParseError(Location location, String message) {
        this(location, message, null);
    }

    ParseError(Location location, String message, Throwable cause) {
        this.message = message;
        this.location = location;
        this.cause = cause;
    }

    /**
     * Returns the location where the error occurred.
     *
     * @return the location where the error occurred.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Returns the error message.
     *
     * @return The error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the cause if supplied
     *
     * @return the cause if supplied
     */
    public Throwable getCause() {
        return cause;
    }

    public String toString() {
        return location + ": " + message;
    }
}
