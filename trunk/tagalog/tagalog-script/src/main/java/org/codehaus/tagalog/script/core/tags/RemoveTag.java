/*
 * $Id: RemoveTag.java,v 1.3 2005-05-17 16:16:23 krisb Exp $
 */

package org.codehaus.tagalog.script.core.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.script.core.Remove;
import org.codehaus.tagalog.script.tags.AbstractSimpleStatementTag;

/**
 * Tag implementation for the JSTL <code>remove</code> action.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class RemoveTag extends AbstractSimpleStatementTag {

    private String var;

    public void begin(String elementName, Attributes attributes)
        throws TagException
    {
        var = requireAttribute(attributes, "var");
    }

    public Object end(String elementName) throws TagException {
        stmt = new Remove(var);
        return super.end(elementName);
    }
}
