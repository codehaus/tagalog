/*
 * $Id: ScriptException.java,v 1.3 2005-06-07 16:33:12 krisb Exp $
 */

package org.codehaus.tagalog.script;

/**
 * Class of exception thrown by failures in script execution.
 * <p>
 * Cause is handled internally by this class to support pre J2SE 1.4.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class ScriptException extends Exception {

    private final Throwable cause;

    /**
     * @param message
     */
    public ScriptException(String message) {
        super(message);
        this.cause = null;
    }

    /**
     * @param message
     * @param cause
     */
    public ScriptException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    /**
     * Returns the cause of this ScriptException.
     * @return the cause of this ScriptException
     * @see java.lang.Throwable#getCause()
     */
    public final Throwable getCause() {
        return cause;
    }
}
