/*
 * $Id: TestSuiteTagLibrary.java,v 1.2 2005-04-07 15:56:15 mhw Exp $
 */

package org.codehaus.tagalog.script.testsuite.tags;

import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.script.tags.ScriptTagLibrary;

/**
 * Tag library for describing {@link Script} unit tests in XML.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class TestSuiteTagLibrary extends ScriptTagLibrary {
    public static final String NS_URI
        = "tagalog:script-test-suite";

    public static final TagBinding TEST_SUITE = new TagBinding("test-suite",
                                                               TestSuiteTag.class);
    public static final TagBinding TEST = new TagBinding("test",
                                                         TestTag.class);
    public static final TagBinding EXPECTED = new TagBinding("expected",
                                                             ExpectedTag.class);

    public TestSuiteTagLibrary() {
        registerTagBinding(TEST_SUITE);
        registerTagBinding(TEST);
        registerTagBinding(EXPECTED);
    }
}
