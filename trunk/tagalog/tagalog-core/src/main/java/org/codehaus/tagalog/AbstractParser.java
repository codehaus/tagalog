/*
 * $Id: AbstractParser.java,v 1.1 2004-02-10 18:56:05 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * AbstractParser
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public abstract class AbstractParser implements TagalogParser {
    private ParserConfiguration configuration;

    /**
     * Construct an <code>AbstractParser</code> that uses the specified
     * parser configuration.
     *
     * @param resolver The fallback tag library resolver.
     */
    protected AbstractParser(ParserConfiguration configuration) {
        this.configuration = configuration;
    }
}
