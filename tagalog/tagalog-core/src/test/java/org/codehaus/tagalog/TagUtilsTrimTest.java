/*
 * $Id: TagUtilsTrimTest.java,v 1.3 2005-06-03 16:48:51 krisb Exp $
 */

package org.codehaus.tagalog;

import java.net.URL;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.codehaus.tagalog.sax.TagalogSAXParserFactory;
import org.codehaus.tagalog.tags.StringTag;

/**
 * Tests for the content trimming methods of the
 * {@link TagUtils} class.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class TagUtilsTrimTest extends TestCase {

    public void testTrimNull() {
        assertNull(TagUtils.trim(null));
    }

    public void _testDoubleNewLineSmartTrim() throws Exception {
        String test = "\n\n    Hello, World\n\n  ";
        String expected = "Hello, World";
        assertEquals(expected, TagUtils.trim(test));
    }

    public void testSmartTrimming() throws Exception {
        ParserConfiguration config = new ParserConfiguration();

        config.addTagLibrary(TestSuiteTagLibrary.NS_URI,
                             new TestSuiteTagLibrary());
        config.setDefaultNamespace(TestSuiteTagLibrary.NS_URI);

        TagalogSAXParserFactory factory = new TagalogSAXParserFactory(config);
        URL url = this.getClass().getResource("TagUtilsTrimTest.xml");
        TagalogParser parser = factory.createParser(url);

        TrimTest[] tests = (TrimTest[]) parser.parse();
        ParseError[] errors = parser.parseErrors();
        if (errors.length != 0)
            throw new ParseFailedException(errors);

        for (int i = 0; i < tests.length; i++) {
            TrimTest test = tests[i];
            assertEquals(test.expected, TagUtils.trim(test.content));
        }
    }

    private static final class TrimTest {
        public String expected;
        public String content;

        public TrimTest(String expected, String content) {
            this.expected = expected;
            this.content = content;
        }
    }

    static final class TestSuiteTagLibrary extends AbstractTagLibrary {
        public static final String NS_URI = "tagalog:trimtest";

        public static final TagBinding
                TEST_SUITE = new TagBinding("test-suite", TestSuiteTag.class);
        public static final TagBinding
                TEST = new TagBinding("test", TestTag.class);

        public TestSuiteTagLibrary() {
            registerTagBinding(TEST_SUITE);
            registerTagBinding(TEST);
        }
    }

    static final class TestSuiteTag extends AbstractTag {
        private ArrayList tests;

        public void begin(String elementName, Attributes attributes)
            throws TagException
        {
            tests = new ArrayList();
        }

        public void child(TagBinding childType, Object child)
            throws TagException
        {
            if (childType == TestSuiteTagLibrary.TEST)
                tests.add(child);
        }

        public Object end(String elementName) throws TagException {
            return (TrimTest[]) tests.toArray(new TrimTest[tests.size()]);
        }

        public boolean recycle() {
            tests = null;
            return super.recycle();
        }
    }

    static final class TestTag extends StringTag {
        private String expect;

        public void begin(String elementName, Attributes attributes)
            throws TagException
        {
            super.begin(elementName, attributes);

            String s = requireAttribute(attributes, "expect");
            expect = s.replaceAll("\\\\n", "\n");
        }

        public Object end(String elementName) throws TagException {
            return new TrimTest(expect, (String) super.end(elementName));
        }

        public boolean recycle() {
            expect = null;
            return super.recycle();
        }
    }
}
