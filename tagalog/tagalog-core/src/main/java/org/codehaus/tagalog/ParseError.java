/*
 * $Id: ParseError.java,v 1.3 2005-05-18 11:28:56 krisb Exp $
 */

package org.codehaus.tagalog;

/**
 * A <code>ParseError</code> collects information about some non-fatal error
 * encountered while parsing content. Typically these errors are raised by
 * implementations of {@link Tag} as they validate and process the content.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public final class ParseError {
    public static final ParseError[] EMPTY_ARRAY = new ParseError[0];

    private final String message;

    private final Location location;

    private final Throwable cause;

    public ParseError(String message, Location location) {
        this(message, location, null);
    }

    public ParseError(String message, Location location, Throwable cause) {
        this.message = message;
        this.location = location;
        this.cause = cause;
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
     * Returns the location where the error occurred.
     *
     * @return the location where the error occurred.
     */
    public Location getLocation() {
        return location;
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
