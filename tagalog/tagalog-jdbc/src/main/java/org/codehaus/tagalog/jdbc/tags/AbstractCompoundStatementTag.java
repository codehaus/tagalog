/*
 * $Id: AbstractCompoundStatementTag.java,v 1.1 2004-02-26 12:32:26 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc.tagalog;

import org.codehaus.tagalog.TagalogParseException;

import com.fintricity.jdbc.CompoundProcStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public class AbstractCompoundStatementTag extends AbstractProcStatementTag {
    public Object end(String elementName) throws TagalogParseException {
        ((CompoundProcStatement) stmt).closeStatementList();
        return super.end(elementName);
    }
}
