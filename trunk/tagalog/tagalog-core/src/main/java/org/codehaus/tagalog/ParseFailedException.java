/*
 * $Id: ParseFailedException.java,v 1.1 2005-04-05 16:17:22 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * Exception that can be thrown by client code to indicate that a tagalog
 * parse failed.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class ParseFailedException extends Exception {
    private final ParseError[] parseErrors;

    public ParseFailedException(ParseError[] parseErrors) {
        super("parse failed");
        this.parseErrors = parseErrors;
    }

    public ParseFailedException(String message, ParseError[] parseErrors) {
        super(message);
        this.parseErrors = parseErrors;
    }

    public ParseError[] getParseErrors() {
        return parseErrors;
    }

    public String toString() {
        if (parseErrors == null)
            return super.toString() + ": parse errors list is null though";
        if (parseErrors.length == 0)
            return super.toString() + ": parse errors list is empty though";

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
