/*
 * $Id: TestSuiteTagLibrary.java,v 1.1 2005-04-05 17:17:27 mhw Exp $
 */

package org.codehaus.tagalog.script.testsuite.tags;

import org.codehaus.tagalog.script.tags.ScriptTagLibrary;

/**
 * Tag library for describing {@link Script} unit tests in XML.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class TestSuiteTagLibrary extends ScriptTagLibrary {
    public static final String NS_URI
        = "tagalog:script-test-suite";

    public TestSuiteTagLibrary() {
        registerTag("test-suite", TestSuiteTag.class);
        registerTag("test", TestTag.class);
        registerTag("expected", ExpectedTag.class);
    }
}
