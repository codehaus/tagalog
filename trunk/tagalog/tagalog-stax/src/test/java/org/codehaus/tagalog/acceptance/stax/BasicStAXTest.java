/*
 * $Id: BasicStAXTest.java,v 1.1 2004-11-17 18:04:35 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.stax;

import java.io.InputStream;
import java.net.URL;

import org.codehaus.tagalog.ParserConfiguration;
import org.codehaus.tagalog.TagalogParser;
import org.codehaus.tagalog.acceptance.AbstractParserTest;
import org.codehaus.tagalog.acceptance.people.People;
import org.codehaus.tagalog.acceptance.people.PeopleTagLibrary;
import org.codehaus.tagalog.stax.TagalogStAXParserFactory;

import junit.framework.TestCase;

/**
 * A basic test of the StAX driver for Tagalog, intended as much as a
 * demonstration of the API.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class BasicStAXTest extends TestCase {
    public void testParse() throws Exception {
        ParserConfiguration config;
        TagalogStAXParserFactory factory;
        URL url;
        InputStream stream;
        TagalogParser parser;
        People people;

        config = new ParserConfiguration();
        config.addTagLibrary(PeopleTagLibrary.NS_URI,
                                          new PeopleTagLibrary());
        config.setDefaultNamespace("tagalog:people");
        factory = new TagalogStAXParserFactory(config);

        url = AbstractParserTest.class.getResource("people-no-ns.xml");
        stream = url.openStream();
        parser = factory.createParser(stream);
        people = (People) parser.parse();
        assertEquals(2, people.getPeople().size());
    }
}
