/*
 * $Id: SAXInputStreamTest.java,v 1.2 2004-02-11 12:49:18 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.sax;

import java.io.InputStream;
import java.net.URL;

import org.codehaus.tagalog.ParserConfiguration;
import org.codehaus.tagalog.TagalogParser;
import org.codehaus.tagalog.acceptance.AbstractParserTest;
import org.codehaus.tagalog.sax.TagalogSAXParserFactory;

/**
 * Test the {@link TagalogSAXParser} while reading <code>InputStream</code>s.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class SAXInputStreamTest extends AbstractParserTest {
    protected TagalogParser createParser(URL testSource,
                                         ParserConfiguration configuration)
        throws Exception
    {
        TagalogSAXParserFactory factory = new TagalogSAXParserFactory(configuration);
        InputStream s = testSource.openStream();
        return factory.createParser(s);
    }
}
