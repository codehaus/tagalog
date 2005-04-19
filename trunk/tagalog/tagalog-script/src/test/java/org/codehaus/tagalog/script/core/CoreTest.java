/*
 * $Id: CoreTest.java,v 1.2 2005-04-19 16:40:02 mhw Exp $
 */

package org.codehaus.tagalog.script.core;

import org.codehaus.tagalog.ParserConfiguration;
import org.codehaus.tagalog.script.core.tags.CoreTagLibrary;
import org.codehaus.tagalog.script.testsuite.ScriptTestCase;

/**
 * Run script unit tests for the core tags.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class CoreTest extends ScriptTestCase {
    protected void configureParser(ParserConfiguration config) {
        config.addTagLibrary(CoreTagLibrary.NS_URI,
                             new CoreTagLibrary());
    }

    public void testCore() throws Exception {
        addTests("OutTestSuite.xml");
        addTests("RemoveTestSuite.xml");

        runTests();
    }
}
