/*
 * $Id: SAXThroughputTest.java,v 1.2 2004-12-07 14:09:10 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.sax;

import java.io.Reader;

import org.codehaus.tagalog.TagalogParser;
import org.codehaus.tagalog.acceptance.AbstractThroughputTest;
import org.codehaus.tagalog.sax.TagalogSAXParserFactory;

/**
 * Simple throughput test, for performance comparisons.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class SAXThroughputTest extends AbstractThroughputTest {
    private TagalogSAXParserFactory factory;

    protected void setUp() {
        super.setUp();

        factory = new TagalogSAXParserFactory(getParserConfiguration());
    }

    protected TagalogParser createParser(Reader reader) throws Exception {
        return factory.createParser(reader);
    }

    public void testDummy() {
        // forces Eclipse to recognise the test
    }
}
