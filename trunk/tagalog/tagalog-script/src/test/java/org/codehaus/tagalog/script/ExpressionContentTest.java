/*
 * $Id: ExpressionContentTest.java,v 1.4 2005-05-17 16:41:37 krisb Exp $
 */

package org.codehaus.tagalog.script;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;
import java.util.StringTokenizer;

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
 * @version $Revision: 1.4 $
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
        
        String[] actual = getAsLines(out.toString().trim());
        
        String[] expected = new String[] {
                "Hello, Mark (4 characters)!",
                "ognl wouldn't like it"};
        
        assertEqualLines(expected, actual);
        
        //NOTE: This test differs in the number of \n found in the output
    }
    
    private static String[] getAsLines(String str) {
        StringTokenizer st = new StringTokenizer(str, "\n");
        String[] retVal = new String[st.countTokens()];
        for (int i = 0; i < retVal.length; i++) {
            retVal[i] = st.nextToken();
        }
        return retVal;
    }
    
    private void assertEqualLines(String[] expected, String[] actual) {
        if (expected != null) {
            if (actual == null) {
                fail("expected non-null but actual was null");
            }
            if (expected.length != actual.length) {
                fail("expected length:<" + expected.length + "> but was:<"
                     + actual.length+">");
            }
            for (int i = 0; i < expected.length; i++) {
                if ((expected[i] == null)
                        ? actual[i] != null
                        : !expected[i].equals(actual[i])) {
                    fail("expected[" + i + "]:<" + expected[i] + "> but was:<"
                         + actual[i] + ">");
                }
            }
        } else {
            if (actual != null) {
                fail("expected null but actual was non-null");
            }
        }
    }
    
}
