/*
 * $Id: PeopleTagLibrary.java,v 1.4 2005-04-07 15:51:33 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.people;

import org.codehaus.tagalog.AbstractTagLibrary;
import org.codehaus.tagalog.TagBinding;

/**
 * A tag library containing some simple information about people.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.4 $
 */
public final class PeopleTagLibrary extends AbstractTagLibrary {
    public static final String NS_URI = "tagalog:people";

    public static final TagBinding PEOPLE = new TagBinding("people",
                                                           PeopleTag.class);
    public static final TagBinding PERSON = new TagBinding("person",
                                                           PersonTag.class);
    public static final TagBinding FIRST_NAME = new TagBinding("first-name",
                                                               AttributeTag.class);
    public static final TagBinding LAST_NAME = new TagBinding("last-name",
                                                           AttributeTag.class);
    public static final TagBinding BROKEN = new TagBinding("broken",
                                                           BrokenTag.class);

    public PeopleTagLibrary() {
        registerTagBinding(PEOPLE);
        registerTagBinding(PERSON);
        registerTagBinding(FIRST_NAME);
        registerTagBinding(LAST_NAME);
        registerTagBinding(BROKEN);
    }
}
