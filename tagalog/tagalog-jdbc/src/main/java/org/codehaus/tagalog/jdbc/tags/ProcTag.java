/*
 * $Id: ProcTag.java,v 1.7 2005-04-14 09:27:00 mhw Exp $
 */

package org.codehaus.tagalog.jdbc.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;

import org.codehaus.tagalog.jdbc.Proc;
import org.codehaus.tagalog.jdbc.ProcStatement;
import org.codehaus.tagalog.jdbc.SequenceStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.7 $
 */
public final class ProcTag extends AbstractCompoundStatementTag {
    Proc proc;

    public ProcTag() {
        rootTag = true;
    }

    public void begin(String elementName, Attributes attributes)
        throws TagException
    {
        super.begin(elementName, attributes);

        proc = new Proc();
        proc.setName(requireAttribute(attributes, elementName, "name"));
        String s = attributes.getValue("connection");
        if (s != null)
            proc.setConnectionName(s);
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
