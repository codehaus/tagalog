/*
 * $Id: MockPrefixTagLibraryResolver.java,v 1.1 2004-02-10 18:56:05 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * MockPrefixTagLibraryResolver
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class MockPrefixTagLibraryResolver
    implements PrefixTagLibraryResolver
{
    private String prefix;

    private TagLibrary tagLibrary;

    public MockPrefixTagLibraryResolver(String prefix, TagLibrary tagLibrary) {
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
