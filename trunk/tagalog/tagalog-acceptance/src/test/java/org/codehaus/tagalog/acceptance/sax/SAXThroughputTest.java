/*
 * $Id: SAXThroughputTest.java,v 1.1 2004-12-07 13:56:16 mhw Exp $
 */

package org.codehaus.tagalog.acceptance.sax;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import junit.framework.TestCase;

import org.codehaus.tagalog.ParserConfiguration;
import org.codehaus.tagalog.TagalogParser;
import org.codehaus.tagalog.acceptance.people.People;
import org.codehaus.tagalog.acceptance.people.PeopleTagLibrary;
import org.codehaus.tagalog.sax.TagalogSAXParserFactory;

/**
 * Simple throughput test, for performance comparisons.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class SAXThroughputTest extends TestCase {
    private static final int K = 1024;
    private static final int SIZE = 512 * K;
    private static final int ITERATIONS = 20;

    private ParserConfiguration config;

    private TagalogSAXParserFactory factory;

    protected void setUp() {
        config = new ParserConfiguration();
        config.addTagLibrary(PeopleTagLibrary.NS_URI,
                                          new PeopleTagLibrary());
        config.setDefaultNamespace("tagalog:people");
        factory = new TagalogSAXParserFactory(config);
    }

    private static String document;

    private static int personCount;

    private synchronized Reader getDocumentReader() {
        if (document == null) {
            StringWriter writer = new StringWriter();

            writer.write("<?xml version=\"1.0\"?>\n");
            writer.write("\n");
            writer.write("<people xmlns=\"tagalog:people\">\n");
            while (writer.getBuffer().length() < SIZE) {
                writer.write("  <person user-id=\"mhw\">\n");
                writer.write("    <first-name>Mark</first-name>\n");
                writer.write("    <last-name>Wilkinson</last-name>\n");
                writer.write("  </person>\n");
                personCount++;
            }
            writer.write("</people>\n");
            document = writer.toString();
        }
        return new StringReader(document);
    }

    public void testParse() throws Exception {
        Reader reader;
        long start;
        long end;
        TagalogParser parser;
        People people;

        reader = getDocumentReader();
        start = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i++) {
            parser = factory.createParser(reader);
            people = (People) parser.parse();
            assertEquals(personCount, people.getPeople().size());
            reader = getDocumentReader();
        }
        end = System.currentTimeMillis();

        int chars = ITERATIONS * document.length();
        long millis = end - start;
        double throughput = 1000 * chars / millis;

        System.out.println("throughput: " + throughput + "cps");
    }
}
