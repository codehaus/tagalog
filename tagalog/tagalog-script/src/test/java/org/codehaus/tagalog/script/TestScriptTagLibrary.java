/*
 * $Id: TestScriptTagLibrary.java,v 1.1 2004-03-02 21:45:29 mhw Exp $
 */

package org.codehaus.tagalog.script;

import org.codehaus.tagalog.script.tags.ScriptTagLibrary;

/**
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class TestScriptTagLibrary extends ScriptTagLibrary {
    public static final String NS_URI
        = "tagalog:test-script";

    public TestScriptTagLibrary() {
        super();
        registerTag("collect", CollectTag.class);
    }
}
