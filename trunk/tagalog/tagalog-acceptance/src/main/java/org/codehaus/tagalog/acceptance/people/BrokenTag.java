/*
 * $Id: BrokenTag.java,v 1.1 2004-02-19 18:19:40 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.people;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;

/**
 * A tag that throws a <code>NullPointerException</code> when the
 * begin method is called, simulating a bug in a tag. This allows us
 * to make sure that exception detail is preserved.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class BrokenTag extends AbstractTag {
    public void begin(String elementName, Attributes attributes) {
        throw new NullPointerException("from BrokenTag");
    }
}
