/*
 * $Id: AbstractStatementTag.java,v 1.3 2004-11-08 12:35:48 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Tag;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;
import org.codehaus.tagalog.script.Statement;

/**
 * Shared behaviour for all tags that create {@link Statement} objects.
 * Provides the following behaviour to classes that extend it:
 *
 * <ul>
 * <li>
 * Storage of the reference to the {@link Statement} object in the
 * {@link #stmt} attribute. A value must be assigned to <code>stmt</code>
 * before the {@link #end(String)} method is called.
 * </li>
 * <li>
 * Adding the completed <code>stmt</code> to the nearest enclosing
 * compound statement when the {@link #end(String)} method is called.
 * If there is no enclosing compound statement a {@link TagException} is
 * thrown, unless the {@link #rootTag} attribute has been set to true.
 * </li>
 * </ul>
 *
 * Subclasses will typically assign a value to the {@link #stmt} attribute
 * in the <code>begin</code> method if all the necessary information is
 * available from the attributes.
 * Otherwise information can be collected during the lifecycle of the tag
 * and the <code>Statement</code> created in the implementation of the
 * {@link #end(String)} method, <strong>before</strong> calling
 * <code>super.end(elementName)</code>.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public abstract class AbstractStatementTag
    extends AbstractTag
{
    /**
     * Is this tag the root tag of a script hierarchy?
     */
    protected boolean rootTag = false;

    protected Statement stmt;

    public Object end(String elementName)
        throws TagException, TagalogParseException
    {
        Tag tag;
        AbstractCompoundStatementTag parent;

        if (rootTag) {
            tag = findAncestorWithClass(AbstractCompoundStatementTag.class);
            if (tag == null)
                return stmt;
        }

        tag = requireAncestor("compound statement",
                              AbstractCompoundStatementTag.class);
        parent = (AbstractCompoundStatementTag) tag;
        parent.addStatement(stmt);
        return stmt;
    }

    public boolean recycle() {
        stmt = null;
        return true;
    }
}
