/*
 * $Id: AbstractParser.java,v 1.18 2005-05-17 14:17:47 krisb Exp $
 */

package org.codehaus.tagalog;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The actual tagalog parser. Processes events received from an XML parser
 * and hands them off to {@link NodeHandler}s.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.18 $
 */
public abstract class AbstractParser implements TagalogParser {
    private ParserConfiguration configuration;

    private boolean handlingPIs;

    private Map context;

    private Object parseResult;

    private NodeHandler currentTag;

    /**
     * Construct an <code>AbstractParser</code> that uses the specified
     * parser configuration.
     *
     * @param configuration Object holding the parser configuration.
     */
    protected AbstractParser(ParserConfiguration configuration) {
        this.configuration = configuration;
        this.handlingPIs = (configuration.getProcessingInstructionTagLibrary() != null);
    }

    public boolean handlingPIs() {
        return handlingPIs;
    }

    public Object parse() throws TagalogParseException {
        return parse(new java.util.HashMap());
    }

    public Object parse(Map context) throws TagalogParseException {
        if (this.context != null)
            throw new IllegalStateException("attempt to reuse parser");
        this.context = context;
        try {
            doParse();
        } catch (TagError e) {
            throw new TagalogParseException(e);
        }
        return parseResult;
    }

    protected abstract void doParse() throws TagalogParseException;

    protected void startElement(String namespaceUri, String elementName,
                                Attributes attributes)
        throws TagalogParseException
    {
        TagLibrary tagLibrary;
        Tag tag;

        tagLibrary = configuration.findTagLibrary(namespaceUri);
        if (tagLibrary == null) {
            tagLibrary = NullTagLibrary.INSTANCE;
            noTagLibrary(namespaceUri);
        }
        tag = (Tag) tagLibrary.getNodeHandler(elementName);
        if (tag == null) {
            tag = (Tag) NullTagLibrary.INSTANCE.getNodeHandler(elementName);
            noTag(elementName, namespaceUri);
        }
        tag.setParser(this);
        tag.setContext(context);
        if (currentTag != null)
            tag.setParent(currentTag);
        currentTag = tag;
        try {
            tag.begin(elementName, attributes);
        } catch (TagException e) {
            addError(e);
        }
    }

    protected void text(char[] characters, int start, int length)
        throws TagalogParseException
    {
        try {
            ((Tag) currentTag).text(characters, start, length);
        } catch (TagException e) {
            addError(e);
        }
    }

    protected void endElement(String namespaceUri, String elementName)
        throws TagalogParseException
    {
        TagLibrary tagLibrary;
        Tag tag;
        Tag parentTag;
        Object value = null;

        tagLibrary = configuration.findTagLibrary(namespaceUri);
        if (tagLibrary == null) {
            tagLibrary = NullTagLibrary.INSTANCE;
            // we've already reported the error when we handled the start tag
        }
        tag = (Tag) currentTag;
        if (tag instanceof NullTagLibrary.NullTag)
            tagLibrary = NullTagLibrary.INSTANCE;
        parentTag = (Tag) tag.getParent();
        try {
            value = tag.end(elementName);
        } catch (TagException e) {
            addError(e);
        }
        if (parentTag != null)
            tag.setParent(null);
        tag.setContext(null);
        tag.setParser(null);
        currentTag = parentTag;
        tagLibrary.releaseNodeHandler(elementName, tag);
        if (parentTag == null) {
            parseResult = value;
        } else {
            try {
                parentTag.child(tag.getTagBinding(), value);
            } catch (TagException e) {
                addError(e);
            }
        }
    }

    //
    // Processing Instructions.
    //

    protected void processingInstruction(String target, String data)
        throws TagalogParseException
    {
        TagLibrary tagLibrary;
        PIHandler pi = null;
        boolean wildcard = false;

        tagLibrary = configuration.getProcessingInstructionTagLibrary();
        if (tagLibrary == null)
            return;
        pi = (PIHandler) tagLibrary.getNodeHandler(target);
        if (pi == null) {
            pi = (PIHandler) tagLibrary.getNodeHandler("*");
            if (pi == null)
                return;
            wildcard = true;
        }
        pi.setParser(this);
        pi.setContext(context);
        if (currentTag != null)
            pi.setParent(currentTag);
        try {
            pi.processingInstruction(target, data);
        } catch (TagException e) {
            addError(e);
        }
        if (currentTag != null)
            pi.setParent(null);
        pi.setContext(null);
        pi.setParser(null);
        tagLibrary.releaseNodeHandler(wildcard? "*" : target, pi);
        if (currentTag != null) {
            try {
                ((Tag) currentTag).child(pi.getTagBinding(), null);
            } catch (TagException e) {
                addError(e);
            }
        }
    }

    //
    // Error handling.
    //

    /**
     * Return a <code>Location</code> object representing the current
     * location in the source document.
     *
     * @return The current location.
     */
    public abstract Location getLocation();

    private List parseErrors = new java.util.LinkedList();

    public ParseError[] parseErrors() {
        return (ParseError[]) parseErrors.toArray(ParseError.EMPTY_ARRAY);
    }

    /**
     * Adds the supplied message to the list of parse errors at the current
     * parse location.
     * @param message the message to add for an error
     */
    public final void addError(String message) {
        parseErrors.add(new ParseError(message, getLocation()));
    }

    private void addError(TagException e) {
        addError(e.getMessage());
    }

    private Set reportedResolutionFailures = new java.util.HashSet();

    private void noTagLibrary(String namespaceUri) {
        namespaceUri = getActualNamespace(namespaceUri);
        if (reportedResolutionFailures.contains(namespaceUri))
            return;
        reportedResolutionFailures.add(namespaceUri);
        if (namespaceUri.length() == 0)
            addError("no tag library for elements with no namespace");
        else
            addError("no tag library for namespace '" + namespaceUri + "'");
    }

    private void noTag(String tag, String namespaceUri) {
        String tagAndNamespace;

        namespaceUri = getActualNamespace(namespaceUri);
        tagAndNamespace = tag + " " + namespaceUri;
        if (reportedResolutionFailures.contains(tagAndNamespace))
            return;
        reportedResolutionFailures.add(tagAndNamespace);
        addError("no tag '" + tag + "' in tag library"
                 + " for namespace '" + namespaceUri + "'");
    }

    private String getActualNamespace(String namespaceUri) {
        if (namespaceUri.length() == 0)
            namespaceUri = configuration.getDefaultNamespace();
        return namespaceUri;
    }
}
