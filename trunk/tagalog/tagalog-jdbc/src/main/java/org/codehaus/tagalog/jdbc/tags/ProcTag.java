/*
 * $Id: ProcTag.java,v 1.3 2004-02-26 20:04:32 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc.tagalog;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;

import com.fintricity.jdbc.Proc;
import com.fintricity.jdbc.SequenceStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public final class ProcTag extends AbstractCompoundStatementTag {
    Proc proc;

    public ProcTag() {
        rootTag = true;
    }

    public void begin(String elementName, Attributes attributes)
        throws TagException
    {
        String s;

        proc = new Proc();
        proc.setName(requireAttribute("name", attributes));
        s = attributes.getValue("connection");
        if (s != null)
            proc.setConnectionName(s);
        stmt = new SequenceStatement();
    }

    public Object end(String elementName) throws TagException {
        SequenceStatement result = (SequenceStatement) super.end(elementName);
        if (result == null)
            throw new TagException("top-level statement group is null");
        proc.setBody(result);
        return proc;
    }

    public boolean recycle() {
        proc = null;
        return super.recycle();
    }
}
