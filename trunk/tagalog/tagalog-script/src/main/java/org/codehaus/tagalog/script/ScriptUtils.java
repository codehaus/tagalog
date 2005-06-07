/*
 * $Id: ScriptUtils.java,v 1.4 2005-06-07 16:36:04 krisb Exp $
 */

package org.codehaus.tagalog.script;

/**
 * Miscellaneous static utility methods.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.4 $
 */
public final class ScriptUtils {

    /**
     * Private constructor to prevent instantiation
     */
    private ScriptUtils() {
    }

    /**
     * Replace characters that have significance in XML with their
     * escaped equivalents, as per the JSTL specification.
     *
     * @param s String to be processed.
     * @return Processed string.
     */
    public static String escapeXml(String s) {
        if (s == null) {
            return null;
        }

        StringBuffer sb = new StringBuffer(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '&') {
                sb.append("&amp;");
            } else if (c == '<') {
                sb.append("&lt;");
            } else if (c == '>') {
                sb.append("&gt;");
            } else if (c == '\'') {
                sb.append("&#039;");
            } else if (c == '\"') {
                sb.append("&#034;");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
