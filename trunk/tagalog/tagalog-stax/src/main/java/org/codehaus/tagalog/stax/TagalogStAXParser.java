/*
 * $Id: TagalogStAXParser.java,v 1.2 2005-04-14 14:09:16 mhw Exp $
 */

package org.codehaus.tagalog.stax;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.tagalog.AbstractParser;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.Location;
import org.codehaus.tagalog.ParserConfiguration;
import org.codehaus.tagalog.TagalogParseException;

/**
 * TagalogStAXParser
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
final class TagalogStAXParser extends AbstractParser {
    private XMLStreamReader xpp;

    TagalogStAXParser(ParserConfiguration configuration, XMLStreamReader xpp) {
        super(configuration);
        if (xpp == null)
            throw new NullPointerException("stax stream reader is null");
        this.xpp = xpp;
    }

    protected void doParse() throws TagalogParseException {
        try {
            internalDoParse();
        } catch (XMLStreamException e) {
            throw new TagalogParseException(e);
        }
    }

    private void internalDoParse()
        throws TagalogParseException, XMLStreamException
    {
        Attributes attributes = new StAXAttributes(xpp);
        boolean handlingProcessingInstructions = handlingProcessingInstructions();
        int eventType;

        handlingProcessingInstructions = handlingProcessingInstructions();
        while ((eventType = xpp.next()) != XMLStreamConstants.END_DOCUMENT) {
            if (eventType == XMLStreamConstants.START_ELEMENT) {
                startElement(xpp.getNamespaceURI(), xpp.getLocalName(), attributes);
            } else if (eventType == XMLStreamConstants.CHARACTERS
                       || eventType == XMLStreamConstants.CDATA) {
                char[] chars = xpp.getTextCharacters();
                text(chars, xpp.getTextStart(), xpp.getTextLength());
            } else if (eventType == XMLStreamConstants.END_ELEMENT) {
                endElement(xpp.getNamespaceURI(), xpp.getLocalName());
            } else if (eventType == XMLStreamConstants.PROCESSING_INSTRUCTION
                       && handlingProcessingInstructions) {
                // fudge for suspected bug in both RI and woodstox
                String data = xpp.getPIData();
                if (data != null && data.startsWith(" "))
                    data = data.substring(1);
                processingInstruction(xpp.getPITarget(), data);
            }
        }
        xpp.close();
    }

    public Location getLocation() {
        javax.xml.stream.Location location = xpp.getLocation();
        return new Location(location.getSystemId(),
                            location.getLineNumber(),
                            location.getColumnNumber());
    }
}
