/*
 * $Id: PIHandler.java,v 1.1 2004-04-10 15:17:08 mhw Exp $
 */

package org.codehaus.tagalog.pi;

import java.util.Map;

/**
 * A processing instruction <code>PIHandler</code> is notified by the
 * parser when an XML Processing Instruction is encountered.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public interface PIHandler {
    /**
     * Handle the Processing Instruction.
     *
     * @param target Target of the Processing Instruction.
     * @param data Processing Instruction data.
     * @param context Shared parse context.
     */
    void processingInstruction(String target, String data, Map context);
}
