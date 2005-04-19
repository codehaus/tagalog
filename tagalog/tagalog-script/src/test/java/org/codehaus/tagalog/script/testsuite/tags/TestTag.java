/*
 * $Id: TestTag.java,v 1.3 2005-04-19 20:51:55 mhw Exp $
 */

package org.codehaus.tagalog.script.testsuite.tags;

import java.util.Map;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.ParseError;
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
 * @version $Revision: 1.3 $
 */
public class TestTag extends AbstractTag implements Tag {
    private static final String ERROR_COUNT = "TestTag.parseErrorCount";

    private Script script;

    private String expected;

    public void begin(String elementName, Attributes attributes)
        throws TagException, TagalogParseException
    {
        Map context = getContext();
        context.put(ERROR_COUNT, new Integer(getParser().parseErrors().length));
    }

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

        int startErrors = ((Integer) getContext().get(ERROR_COUNT)).intValue();
        ParseError[] errors = getParser().parseErrors();

        if (startErrors < errors.length) {
            int newErrors = errors.length - startErrors;
            ParseError[] newParseErrors = new ParseError[newErrors];
            System.arraycopy(errors, startErrors,
                             newParseErrors, 0, newErrors);
            return new Test(script, newParseErrors, expected);
        }
        return new Test(script, expected);
    }

    public boolean recycle() {
        script = null;
        expected = null;
        return super.recycle();
    }
}
