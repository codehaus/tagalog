/*
 * $Id: StringTag.java,v 1.3 2005-05-17 14:17:47 krisb Exp $
 */

package org.codehaus.tagalog.tags;

import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.TagException;

/**
 * Collect the textual content of an element, disallowing embedded
 * markup.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public final class StringTag extends AbstractStringTag {
    public void child(TagBinding childType, Object child) throws TagException {
        TagBinding type = getTagBinding();

        throw new TagException("<" + type.getName() + ">"
                               + " cannot contain markup");
    }
}
