/*
 * $Id: XmlPullThroughputTest.java,v 1.1 2004-12-07 14:09:24 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.xpp;

import java.io.Reader;

import org.codehaus.tagalog.TagalogParser;
import org.codehaus.tagalog.acceptance.AbstractThroughputTest;
import org.codehaus.tagalog.xpp.TagalogXmlPullParserFactory;

/**
 * Simple throughput test, for performance comparisons.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class XmlPullThroughputTest extends AbstractThroughputTest {
    private TagalogXmlPullParserFactory factory;

    protected void setUp() {
        super.setUp();

        factory = new TagalogXmlPullParserFactory(getParserConfiguration());
    }

    protected TagalogParser createParser(Reader reader) throws Exception {
        return factory.createParser(reader);
    }

    public void testDummy() {
        // forces Eclipse to recognise the test
    }
}
