/*
 * $Id: TooManyRowsException.java,v 1.4 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

/**
 * Specific subclass of {@link ProcException} to indicate that a query
 * has returned more rows than were suggested by the query's 'rows'
 * property.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.4 $
 */
public class TooManyRowsException extends ProcException {
    public static final String MESSAGE_PREFIX
        = "too many rows retrieved when expecting ";

    public TooManyRowsException(int expected, ProcStatement errorBlock) {
        super(MESSAGE_PREFIX + expected, errorBlock);
    }
}
