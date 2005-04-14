/*
 * $Id: DialectChoiceStatement.java,v 1.5 2005-04-14 13:52:11 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.util.Set;

import org.codehaus.plexus.component.repository.exception.ComponentLookupException;

/**
 * Choose the appropriate matching SQL statement from a set of alternatives.
 * The default behaviour of the dialect choice statement is to choose the
 * contained statement whose <code>dialect</code> property matches that of
 * the current database connection manager. If there is no match for the
 * dialect of the connection, the contained statement whose dialect property
 * is <code>null</code> will be executed.
 *
 * <p>
 * If no dialect matches and there is no default statement this is normally
 * considered an error: {@link ProcException} will be thrown because the
 * dialect choice was unable to find a suitable statement.
 * Passing the value <code>true</code> to the {@link #setOptional} method
 * causes this condition to be silently ignored: effectively the contents
 * of the dialect choice become optional statements to be executed only on
 * certain types of databases.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.5 $
 */
public class DialectChoiceStatement implements CompoundProcStatement {
    private boolean optional = false;

    /**
     * Indicate that this dialect choice is not mandatory; that is, it is
     * not an error if there is no statement matching the current database
     * dialect.
     *
     * @param optional True to indicate that execution of this dialect
     * choice is optional.
     */
    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    /**
     * Return the database dialect that this {@link ProcStatement} is
     * intended for. <code>DialectChoiceStatement</code> does not have
     * a dialect itself, so this method always returns <code>null</code>.
     *
     * @return <code>null</code>
     */
    public String getDialect() {
        return null;
    }

    private StatementGroup statementGroup = new StatementGroup();

    public void addStatement(ProcStatement statement) {
        statementGroup.addStatement(statement);
    }

    public void closeStatementList() {
        statementGroup.closeStatementList();
        ProcStatement[] statements = statementGroup.getStatementList();
        Set dialects = new java.util.HashSet();
        for (int i = 0; i < statements.length; i++) {
            dialects.add(statements[i].getDialect());
        }
        if (dialects.size() != statements.length) {
            throw new IllegalStateException(
                "dialect choice contains duplicate dialects"
            );
        }
    }

    public String toString() {
        return "choice " + statementGroup.toString();
    }

    public Object execute(Catalog catalog, Proc proc, ProcContext ctx)
        throws ProcException
    {
        String requiredDialect;
        ProcStatement[] statements = statementGroup.getStatementList();
        ProcStatement defaultStatement = null;
        ProcStatement foundStatement = null;

        try {
            requiredDialect = ctx.getDialect(catalog);
        } catch (ComponentLookupException e) {
            throw new ProcException(e);
        }
        for (int i = 0; i < statements.length; i++) {
            ProcStatement stmt = statements[i];
            String statementDialect = stmt.getDialect();
            if (statementDialect == null)
                defaultStatement = stmt;
            else if (statementDialect.equals(requiredDialect))
                foundStatement = stmt;
        }
        if (foundStatement != null) {
            return foundStatement.execute(catalog, proc, ctx);
        } else if (defaultStatement != null) {
            return defaultStatement.execute(catalog, proc, ctx);
        } else if (optional) {
            return null;
        } else {
            if (requiredDialect != null) {
                throw new ProcException(
                    "no statement matching dialect " + requiredDialect, this
                );
            }
            throw new ProcException("no default statement", this);
        }
    }
}
