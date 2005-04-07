/*
 * $Id: ScriptTagLibrary.java,v 1.2 2005-04-07 15:56:15 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.AbstractTagLibrary;
import org.codehaus.tagalog.TagBinding;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public class ScriptTagLibrary extends AbstractTagLibrary {
    public static final String NS_URI
        = "tagalog:tagalog";

    public static final TagBinding SCRIPT = new TagBinding("script",
                                                           ScriptTag.class);

    public ScriptTagLibrary() {
        registerTagBinding(SCRIPT);
    }
}
