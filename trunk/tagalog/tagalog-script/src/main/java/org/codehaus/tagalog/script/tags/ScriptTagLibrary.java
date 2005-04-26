/*
 * $Id: ScriptTagLibrary.java,v 1.3 2005-04-26 15:30:37 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.AbstractTagLibrary;
import org.codehaus.tagalog.TagBinding;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public class ScriptTagLibrary extends AbstractTagLibrary {
    public static final String NS_URI
        = "tagalog:tagalog";

    /**
     * Context key holding the expression language
     * {@link org.codehaus.tagalog.el.ParseController} at script parse-time.
     */
    public static final String TAGALOG_EL_PARSER = "tagalog.el.parser";

    public static final TagBinding SCRIPT = new TagBinding("script",
                                                           ScriptTag.class);

    public ScriptTagLibrary() {
        registerTagBinding(SCRIPT);
    }
}
