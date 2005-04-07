/*
 * $Id: PeopleTag.java,v 1.4 2005-04-07 15:51:33 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.people;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagBinding;

/**
 * PeopleTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.4 $
 */
public class PeopleTag extends AbstractTag {
    private People people;

    public void begin(String elementName, Attributes attributes) {
        people = new People();
    }

    public void child(TagBinding childType, Object child) {
        Person person = (Person) child;
        people.addPerson(person);
    }

    public Object end(String elementName) {
        return people;
    }

    public boolean recycle() {
        people = null;
        return true;
    }
}
