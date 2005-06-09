/*
 * $Id: TextScanner.java,v 1.2 2005-06-09 19:54:02 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * Scanner to identify important components of typical XML text content.
 * The scanner tries to do a good job of separating 'significant' white
 * space in XML content from the white space that can be ignored. These
 * are the principles:
 * <ul>
 * <li>The runs of white space between text content and the start and
 * end element tags are removed.
 * Hence
 * <p><pre>
 * &lt;text&gt;
 * Content
 * &lt;/&gt;
 * </pre></p>
 * is the equivalent to
 * <p><pre>
 * &lt;text&gt;Content&lt;/&gt;
 * </pre></p>
 * </li>
 *
 * <li>Whitespace used to indent content is removed, as is any trailing
 * whitespace on a line. The relative indentation of lines is preserved.
 * Hence
 * <p><pre>
 * &lt;text&gt;
 *     Content
 *        on two lines
 * &lt;/&gt;
 * </pre></p>
 * is the equivalent to
 * <p><pre>
 * &lt;text&gt;Content
 *    on two lines&lt;/text&gt;
 * </pre></p>
 * </li>
 *
 * <li>Blank lines are preserved and have all white space removed.
 * Hence
 * <p><pre>
 * &lt;text&gt;
 *     Content
 *
 *        on three lines
 * &lt;/text&gt;
 * </pre></p>
 * is the equivalent to
 * <p><pre>
 * &lt;text&gt;Content
 *
 *    on three lines&lt;/text&gt;
 * </pre></p>
 * </li>
 *
 * <li>Blank lines between text content and the start and end element tags
 * are also preserved.
 * Hence
 * <p><pre>
 * &lt;text&gt;
 *
 *     Content
 *
 *        on three lines
 *
 * &lt;/text&gt;
 * </pre></p>
 * is the equivalent to
 * <p><pre>
 * &lt;text&gt;
 *
 * Content
 *
 *    on five lines
 *
 * &lt;/text&gt;
 * </pre></p>
 * </li>
 * </ul>
 *
 * <p>
 * The preceding discussion has implied that after processing lines of text
 * are separated by new line characters. However this policy is not
 * implemented by the <code>TextScanner</code> class: <code>TextScanner</code>
 * provides access to an array of trimmed lines, and the
 * {@link #appendLine(StringBuffer, int)} method returns a boolean advising
 * whether a new line should be appended, but it is up to users of the class
 * to decide how new lines should be represented.
 * </p>
 * <p>
 * Only the '\n' character is treated as a new line
 * <p>
 * This leads to the slight anomaly that
 * <pre>
 * &lt;text&gt;
 *     Content
 *
 * &lt;/text&gt;
 * </pre>
 * will contain two new line chara
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class TextScanner {

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

    public boolean appendLine(StringBuffer buffer, int line) {
        int start = lineOffsets.getStart(line);
        int length = lineOffsets.getLength(line);
        buffer.append(content, start, length);
        return (line < getLineCount() - 1 || length == 0);
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

    // Character categories

    public static final int NORM = 0;
    public static final int WS = 1;
    public static final int NL = 2;
    public static final int END = 3;

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
                cat = END;
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
                if (cat == END)
                    break;
                else if (cat == NL) {
                    lineOffsets.putStart(p);
                    lineOffsets.putEnd();
                    break;
                }
                lineOffsets.putStart(p);
                if (cat == WS) {
                    thisIndent = 1;
                    state = IN_INDENT;
                } else {
                    state = IN_TEXT;
                }
                break;
            case IN_INDENT:
                if (cat == NORM) {
                    indent = Math.min(indent, thisIndent);
                    state = IN_TEXT;
                } else if (cat == WS) {
                    thisIndent++;
                } else if (cat == NL) {
                    lineOffsets.putEnd();
                    state = IN_NL;
                } else if (cat == END)
                    lineOffsets.voidLine();
                break;
            case IN_TEXT:
                if (cat == WS) {
                    whiteSpaceStart = p;
                    state = IN_WHITE_SPACE;
                } else if (cat == NL || cat == END) {
                    lineOffsets.putEnd(p);
                    state = IN_NL;
                }
                break;
            case IN_WHITE_SPACE:
                if (cat == NORM) {
                    state = IN_TEXT;
                } else if (cat == NL || cat == END) {
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

    private static final int[] charCategory = {
        // 0     1     2     3     4     5     6     7     8     9
        NORM, NORM, NORM, NORM, NORM, NORM, NORM, NORM, NORM,   WS,  // 00
          NL, NORM, NORM, NORM, NORM, NORM, NORM, NORM, NORM, NORM,  // 10
        NORM, NORM, NORM, NORM, NORM, NORM, NORM, NORM, NORM, NORM,  // 20
        NORM, NORM,   WS,                                            // 30
    };

    public static int category(char c) {
        return (c > 32)? NORM : charCategory[c];
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

        public void putEnd() {
            end[size] = start[size];
            ++size;
        }

        public void voidLine() {
            start[size] = 0;
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
