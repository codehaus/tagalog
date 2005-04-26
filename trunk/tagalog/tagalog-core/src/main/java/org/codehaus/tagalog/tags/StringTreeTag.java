/*
 * $Id: StringTreeTag.java,v 1.2 2005-04-26 14:29:57 mhw Exp $
 */

package org.codehaus.tagalog.tags;

import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;

/**
 * Collect the textual content of an element, including that from child
 * elements.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class StringTreeTag extends AbstractStringTag {
    public void child(TagBinding childType, Object child)
        throws TagException, TagalogParseException
    {
        if (child instanceof String)
            getBuffer().append((String) child);
        else {
            TagBinding type = getTagBinding();

            throw new TagException("child <" + childType.getName() + ">"
                                   + " of <" + type.getName() + ">"
                                   + " returned " + child.getClass().getName()
                                   + " instead of String");
        }
    }
}
