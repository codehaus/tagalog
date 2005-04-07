/*
 * $Id: AttributeTag.java,v 1.6 2005-04-07 15:51:33 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.people;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;

/**
 * AttributeTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.6 $
 */
public final class AttributeTag extends AbstractTag {
    private StringBuffer buffer;

    public void begin(String elementName, Attributes attributes) {
        buffer = new StringBuffer();
    }

    public void text(char[] characters, int start, int length) {
        buffer.append(characters, start, length);
    }

    public Object end(String elementName) throws TagException {
        return buffer.toString();
    }

    public boolean recycle() {
        buffer = null;
        return true;
    }
}
