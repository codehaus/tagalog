/*
 * $Id: CoreTagLibrary.java,v 1.2 2005-04-05 17:14:01 mhw Exp $
 */

package org.codehaus.tagalog.script.core.tags;

import org.codehaus.tagalog.AbstractTagLibrary;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public class CoreTagLibrary extends AbstractTagLibrary {
    public static final String NS_URI
        = "tagalog:core";

    public CoreTagLibrary() {
        registerTag("out", OutTag.class);
        registerTag("set", SetTag.class);
    }
}
