/*
 * $Id: PeopleTagLibrary.java,v 1.3 2004-02-19 18:19:40 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.people;

import org.codehaus.tagalog.AbstractTagLibrary;

/**
 * A tag library containing some simple information about people.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public final class PeopleTagLibrary extends AbstractTagLibrary {
    public static final String NS_URI = "tagalog:people";

    public PeopleTagLibrary() {
        registerTag("people", PeopleTag.class);
        registerTag("person", PersonTag.class);
        registerTag("first-name", AttributeTag.class);
        registerTag("last-name", AttributeTag.class);
        registerTag("broken", BrokenTag.class);
    }
}
