/*
 * $Id: BracketedExpressionParser.java,v 1.4 2005-04-14 13:09:27 mhw Exp $
 */

package org.codehaus.tagalog.el;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.4 $
 */
public final class BracketedExpressionParser implements ExpressionParser {
    private static final int META_CHAR_COUNT = 3;

    public static final char QUOTE = '\\';

    private final ParseController parseController;

    private final char open;

    private final char close;

    private Map parsers = new java.util.TreeMap();

    private String metaChars = null;

    private String startChars = null;

    public BracketedExpressionParser(ParseController parseController,
                                     char open, char close)
    {
        this.parseController = parseController;
        this.open = open;
        this.close = close;
    }

    public synchronized void addExpressionParser(char start, String name) {
        if (start == QUOTE)
            throw new IllegalArgumentException(
                "bracketed expressions cannot start "
                + "with the quote character '" + QUOTE + "'");
        Character c = new Character(start);
        if (parsers.containsKey(c))
            throw new IllegalArgumentException(
                "start character '" + start + "' "
                + "is already used by expression language '"
                + ((String) parsers.get(c)) + "'");
        parsers.put(c, name);
        metaChars = null;
        startChars = null;
    }

    synchronized String getMetaChars() {
        if (metaChars == null) {
            StringBuffer buf;
            Iterator iter;

            buf = new StringBuffer(META_CHAR_COUNT + parsers.size());
            buf.append(QUOTE);
            buf.append(open);
            buf.append(close);
            iter = parsers.keySet().iterator();
            while (iter.hasNext())
                buf.append(((Character) iter.next()).charValue());
            metaChars = buf.toString();
        }
        return metaChars;
    }

    synchronized String getStartChars() {
        if (startChars == null)
            startChars = getMetaChars().substring(META_CHAR_COUNT);
        return startChars;
    }

    public Expression parse(String text) throws ExpressionParseException {
        String metaChars = getMetaChars();
        String startChars = getStartChars();
        List expressions = new java.util.ArrayList();
        COWBuffer buf;
        int c;
        String s;

        buf = new COWBuffer(text, 0);
        while ((c = buf.getChar()) != -1) {
            if (c == QUOTE) {
                if ((c = buf.getChar()) == -1) {
                    buf.put(QUOTE);
                    break;
                }
                if (metaChars.indexOf(c) == -1)
                    buf.put(QUOTE);
                buf.put(c);
            } else if (startChars.indexOf(c) != -1) {
                int startChar = c;
                if ((c = buf.getChar()) == -1) {
                    buf.put(startChar);
                    continue;
                }
                if (c != open) {
                    buf.put(startChar);
                    if (c != QUOTE && c != startChar)
                        buf.put(c);
                    continue;
                }
                s = buf.getSpan();
                if (s.length() > 0)
                    expressions.add(new ConstantExpression(s));
                while ((c = buf.getChar()) != -1) {
                    if (c == close) {
                        break;
                    } else if (c == QUOTE) {
                        if ((c = buf.getChar()) == -1) {
                            buf.put(QUOTE);
                            break;
                        }
                        if (metaChars.indexOf(c) == -1)
                            buf.put(QUOTE);
                        buf.put(c);
                    } else {
                        buf.put(c);
                    }
                }
                if (c == -1)
                    throw new ExpressionParseException(
                            "could not find expression close character '"
                            + close + "' in '" + text + "'");
                addExpression(expressions, startChar, buf.getSpan());
            } else {
                buf.put(c);
            }
        }
        s = buf.getSpan();
        if (s.length() > 0)
            expressions.add(new ConstantExpression(s));
        switch (expressions.size()) {
        case 0:
            return new ConstantExpression("");
        case 1:
            return (Expression) expressions.get(0);
        default:
            return new ConcatenationExpression(expressions);
        }
    }

    private void addExpression(List expressions, int startChar,
                               String expressionText)
        throws ExpressionParseException
    {
        String parserName;
        ExpressionParser parser;
        Expression exp;

        parserName = (String) parsers.get(new Character((char) startChar));
        parser = parseController.findByName(parserName);
        exp = parser.parse(expressionText);
        expressions.add(exp);
    }

    static class COWBuffer {
        private final String source;
        private final int length;
        private int start;
        private int readPoint;
        private int writePoint;
        private StringBuffer copy;

        public COWBuffer(String source, int start) {
            this.source = source;
            this.length = source.length();
            this.start = start;
            this.readPoint = start;
            this.writePoint = start;
            this.copy = null;
        }

        public int getChar() {
            if (readPoint < length)
                return source.charAt(readPoint++);
            return -1;
        }

        public void put(int c) {
            if (copy == null) {
                if (writePoint < length && c == source.charAt(writePoint)) {
                    writePoint++;
                    return;
                }
                copy = new StringBuffer(source.substring(start, writePoint));
            }
            copy.append((char) c);
        }

        public String getSpan() {
            String s;

            if (copy == null)
                s = source.substring(start, writePoint);
            else
                s = copy.toString();
            start = readPoint;
            writePoint = start;
            copy = null;
            return s;
        }
    }
}
