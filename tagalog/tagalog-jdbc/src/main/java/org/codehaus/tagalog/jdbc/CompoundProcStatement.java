/*
 * $Id: CompoundProcStatement.java,v 1.2 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public interface CompoundProcStatement extends ProcStatement {

    void addStatement(ProcStatement statement);

    void closeStatementList();

}
