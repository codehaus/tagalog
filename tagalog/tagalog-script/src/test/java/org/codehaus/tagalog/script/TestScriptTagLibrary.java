/*
 * $Id: TestScriptTagLibrary.java,v 1.2 2004-11-08 07:19:03 mhw Exp $
 */

package org.codehaus.tagalog.script;

import org.codehaus.tagalog.script.tags.ScriptTagLibrary;

/**
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class TestScriptTagLibrary extends ScriptTagLibrary {
    public static final String NS_URI
        = "tagalog:test-script";

    public TestScriptTagLibrary() {
        super();
        registerTag("sequence", SequenceTag.class);
        registerTag("collect", CollectTag.class);
    }
}
