/*
 * $Id: CoreTest.java,v 1.4 2005-04-26 16:41:25 mhw Exp $
 */

package org.codehaus.tagalog.script.core;

import org.codehaus.tagalog.ParserConfiguration;
import org.codehaus.tagalog.script.core.tags.CoreTagLibrary;
import org.codehaus.tagalog.script.tags.PITagLibrary;
import org.codehaus.tagalog.script.testsuite.ScriptTestCase;

/**
 * Run script unit tests for the core tags.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.4 $
 */
public class CoreTest extends ScriptTestCase {
    protected void configureParser(ParserConfiguration config) {
        config.addTagLibrary(CoreTagLibrary.NS_URI,
                             new CoreTagLibrary());

        config.setProcessingInstructionTagLibrary(new PITagLibrary());
    }

    public void testOut() throws Exception {
        addTests("OutTestSuite.xml");
        runTests();
    }

    public void testSet() throws Exception {
        addTests("SetTestSuite.xml");
        runTests();
    }

    public void testRemove() throws Exception {
        addTests("RemoveTestSuite.xml");
        runTests();
    }
}
