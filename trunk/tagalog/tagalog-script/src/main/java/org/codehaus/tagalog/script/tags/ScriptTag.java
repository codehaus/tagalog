/*
 * $Id: ScriptTag.java,v 1.5 2005-05-17 14:23:10 krisb Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.script.Script;
import org.codehaus.tagalog.script.Statement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.5 $
 */
public final class ScriptTag extends AbstractCompoundStatementTag {
    private Script script;

    public void begin(String elementName, Attributes attributes)
        throws TagException
    {
        super.begin(elementName, attributes);
        script = new Script();
        script.setName(requireAttribute(attributes, elementName, "name"));
    }

    public Object end(String elementName) throws TagException {
        Statement body = (Statement) super.end(elementName);
        if (body == null)
            throw new TagException("script body is null");
        script.setBody(body);
        return script;
    }

    public boolean recycle() {
        script = null;
        return super.recycle();
    }
}
