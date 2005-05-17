/*
 * $Id: PeopleTagLibrary.java,v 1.7 2005-05-17 14:21:38 krisb Exp $
 */

package org.codehaus.tagalog.acceptance.people;

import org.codehaus.tagalog.AbstractTagLibrary;
import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.tags.StringTag;
import org.codehaus.tagalog.tags.StringTreeTag;

/**
 * A tag library containing some simple information about people.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.7 $
 */
public final class PeopleTagLibrary extends AbstractTagLibrary {
    public static final String NS_URI = "tagalog:people";

    public static final
            TagBinding PEOPLE     = new TagBinding("people",
                                                   PeopleTag.class);
    public static final
            TagBinding PERSON     = new TagBinding("person",
                                                   PersonTag.class);
    public static final
            TagBinding FIRST_NAME = new TagBinding("first-name",
                                                   StringTag.class);
    public static final
            TagBinding LAST_NAME  = new TagBinding("last-name",
                                                   StringTag.class);
    public static final
            TagBinding COMMENT    = new TagBinding("comment",
                                                   StringTreeTag.class);
    public static final
            TagBinding BOLD       = new TagBinding("b",
                                                   StringTreeTag.class);
    public static final
            TagBinding BROKEN     = new TagBinding("broken",
                                                   BrokenTag.class);

    public static final
            TagBinding TAG_ERROR  = new TagBinding("tag-error",
                                                   TagErrorTag.class);
    
    public PeopleTagLibrary() {
        registerTagBinding(PEOPLE);
        registerTagBinding(PERSON);
        registerTagBinding(FIRST_NAME);
        registerTagBinding(LAST_NAME);
        registerTagBinding(COMMENT);
        registerTagBinding(BOLD);
        registerTagBinding(BROKEN);
        registerTagBinding(TAG_ERROR);
    }
}
