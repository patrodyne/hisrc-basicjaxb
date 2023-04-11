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
 * <p>This plugin enables <em>Schema Derived</em> (SD) classes to extend 
 * a non-SD class or implement non-SD interfaces.</p>
 * 
 * <b>Inheritance</b>
 * <pre>
 * xmlns:inh="http://jvnet.org/basicjaxb/xjc/inheritance"
 * </pre>
 * 
 * {@link ObjectFactoryCustomization}
 * <pre>
 * &lt;inh:objectFactory packageName="org.example.my_model"&gt;
 *   &lt;inh:extends&gt;org.example.my_base.ObjectFactory&lt;/inh:extends&gt;
 *   &lt;inh:implements&gt;org.example.my_base.MyInterface1&lt;/inh:implements&gt;
 *   &lt;inh:implements&gt;org.example.my_base.MyInterface2&lt;/inh:implements&gt;
 * &lt;/inh:objectFactory&gt;
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
 * 
 */
public class InheritancePlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "Xinheritance";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "makes schema-derived classes extend certain class or implement certain interfaces";

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
				Customizations.EXTENDS_ELEMENT_NAME,
				Customizations.IMPLEMENTS_ELEMENT_NAME,
				Customizations.OBJECT_FACTORY_ELEMENT_NAME
			);
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
		generateExtends(classOutline, knownClasses, knownClassInfos);
		generateImplements(classOutline, knownClasses);
		ignoreCustomzationsOnProperties(classOutline);
	}

	private void ignoreCustomzationsOnProperties(ClassOutline classOutline)
	{
		for (CPropertyInfo propertyInfo : classOutline.target.getProperties())
		{
			findCustomization(propertyInfo, Customizations.EXTENDS_ELEMENT_NAME);
			findCustomization(propertyInfo, Customizations.IMPLEMENTS_ELEMENT_NAME);
		}
	}

	private void processEnumOutline(EnumOutline enumOutline, Map<String, JClass> knownClasses)
	{
		generateExtends(enumOutline, knownClasses);
		generateImplements(enumOutline, knownClasses);
	}

	private void processElementOutline(ElementOutline elementOutline, Map<String, JClass> knownClasses)
	{
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
					final JDefinedClass theClass = packageOutline.objectFactory();
					// Filter on declared package name.
					if (packageName.equals(packageOutline._package().name()))
					{
						ExtendsClass extendsClass = objectFactoryCustomization.getExtendsClass();
						if (extendsClass != null)
							generateExtends(theClass, extendsClass, knownClasses);
						
						List<ImplementsInterface> implementsInterfaces =
							objectFactoryCustomization.getImplementsInterface();
						if (implementsInterfaces != null)
						{
							for (ImplementsInterface implementsInterface : implementsInterfaces)
							{
								if (implementsInterface != null)
									generateImplements(theClass, implementsInterface, knownClasses);
							}
						}
					}
				}
			}
		}
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
