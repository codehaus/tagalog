/*
 * $Id: PersonTag.java,v 1.9 2005-05-17 16:16:23 krisb Exp $
 */

package org.codehaus.tagalog.acceptance.people;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.TagException;

/**
 * PersonTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.9 $
 */
public final class PersonTag extends AbstractTag {
    Person person;

    public void begin(String elementName, Attributes attributes)
        throws TagException
    {
        person = new Person();
        person.setUserId(requireAttribute(attributes, "user-id"));
    }

    public void child(TagBinding childType, Object child) throws TagException {
        if (childType == PeopleTagLibrary.FIRST_NAME)
            person.setFirstName((String) child);
        else if (childType == PeopleTagLibrary.LAST_NAME)
            person.setLastName((String) child);
        else if (childType == PeopleTagLibrary.COMMENT)
            person.setComment((String) child);
    }

    public Object end(String elementName) {
        return person;
    }

    public boolean recycle() {
        person = null;
        return true;
    }
}
