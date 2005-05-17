/*
 * $Id: TagErrorTag.java,v 1.1 2005-05-17 14:21:38 krisb Exp $
 */

package org.codehaus.tagalog.acceptance.people;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagError;

/**
 * A Tag that throws a TagError.
 *
 * @author <a href="mailto:krisb@codehaus.org">Kristopher Brown</a>
 * @version $Revision: 1.1 $
 */
public final class TagErrorTag extends AbstractTag {
    public void begin(String elementName, Attributes attributes) {
        throw new TagError("tag error");
    }
}
