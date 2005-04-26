/*
 * $Id: ExpressionContentTest.java,v 1.3 2005-04-26 15:35:56 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;

import junit.framework.TestCase;

import org.codehaus.tagalog.ParserConfiguration;
import org.codehaus.tagalog.TagalogParser;
import org.codehaus.tagalog.sax.TagalogSAXParserFactory;
import org.codehaus.tagalog.script.tags.PITagLibrary;
import org.codehaus.tagalog.script.tags.ScriptTagLibrary;

/**
 * ExpressionContentTest
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class ExpressionContentTest extends TestCase {
    private ParserConfiguration configuration;

    private TagalogSAXParserFactory factory;

    public void setUp() {
        configuration = new ParserConfiguration();
        configuration.addTagLibrary(ScriptTagLibrary.NS_URI,
                                    new ScriptTagLibrary());
        configuration.setProcessingInstructionTagLibrary(new PITagLibrary());
        factory = new TagalogSAXParserFactory(configuration);
    }

    public void testScript() throws Exception {
        InputStream in;
        TagalogParser parser;
        Map context;

        in = ScriptTest.class.getResourceAsStream("ExpressionContentTest.xml");
        parser = factory.createParser(in);
        Script script = (Script) parser.parse();
        assertEquals(0, parser.parseErrors().length);

        context = new java.util.HashMap();
        context.put("name", "Mark");
        context.put("tagalog.out", new StringWriter());
        script.execute(context);

        StringWriter out = (StringWriter) context.get("tagalog.out");
        assertEquals("Hello, Mark!", out.toString().trim());
    }

    public void testExpressionLanguageSwitching() throws Exception {
        InputStream in;
        TagalogParser parser;
        Map context;

        in = ScriptTest.class.getResourceAsStream("ExpressionLanguageSwitchingTest.xml");
        parser = factory.createParser(in);
        Script script = (Script) parser.parse();
        assertEquals(0, parser.parseErrors().length);

        context = new java.util.HashMap();
        context.put("name", "Mark");
        context.put("$", "ognl wouldn't like it");
        context.put("tagalog.out", new StringWriter());
        script.execute(context);

        StringWriter out = (StringWriter) context.get("tagalog.out");
        assertEquals("Hello, Mark (4 characters)!\nognl wouldn't like it",
                     out.toString().trim());
    }
}
