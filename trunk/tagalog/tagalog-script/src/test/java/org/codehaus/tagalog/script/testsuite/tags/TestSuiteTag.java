/*
 * $Id: TestSuiteTag.java,v 1.2 2005-04-19 17:23:40 mhw Exp $
 */

package org.codehaus.tagalog.script.testsuite.tags;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.Tag;
import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;
import org.codehaus.tagalog.script.testsuite.Test;
import org.codehaus.tagalog.script.testsuite.TestSuite;

/**
 * <code>Tag</code> for handling the &lt;test-suite&gt; element.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class TestSuiteTag extends AbstractTag implements Tag {
    private TestSuite suite;

    public void begin(String elementName, Attributes attributes)
        throws TagException, TagalogParseException
    {
        suite = new TestSuite();
    }

    public void child(TagBinding childType, Object child)
        throws TagException, TagalogParseException
    {
        if (childType == TestSuiteTagLibrary.TEST)
            suite.addTest((Test) child);
    }

    public Object end(String elementName)
        throws TagException, TagalogParseException
    {
        return suite;
    }

    public boolean recycle() {
        suite = null;
        return super.recycle();
    }
}
