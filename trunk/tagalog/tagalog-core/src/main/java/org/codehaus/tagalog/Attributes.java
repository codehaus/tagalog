/*
 * $Id: Attributes.java,v 1.1 2004-02-11 17:27:54 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * Collection of XML attributes supplied to the {@link Tag#begin} method.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public interface Attributes {
    /**
     * Return the number of attributes that the element has.
     *
     * @return the number of attributes that the element has.
     */
    int getAttributeCount();

    String getNamespaceUri(int attributeIndex);

    String getName(int attributeIndex);

    String getValue(int attributeIndex);

    String getValue(String attributeName);

    String getValue(String namespaceUri, String attributeName);
}
