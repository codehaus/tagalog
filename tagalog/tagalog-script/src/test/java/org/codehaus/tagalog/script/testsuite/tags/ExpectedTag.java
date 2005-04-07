/*
 * $Id: ExpectedTag.java,v 1.2 2005-04-07 15:56:15 mhw Exp $
 */

package org.codehaus.tagalog.script.testsuite.tags;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Tag;
import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;

/**
 * <code>Tag</code> for handling the &lt;expected&gt; element.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class ExpectedTag extends AbstractTag implements Tag {
    private StringBuffer content = new StringBuffer();

    public void text(char[] characters, int start, int length)
        throws TagException, TagalogParseException
    {
        content.append(characters, start, length);
    }

    public void child(TagBinding childType, Object child)
        throws TagException, TagalogParseException
    {
        if (child instanceof String)
            content.append((String) child);
        else
            content.append(child.toString());
    }

    public Object end(String elementName)
        throws TagException, TagalogParseException
    {
        return content.toString();
    }
}
