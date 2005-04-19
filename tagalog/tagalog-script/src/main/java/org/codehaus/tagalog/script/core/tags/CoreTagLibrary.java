/*
 * $Id: CoreTagLibrary.java,v 1.4 2005-04-19 16:40:02 mhw Exp $
 */

package org.codehaus.tagalog.script.core.tags;

import org.codehaus.tagalog.AbstractTagLibrary;
import org.codehaus.tagalog.TagBinding;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.4 $
 */
public class CoreTagLibrary extends AbstractTagLibrary {
    public static final String NS_URI
        = "tagalog:core";

    public static final TagBinding OUT = new TagBinding("out",
                                                        OutTag.class);
    public static final TagBinding SET = new TagBinding("set",
                                                        SetTag.class);
    public static final TagBinding REMOVE = new TagBinding("remove",
                                                           RemoveTag.class);

    public CoreTagLibrary() {
        registerTagBinding(OUT);
        registerTagBinding(SET);
        registerTagBinding(REMOVE);
    }
}
