/*
 * $Id: PersonTag.java,v 1.2 2004-02-11 12:45:34 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.people;

import org.codehaus.tagalog.AbstractTag;

/**
 * PersonTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class PersonTag extends AbstractTag {
    Person person;

    public void begin(String elementName) {
        person = new Person();
    }

    public Object end(String elementName) {
        return person;
    }

    public boolean recycle() {
        person = null;
        return true;
    }
}
