/*
 * $Id: CatalogTest.java,v 1.1 2004-10-06 10:16:21 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.net.URL;

import org.codehaus.plexus.PlexusTestCase;
import org.codehaus.tagalog.TagalogParseException;

/**
 * Basic tests for {@link Catalog}: construction and parsing.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class CatalogTest extends PlexusTestCase {
    private URL url;

    private Catalog catalog;

    private URL resource(String name) {
        return CatalogTest.class.getResource(name);
    }

    public void testParsing() throws Exception {
        try {
            url = resource("CatalogTest-parse-fail.xml");
            catalog = new Catalog(getContainer(), url);
            fail("parse succeeded");
        } catch (TagalogParseException e) {
            // expected
        }

        url = resource("CatalogTest-unknown-tag.xml");
        catalog = new Catalog(getContainer(), url);
        assertNotNull(catalog.parseErrors());
        try {
            catalog.run("foo");
            fail("foo existed");
        } catch (IllegalArgumentException e) {
            assertEquals("unknown proc 'foo'", e.getMessage());
        }

        url = resource("CatalogTest-good.xml");
        catalog = new Catalog(getContainer(), url);
        assertNull(catalog.parseErrors());
    }

    public void testGetContainer() throws Exception {
        url = resource("CatalogTest-good.xml");
        catalog = new Catalog(getContainer(), url);
        assertSame(getContainer(), catalog.getContainer());
    }
}
