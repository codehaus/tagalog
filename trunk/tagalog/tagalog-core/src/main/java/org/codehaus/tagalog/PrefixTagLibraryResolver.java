/*
 * $Id: PrefixTagLibraryResolver.java,v 1.1 2004-02-10 18:56:05 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * A <code>PrefixTagLibraryResolver</code> resolves URIs that consist of
 * a prefix and a suffix separated by a colon. An instance of
 * <code>PrefixTagLibraryResolver</code> handles a set of URIs with the
 * same prefix.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public interface PrefixTagLibraryResolver extends TagLibraryResolver {
    /**
     * Return the URI prefix for which the
     * <code>PrefixTagLibraryResolver</code> can create
     * <code>TagLibrary</code>s.
     *
     * @return A URI prefix.
     */
    String uriPrefix();
}
