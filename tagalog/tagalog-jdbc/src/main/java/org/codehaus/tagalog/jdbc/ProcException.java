/*
 * $Id: ProcException.java,v 1.5 2005-04-14 13:51:45 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

/**
 * Wrapper exception for errors during {@link Proc} and
 * {@link ProcStatement} execution.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.5 $
 */
public class ProcException extends Exception {

    private final ProcStatement errorBlock;

    /**
     * @param message
     */
    public ProcException(String message) {
        super(message);
        this.errorBlock = null;
    }

    /**
     * @param message
     * @param errorBlock The <code>ProcStatement</code> that caused the
     * error.
     */
    public ProcException(String message, ProcStatement errorBlock) {
        super(message);
        this.errorBlock = errorBlock;
    }

    /**
     * @param cause
     */
    public ProcException(Throwable cause) {
        super(cause);
        this.errorBlock = null;
    }

    /**
     * @param cause
     * @param errorBlock The <code>ProcStatement</code> that caused the
     * error.
     */
    public ProcException(Throwable cause, ProcStatement errorBlock) {
        super(cause);
        this.errorBlock = errorBlock;
    }

    /**
     * @param message
     * @param cause
     */
    public ProcException(String message, Throwable cause) {
        super(message, cause);
        this.errorBlock = null;
    }

    /**
     * @param message
     * @param cause
     * @param errorBlock The <code>ProcStatement</code> that caused the
     * error.
     */
    public ProcException(String message, Throwable cause,
                         ProcStatement errorBlock)
    {
        super(message, cause);
        this.errorBlock = errorBlock;
    }

    /**
     * @return
     */
    public ProcStatement getErrorBlock() {
        return errorBlock;
    }

    public String getMessage() {
        if (errorBlock != null)
            return super.getMessage() + " (in " + errorBlock.toString() + ")";
        return super.getMessage();
    }
}
