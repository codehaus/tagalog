/*
 * $Id: ParseError.java,v 1.1 2004-02-26 17:38:47 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * A <code>ParseError</code> collects information about some non-fatal error
 * encountered while parsing content. Typically these errors are raised by
 * implementations of {@link Tag} as they validate and process the content.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class ParseError {
    public static final ParseError[] EMPTY_ARRAY = new ParseError[0];

    private final String message;

    private final int lineNumber;

    public ParseError(String message, int lineNumber) {
        this.message = message;
        this.lineNumber = lineNumber;
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
     * Returns the approximate line where the error occurred.
     *
     * @return the approximate line where the error occurred.
     */
    public int getLineNumber() {
        return lineNumber;
    }

    public String toString() {
        return lineNumber + ": " + message;
    }
}
