/*
 * $Id: TooFewRowsException.java,v 1.4 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

/**
 * Specific subclass of {@link ProcException} to indicate that a query
 * has returned fewer rows than were suggested by the query's 'rows'
 * property.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.4 $
 */
public class TooFewRowsException extends ProcException {
    public static final String MESSAGE_PREFIX
        = "too few rows retrieved when expecting ";

    public TooFewRowsException(int expected, ProcStatement errorBlock) {
        super(MESSAGE_PREFIX + expected, errorBlock);
    }
}
