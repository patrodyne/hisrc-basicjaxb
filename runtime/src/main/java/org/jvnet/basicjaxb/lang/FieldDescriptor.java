package org.jvnet.basicjaxb.lang;

import static java.lang.Character.toUpperCase;
import static org.jvnet.basicjaxb.lang.Access.READ_ONLY;
import static org.jvnet.basicjaxb.lang.Access.READ_WRITE;
import static org.jvnet.basicjaxb.lang.Access.WRITE_ONLY;
import static org.jvnet.basicjaxb.lang.Alignment.LEFT;
import static org.jvnet.basicjaxb.lang.Alignment.RIGHT;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

/**
 * A {@code FieldDescriptor} extends {@link PropertyDescriptor} and describes
 * one property that a Java Bean exports via a pair of accessor methods. In
 * this context a <em>field</em> refers to a GUI display component.
 * 
 * <p>This class adds properties to configure the display of GUI <em>field(s)</em>.</p>
 */
public class FieldDescriptor
	extends PropertyDescriptor
{
    public static final String GET_PREFIX = "get";
    public static final String SET_PREFIX = "set";
    public static final String IS_PREFIX = "is";
    
	private static final String FIELD_INDEX = "column.index";
	private static final String FIELD_ACCESS = "column.access";
	private static final String FIELD_ALIGNMENT = "column.alignment";
	private static final String FIELD_EDITABLE = "column.editable";
	private static final String FIELD_RESIZEABLE = "column.resizable";
	private static final String FIELD_EDITOR_CLASS = "column.editorClass";
	private static final String FIELD_EDITOR = "column.editor";
	private static final String FIELD_RENDERER_CLASS = "column.rendererClass";
	private static final String FIELD_RENDERER = "column.renderer";
	
	private static final String FIELD_MAX_WIDTH = "column.maxWidth";
	private static final String FIELD_MIN_WIDTH = "column.minWidth";
	private static final String FIELD_PREFERRED_WIDTH = "column.preferredWidth";
	
	private static final String FIELD_PATTERN = "facet.pattern";
	private static final String FIELD_LENGTH = "facet.length";
	private static final String FIELD_MAX_LENGTH = "facet.maxLength";
	private static final String FIELD_MIN_LENGTH = "facet.minLength";
	private static final String FIELD_MAX_EXCLUSIVE = "facet.maxExclusive";
	private static final String FIELD_MIN_EXCLUSIVE = "facet.minExclusive";
	private static final String FIELD_MAX_INCLUSIVE = "facet.maxInclusive";
	private static final String FIELD_MIN_INCLUSIVE = "facet.minInclusive";
	private static final String FIELD_TOTAL_DIGITS = "facet.totalDigits";
	private static final String FIELD_FRACTION_DIGITS = "facet.fractionDigits";
	
	public static final Access DEFAULT_ACCESS = Access.READ_WRITE;
	public static final Alignment DEFAULT_ALIGNMENT = Alignment.LEFT;
	
	public static final int DEFAULT_MAX_WIDTH = 254;
	public static final int DEFAULT_MIN_WIDTH =  20;
	public static final Width DEFAULT_WIDTH = new Width(DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH);
	
	public static final Map<javax.xml.namespace.QName, Alignment> ALIGN_BY_QNAME_MAP = new HashMap<>();
	public static final Map<Class<?>, Alignment> ALIGN_BY_TYPE_MAP = new HashMap<>();
	
	public static final Map<javax.xml.namespace.QName, Width> WIDTH_BY_QNAME_MAP = new HashMap<>();
	public static final Map<Class<?>, Width> WIDTH_BY_TYPE_MAP = new HashMap<>();
	
	public static final Set<Class<?>> UNHIDE_BY_TYPE_SET = new HashSet<>();
	
	static
	{
		// Align by QName Mappings
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","anySimpleType"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","anyURI"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","base64Binary"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","boolean"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","byte"), RIGHT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","date"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","dateTime"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","decimal"), RIGHT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","double"), RIGHT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","duration"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","ENTITY"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","float"), RIGHT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","gDay"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","gMonth"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","gMonthDay"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","gYear"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","gYearMonth"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","hexBinary"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","ID"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","int"), RIGHT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","integer"), RIGHT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","language"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","long"), RIGHT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","Name"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","NCName"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","negativeInteger"), RIGHT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","NMTOKEN"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","nonNegativeInteger"), RIGHT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","nonPositiveInteger"), RIGHT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","normalizedString"), RIGHT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","positiveInteger"), RIGHT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","QName"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","short"), RIGHT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","string"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","time"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","token"), LEFT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","unsignedByte"), RIGHT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","unsignedInt"), RIGHT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","unsignedLong"), RIGHT);
		ALIGN_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","unsignedShort"), RIGHT);
		
		// Align by Type Mappings
		ALIGN_BY_TYPE_MAP.put(boolean.class, LEFT);
		ALIGN_BY_TYPE_MAP.put(byte.class, RIGHT);
		ALIGN_BY_TYPE_MAP.put(char.class, LEFT);
		ALIGN_BY_TYPE_MAP.put(short.class, RIGHT);
		ALIGN_BY_TYPE_MAP.put(int.class, RIGHT);
		ALIGN_BY_TYPE_MAP.put(long.class, RIGHT);
		ALIGN_BY_TYPE_MAP.put(float.class, RIGHT);
		ALIGN_BY_TYPE_MAP.put(double.class, RIGHT);
		ALIGN_BY_TYPE_MAP.put(String.class, LEFT);
		ALIGN_BY_TYPE_MAP.put(BigInteger.class, RIGHT);
		ALIGN_BY_TYPE_MAP.put(Integer.class, RIGHT);
		ALIGN_BY_TYPE_MAP.put(Long.class, RIGHT);
		ALIGN_BY_TYPE_MAP.put(Short.class, RIGHT);
		ALIGN_BY_TYPE_MAP.put(BigDecimal.class, RIGHT);
		ALIGN_BY_TYPE_MAP.put(Float.class, RIGHT);
		ALIGN_BY_TYPE_MAP.put(Double.class, RIGHT);
		ALIGN_BY_TYPE_MAP.put(Boolean.class, LEFT);
		ALIGN_BY_TYPE_MAP.put(Byte.class, RIGHT);
		ALIGN_BY_TYPE_MAP.put(String.class, LEFT);
		ALIGN_BY_TYPE_MAP.put(QName.class, LEFT);
		ALIGN_BY_TYPE_MAP.put(XMLGregorianCalendar.class, LEFT);
		ALIGN_BY_TYPE_MAP.put(Duration.class, LEFT);
		ALIGN_BY_TYPE_MAP.put(Character.class, LEFT);
		
		// Width by QName Mappings
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","anySimpleType"), new Width(20,50));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","anyURI"), new Width(20,50));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","base64Binary"), new Width(50,254));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","boolean"), new Width(5));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","byte"), new Width(4));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","date"), new Width(10));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","dateTime"), new Width(30));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","decimal"), new Width(10,20));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","double"), new Width(10,20));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","duration"), new Width(8));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","ENTITY"), new Width(20));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","float"), new Width(10,20));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","gDay"), new Width(2));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","gMonth"), new Width(2));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","gMonthDay"), new Width(5));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","gYear"), new Width(4));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","gYearMonth"), new Width(7));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","hexBinary"), new Width(50,254));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","ID"), new Width(10,254));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","int"), new Width(10,20));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","integer"), new Width(10,20));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","language"), new Width(5));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","long"), new Width(10,20));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","Name"), new Width(20,254));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","NCName"), new Width(20,254));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","negativeInteger"), new Width(10,20));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","NMTOKEN"), new Width(20,254));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","nonNegativeInteger"), new Width(10,20));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","nonPositiveInteger"), new Width(10,20));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","normalizedString"), new Width(20,254));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","positiveInteger"), new Width(10,20));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","QName"), new Width(30,254));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","short"), new Width(10,20));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","string"), new Width(20,254));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","time"), new Width(9));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","token"), new Width(20,254));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","unsignedByte"), new Width(4));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","unsignedInt"), new Width(10,20));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","unsignedLong"), new Width(10,20));
		WIDTH_BY_QNAME_MAP.put(new QName("http://www.w3.org/2001/XMLSchema","unsignedShort"), new Width(10,20));

		// Width by Type Mappings
		WIDTH_BY_TYPE_MAP.put(boolean.class, new Width(5));
		WIDTH_BY_TYPE_MAP.put(byte.class, new Width(4));
		WIDTH_BY_TYPE_MAP.put(char.class, new Width(1));
		WIDTH_BY_TYPE_MAP.put(short.class, new Width(10,20));
		WIDTH_BY_TYPE_MAP.put(int.class, new Width(10,20));
		WIDTH_BY_TYPE_MAP.put(long.class, new Width(10,20));
		WIDTH_BY_TYPE_MAP.put(float.class, new Width(10,20));
		WIDTH_BY_TYPE_MAP.put(double.class, new Width(10,20));
		WIDTH_BY_TYPE_MAP.put(String.class, new Width(20,254));
		WIDTH_BY_TYPE_MAP.put(BigInteger.class, new Width(10,20));
		WIDTH_BY_TYPE_MAP.put(Integer.class, new Width(10,20));
		WIDTH_BY_TYPE_MAP.put(Long.class, new Width(10,20));
		WIDTH_BY_TYPE_MAP.put(Short.class, new Width(10,20));
		WIDTH_BY_TYPE_MAP.put(BigDecimal.class, new Width(10,20));
		WIDTH_BY_TYPE_MAP.put(Float.class, new Width(10,20));
		WIDTH_BY_TYPE_MAP.put(Double.class, new Width(10,20));
		WIDTH_BY_TYPE_MAP.put(Boolean.class, new Width(5));
		WIDTH_BY_TYPE_MAP.put(Byte.class, new Width(4));
		WIDTH_BY_TYPE_MAP.put(String.class, new Width(20,254));
		WIDTH_BY_TYPE_MAP.put(QName.class, new Width(30,254));
		WIDTH_BY_TYPE_MAP.put(XMLGregorianCalendar.class, new Width(30));
		WIDTH_BY_TYPE_MAP.put(Duration.class, new Width(8));
		WIDTH_BY_TYPE_MAP.put(Character.class, new Width(1));
		
		// Hide by Type Mappings
		UNHIDE_BY_TYPE_SET.add(boolean.class);
		UNHIDE_BY_TYPE_SET.add(byte.class);
		UNHIDE_BY_TYPE_SET.add(char.class);
		UNHIDE_BY_TYPE_SET.add(short.class);
		UNHIDE_BY_TYPE_SET.add(int.class);
		UNHIDE_BY_TYPE_SET.add(long.class);
		UNHIDE_BY_TYPE_SET.add(float.class);
		UNHIDE_BY_TYPE_SET.add(double.class);
		UNHIDE_BY_TYPE_SET.add(String.class);
		UNHIDE_BY_TYPE_SET.add(BigInteger.class);
		UNHIDE_BY_TYPE_SET.add(Integer.class);
		UNHIDE_BY_TYPE_SET.add(Long.class);
		UNHIDE_BY_TYPE_SET.add(Short.class);
		UNHIDE_BY_TYPE_SET.add(BigDecimal.class);
		UNHIDE_BY_TYPE_SET.add(Float.class);
		UNHIDE_BY_TYPE_SET.add(Double.class);
		UNHIDE_BY_TYPE_SET.add(Boolean.class);
		UNHIDE_BY_TYPE_SET.add(Byte.class);
		UNHIDE_BY_TYPE_SET.add(String.class);
		UNHIDE_BY_TYPE_SET.add(QName.class);
		UNHIDE_BY_TYPE_SET.add(XMLGregorianCalendar.class);
		UNHIDE_BY_TYPE_SET.add(Duration.class);
		UNHIDE_BY_TYPE_SET.add(Character.class);
	}
	
	public static Alignment alignByType(QName fieldTypeName)
	{
		Alignment alignment = ALIGN_BY_QNAME_MAP.get(fieldTypeName);
		if ( alignment == null )
			alignment = DEFAULT_ALIGNMENT;
		return alignment;
	}
	
	public static Width widthByType(QName fieldTypeName)
	{
		Width width = WIDTH_BY_QNAME_MAP.get(fieldTypeName);
		if ( width == null )
			width = DEFAULT_WIDTH;
		return width;
	}
	
	public static Alignment alignByType(String fieldTypeName)
	{
		Alignment alignment = DEFAULT_ALIGNMENT;
		for ( Entry<Class<?>, Alignment> entry : ALIGN_BY_TYPE_MAP.entrySet() )
		{
			if ( entry.getKey().getName().equals(fieldTypeName) )
			{
				alignment = entry.getValue();
				break;
			}
		}
		return alignment;
	}

	public static Alignment alignByType(Class<?> fieldType)
	{
		Alignment alignment = ALIGN_BY_TYPE_MAP.get(fieldType);
		if ( alignment == null )
		{
			if ( String.class.isAssignableFrom(fieldType) )
				alignment = Alignment.LEFT;
			else if ( Number.class.isAssignableFrom(fieldType) )
				alignment = Alignment.RIGHT;
			else 
				alignment = DEFAULT_ALIGNMENT;
		}
		return alignment;
	}
	
	public static Width widthByType(Class<?> fieldType)
	{
		Width width = WIDTH_BY_TYPE_MAP.get(fieldType);
		if ( width == null )
			width = DEFAULT_WIDTH;
		return width;
	}
	
	public static boolean hideByType(Class<?> fieldType)
	{
		Boolean hide = false;
		if ( fieldType.isArray() )
			hide = true;
		else if ( fieldType.isAssignableFrom(Collection.class) )
			hide = true;
		else if ( fieldType.isAssignableFrom(Map.class) )
			hide = true;
		else
		{
			hide = true;
			for ( Class<?> unhideType : UNHIDE_BY_TYPE_SET )
			{
				if ( fieldType.isAssignableFrom(unhideType) )
				{
					hide = false;
					break;
				}
			}
		}
		return hide;
	}

	/**
	 * Return a capitalized version of the specified property name.
	 *
	 * @param name <code>String</code> The property name
	 * 
	 * @return <code>String</code> given String with 1st letter capitalized
	 *        or blank/null.
	 */
	public static String capitalize(final String name)
	{
		String cs = null;
		if ( (name != null) && (name.length() > 0) )
		{
			final char[] chars = name.toCharArray();
			chars[0] = toUpperCase(chars[0]);
			cs = new String(chars);
		}
		return cs;
	}
	
	/**
	 * Convert the given {@code name} to a normal Java variable name
	 * capitalization by converting the first character from upper case to lower
	 * case, but in the special case when there is more than one character and
	 * both the first and second characters are upper case, we leave it alone.
	 * 
	 * <p>
	 * Thus "FooBah" becomes "fooBah" and "X" becomes "x", but "URL" stays as
	 * "URL".
	 * </p>
	 *
	 * @param name The string to be decapitalized.
	 * 
	 * @return The decapitalized version of the string or blank/null.
	 */
	public static String decapitalize(final String name)
	{
		return Introspector.decapitalize(name);
	}
	
	/**
	 * Promote a {@link PropertyDescriptor} to a {@link FieldDescriptor}.
	 * 
	 * <p>Note: When not set, the display name is set to the capitalized name.</p>
	 * 
	 * @param pd A {@link PropertyDescriptor} instance of {@link FieldDescriptor}.
	 * 
	 * @return The instance of {@link FieldDescriptor}
	 */
	public static FieldDescriptor promote(PropertyDescriptor pd)
	{
		// Capitalize the display name when it defaults to the name.
		if ( isDefaultDisplayName(pd) )
			pd.setDisplayName(capitalize(pd.getName()));
		return (FieldDescriptor) pd;
	}
	
	/**
	 * Does the display name accessor return the default name?
	 * 
	 * @param pd A {@link PropertyDescriptor} instance.
	 * 
	 * @return True when the display name defaults to the name.
	 */
	public static boolean isDefaultDisplayName(PropertyDescriptor pd)
	{
		return pd.getName().equals(pd.getDisplayName()) ;
	}
	
	private static String readerName(String name)
	{
		return readerName(name, READ_WRITE);
	}
	
	private static String writerName(String name)
	{
		return writerName(name, READ_WRITE);
	}

	private static String readerName(String name, Access access)
	{
		// Note: java.beans.PropertyDescriptor.getReadMethod() handles GET_PREFIX.
		return WRITE_ONLY.equals(access) ? null : IS_PREFIX + capitalize(name);
	}
	
	private static String writerName(String name, Access access)
	{
		return READ_ONLY.equals(access) ? null : SET_PREFIX + capitalize(name);
	}
	
	/**
     * Constructs a FieldDescriptor for a property that follows
     * the standard Java convention by having getFoo and setFoo
     * accessor methods. A <em>field</em> is a display component.
     *
     * @param name The programmatic name of the property.
     * @param beanClass The Class object for the target bean.
     *          
     * @throws IntrospectionException if an exception occurs during introspection.
     */
	public FieldDescriptor(String name, Class<?> beanClass)
		throws IntrospectionException
	{
        this(name, beanClass, readerName(name), writerName(name));
        setAccess(Access.READ_WRITE);
	}
	
	/**
     * Constructs a FieldDescriptor for a property that follows
     * the standard Java convention by having getFoo and setFoo
     * accessor methods. A <em>field</em> is a display component.
     *
     * @param name The programmatic name of the property.
     * @param beanClass The Class object for the target bean.
     * @param access The property access type.
     *          
     * @throws IntrospectionException if an exception occurs during introspection.
     */
	public FieldDescriptor(String name, Class<?> beanClass, Access access)
		throws IntrospectionException
	{
        this(name, beanClass, readerName(name, access), writerName(name, access));
        setAccess(access);
	}
	
    /**
     * This constructor takes the name of a simple property, and method
     * names for reading and writing the property. A <em>field</em> is a
     * display component.
     *
     * @param propertyName The programmatic name of the property.
     * @param beanClass The Class object for the target bean.
     * @param readMethodName The name of the method used for reading the property value.
     * @param writeMethodName The name of the method used for writing the property value.
     * 
     * @throws IntrospectionException if an exception occurs during introspection.
     */
    public FieldDescriptor(String propertyName, Class<?> beanClass,
    	String readMethodName, String writeMethodName) throws IntrospectionException
    {
    	super(propertyName, beanClass, readMethodName, writeMethodName);
    	if ( (readMethodName != null) && (writeMethodName != null) )
    		setAccess(READ_WRITE);
    	else if ( (readMethodName != null) && (writeMethodName == null) )
    		setAccess(READ_ONLY);
    	else if ( (readMethodName == null) && (writeMethodName != null) )
    		setAccess(WRITE_ONLY);
    }
	
    /**
     * Constructs a FieldDescriptor from an existing {@link PropertyDescriptor}.
     * A <em>field</em> is a display component.
     *
     * @param pd A description of one property for a Java bean.
     * 
     * @throws IntrospectionException if an exception occurs during introspection.
     */
	public FieldDescriptor(PropertyDescriptor pd)
		throws IntrospectionException
	{
		super(pd.getName(), pd.getReadMethod(), pd.getWriteMethod());
		
		//
		// FeatureDescriptor
		//
		
		setName(pd.getName());
		// Capitalize the display name when it defaults to the name.
		if ( isDefaultDisplayName(pd) )
			setDisplayName(capitalize(pd.getName()));
		setExpert(pd.isExpert());
		setHidden(pd.isHidden());
		setPreferred(pd.isPreferred());
		setShortDescription(pd.getShortDescription());
		
		//
		// PropertyDescriptor
		//
		
		setBound(pd.isBound());
		setConstrained(pd.isConstrained());
		setPropertyEditorClass(pd.getPropertyEditorClass());
		setReadMethod(pd.getReadMethod());
		setWriteMethod(pd.getWriteMethod());
		
		//
		// FieldDescriptor
		//
		
		if ( pd.getValue(FIELD_INDEX) != null )
			setValue(FIELD_INDEX, pd.getValue(FIELD_INDEX));
		if ( pd.getValue(FIELD_ALIGNMENT) != null )
			setValue(FIELD_ALIGNMENT, pd.getValue(FIELD_ALIGNMENT));
		if ( pd.getValue(FIELD_EDITABLE) != null )
			setValue(FIELD_EDITABLE, pd.getValue(FIELD_EDITABLE));
		if ( pd.getValue(FIELD_RESIZEABLE) != null )
			setValue(FIELD_RESIZEABLE, pd.getValue(FIELD_RESIZEABLE));
		if ( pd.getValue(FIELD_EDITOR_CLASS) != null )
			setValue(FIELD_EDITOR_CLASS, pd.getValue(FIELD_EDITOR_CLASS));
		if ( pd.getValue(FIELD_EDITOR) != null )
			setValue(FIELD_EDITOR, pd.getValue(FIELD_EDITOR));
		if ( pd.getValue(FIELD_RENDERER) != null )
			setValue(FIELD_RENDERER, pd.getValue(FIELD_RENDERER));
		if ( pd.getValue(FIELD_RENDERER_CLASS) != null )
			setValue(FIELD_RENDERER_CLASS, pd.getValue(FIELD_RENDERER_CLASS));
		                                                                 
		if ( pd.getValue(FIELD_MAX_WIDTH) != null )
			setValue(FIELD_MAX_WIDTH, pd.getValue(FIELD_MAX_WIDTH));
		if ( pd.getValue(FIELD_MIN_WIDTH) != null )
			setValue(FIELD_MIN_WIDTH, pd.getValue(FIELD_MIN_WIDTH));
		if ( pd.getValue(FIELD_PREFERRED_WIDTH) != null )
			setValue(FIELD_PREFERRED_WIDTH, pd.getValue(FIELD_PREFERRED_WIDTH));
		                                                                 
		if ( pd.getValue(FIELD_PATTERN) != null )
			setValue(FIELD_PATTERN, pd.getValue(FIELD_PATTERN));
		if ( pd.getValue(FIELD_LENGTH) != null )
			setValue(FIELD_LENGTH, pd.getValue(FIELD_LENGTH));
		if ( pd.getValue(FIELD_MAX_LENGTH) != null )
			setValue(FIELD_MAX_LENGTH, pd.getValue(FIELD_MAX_LENGTH));
		if ( pd.getValue(FIELD_MIN_LENGTH) != null )
			setValue(FIELD_MIN_LENGTH, pd.getValue(FIELD_MIN_LENGTH));
		if ( pd.getValue(FIELD_MAX_EXCLUSIVE) != null )
			setValue(FIELD_MAX_EXCLUSIVE, pd.getValue(FIELD_MAX_EXCLUSIVE));
		if ( pd.getValue(FIELD_MIN_EXCLUSIVE) != null )
			setValue(FIELD_MIN_EXCLUSIVE, pd.getValue(FIELD_MIN_EXCLUSIVE));
		if ( pd.getValue(FIELD_MAX_INCLUSIVE) != null )
			setValue(FIELD_MAX_INCLUSIVE, pd.getValue(FIELD_MAX_INCLUSIVE));
		if ( pd.getValue(FIELD_MIN_INCLUSIVE) != null )
			setValue(FIELD_MIN_INCLUSIVE, pd.getValue(FIELD_MIN_INCLUSIVE));
		if ( pd.getValue(FIELD_TOTAL_DIGITS) != null )
			setValue(FIELD_TOTAL_DIGITS, pd.getValue(FIELD_TOTAL_DIGITS));
		if ( pd.getValue(FIELD_FRACTION_DIGITS) != null )
			setValue(FIELD_FRACTION_DIGITS, pd.getValue(FIELD_FRACTION_DIGITS));
	}
	
	//
	// FieldDescriptor: properties
	//
	public Integer getIndex()
	{
		return (Integer) getValue(FIELD_INDEX);
	}
	public void setIndex(int index)
	{
		setValue(FIELD_INDEX, index);
	}
	
	public Access getAccess()
	{
		return (Access) getValue(FIELD_ACCESS);
	}
	public void setAccess(Access access)
	{
		setValue(FIELD_ACCESS, access);
	}
	
	public Alignment getAlignment()
	{
		return (Alignment) getValue(FIELD_ALIGNMENT);
	}
	public void setAlignment(Alignment alignment)
	{
		setValue(FIELD_ALIGNMENT, alignment);
	}
	
	public Boolean isEditable()
	{
		return (Boolean) getValue(FIELD_EDITABLE);
	}
	public void setEditable(boolean editable)
	{
		setValue(FIELD_EDITABLE, editable);
	}
	
	public Boolean isResizable()
	{
		return (Boolean) getValue(FIELD_RESIZEABLE);
	}
	public void setResizable(boolean resizable)
	{
		setValue(FIELD_RESIZEABLE, resizable);
	}

	public TableCellRenderer getRenderer()
	{
		return (TableCellRenderer) getValue(FIELD_RENDERER);
	}
	public void setRenderer(TableCellRenderer renderer)
	{
		setValue(FIELD_RENDERER, renderer);
	}
	
	public Class<?> getRendererClass()
	{
		return (Class<?>) getValue(FIELD_RENDERER_CLASS);
	}
	public void setRendererClass(Class<?> rendererClass)
	{
		setValue(FIELD_RENDERER_CLASS, rendererClass);
	}

	public Class<?> getEditorClass()
	{
		return (Class<?>) getValue(FIELD_EDITOR_CLASS);
	}
	public void setEditorClass(Class<?> editorClass)
	{
		setValue(FIELD_EDITOR_CLASS, editorClass);
	}
	
	public TableCellEditor getEditor()
	{
		return (TableCellEditor) getValue(FIELD_EDITOR);
	}
	public void setEditor(TableCellEditor editor)
	{
		setValue(FIELD_EDITOR, editor);
	}
	
	public int getMaxWidth()
	{
		Integer v = (Integer) getValue(FIELD_MAX_WIDTH);
		return (v != null) ? v
			: (getMaxLength() != null) ? getMaxLength()
			: (getTotalDigitsPlus() != null ) ? getTotalDigitsPlus()
			: widthByType(getPropertyType()).getMax();
	}
	public void setMaxWidth(int maxWidth)
	{
		setValue(FIELD_MAX_WIDTH, maxWidth);
	}
	
	public int getMinWidth()
	{
		Integer v = (Integer) getValue(FIELD_MIN_WIDTH);
		return (v != null) ? v
			: (getMinLength() != null) ? getMinLength()
			: widthByType(getPropertyType()).getMin();
	}
	public void setMinWidth(int minWidth)
	{
		setValue(FIELD_MIN_WIDTH, minWidth);
	}
	
	public int getPreferredWidth()
	{
		Integer v = (Integer) getValue(FIELD_PREFERRED_WIDTH);
		return (v != null) ? v : getMinWidth();
	}
	public void setPreferredWidth(int preferredWidth)
	{
		setValue(FIELD_PREFERRED_WIDTH, preferredWidth);
	}
	
	public String getPattern()
	{
		return (String) getValue(FIELD_PATTERN);
	}
	public void setPattern(String pattern)
	{
		setValue(FIELD_PATTERN, pattern);
	}
	
	public Integer getLength()
	{
		return (Integer) getValue(FIELD_LENGTH);
	}
	public void setLength(Integer length)
	{
		setValue(FIELD_LENGTH, length);
	}
	
	public Integer getMaxLength()
	{
		Integer v = (Integer) getValue(FIELD_MAX_LENGTH);
		return (v != null) ? v : getLength();
	}
	public void setMaxLength(Integer maxLength)
	{
		setValue(FIELD_MAX_LENGTH, maxLength);
	}
	
	public Integer getMinLength()
	{
		Integer v = (Integer) getValue(FIELD_MIN_LENGTH);
		return (v != null) ? v : getLength();
	}
	public void setMinLength(Integer minLength)
	{
		setValue(FIELD_MIN_LENGTH, minLength);
	}
	
	public Integer getMaxExclusive()
	{
		return (Integer) getValue(FIELD_MAX_EXCLUSIVE);
	}
	public void setMaxExclusive(Integer maxExclusive)
	{
		setValue(FIELD_MAX_EXCLUSIVE, maxExclusive);
	}
	
	public Integer getMinExclusive()
	{
		return (Integer) getValue(FIELD_MIN_EXCLUSIVE);
	}
	public void setMinExclusive(Integer minExclusive)
	{
		setValue(FIELD_MIN_EXCLUSIVE, minExclusive);
	}
	
	public Integer getMaxInclusive()
	{
		return (Integer) getValue(FIELD_MAX_INCLUSIVE);
	}
	public void setMaxInclusive(Integer maxInclusive)
	{
		setValue(FIELD_MAX_INCLUSIVE, maxInclusive);
	}
	
	public Integer getMinInclusive()
	{
		return (Integer) getValue(FIELD_MIN_INCLUSIVE);
	}
	public void setMinInclusive(Integer minInclusive)
	{
		setValue(FIELD_MIN_INCLUSIVE, minInclusive);
	}
	
	public Integer getTotalDigits()
	{
		return (Integer) getValue(FIELD_TOTAL_DIGITS);
	}
	public void setTotalDigits(Integer totalDigits)
	{
		setValue(FIELD_TOTAL_DIGITS, totalDigits);
	}
	
	public Integer getFractionDigits()
	{
		return (Integer) getValue(FIELD_FRACTION_DIGITS);
	}
	public void setFractionDigits(Integer fractionDigits)
	{
		setValue(FIELD_FRACTION_DIGITS, fractionDigits);
	}
	
	public Integer getTotalDigitsPlus()
	{
		Integer tdp = getTotalDigits();
		if ( tdp != null )
		{
			Integer wd = tdp;
			Integer fd = getFractionDigits();
			if ( fd != null )
				wd -= fd;
			// Plus commas
			tdp += (wd-1) / 3;
			// Plus sign and dot.
			tdp += 2;
		}
		return tdp;
	}
}
