/*
 * $Id: PersonTag.java,v 1.5 2004-11-02 12:10:43 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.people;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;

/**
 * PersonTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.5 $
 */
public final class PersonTag extends AbstractTag {
    Person person;

    public void begin(String elementName, Attributes attributes)
        throws TagException
    {
        person = new Person();
        person.setUserId(requireAttribute(attributes, elementName, "user-id"));
    }

    public Object end(String elementName) {
        return person;
    }

    public boolean recycle() {
        person = null;
        return true;
    }
}
