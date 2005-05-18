/*
 * $Id: CauseTag.java,v 1.1 2005-05-18 14:21:13 krisb Exp $
 */

package org.codehaus.tagalog.acceptance.people;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;

/**
 * A Tag that throws a TagException with an underlying cause.
 *
 * @author <a href="mailto:krisb@codehaus.org">Kristopher Brown</a>
 * @version $Revision: 1.1 $
 */
public final class CauseTag extends AbstractTag {
    public void begin(String elementName, Attributes attributes)
            throws TagException
    {
        Throwable cause = new IllegalStateException("invalid state");
        throw new TagException("tag exception with cause", cause);
    }
}
