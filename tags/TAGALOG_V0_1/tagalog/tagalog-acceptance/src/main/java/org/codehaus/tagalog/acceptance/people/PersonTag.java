/*
 * $Id: PersonTag.java,v 1.4 2004-02-26 17:48:59 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.people;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;

/**
 * PersonTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.4 $
 */
public final class PersonTag extends AbstractTag {
    Person person;

    public void begin(String elementName, Attributes attributes)
        throws TagException
    {
        person = new Person();
        person.setUserId(requireAttribute("user-id", attributes));
    }

    public Object end(String elementName) {
        return person;
    }

    public boolean recycle() {
        person = null;
        return true;
    }
}
