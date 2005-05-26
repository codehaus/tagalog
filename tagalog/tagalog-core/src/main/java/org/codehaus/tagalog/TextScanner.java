/*
 * $Id: TextScanner.java,v 1.1 2005-05-26 21:43:39 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * Internal helper class used to identify important components of typical
 * XML text content.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
class TextScanner {

    private final char[] content;

    private final LineOffsets lineOffsets = new LineOffsets();

    public TextScanner(String text) {
        content = text.toCharArray();

        scan();
    }

    public int getLineCount() {
        return lineOffsets.size();
    }

    public String getLine(int line) {
        return new String(content, lineOffsets.getStart(line),
                                   lineOffsets.getLength(line));
    }

    public void appendLine(StringBuffer buffer, int line) {
        buffer.append(content, lineOffsets.getStart(line),
                               lineOffsets.getLength(line));
    }

    /**
     * The prelude is the possibly empty inital run of white space prior
     * to the first bit of non-whitespace content.
     */
    private static final int IN_PRELUDE = 0;

    /**
     * Inside a run of new-line characters.
     */
    private static final int IN_NL = 1;

    /**
     * Inside the white space immediately after a new line (i.e. the
     * indentation).
     */
    private static final int IN_INDENT = 2;

    /**
     * Inside non-whitespace during line.
     */
    private static final int IN_TEXT = 3;

    /**
     * Inside whitespace during line.
     */
    private static final int IN_WHITE_SPACE = 4;

    private void scan() {
        int indent = Integer.MAX_VALUE;
        int l = content.length;
        int p = 0;
        int cat;
        int state = IN_PRELUDE;
        int thisIndent = 0;
        int whiteSpaceStart = 0;

        while (p <= l) {
            if (p == l)
                cat = NL;
            else
                cat = category(content[p]);
            switch (state) {
            case IN_PRELUDE:
                if (cat == NORM) {
                    lineOffsets.putStart(p);
                    state = IN_TEXT;
                } else if (cat == NL)
                    state = IN_NL;
                break;
            case IN_NL:
                if (cat != NL) {
                    lineOffsets.putStart(p);
                    if (cat == WS) {
                        thisIndent = 1;
                        state = IN_INDENT;
                    } else {
                        state = IN_TEXT;
                    }
                } else {
                    lineOffsets.putStart(p);
                    lineOffsets.putEnd(p);
                }
                break;
            case IN_INDENT:
                if (cat == NORM) {
                    indent = Math.min(indent, thisIndent);
                    state = IN_TEXT;
                } else if (cat == WS) {
                    thisIndent++;
                } else if (cat == NL) {
                    lineOffsets.putEnd(lineOffsets.getStart(lineOffsets.size()));
                    state = IN_NL;
                }
                break;
            case IN_TEXT:
                if (cat == WS) {
                    whiteSpaceStart = p;
                    state = IN_WHITE_SPACE;
                } else if (cat == NL) {
                    lineOffsets.putEnd(p);
                    state = IN_NL;
                }
                break;
            case IN_WHITE_SPACE:
                if (cat == NORM) {
                    state = IN_TEXT;
                } else if (cat == NL) {
                    lineOffsets.putEnd(whiteSpaceStart);
                    state = IN_NL;
                }
                break;
            }
            p++;
        }

        for (int i = 0; i < lineOffsets.size(); i++) {
            System.out.println(lineOffsets.getStart(i) + ":" + lineOffsets.getEnd(i));
        }
        for (int i = 0; i < lineOffsets.size(); i++) {
            System.out.println(new String(content, lineOffsets.getStart(i),
                                             lineOffsets.getEnd(i) - lineOffsets.getStart(i)));
        }

        if (indent != Integer.MAX_VALUE && indent > 0) {
            lineOffsets.setIndent(indent);
            System.out.println("min indent: " + indent);
        }

        System.out.println("final state: " + state);
    }

    public static final int NORM = 0;
    public static final int WS = 1;
    public static final int NL = 2;

    /**
     * Bitmask with bits set for the white space characters: 0x9 (tab)
     * and 0x20 (space).
     */
    private static int spaceBits =   0x80000100;

    /**
     * Bitmask with bits set for the new line characters: 0xA (line feed)
     * and 0xD (carriage return).
     */
    private static int newLineBits = 0x00001200;

    public static int category(char c) {
        if (c > 0x20)
            return NORM;
        --c;
        if (((spaceBits >> c) & 1) == 1)
            return WS;
        if (((newLineBits >> c) & 1) == 1)
            return NL;
        return NORM;
    }

    private static final class LineOffsets {
        private int[] start;
        private int[] end;
        private int size;
        private int indent;

        public LineOffsets() {
            this(20);
        }

        public LineOffsets(int initialCapacity) {
            start = new int[initialCapacity];
            end = new int[initialCapacity];
            size = 0;
            indent = 0;
        }

        public int size() {
            return size;
        }

        public void putStart(int value) {
            if (size == start.length) {
                int[] newStart = new int[size * 2];
                System.arraycopy(start, 0, newStart, 0, size);
                start = newStart;
                int[] newEnd = new int[size * 2];
                System.arraycopy(end, 0, newEnd, 0, size);
                end = newEnd;
            }
            start[size] = value;
        }

        public void putEnd(int value) {
            end[size++] = value;
        }

        public void setIndent(int indent) {
            this.indent = indent;
        }

        public int getStart(int index) {
            return Math.min(start[index] + indent, end[index]);
        }

        public int getEnd(int index) {
            return end[index];
        }

        public int getLength(int index) {
            return end[index] - getStart(index);
        }
    }
}
