/*
 * $Id: TooManyRowsException.java,v 1.1 2004-01-28 13:04:05 mhw Exp $
 *
 * Copyright (c) 2003 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

/**
 * Specific subclass of {@link ProcException} to indicate that a query
 * has returned more rows than were suggested by the query's 'rows'
 * property.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public class TooManyRowsException extends ProcException {
    public TooManyRowsException(int expected, ProcStatement errorBlock) {
        super("too many rows retrieved when expecting " + expected, errorBlock);
    }
}
