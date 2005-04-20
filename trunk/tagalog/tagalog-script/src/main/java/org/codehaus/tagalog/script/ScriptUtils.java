/*
 * $Id: ScriptUtils.java,v 1.2 2005-04-20 16:01:09 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Map;

import org.codehaus.tagalog.el.ParseController;

/**
 * Miscellaneous static utility methods.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class ScriptUtils {

    /**
     * Private constructor to prevent instantiation
     */
    private ScriptUtils() {
    }

    public static ParseController expressionParseController(Map context) {
        ParseController result;

        result = (ParseController) context.get(Script.TAGALOG_EL_PARSER);
        if (result == null) {
            result = (ParseController) ParseController.DEFAULT.clone();
            context.put(Script.TAGALOG_EL_PARSER, result);
        }
        return result;
    }

    private static String[][] xmlReplacements = {
        { "&", "&amp;" },
        { "<", "&lt;" },
        { ">", "&gt;" },
        { "'", "&#039;" },
        { "\"", "&#034;" },
    };

    /**
     * Replace characters that have significance in XML with their
     * escaped equivalents, as per the JSTL specification.
     *
     * @param s String to be processed.
     * @return Processed string.
     */
    public static String escapeXml(String s) {
        for (int i = 0; i < xmlReplacements.length; i++) {
            s = s.replaceAll(xmlReplacements[i][0], xmlReplacements[i][1]);
        }
        return s;
    }
}
