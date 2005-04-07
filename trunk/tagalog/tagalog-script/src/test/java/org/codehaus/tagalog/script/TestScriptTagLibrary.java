/*
 * $Id: TestScriptTagLibrary.java,v 1.3 2005-04-07 15:56:15 mhw Exp $
 */

package org.codehaus.tagalog.script;

import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.script.tags.ScriptTagLibrary;

/**
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class TestScriptTagLibrary extends ScriptTagLibrary {
    public static final String NS_URI
        = "tagalog:test-script";

    public static final TagBinding SEQUENCE = new TagBinding("sequence",
                                                             SequenceTag.class);
    public static final TagBinding COLLECT = new TagBinding("collect",
                                                            CollectTag.class);

    public TestScriptTagLibrary() {
        super();
        registerTagBinding(SEQUENCE);
        registerTagBinding(COLLECT);
    }
}
