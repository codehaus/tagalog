/*
 * $Id: JavaTagLibraryResolver.java,v 1.2 2005-04-05 16:48:27 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * A tag library resolver for URIs that begin with "java:". The suffix
 * is the name of a Java class that implements the {@link TagLibrary}
 * interface.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class JavaTagLibraryResolver implements TagLibraryResolver {

    public String uriPrefix() {
        return "java";
    }

    public TagLibrary resolve(String uri) {
        ClassLoader loader;
        Class c;
        Object o;

        try {
            loader = Thread.currentThread().getContextClassLoader();
            c = loader.loadClass(uri);
            o = c.newInstance();
        } catch (Exception e) {
            return null;
        }
        if (o instanceof TagLibrary)
            return (TagLibrary) o;
        return null;
    }
}
