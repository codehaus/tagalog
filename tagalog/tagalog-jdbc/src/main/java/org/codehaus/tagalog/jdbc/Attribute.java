/*
 * $Id: Attribute.java,v 1.6 2005-03-30 15:06:20 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * <code>Attribute</code> holds the value of an attribute that might
 * be expanded into a SQL statement (using the '${blah}' syntax) or bound
 * to one of the <code>PreparedStatement</code> variables (using the
 * '?{blah}' syntax). Each attribute is paired with an enumeration value
 * of type <code>Type</code> that denotes the setter method that will be
 * used with the value.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.6 $
 */
final class Attribute {
    private final Object value;

    private final Type type;

    private final int sqlType;

    public Attribute(Object value, Type type) {
        if (value == null)
            throw new NullPointerException("attribute value is null");
        if (type == null)
            throw new NullPointerException("attribute type is null");
        this.value = value;
        this.type = type;
        this.sqlType = Types.NULL;
    }

    private Attribute(Object value, Type type, int sqlType) {
        this.value = value;
        this.type = type;
        this.sqlType = sqlType;
    }

    public static Attribute nullAttribute(int sqlType) {
        return new Attribute(null, NULL, sqlType);
    }

    public static Attribute nullAttribute(int sqlType, String typeName) {
        if (typeName != null)
            throw new NullPointerException("type name is null");
        return new Attribute(typeName, NULL, sqlType);
    }

    public Object getValue() {
        return value;
    }

    public int getSqlType() {
        return sqlType;
    }

    public void bind(PreparedStatement stmt, int index) throws SQLException {
        type.bind(stmt, index, this);
    }

    public static Type BOOLEAN = new Type("Boolean") {
        protected void bind(PreparedStatement stmt, int index, Attribute value)
            throws SQLException
        {
            stmt.setBoolean(index, ((Boolean) value.getValue()).booleanValue());
        }
    };

    public static Type INT = new Type("Integer") {
        protected void bind(PreparedStatement stmt, int index, Attribute value)
            throws SQLException
        {
            stmt.setInt(index, ((Integer) value.getValue()).intValue());
        }
    };

    public static Type BIG_DECIMAL = new Type("BigDecimal") {
        protected void bind(PreparedStatement stmt, int index, Attribute value)
            throws SQLException
        {
            stmt.setBigDecimal(index, (BigDecimal) value.getValue());
        }
    };

    public static Type STRING = new Type("String") {
        protected void bind(PreparedStatement stmt, int index, Attribute value)
            throws SQLException
        {
            stmt.setString(index, (String) value.getValue());
        }
    };

    public static Type DATE = new Type("Date") {
        protected void bind(PreparedStatement stmt, int index, Attribute value)
            throws SQLException
        {
            DateCalendarPair p = (DateCalendarPair) value.getValue();

            if (p.calendar == null) {
                stmt.setDate(index, (java.sql.Date) p.date);                
            } else {
                stmt.setDate(index, (java.sql.Date) p.date, p.calendar);
            }
        }
    };

    public static Type TIME = new Type("Time") {
        protected void bind(PreparedStatement stmt, int index, Attribute value)
            throws SQLException
        {
            DateCalendarPair p = (DateCalendarPair) value.getValue();

            if (p.calendar == null) {
                stmt.setTime(index, (java.sql.Time) p.date);                
            } else {
                stmt.setTime(index, (java.sql.Time) p.date, p.calendar);
            }
        }
    };

    public static Type TIMESTAMP = new Type("Timestamp") {
        protected void bind(PreparedStatement stmt, int index, Attribute value)
            throws SQLException
        {
            DateCalendarPair p = (DateCalendarPair) value.getValue();

            if (p.calendar == null) {
                stmt.setTimestamp(index, (java.sql.Timestamp) p.date);                
            } else {
                stmt.setTimestamp(index, (java.sql.Timestamp) p.date, p.calendar);
            }
        }
    };

    public static Type OBJECT = new Type("Object") {
        protected void bind(PreparedStatement stmt, int index, Attribute value)
            throws SQLException
        {
            stmt.setObject(index, value.getValue());
        }
    };

    public static Type NULL = new Type("null") {
        protected void bind(PreparedStatement stmt, int index, Attribute value)
            throws SQLException
        {
            stmt.setNull(index, value.getSqlType());
        }
    };

    static abstract class Type {
        private final String name;

        protected Type(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        protected abstract void bind(PreparedStatement stmt, int index,
                                     Attribute value)
            throws SQLException;
    }

    public static final class DateCalendarPair {
        java.util.Date date;
        java.util.Calendar calendar;

        public DateCalendarPair(java.util.Date date,
                                java.util.Calendar calendar)
        {
            this.date = (java.util.Date) date.clone();
            if (calendar != null)
                this.calendar = (java.util.Calendar) calendar.clone();
        }
    }
}
