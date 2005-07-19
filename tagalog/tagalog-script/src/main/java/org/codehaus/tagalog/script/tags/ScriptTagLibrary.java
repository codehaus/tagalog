/*
 * $Id: ScriptTagLibrary.java,v 1.4 2005-07-19 08:57:22 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.AbstractTagLibrary;
import org.codehaus.tagalog.TagBinding;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.4 $
 */
public class ScriptTagLibrary extends AbstractTagLibrary {
    public static final String NS_URI
        = "tagalog:tagalog";

    public static final String NS_NAMESPACE
        = "http://www.w3.org/XML/1998/namespace";

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
