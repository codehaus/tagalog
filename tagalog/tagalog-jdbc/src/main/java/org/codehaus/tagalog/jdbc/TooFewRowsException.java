/*
 * $Id: TooFewRowsException.java,v 1.2 2004-01-28 15:25:03 mhw Exp $
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
 * has returned fewer rows than were suggested by the query's 'rows'
 * property.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public class TooFewRowsException extends ProcException {
    public static final String MESSAGE_PREFIX
        = "too few rows retrieved when expecting ";

    public TooFewRowsException(int expected, ProcStatement errorBlock) {
        super(MESSAGE_PREFIX + expected, errorBlock);
    }
}
