/*
 * $Id: TagLibraryResolver.java,v 1.2 2005-04-05 16:48:27 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * A <code>TagLibraryResolver</code> can be used by
 * {@link ParserConfiguration} to resolve URIs that consist of
 * a prefix and a suffix separated by a colon. A single instance of
 * <code>TagLibraryResolver</code> handles a set of URIs with the
 * same prefix; the suffix can then be used to find an appropriate
 * {@link TagLibrary}. This interface is typically used to provide
 * a bridge into other tag-library systems.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public interface TagLibraryResolver {
    /**
     * Return the URI prefix for which the <code>TagLibraryResolver</code>
     * can create <code>TagLibrary</code>s.
     *
     * @return A URI prefix.
     */
    String uriPrefix();

    /**
     * Resolve a URI suffix to a <code>TagLibrary</code> instance.
     *
     * @param uri The URI suffix to resolve.
     * @return The tag library that handles tags in the namespace identified
     * by the URI, or <code>null</code> if no such tag library is known.
     */
    TagLibrary resolve(String uri);
}
