/*
 * $Id: SequenceStatement.java,v 1.1 2004-01-27 22:02:17 mhw Exp $
 *
 * Copyright (c) 2003 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

/**
 * A sequence of statements.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public class SequenceStatement extends StatementGroup
    implements ProcStatement
{
    public Object execute(Catalog catalog, Proc proc, ProcContext ctx)
        throws ProcException
    {
        Object result = null;

        for (int i = 0; i < statements.length; i++) {
            Object o = statements[i].execute(catalog, proc, ctx);
            if (o != null) {
                if (result instanceof DiscardableProcResult) {
                    ((DiscardableProcResult) result).discard();
                }
                result = o;
            }
        }
        return result;
    }
}
