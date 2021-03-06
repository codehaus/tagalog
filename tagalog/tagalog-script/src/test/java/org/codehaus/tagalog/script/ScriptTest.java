/*
 * $Id: ScriptTest.java,v 1.3 2004-10-29 13:50:15 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.codehaus.tagalog.ParserConfiguration;
import org.codehaus.tagalog.TagalogParser;
import org.codehaus.tagalog.sax.TagalogSAXParserFactory;

/**
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class ScriptTest extends TestCase {
    private ParserConfiguration configuration;

    private TagalogSAXParserFactory factory;

    public void setUp() {
        configuration = new ParserConfiguration();
        configuration.addTagLibrary(TestScriptTagLibrary.NS_URI,
                                    new TestScriptTagLibrary());
        factory = new TagalogSAXParserFactory(configuration);
    }

    public void testScript() throws Exception {
        InputStream in;
        TagalogParser parser;
        List collection;
        Map context;

        in = ScriptTest.class.getResourceAsStream("ScriptTest.xml");
        parser = factory.createParser(in);
        Script script = (Script) parser.parse();
        assertEquals(0, parser.parseErrors().length);

        collection = new java.util.ArrayList();
        context = new java.util.HashMap();
        context.put("collection", collection);
        script.execute(context);

        assertEquals(7, collection.size());
        assertEquals("[start, one, two, middle, three, four, end]",
                     collection.toString());
    }
}
