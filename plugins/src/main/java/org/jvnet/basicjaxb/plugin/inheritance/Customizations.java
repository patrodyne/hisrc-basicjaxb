package org.jvnet.basicjaxb.plugin.inheritance;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.util.CustomizationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CPluginCustomization;

public class Customizations
{
	public static String NAMESPACE_URI = "http://jvnet.org/basicjaxb/xjc/inheritance";
	public static QName OBJECT_FACTORY_ELEMENT_NAME = new QName(NAMESPACE_URI, "objectFactory");
	public static QName EXTENDS_ELEMENT_NAME = new QName(NAMESPACE_URI, "extends");
	public static QName IMPLEMENTS_ELEMENT_NAME = new QName(NAMESPACE_URI, "implements");
	public static QName ANNOTATES_ELEMENT_NAME = new QName(NAMESPACE_URI, "annotates");
	public static QName ANNOTATES_ELEMENT_ELEMENT_NAME = new QName(NAMESPACE_URI, "element");
	public static QName ANNOTATES_ELEMENTS_ELEMENT_NAME = new QName(NAMESPACE_URI, "elements");
	
    private static final Logger logger = LoggerFactory.getLogger(Customizations.class);
    public static Logger getLogger() { return logger; }
	
	private static final JAXBContext context;
	static
	{
		try
		{
			context = JAXBContext.newInstance
			(
				ObjectFactory.class.getPackage().getName(),
				ObjectFactory.class.getClassLoader()
			);
		}
		catch (JAXBException ex)
		{
			getLogger().error("Cannot initialize JAXBContext", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static JAXBContext getContext()
	{
		return context;
	}

	public static void _annotates(CClassInfo classInfo, Map<String, Map<String,Object>> annotationMap)
	{
		for (String annotation : annotationMap.keySet())
		{
			AnnotatesMetaObject annotatesMetaObject = new AnnotatesMetaObject();
			annotatesMetaObject.setAnnotation(annotation);
			Map<String, Object> elementMap = annotationMap.get(annotation);
			if ( elementMap != null )
			{
				for ( String name : elementMap.keySet() )
				{
					Object object = elementMap.get(name);
					if ( object != null )
					{
						if ( object.getClass().isArray() )
						{
						    Class<?> componentType = object.getClass().getComponentType();
							AnnotatesMetaObject.Elements elements = new AnnotatesMetaObject.Elements();
							elements.setName(name);
							elements.setType(componentType.getName());
							for ( Object value : _toObjectArray(object, componentType) )
								elements.getValue().add(value.toString());
							annotatesMetaObject.getElements().add(elements);
						}
						else
						{
							AnnotatesMetaObject.Element element = new AnnotatesMetaObject.Element();
							element.setName(name);
							element.setType(object.getClass().getName());
							element.setValue(object.toString());
							annotatesMetaObject.getElement().add(element);
						}
					}
				}
			}
			CPluginCustomization customization =
				CustomizationUtils.marshal(getContext(), Customizations.ANNOTATES_ELEMENT_NAME, annotatesMetaObject);
			customization.markAsAcknowledged();
			classInfo.getCustomizations().add(customization);
		}
	}
	
	private static Object[] _toObjectArray(Object array, Class<?> componentType)
	{
	    if (componentType.isPrimitive())
	    {
	        List<Object> objects = new ArrayList<>();
	        int length = Array.getLength(array);
	        for (int i = 0; i < length; i++)
	        {
	        	Object object = Array.get(array, i);
	        	if ( object != null )
	        		objects.add(object);
	        }
	        return objects.toArray();
	    }
	    else
	        return (Object[]) array;
	}
	
	public static void _extends(CClassInfo classInfo, String className)
	{
		final ExtendsClass extendsClass = new ExtendsClass();
		extendsClass.setClassName(className);
		final CPluginCustomization customization =
			CustomizationUtils.marshal(getContext(), Customizations.EXTENDS_ELEMENT_NAME, extendsClass);
		classInfo.getCustomizations().add(customization);
		customization.markAsAcknowledged();
	}

	public static void _implements(CClassInfo classInfo, String... interfaceNames)
	{
		for (String interfaceName : interfaceNames)
		{
			final ImplementsInterface implementsInterface = new ImplementsInterface();
			implementsInterface.setInterfaceName(interfaceName);
			final CPluginCustomization customization =
				CustomizationUtils.marshal(getContext(), Customizations.IMPLEMENTS_ELEMENT_NAME, implementsInterface);
			customization.markAsAcknowledged();
			classInfo.getCustomizations().add(customization);
		}
	}
}
