/*
 * $Id: ProcTag.java,v 1.6 2004-12-17 13:43:04 mhw Exp $
 */

package org.codehaus.tagalog.jdbc.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;

import org.codehaus.tagalog.jdbc.Proc;
import org.codehaus.tagalog.jdbc.ProcStatement;
import org.codehaus.tagalog.jdbc.SequenceStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.6 $
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
        proc.setName(requireAttribute(attributes, elementName, "name"));
        s = attributes.getValue("connection");
        if (s != null)
            proc.setConnectionName(s);
        super.begin(elementName, attributes);
    }

    protected ProcStatement createProcStatement(String elementName,
                                                Attributes attributes)
    {
        return new SequenceStatement();
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
