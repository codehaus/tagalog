/*
 * $Id: AbstractProcStatement.java,v 1.2 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

/**
 * Abstract base class implementing the basic contract of
 * {@link ProcStatement}.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public abstract class AbstractProcStatement implements ProcStatement {

    protected String dialect;

    public final void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public final String getDialect() {
        return dialect;
    }
}
