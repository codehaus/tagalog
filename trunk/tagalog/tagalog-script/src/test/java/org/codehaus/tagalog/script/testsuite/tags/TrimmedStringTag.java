/*
 * $Id: TrimmedStringTag.java,v 1.1 2005-07-19 08:57:22 mhw Exp $
 */

package org.codehaus.tagalog.script.testsuite.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.Tag;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagUtils;
import org.codehaus.tagalog.script.tags.ScriptTagLibrary;
import org.codehaus.tagalog.tags.StringTag;

/**
 * A tag that returns 'smart' trimmed text content.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class TrimmedStringTag extends StringTag implements Tag {
    private boolean preserveWhitespace = false;

    public void begin(String elementName, Attributes attributes)
        throws TagException
    {
        super.begin(elementName, attributes);

        String s = attributes.getValue(ScriptTagLibrary.NS_NAMESPACE, "space");
        preserveWhitespace = (s != null && s.equals("preserve"));
    }

    public Object end(String elementName) throws TagException {
        String s = (String) super.end(elementName);
        if (!preserveWhitespace)
            s = TagUtils.trim(s);
        return s;
    }
}
