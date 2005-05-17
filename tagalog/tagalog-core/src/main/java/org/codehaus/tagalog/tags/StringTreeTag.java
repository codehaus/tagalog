/*
 * $Id: StringTreeTag.java,v 1.3 2005-05-17 14:17:47 krisb Exp $
 */

package org.codehaus.tagalog.tags;

import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.TagException;

/**
 * Collect the textual content of an element, including that from child
 * elements.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public final class StringTreeTag extends AbstractStringTag {
    public void child(TagBinding childType, Object child) throws TagException {
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
