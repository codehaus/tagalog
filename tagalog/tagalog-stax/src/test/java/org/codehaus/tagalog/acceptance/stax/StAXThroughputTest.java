/*
 * $Id: StAXThroughputTest.java,v 1.1 2004-12-07 16:10:32 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.stax;

import java.io.Reader;

import org.codehaus.tagalog.TagalogParser;
import org.codehaus.tagalog.acceptance.AbstractThroughputTest;
import org.codehaus.tagalog.stax.TagalogStAXParserFactory;

/**
 * Simple throughput test, for performance comparisons.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class StAXThroughputTest extends AbstractThroughputTest {
    private TagalogStAXParserFactory factory;

    protected void setUp() {
        super.setUp();

        factory = new TagalogStAXParserFactory(getParserConfiguration());
    }

    protected TagalogParser createParser(Reader reader) throws Exception {
        return factory.createParser(reader);
    }

    public void testDummy() {
        // forces Eclipse to recognise the test
    }
}
