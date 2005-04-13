/*
 * $Id: ParseFailedException.java,v 1.3 2005-04-13 14:39:55 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * Exception that can be thrown by client code to indicate that a tagalog
 * parse failed.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class ParseFailedException extends Exception {
    private final ParseError[] parseErrors;

    public ParseFailedException(ParseError[] parseErrors) {
        this("parse failed", parseErrors);
    }

    public ParseFailedException(String message, ParseError[] parseErrors) {
        super(message);
        if (parseErrors.length == 0)
            throw new IllegalArgumentException("no errors");
        this.parseErrors = (ParseError[]) parseErrors.clone();
    }

    public ParseError[] getParseErrors() {
        return (ParseError[]) parseErrors.clone();
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(super.toString());
        for (int i = 0; i < parseErrors.length; i++) {
            if (i == 0)
                buffer.append(": ");
            else
                buffer.append(", ");
            buffer.append(parseErrors[i].toString());
        }
        return buffer.toString();
    }
}
