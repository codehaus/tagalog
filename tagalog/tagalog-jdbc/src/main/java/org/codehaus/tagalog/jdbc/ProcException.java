/*
 * $Id: ProcException.java,v 1.1 2004-01-23 15:21:36 mhw Exp $
 *
 * Copyright (c) 2003 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

/**
 * Wrapper exception for errors during {@link Proc} execution.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public class ProcException extends Exception {

    /**
     * @param message
     */
    public ProcException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ProcException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ProcException(String message, Throwable cause) {
        super(message, cause);
    }
}
