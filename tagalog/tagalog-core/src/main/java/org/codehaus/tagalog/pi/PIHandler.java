/*
 * $Id: PIHandler.java,v 1.2 2004-11-17 14:20:34 krisb Exp $
 */

package org.codehaus.tagalog.pi;

import java.util.Map;

/**
 * A processing instruction <code>PIHandler</code> is notified by the
 * parser when an XML Processing Instruction is encountered.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
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
