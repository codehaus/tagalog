/*
 * $Id: CoreTest.java,v 1.1 2005-04-05 17:17:50 mhw Exp $
 */

package org.codehaus.tagalog.script.core;

import org.codehaus.tagalog.ParserConfiguration;
import org.codehaus.tagalog.script.core.tags.CoreTagLibrary;
import org.codehaus.tagalog.script.testsuite.ScriptTestCase;

/**
 * Run script unit tests for the core tags.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class CoreTest extends ScriptTestCase {
    protected void configureParser(ParserConfiguration config) {
        config.addTagLibrary(CoreTagLibrary.NS_URI,
                             new CoreTagLibrary());
    }

    public void testCore() throws Exception {
        addTests("OutTestSuite.xml");

        runTests();
    }
}
