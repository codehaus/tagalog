/*
 * $Id: PeopleTag.java,v 1.2 2004-02-11 12:45:34 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.people;

import org.codehaus.tagalog.AbstractTag;

/**
 * PeopleTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class PeopleTag extends AbstractTag {
    private People people;

    public void begin(String elementName) {
        people = new People();
    }

    public void child(Object child) {
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
