/*
 * $Id: People.java,v 1.1 2004-02-10 23:52:01 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.people;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * People
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class People {
    private List people = new ArrayList();

    public void addPerson(Person p) {
        people.add(p);
    }

    public List getPeople() {
        return Collections.unmodifiableList(people);
    }
}
