/*
 * $Id: StAXAttributes.java,v 1.1 2004-11-17 18:04:35 mhw Exp $
 */

package org.codehaus.tagalog.stax;

import javax.xml.stream.XMLStreamReader;

import org.codehaus.tagalog.Attributes;

/**
 * A simple wrapper to convert the API of the StAX attribute
 * access methods into the Tagalog {@link Attributes} interface.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
class StAXAttributes implements Attributes {
    XMLStreamReader reader;

    public StAXAttributes(XMLStreamReader reader) {
        this.reader = reader;
    }

    public int getAttributeCount() {
        return reader.getAttributeCount();
    }

    public String getNamespaceUri(int attributeIndex) {
        return reader.getAttributeNamespace(attributeIndex);
    }

    public String getName(int attributeIndex) {
        return reader.getAttributeLocalName(attributeIndex);
    }

    public String getValue(int attributeIndex) {
        return reader.getAttributeValue(attributeIndex);
    }

    public String getValue(String attributeName) {
        return reader.getAttributeValue("", attributeName);
    }

    public String getValue(String namespaceUri, String attributeName) {
        return reader.getAttributeValue(namespaceUri, attributeName);
    }
}
