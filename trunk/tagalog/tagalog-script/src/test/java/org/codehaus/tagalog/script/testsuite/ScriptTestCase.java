/*
 * $Id: ScriptTestCase.java,v 1.3 2005-04-19 20:58:25 mhw Exp $
 */

package org.codehaus.tagalog.script.testsuite;

import java.io.InputStream;

import junit.framework.TestCase;

import org.codehaus.tagalog.ParseFailedException;
import org.codehaus.tagalog.ParserConfiguration;
import org.codehaus.tagalog.TagalogParser;
import org.codehaus.tagalog.sax.TagalogSAXParserFactory;
import org.codehaus.tagalog.script.testsuite.tags.TestSuiteTagLibrary;

/**
 * Base class for test cases that are loaded from XML files containing
 * scripts.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public abstract class ScriptTestCase extends TestCase {
    private TagalogSAXParserFactory parserFactory;

    private TestSuite scriptSuite;

    protected void setUp() throws Exception {
        super.setUp();

        setUpParser();

        scriptSuite = new TestSuite();
    }

    private void setUpParser() {
        ParserConfiguration config = new ParserConfiguration();

        config.addTagLibrary(TestSuiteTagLibrary.NS_URI,
                             new TestSuiteTagLibrary());
        config.setDefaultNamespace(TestSuiteTagLibrary.NS_URI);

        configureParser(config);

        parserFactory = new TagalogSAXParserFactory(config);
    }

    protected abstract void configureParser(ParserConfiguration config);

    protected void addTests(String scriptName) throws Exception {
        InputStream stream = targetClass().getResourceAsStream(scriptName);

        if (stream == null)
            throw new IllegalArgumentException("could not find resource "
                                               + scriptName);

        TagalogParser parser = parserFactory.createParser(stream);

        scriptSuite = (TestSuite) parser.parse();

        if (parser.parseErrors().length != scriptSuite.caughtParseErrors())
            throw new ParseFailedException(parser.parseErrors());
    }

    /**
     * Return the <code>Class</code> object to be used to locate
     * resources in {@link #addTests}. This implementtion uses the
     * class of the concrete test (i.e. <code>this.getClass()</code>).
     * Putting this in a protected method is mainly to silence a
     * FindBugs warning about this usage of <code>getResourceAsStream</code>.
     */
    private final Class targetClass() {
        return this.getClass();
    }

    protected void runTests() throws Exception {
        scriptSuite.run();
    }
}
