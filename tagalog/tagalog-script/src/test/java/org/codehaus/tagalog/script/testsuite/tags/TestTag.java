/*
 * $Id: TestTag.java,v 1.2 2005-04-07 15:56:15 mhw Exp $
 */

package org.codehaus.tagalog.script.testsuite.tags;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Tag;
import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;
import org.codehaus.tagalog.script.Script;
import org.codehaus.tagalog.script.testsuite.Test;

/**
 * <code>Tag</code> for handling the &lt;test&gt; element.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class TestTag extends AbstractTag implements Tag {
    private Script script;

    private String expected;

    public void child(TagBinding childType, Object child)
        throws TagException, TagalogParseException
    {
        if (child instanceof Script)
            script = (Script) child;
        else if (child instanceof String)
            expected = (String) child;
        else if (child == null)
            throw new TagException("child of <test> is null");
        else
            throw new TagException("unknown child element of type "
                                   + child.getClass());
    }

    public Object end(String elementName)
        throws TagException, TagalogParseException
    {
        if (script == null)
            throw new TagException("<test> element must contain a <script>");
        if (expected == null)
            throw new TagException("<test> element must contain <expected>");
        return new Test(script, expected);
    }
}
