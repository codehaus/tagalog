/*
 * $Id: ParserConfiguration.java,v 1.10 2005-04-26 14:30:54 mhw Exp $
 */

package org.codehaus.tagalog;

import java.util.Map;

/**
 * The collection of information that defines the behaviour of a parser.
 * Instances of this class can be shared by many parser instances, reducing
 * the overhead of setting up a parser. The parser configuration specifies
 * how a parser should process XML elements during the parse; it does not
 * specify anything about how the parser is connected to a source of
 * XML parse events such as a SAX parser.
 *
 * <p>
 * The typical use of this class would be something like:
 * <pre>
 * config = new ParserConfiguration();
 * config.addTagLibrary(PeopleTagLibrary.NS_URI,
 *                      new PeopleTagLibrary());
 * config.setDefaultNamespace("tagalog:people");
 * factory = new TagalogSAXParserFactory(config);
 * </pre>
 * <code>factory</code> can now be used to create any number of parser
 * objects. Factory objects are thread-safe and so can be shared across
 * any number of threads; they would normally be singleton objects.
 * Once a <code>ParserConfiguration</code> has been passed to a
 * factory the factory is considered the owner of the configuration.
 * <code>ParserConfiguration</code>s should not be modified after they
 * have been used to create a factory, nor should they be shared by
 * multiple factory objects.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.10 $
 */
public final class ParserConfiguration {
    private String defaultNamespaceUri = "";

    private Map uriMap = new java.util.TreeMap();

    private TagLibrary processingInstructionTagLibrary;

    /**
     * Set the namespace that will be used if the document does not
     * specify a namespace itself.
     *
     * @param namespaceUri The URI of the default namespace.
     *
     * @throws NullPointerException If <code>namespaceUri</code> was
     * <code>null</code>.
     */
    public void setDefaultNamespace(String namespaceUri) {
        if (namespaceUri == null)
            throw new NullPointerException("null namespace URI");
        defaultNamespaceUri = namespaceUri;
    }

    /**
     * Return the namespace that will be used if the document does not
     * specify a namespace itself.
     *
     * @return namespaceUri The URI of the default namespace. Will not
     * be <code>null</code>.
     */
    public String getDefaultNamespace() {
        return defaultNamespaceUri;
    }

    /**
     * Associate a tag library with a URI. Note that tag libraries that are
     * registered with the prefix-based tag library resolvers will be found
     * first.
     *
     * @param uri URI of the tag library.
     * @param tagLibrary The tag library itself.
     */
    public void addTagLibrary(String uri, TagLibrary tagLibrary) {
        if (uri.length() == 0)
            throw new IllegalArgumentException("uri is empty");
        if (tagLibrary == null)
            throw new NullPointerException("tag library is null");
        uriMap.put(uri, tagLibrary);
    }

    /**
     * Register a <code>TagLibraryResolver</code> with the parser.
     *
     * @param resolver The tag library resolver to register.
     * @throws IllegalArgumentException if the prefix returned by the resolver
     * is empty.
     */
    public void addTagLibraryResolver(TagLibraryResolver resolver) {
        String uriPrefix = resolver.uriPrefix();
        if (uriPrefix.length() == 0)
            throw new IllegalArgumentException("resolver prefix is empty");
        uriMap.put(uriPrefix, resolver);
    }

    TagLibrary findTagLibrary(String uri) {
        TagLibrary tagLibrary = null;
        int colon;

        if (uri.length() == 0 && defaultNamespaceUri != null)
            uri = defaultNamespaceUri;
        colon = uri.indexOf(':');
        if (colon != -1) {
            String prefix = uri.substring(0, colon);
            Object o = uriMap.get(prefix);
            if (o instanceof TagLibraryResolver) {
                String suffix = uri.substring(colon + 1);
                tagLibrary = ((TagLibraryResolver) o).resolve(suffix);
            }
        }
        if (tagLibrary == null) {
            Object o = uriMap.get(uri);
            if (o instanceof TagLibrary)
                tagLibrary = (TagLibrary) o;
        }
        return tagLibrary;
    }

    /**
     * Set the processing instruction tag library for the parser configuration.
     * Tagalog provides some useful processing instruction handlers in the
     * {@link <a href="pi/package-summary.html">org.codehaus.tagalog.pi</a>}
     * package.
     * If this method is not called, or the tag library
     * is <code>null</code>, processing instructions are ignored.
     *
     * @param library Tag library to use to look up processing instructions
     */
    public void setProcessingInstructionTagLibrary(TagLibrary library) {
        processingInstructionTagLibrary = library;
    }

    /**
     * Return the processing instruction tag library to use for this parser
     * configuration, or <code>null</code> if none is being used.
     *
     * @return the processing instruction tag library, or <code>null</code>.
     */
    public TagLibrary getProcessingInstructionTagLibrary() {
        return processingInstructionTagLibrary;
    }
}
