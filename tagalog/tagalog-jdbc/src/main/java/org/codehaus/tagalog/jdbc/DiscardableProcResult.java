/*
 * $Id: DiscardableProcResult.java,v 1.2 2004-01-30 17:48:58 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

/**
 * Interface that should be implemented by any object that can be returned
 * from a {@link ProcStatement}, if the object has special requirements
 * to ensure that resources are released properly. This interface is used
 * to release resources returned by an intermediate <code>ProcStatement</code>
 * when a later <code>ProcStatement</code> also returns a result
 * (given that a {@link Proc} only returns that object resulting from
 * the last statement that returns a non-null value).
 * 
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public interface DiscardableProcResult {
    /**
     * Release all resources associated with this result.
     *
     * @throws ProcException Wrapping any exception generated by discarding
     * the result.
     */
    void discard() throws ProcException;
}