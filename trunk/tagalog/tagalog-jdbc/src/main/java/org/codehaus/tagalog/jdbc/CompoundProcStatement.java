/*
 * $Id: CompoundProcStatement.java,v 1.1 2004-02-26 12:27:11 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public interface CompoundProcStatement extends ProcStatement {

    void addStatement(ProcStatement statement);

    void closeStatementList();

}
