/*
 * $Id: RemoveTag.java,v 1.1 2005-04-19 16:40:02 mhw Exp $
 */

package org.codehaus.tagalog.script.core.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;
import org.codehaus.tagalog.script.core.Remove;
import org.codehaus.tagalog.script.tags.AbstractSimpleStatementTag;

/**
 * Tag implementation for the JSTL <code>remove</code> action.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class RemoveTag extends AbstractSimpleStatementTag {

    private String var;

    public void begin(String elementName, Attributes attributes)
        throws TagException, TagalogParseException
    {
        var = requireAttribute(attributes, elementName, "var");
    }

    public Object end(String elementName)
        throws TagException, TagalogParseException
    {
        stmt = new Remove(var);
        return super.end(elementName);
    }
}
