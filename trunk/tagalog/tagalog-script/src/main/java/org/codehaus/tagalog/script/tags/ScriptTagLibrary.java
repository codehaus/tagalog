/*
 * $Id: ScriptTagLibrary.java,v 1.1 2004-11-04 18:04:57 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.AbstractTagLibrary;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public class ScriptTagLibrary extends AbstractTagLibrary {
    public static final String NS_URI
        = "tagalog:tagalog";

    public ScriptTagLibrary() {
        registerTag("script", ScriptTag.class);
    }
}
