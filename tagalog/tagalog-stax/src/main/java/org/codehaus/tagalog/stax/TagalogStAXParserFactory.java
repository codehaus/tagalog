/*
 * $Id: TagalogStAXParserFactory.java,v 1.2 2005-04-14 14:09:16 mhw Exp $
 */

package org.codehaus.tagalog.stax;

import java.io.InputStream;
import java.io.Reader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.tagalog.ParserConfiguration;
import org.codehaus.tagalog.TagalogParser;

/**
 * TagalogStAXParserFactory
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class TagalogStAXParserFactory {
    private ParserConfiguration configuration;

    private XMLInputFactory factory;

    /**
     * Create a factory for parsers that will use the StAX Pull Parser
     * as the low-level XML parser. All parsers created by this factory
     * will share the configuration passed to the constructor. The
     * parser configuration should not be altered after it has been
     * passed to a parser factory.
     *
     * @param configuration The configuration for parsers created by this
     * factory. Configurations should not be shared or altered after they
     * have been used to create a factory.
     */
    public TagalogStAXParserFactory(ParserConfiguration configuration) {
        if (configuration == null)
            throw new NullPointerException("configuration is null");
        this.configuration = configuration;
    }

    private synchronized final XMLInputFactory factory() {
        if (factory == null) {
            factory = XMLInputFactory.newInstance();
        }
        return factory;
    }

    private final TagalogParser createParser(XMLStreamReader reader) {
        return new TagalogStAXParser(configuration, reader);
    }

    public TagalogParser createParser(InputStream byteStream)
        throws XMLStreamException
    {
        return createParser(factory().createXMLStreamReader(byteStream));
    }

    public TagalogParser createParser(InputStream byteStream, String encoding)
        throws XMLStreamException
    {
        return createParser(factory().createXMLStreamReader(byteStream,
                                                            encoding));
    }

    public TagalogParser createParser(Reader characterStream)
        throws XMLStreamException
    {
        return createParser(factory().createXMLStreamReader(characterStream));
    }
}
