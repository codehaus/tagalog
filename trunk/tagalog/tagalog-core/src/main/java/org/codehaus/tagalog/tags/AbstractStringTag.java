/*
 * $Id: AbstractStringTag.java,v 1.2 2005-04-14 11:34:58 mhw Exp $
 */

package org.codehaus.tagalog.tags;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;

/**
 * An abstract base class for {@link org.codehaus.tagalog.Tag}s that
 * collect their content into a {@link StringBuffer}.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public abstract class AbstractStringTag extends AbstractTag {
    private StringBuffer buffer;

    protected final StringBuffer getBuffer() {
        return buffer;
    }

    public void begin(String elementName, Attributes attributes) {
        buffer = new StringBuffer();
    }

    public void text(char[] characters, int start, int length) {
        getBuffer().append(characters, start, length);
    }

    public Object end(String elementName) throws TagException {
        return getBuffer().toString();
    }

    public boolean recycle() {
        buffer = null;
        return true;
    }
}
