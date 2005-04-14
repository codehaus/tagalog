/*
 * $Id: ProcStatement.java,v 1.5 2005-04-14 13:59:06 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.5 $
 */
public interface ProcStatement {
    Object[] EMPTY_ARRAY = new ProcStatement[0];

    /**
     * Return the database dialect this statement is designed for, or
     * <code>null</code> if the statement is suitable for any database.
     *
     * @return Database dialect, or <code>null</code>.
     */
    String getDialect();

    /**
     * Execute a statement.
     *
     * @param catalog The catalog that the statement belongs to.
     * @param ctx Context object for this invocation, carrying attribute
     * values.
     */
    Object execute(Catalog catalog, ProcContext ctx)
        throws ProcException;
}
