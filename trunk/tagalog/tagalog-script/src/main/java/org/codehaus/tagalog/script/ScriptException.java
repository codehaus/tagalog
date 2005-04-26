/*
 * $Id: ScriptException.java,v 1.2 2005-04-26 16:42:08 mhw Exp $
 */

package org.codehaus.tagalog.script;

/**
 * Class of exception thrown by failures in script execution.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class ScriptException extends Exception {

    /**
     * @param message
     */
    public ScriptException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ScriptException(String message, Throwable cause) {
        super(message, cause);
    }
}
