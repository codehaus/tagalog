/*
 * $Id: CollectTag.java,v 1.2 2004-11-08 12:35:48 mhw Exp $
 */

package org.codehaus.tagalog.script;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;
import org.codehaus.tagalog.script.tags.AbstractStatementTag;

/**
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class CollectTag extends AbstractStatementTag {
    StringBuffer buffer;

    public void begin(String elementName, Attributes attr)
        throws TagException, TagalogParseException
    {
        stmt = new CollectStatement();
        buffer = new StringBuffer();
    }

    public void text(char[] characters, int start, int length)
        throws TagException, TagalogParseException
    {
        buffer.append(characters, start, length);
    }

    public Object end(String elementName)
        throws TagException, TagalogParseException
    {
        ((CollectStatement) stmt).setContent(buffer.toString());
        return super.end(elementName);
    }

    public boolean recycle() {
        buffer = null;
        return super.recycle();
    }
}
