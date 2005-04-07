/*
 * $Id: PersonTag.java,v 1.6 2005-04-07 15:51:33 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.people;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;

/**
 * PersonTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.6 $
 */
public final class PersonTag extends AbstractTag {
    Person person;

    public void begin(String elementName, Attributes attributes)
        throws TagException
    {
        person = new Person();
        person.setUserId(requireAttribute(attributes, elementName, "user-id"));
    }

    public void child(TagBinding childType, Object child)
        throws TagException, TagalogParseException
    {
        if (childType == PeopleTagLibrary.FIRST_NAME)
            person.setFirstName((String) child);
        else if (childType == PeopleTagLibrary.LAST_NAME)
            person.setLastName((String) child);
    }

    public Object end(String elementName) {
        return person;
    }

    public boolean recycle() {
        person = null;
        return true;
    }
}
