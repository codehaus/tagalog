/*
 * $Id: TagalogSAXParserFactory.java,v 1.2 2004-12-03 14:48:36 mhw Exp $
 */

package org.codehaus.tagalog.sax;

import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.codehaus.tagalog.TagalogParser;
import org.codehaus.tagalog.ParserConfiguration;

/**
 * TagalogSAXParserFactory
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class TagalogSAXParserFactory {
    private ParserConfiguration configuration;

    private SAXParserFactory factory;

    /**
     * Create a factory for parsers that will use the default SAX parser
     * as the low-level XML parser. All parsers created by this factory
     * will share the configuration passed to the constructor. The
     * parser configuration should not be altered after it has been
     * passed to a parser factory.
     *
     * @param configuration The configuration for parsers created by this
     * factory. Configurations should not be shared or altered after they
     * have been used to create a factory.
     */
    public TagalogSAXParserFactory(ParserConfiguration configuration) {
        if (configuration == null)
            throw new NullPointerException("configuration is null");
        this.configuration = configuration;
    }

    private synchronized final SAXParser getSAXParser()
        throws ParserConfigurationException, SAXException
    {
        if (factory == null) {
            factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
        }
        return factory.newSAXParser();
    }

    public TagalogParser createParser(InputSource inputSource)
        throws ParserConfigurationException, SAXException
    {
        return new TagalogSAXParser(configuration, getSAXParser(), inputSource);
    }

    public TagalogParser createParser(InputStream byteStream)
        throws ParserConfigurationException, SAXException
    {
        return createParser(new InputSource(byteStream));
    }

    public TagalogParser createParser(Reader characterStream)
        throws ParserConfigurationException, SAXException
    {
        return createParser(new InputSource(characterStream));
    }

    public TagalogParser createParser(URL url)
        throws ParserConfigurationException, SAXException
    {
        return createParser(new InputSource(url.toExternalForm()));
    }
}
