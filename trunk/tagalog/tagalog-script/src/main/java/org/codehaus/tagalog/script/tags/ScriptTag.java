/*
 * $Id: ScriptTag.java,v 1.3 2004-11-08 12:35:48 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;
import org.codehaus.tagalog.script.Script;
import org.codehaus.tagalog.script.Sequence;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public final class ScriptTag extends AbstractCompoundStatementTag {
    private Script script;

    public ScriptTag() {
        rootTag = true;
    }

    public void begin(String elementName, Attributes attributes)
        throws TagException, TagalogParseException
    {
        super.begin(elementName, attributes);
        script = new Script();
        script.setName(requireAttribute(attributes, elementName, "name"));
    }

    public Object end(String elementName)
        throws TagException, TagalogParseException
    {
        Sequence result = (Sequence) super.end(elementName);
        if (result == null)
            throw new TagException("top-level statement list is null");
        script.setBody(result);
        return script;
    }

    public boolean recycle() {
        script = null;
        return super.recycle();
    }
}
