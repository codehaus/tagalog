/*
 * $Id: CollectTag.java,v 1.1 2004-03-02 21:45:29 mhw Exp $
 */

package org.codehaus.tagalog.script;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.script.tags.AbstractStatementTag;

/**
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class CollectTag extends AbstractStatementTag {
    StringBuffer buffer;

    public void begin(String elementName, Attributes attr)
        throws TagException
    {
        stmt = new CollectStatement();
        buffer = new StringBuffer();
    }

    public void text(char[] characters, int start, int length) {
        buffer.append(characters, start, length);
    }

    public Object end(String elementName) throws TagException {
        ((CollectStatement) stmt).setContent(buffer.toString());
        return super.end(elementName);
    }

    public boolean recycle() {
        buffer = null;
        return super.recycle();
    }
}
