/*
 * $Id: PropertyInfoTest.java,v 1.1 2005-03-01 10:34:34 mhw Exp $
 */

package org.codehaus.tagalog.conv;

import java.lang.reflect.Method;
import java.util.ArrayList;

import junit.framework.TestCase;

/**
 * Tests for the PropertyInfo class.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class PropertyInfoTest extends TestCase {
    private Class testClass;

    private Method[] methods;

    private Method intInt;

    private Method intString;

    private Method intStringBuffer;

    protected void setUp() {
        testClass = DuplicatePropertyBean.class;

        ArrayList list = new ArrayList();
        methods = testClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            String methodName = method.getName();
            Class[] parameterTypes = method.getParameterTypes();
            if (methodName.equals("setIntValue")
                && parameterTypes.length == 1) {
                list.add(method);
                if (parameterTypes[0] == Integer.TYPE)
                    intInt = method;
                else if (parameterTypes[0] == String.class)
                    intString = method;
                else if (parameterTypes[0] == StringBuffer.class)
                    intStringBuffer = method;
            }
        }
        methods = new Method[list.size()];
        methods = (Method[]) list.toArray(methods);
    }

    public void testNormalOperation() {
        PropertyInfo info = new PropertyInfo(intInt, Integer.TYPE);

        assertEquals(Integer.TYPE, info.getPropertyType());
        assertEquals(intInt, info.getMainSetMethod());

        info.addSetMethod(intString, String.class);

        assertEquals(String.class, info.getPropertyType());
        assertEquals(intString, info.getMainSetMethod());

        info.addSetMethod(intStringBuffer, StringBuffer.class);

        assertEquals(String.class, info.getPropertyType());
        assertEquals(intString, info.getMainSetMethod());
    }

    public void testIntDuplication() {
        PropertyInfo info = new PropertyInfo(intInt, Integer.TYPE);

        assertEquals(Integer.TYPE, info.getPropertyType());

        try {
            info.addSetMethod(intInt, Integer.TYPE);
            fail();
        } catch (IllegalStateException e) {
            // expected
        }

        info = new PropertyInfo(intString, String.class);
        info.addSetMethod(intInt, Integer.TYPE);
        try {
            info.addSetMethod(intInt, Integer.TYPE);
            fail();
        } catch (IllegalStateException e) {
            // expected
        }
    }

    public void testStringDuplication() {
        PropertyInfo info = new PropertyInfo(intString, String.class);

        assertEquals(String.class, info.getPropertyType());

        try {
            info.addSetMethod(intString, String.class);
            fail();
        } catch (IllegalStateException e) {
            // expected
        }

        info = new PropertyInfo(intInt, Integer.TYPE);
        info.addSetMethod(intString, String.class);
        try {
            info.addSetMethod(intString, String.class);
            fail();
        } catch (IllegalStateException e) {
            // expected
        }
    }
}
