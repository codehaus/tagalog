/*
 * $Id: SequenceTag.java,v 1.1 2004-02-26 12:32:26 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc.tagalog;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagalogParseException;

import com.fintricity.jdbc.SequenceStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public class SequenceTag extends AbstractCompoundStatementTag {
    public void begin(String elementName, Attributes attribute)
        throws TagalogParseException
    {
        stmt = new SequenceStatement();
    }
}
