/*
 * $Id: StAXInputStreamTest.java,v 1.1 2004-11-17 18:04:35 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.stax;

import java.io.InputStream;
import java.net.URL;

import org.codehaus.tagalog.ParserConfiguration;
import org.codehaus.tagalog.TagalogParser;
import org.codehaus.tagalog.acceptance.AbstractParserTest;
import org.codehaus.tagalog.stax.TagalogStAXParserFactory;

/**
 * Test the {@link TagalogStAXParser} while reading <code>InputStream</code>s.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class StAXInputStreamTest extends AbstractParserTest {
    protected TagalogParser createParser(URL testSource,
                                         ParserConfiguration configuration)
        throws Exception
    {
        TagalogStAXParserFactory factory = new TagalogStAXParserFactory(configuration);
        InputStream s = testSource.openStream();
        return factory.createParser(s);
    }
}
