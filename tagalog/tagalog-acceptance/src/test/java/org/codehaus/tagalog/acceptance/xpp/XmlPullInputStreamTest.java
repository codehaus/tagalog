/*
 * $Id: XmlPullInputStreamTest.java,v 1.2 2004-12-03 14:41:48 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.xpp;

import java.io.InputStream;
import java.net.URL;

import org.codehaus.tagalog.ParserConfiguration;
import org.codehaus.tagalog.TagalogParser;
import org.codehaus.tagalog.acceptance.AbstractParserTest;
import org.codehaus.tagalog.xpp.TagalogXmlPullParserFactory;

/**
 * Test the {@link TagalogXmlPullParser} while reading
 * <code>InputStream</code>s.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class XmlPullInputStreamTest extends AbstractParserTest {
    protected TagalogParser createParser(URL testSource,
                                         ParserConfiguration configuration)
        throws Exception
    {
        TagalogXmlPullParserFactory factory = new TagalogXmlPullParserFactory(configuration);
        InputStream s = testSource.openStream();
        return factory.createParser(s);
    }

    public void testDummy() {
        // forces Eclipse to recognise the test
    }
}
