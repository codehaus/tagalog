/*
 * $Id: ScriptTag.java,v 1.1 2004-11-04 18:04:57 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.script.Script;
import org.codehaus.tagalog.script.Sequence;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public final class ScriptTag extends AbstractCompoundStatementTag {
    Script script;

    public ScriptTag() {
        rootTag = true;
    }

    public void begin(String elementName, Attributes attributes)
        throws TagException
    {
        script = new Script();
        script.setName(requireAttribute(attributes, elementName, "name"));
        stmt = new Sequence();
    }

    public Object end(String elementName) throws TagException {
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
