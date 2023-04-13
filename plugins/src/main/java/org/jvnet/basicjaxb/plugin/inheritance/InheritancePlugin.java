package org.jvnet.basicjaxb.plugin.inheritance;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.util.CustomizationUtils.findCustomization;
import static org.jvnet.basicjaxb.util.CustomizationUtils.findCustomizations;
import static org.jvnet.basicjaxb.util.CustomizationUtils.unmarshall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.inheritance.util.JavaTypeParser;
import org.xml.sax.ErrorHandler;

import com.sun.codemodel.JAnnotationArrayMember;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CClassRef;
import com.sun.tools.xjc.model.CCustomizations;
import com.sun.tools.xjc.model.CElementInfo;
import com.sun.tools.xjc.model.CPluginCustomization;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.ElementOutline;
import com.sun.tools.xjc.outline.EnumOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.tools.xjc.outline.PackageOutline;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIEnum;

/**
 * <p>This plugin enables <em>Schema Derived</em> (SD) classes to:</p>
 * 
 * <ul>
 *   <li>Be annotated</li>
 *   <li>Extend a non-SD class</li>
 *   <li>Implement non-SD interface(s)</li>
 * </ul>
 * 
 * <b>Inheritance</b>
 * <pre>
 * xmlns:inh="http://jvnet.org/basicjaxb/xjc/inheritance"
 * </pre>
 * 
 * {@link ObjectFactoryCustomization}
 * <pre>
 * &lt;inh:objectFactory packageName="org.example.my_model"&gt;
 *
 *   &lt;inh:annotates annotation="java.lang.SuppressWarnings"&gt;
 *      &lt;inh:elements &gt;rawtypes unchecked&lt;/inh:elements&gt;
 *   &lt;/inh:annotates&gt;
 * 
 *   &lt;inh:annotates annotation="java.lang.Deprecated"&gt;
 *      &lt;inh:element name="since" type="java.lang.String"&gt;8&lt;/inh:element&gt;
 *      &lt;inh:element name="forRemoval" type="java.lang.Boolean"&gt;true&lt;/inh:element&gt;
 *   &lt;/inh:annotates&gt;
 *   
 *   &lt;inh:annotates annotation="jakarta.xml.bind.annotation.XmlSeeAlso"&gt;
 *      &lt;inh:element type="java.lang.Class"&gt;javax.xml.datatype.XMLGregorianCalendar&lt;/inh:element&gt;
 *   &lt;/inh:annotates&gt;
 *   
 *   &lt;inh:annotates annotation="jakarta.xml.bind.annotation.XmlAccessorType"&gt;
 *      &lt;inh:element type="java.lang.Enum"&gt;jakarta.xml.bind.annotation.XmlAccessType.FIELD&lt;/inh:element&gt;
 *   &lt;/inh:annotates&gt;
 *   
 *   &lt;inh:extends&gt;org.example.my_base.ObjectFactory&lt;/inh:extends&gt;
 *   
 *   &lt;inh:implements&gt;org.example.my_base.MyInterface1&lt;/inh:implements&gt;
 *   
 *   &lt;inh:implements&gt;org.example.my_base.MyInterface2&lt;/inh:implements&gt;
 *   
 * &lt;/inh:objectFactory&gt;
 * </pre>
 *
 * {@link AnotateMetaObject}
 * <pre>
 * &lt;inh:annotates annotation="java.beans.JavaBean"&gt;
 *   &lt;inh:element name="description"&gt;Personal identity information.&lt;/inh:element&gt;
 * &lt;/inh:annotates&gt;
 * </pre>
 *
 * {@link ExtendsClass}
 * <pre>
 * &lt;inh:extends&gt;org.example.BaseClass&lt;/inh:extends&gt;
 * </pre>
 * 
 * {@link ImplementsInterface}
 * <pre>
 * &lt;inh:implements&gt;java.lang.Cloneable&lt;inh:implements&gt;
 * </pre>
 */
public class InheritancePlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "Xinheritance";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "annotate, extend or implement schema-derived classes";

	@Override
	public String getOptionName()
	{
		return OPTION_NAME;
	}

	@Override
	public String getUsage()
	{
		return format(USAGE_FORMAT, OPTION_NAME, OPTION_DESC);
	}

	@Override
	public Collection<QName> getCustomizationElementNames()
	{
		return Arrays.asList
			(
				Customizations.ANNOTATES_ELEMENT_NAME,
				Customizations.ANNOTATES_ELEMENT_ELEMENT_NAME,
				Customizations.ANNOTATES_ELEMENTS_ELEMENT_NAME,
				Customizations.EXTENDS_ELEMENT_NAME,
				Customizations.IMPLEMENTS_ELEMENT_NAME,
				Customizations.OBJECT_FACTORY_ELEMENT_NAME
			);
	}

	private void ignoreCustomzationsOnProperties(ClassOutline classOutline)
	{
		// Ignore by finding and marking customization as acknowledged.
		for (CPropertyInfo propertyInfo : classOutline.target.getProperties())
		{
			findCustomization(propertyInfo, Customizations.ANNOTATES_ELEMENT_NAME);
			findCustomization(propertyInfo, Customizations.ANNOTATES_ELEMENT_ELEMENT_NAME);
			findCustomization(propertyInfo, Customizations.ANNOTATES_ELEMENTS_ELEMENT_NAME);
			findCustomization(propertyInfo, Customizations.EXTENDS_ELEMENT_NAME);
			findCustomization(propertyInfo, Customizations.IMPLEMENTS_ELEMENT_NAME);
		}
	}

	@Override
	public boolean run(Outline outline, Options opt, ErrorHandler errorHandler)
	{
		final Map<String, JClass> knownClasses = new HashMap<String, JClass>();
		final Map<JClass, CClassInfo> knownClassInfos = new IdentityHashMap<JClass, CClassInfo>();
		
		for (final ClassOutline classOutline : outline.getClasses())
		{
			knownClasses.put(classOutline.implClass.fullName(), classOutline.implClass);
			knownClassInfos.put(classOutline.implClass, classOutline.target);
		}
		
		for (final ClassOutline classOutline : outline.getClasses())
			processClassOutline(classOutline, knownClasses, knownClassInfos);
		
		for (final EnumOutline enumOutline : outline.getEnums())
			processEnumOutline(enumOutline, knownClasses);
		
		for (final CElementInfo elementInfo : outline.getModel().getAllElements())
		{
			final ElementOutline elementOutline = outline.getElement(elementInfo);
			if (elementOutline != null)
				processElementOutline(elementOutline, knownClasses);
		}
		
		processPackageOutlines(outline, knownClasses);
		
		return true;
	}

	private void processClassOutline(ClassOutline classOutline, Map<String, JClass> knownClasses,
		Map<JClass, CClassInfo> knownClassInfos)
	{
		generateAnnotates(classOutline, knownClasses);
		generateExtends(classOutline, knownClasses, knownClassInfos);
		generateImplements(classOutline, knownClasses);
		ignoreCustomzationsOnProperties(classOutline);
	}

	private void processEnumOutline(EnumOutline enumOutline, Map<String, JClass> knownClasses)
	{
		generateAnnotates(enumOutline, knownClasses);
		generateExtends(enumOutline, knownClasses);
		generateImplements(enumOutline, knownClasses);
	}

	private void processElementOutline(ElementOutline elementOutline, Map<String, JClass> knownClasses)
	{
		generateAnnotates(elementOutline, knownClasses);
		generateExtends(elementOutline, knownClasses);
		generateImplements(elementOutline, knownClasses);
	}

	private void processPackageOutlines(Outline outline, Map<String, JClass> knownClasses)
	{
		List<CPluginCustomization> customizations = findCustomizations(outline, Customizations.OBJECT_FACTORY_ELEMENT_NAME);
		
		for (CPluginCustomization customization : customizations)
		{
			final ObjectFactoryCustomization objectFactoryCustomization =
				(ObjectFactoryCustomization) unmarshall(Customizations.getContext(), customization);
			
			final String packageName = objectFactoryCustomization.getPackageName();
			
			if (packageName != null)
			{
				for (PackageOutline packageOutline : outline.getAllPackageContexts())
				{
					final JDefinedClass theObjectFactoryClass = packageOutline.objectFactory();
					// Filter on declared package name.
					if (packageName.equals(packageOutline._package().name()))
					{
						// PackageOutline: Annotate MetaObject(s)
						List<AnnotatesMetaObject> annotatesMetaObjects =
							objectFactoryCustomization.getAnnotatesMetaObject();
						if (annotatesMetaObjects != null)
						{
							for (AnnotatesMetaObject annotatesMetaObject : annotatesMetaObjects)
							{
								if (annotatesMetaObject != null)
									generateAnnotates(theObjectFactoryClass, annotatesMetaObject, knownClasses);
							}
						}

						// PackageOutline: Extends Class(es)
						ExtendsClass extendsClass = objectFactoryCustomization.getExtendsClass();
						if (extendsClass != null)
							generateExtends(theObjectFactoryClass, extendsClass, knownClasses);
						
						// PackageOutline: Implements Interface(s)
						List<ImplementsInterface> implementsInterfaces =
							objectFactoryCustomization.getImplementsInterface();
						if (implementsInterfaces != null)
						{
							for (ImplementsInterface implementsInterface : implementsInterfaces)
							{
								if (implementsInterface != null)
									generateImplements(theObjectFactoryClass, implementsInterface, knownClasses);
							}
						}
					}
				}
			}
		}
	}

	private List<JClass> generateAnnotates(ClassOutline classOutline, Map<String, JClass> knownClasses)
	{
		final JDefinedClass theClass = classOutline.implClass;
		final List<CPluginCustomization> annotatesMetaObjectCustomizations =
			findCustomizations(classOutline, Customizations.ANNOTATES_ELEMENT_NAME);
		return generateAnnotates(theClass, annotatesMetaObjectCustomizations, knownClasses);
	}

	private List<JClass> generateAnnotates(EnumOutline enumOutline, Map<String, JClass> knownClasses)
	{
		final JDefinedClass theClass = enumOutline.clazz;
		final List<CPluginCustomization> annotatesMetaObjectCustomizations =
			findCustomizations(enumOutline, Customizations.ANNOTATES_ELEMENT_NAME);
		return generateAnnotates(theClass, annotatesMetaObjectCustomizations, knownClasses);
	}

	private List<JClass> generateAnnotates(ElementOutline elementOutline, Map<String, JClass> knownClasses)
	{
		final JDefinedClass theClass = elementOutline.implClass;
		final List<CPluginCustomization> annotatesMetaObjectCustomizations =
			findCustomizations(elementOutline, Customizations.ANNOTATES_ELEMENT_NAME);
		return generateAnnotates(theClass, annotatesMetaObjectCustomizations, knownClasses);
	}

	private List<JClass> generateAnnotates(final JDefinedClass theClass,
		final List<CPluginCustomization> annotatesMetaObjectCustomizations, Map<String, JClass> knownClasses)
		throws AssertionError
	{
		final List<JClass> annotatedMetaObjects = new ArrayList<JClass>(annotatesMetaObjectCustomizations.size());
		for (final CPluginCustomization annotatesMetaObjectCustomization : annotatesMetaObjectCustomizations)
		{
			if (annotatesMetaObjectCustomization != null)
			{
				final AnnotatesMetaObject annotatesMetaObject =
					(AnnotatesMetaObject) unmarshall(Customizations.getContext(), annotatesMetaObjectCustomization);
				
				final JClass annotatedMetaObject = generateAnnotates(theClass, annotatesMetaObject, knownClasses);
				
				if (annotatedMetaObject != null)
					annotatedMetaObjects.add(annotatedMetaObject);
			}
		}
		return annotatedMetaObjects;
	}

	private JClass generateAnnotates(final JDefinedClass theClass, final AnnotatesMetaObject annotatesMetaObject,
		Map<String, JClass> knownClasses)
	{
		String annotation = annotatesMetaObject.getAnnotation();
		if (annotation != null)
		{
			final JClass targetClass = parseClass(annotation, theClass.owner(), knownClasses);
			JAnnotationUse use = theClass.annotate(targetClass);
			
			// Element
			for ( AnnotatesMetaObject.Element element : annotatesMetaObject.getElement() )
			{
				String name = element.getName();
				if ( element.getValue() == null )
					use.param(name, "all");
				else
				{
					Object obj = parse(element.getValue(), element.getType());
					useParameter(use, name, obj);
				}
			}
			
			// Elements
			for ( AnnotatesMetaObject.Elements elements : annotatesMetaObject.getElements() )
			{
				String name = elements.getName();
				if ( elements.getValue().size() == 0 )
					use.param(name, "all");
				else if ( elements.getValue().size() == 1 )
				{
					Object obj = parse(elements.getValue().get(0), elements.getType());
					useParameter(use, name, obj);
				}
				else if ( elements.getValue().size() > 1 )
				{
					JAnnotationArrayMember values = use.paramArray(name);
					for ( String value : elements.getValue() )
					{
						Object obj = parse(value, elements.getType());
						addParameterValue(values, obj);
					}
				}
			}
			
			return targetClass;
		}
		else
			return null;
	}

	/**
	 * Adds a member value pair to the given {@link JAnnotationUse}.
	 * 
	 * @param use The {@link JAnnotationUse} to use.
	 * @param name The simple name for this annotation.
	 * @param value The object value to use.
	 */
	@SuppressWarnings("rawtypes")
	private void useParameter(JAnnotationUse use, String name, Object value)
	{
		if ( value instanceof String )
			use.param(name, (String) value);
		else if ( value instanceof Boolean )
			use.param(name, (Boolean) value);
		else if ( value instanceof Integer )
			use.param(name, (Integer) value);
		else if ( value instanceof Long )
			use.param(name, (Long) value);
		else if ( value instanceof Short )
			use.param(name, (Short) value);
		else if ( value instanceof Double )
			use.param(name, (Double) value);
		else if ( value instanceof Float )
			use.param(name, (Float) value);
		else if ( value instanceof Byte )
			use.param(name, (Byte) value);
		else if ( value instanceof Character )
			use.param(name, (Character) value);
		else if ( value instanceof Enum )
			use.param(name, (Enum) value);
		else if ( value instanceof Class )
			use.param(name, (Class) value);
	}
	
	/**
	 * Adds a member value pair to the given {@link JAnnotationArrayMember}.
	 * 
	 * @param values The {@link JAnnotationArrayMember} to use.
	 * @param value The object value to add.
	 */
	@SuppressWarnings("rawtypes")
	private void addParameterValue(JAnnotationArrayMember values, Object value)
	{
		if ( value instanceof String )
			values.param((String) value);
		else if ( value instanceof Boolean )
			values.param((Boolean) value);
		else if ( value instanceof Integer )
			values.param((Integer) value);
		else if ( value instanceof Long )
			values.param((Long) value);
		else if ( value instanceof Short )
			values.param((Short) value);
		else if ( value instanceof Double )
			values.param((Double) value);
		else if ( value instanceof Float )
			values.param((Float) value);
		else if ( value instanceof Byte )
			values.param((Byte) value);
		else if ( value instanceof Character )
			values.param((Character) value);
		else if ( value instanceof Enum )
			values.param((Enum) value);
		else if ( value instanceof Class )
			values.param((Class) value);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object parse(String value, String type)
	{
		Object obj = value;
		if ( value != null )
		{
			if (String.class.getName().equals(type))
				obj = value;
			else if (Boolean.class.getName().equals(type))
				obj = Boolean.valueOf(value);
			else if (Integer.class.getName().equals(type))
				obj = Integer.valueOf(value);
			else if (Long.class.getName().equals(type))
				obj = Long.valueOf(value);
			else if (Short.class.getName().equals(type))
				obj = Short.valueOf(value);
			else if (Double.class.getName().equals(type))
				obj = Double.valueOf(value);
			else if (Float.class.getName().equals(type))
				obj = Float.valueOf(value);
			else if (Byte.class.getName().equals(type))
				obj = Byte.valueOf(value);
			else if (Character.class.getName().equals(type))
				obj = value.charAt(0);
			else
			{
				try
				{
					if (Class.class.getName().equals(type))
						obj = Class.forName(value);
					else if (Enum.class.getName().equals(type))
					{
						int nameIndex = value.lastIndexOf('.');
						String className = value.substring(0, nameIndex);
						String enumName = value.substring(nameIndex+1);
						Class enumClass = Class.forName(className);
						obj = Enum.valueOf(enumClass, enumName);
					}
				}
				catch (ClassNotFoundException | IllegalArgumentException ex)
				{
					String msg = ex.getClass().getSimpleName() + ": " + ex.getMessage();
					getLogger().error(msg + " for " + value, ex);
					obj = "UNKNOWN";
				}
			}
		}
		return obj;
	}

	private JClass generateExtends(ClassOutline classOutline, Map<String, JClass> knownClasses,
		Map<JClass, CClassInfo> knownClassInfos)
	{
		final JDefinedClass theClass = classOutline.implClass;
		final CPluginCustomization extendsClassCustomization = findCustomization(classOutline,	Customizations.EXTENDS_ELEMENT_NAME);
		JClass targetClass = generateExtends(theClass, extendsClassCustomization, knownClasses);
		final CClassInfo classInfo = classOutline.target;
		
		if (targetClass != null && classInfo.getBaseClass() == null && classInfo.getRefBaseClass() == null)
		{
			final CClassInfo targetClassInfo = knownClassInfos.get(targetClass);
			if (targetClassInfo == null && classInfo.getRefBaseClass() == null)
			{
				final Model model = classInfo.model;
				// BIEnum as BIClass is protected too much
				final BIEnum decl = new BIEnum();
				decl.ref = targetClass.fullName();
				final CClassRef baseClass =
					new CClassRef(model, classInfo.getSchemaComponent(), decl, new CCustomizations());
				classInfo.setBaseClass(baseClass);
			}
			else if (targetClassInfo != null && classInfo.getBaseClass() == null)
				classInfo.setBaseClass(targetClassInfo);
		}
		
		return targetClass;
	}

	private JClass generateExtends(EnumOutline enumOutline, Map<String, JClass> knownClasses)
	{
		final JDefinedClass theClass = enumOutline.clazz;
		final CPluginCustomization extendsClassCustomization = findCustomization(enumOutline, Customizations.EXTENDS_ELEMENT_NAME);
		return generateExtends(theClass, extendsClassCustomization, knownClasses);
	}

	private JClass generateExtends(ElementOutline elementOutline, Map<String, JClass> knownClasses)
	{
		final JDefinedClass theClass = elementOutline.implClass;
		final CPluginCustomization extendsClassCustomization = findCustomization(elementOutline, Customizations.EXTENDS_ELEMENT_NAME);
		return generateExtends(theClass, extendsClassCustomization, knownClasses);
	}

	private JClass generateExtends(final JDefinedClass theClass, final CPluginCustomization extendsClassCustomization,
		Map<String, JClass> knownClasses) throws AssertionError
	{
		if (extendsClassCustomization != null)
		{
			final ExtendsClass extendsClass = (ExtendsClass) unmarshall(Customizations.getContext(), extendsClassCustomization);
			return generateExtends(theClass, extendsClass, knownClasses);
		}
		else
			return null;
	}

	private JClass generateExtends(final JDefinedClass theClass, final ExtendsClass extendsClass,
		Map<String, JClass> knownClasses)
	{
		if (extendsClass.getClassName() != null)
		{
			final String _class = extendsClass.getClassName();
			final JClass targetClass = parseClass(_class, theClass.owner(), knownClasses);
			theClass._extends(targetClass);
			return targetClass;
		}
		else
			return null;
	}

	private List<JClass> generateImplements(ClassOutline classOutline, Map<String, JClass> knownClasses)
	{
		final JDefinedClass theClass = classOutline.implClass;
		final List<CPluginCustomization> implementsInterfaceCustomizations =
			findCustomizations(classOutline, Customizations.IMPLEMENTS_ELEMENT_NAME);
		return generateImplements(theClass, implementsInterfaceCustomizations, knownClasses);
	}

	private List<JClass> generateImplements(EnumOutline enumOutline, Map<String, JClass> knownClasses)
	{
		final JDefinedClass theClass = enumOutline.clazz;
		final List<CPluginCustomization> implementsInterfaceCustomizations =
			findCustomizations(enumOutline, Customizations.IMPLEMENTS_ELEMENT_NAME);
		return generateImplements(theClass, implementsInterfaceCustomizations, knownClasses);
	}

	private List<JClass> generateImplements(ElementOutline elementOutline, Map<String, JClass> knownClasses)
	{
		final JDefinedClass theClass = elementOutline.implClass;
		final List<CPluginCustomization> implementsInterfaceCustomizations =
			findCustomizations(elementOutline, Customizations.IMPLEMENTS_ELEMENT_NAME);
		return generateImplements(theClass, implementsInterfaceCustomizations, knownClasses);
	}

	private List<JClass> generateImplements(final JDefinedClass theClass,
		final List<CPluginCustomization> implementsInterfaceCustomizations, Map<String, JClass> knownClasses)
		throws AssertionError
	{
		final List<JClass> implementedInterfaces = new ArrayList<JClass>(implementsInterfaceCustomizations.size());
		for (final CPluginCustomization implementsInterfaceCustomization : implementsInterfaceCustomizations)
		{
			if (implementsInterfaceCustomization != null)
			{
				final ImplementsInterface implementsInterface =
					(ImplementsInterface) unmarshall(Customizations.getContext(), implementsInterfaceCustomization);
				
				final JClass implementedInterface = generateImplements(theClass, implementsInterface, knownClasses);
				
				if (implementedInterface != null)
					implementedInterfaces.add(implementedInterface);
			}
		}
		return implementedInterfaces;
	}

	private JClass generateImplements(final JDefinedClass theClass, final ImplementsInterface implementsInterface,
		Map<String, JClass> knownClasses)
	{
		String _interface = implementsInterface.getInterfaceName();
		if (_interface != null)
		{
			final JClass targetClass = parseClass(_interface, theClass.owner(), knownClasses);
			theClass._implements(targetClass);
			return targetClass;
		}
		else
			return null;
	}

	private JClass parseClass(String _class, JCodeModel codeModel, Map<String, JClass> knownClasses)
	{
		return new JavaTypeParser(knownClasses).parseClass(_class, codeModel);
	}
}
