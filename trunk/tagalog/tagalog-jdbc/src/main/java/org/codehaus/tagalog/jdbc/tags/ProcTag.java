/*
 * $Id: ProcTag.java,v 1.1 2004-02-11 19:03:21 mhw Exp $
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

import com.fintricity.jdbc.Proc;
import com.fintricity.jdbc.SequenceStatement;
import com.fintricity.jdbc.StatementGroup;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public final class ProcTag extends StatementGroupTag {
    Proc proc;

    public void begin(String elementName, Attributes attributes)
        throws TagalogParseException
    {
        String s;

        proc = new Proc();
        proc.setName(requireAttribute("name", attributes));
        s = attributes.getValue("connection");
        if (s != null)
            proc.setConnectionName(s);
        stmt = new SequenceStatement();
    }

    public Object end(String elementName) throws TagalogParseException {
        StatementGroup result = (StatementGroup) super.end(elementName);
        if (result == null)
            throw new TagalogParseException("top-level statement group is null");
        proc.setBody(result);
        return proc;
    }

    public boolean recycle() {
        proc = null;
        stmt = null;
        return true;
    }
}
