/*
 * $Id: MockTagLibraryResolver.java,v 1.1 2005-04-05 16:48:27 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * MockTagLibraryResolver
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class MockTagLibraryResolver
    implements TagLibraryResolver
{
    private String prefix;

    private TagLibrary tagLibrary;

    public MockTagLibraryResolver(String prefix, TagLibrary tagLibrary) {
        this.prefix = prefix;
        this.tagLibrary = tagLibrary;
    }

    public String uriPrefix() {
        return prefix;
    }

    public TagLibrary resolve(String uri) {
        return tagLibrary;
    }
}
