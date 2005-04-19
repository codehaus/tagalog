/*
 * $Id: ScriptTag.java,v 1.4 2005-04-19 16:28:36 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;
import org.codehaus.tagalog.script.Script;
import org.codehaus.tagalog.script.Statement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.4 $
 */
public final class ScriptTag extends AbstractCompoundStatementTag {
    private Script script;

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
