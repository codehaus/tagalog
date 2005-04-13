/*
 * $Id: StringTag.java,v 1.1 2005-04-13 13:53:24 mhw Exp $
 */

package org.codehaus.tagalog.tags;

import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;

/**
 * Collect the textual content of an element, disallowing embedded
 * markup.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class StringTag extends AbstractStringTag {
    public void child(TagBinding childType, Object child)
        throws TagException, TagalogParseException
    {
        TagBinding type = getTagBinding();

        throw new TagException("<" + type.getElementName() + ">"
                               + " cannot contain markup");
    }
}
