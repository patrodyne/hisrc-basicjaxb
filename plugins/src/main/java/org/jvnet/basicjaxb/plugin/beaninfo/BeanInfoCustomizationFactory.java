package org.jvnet.basicjaxb.plugin.beaninfo;

import static org.jvnet.basicjaxb.plugin.beaninfo.Customizations.BEAN_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.beaninfo.Customizations.PROPERTY_ELEMENT_NAME;
import static org.jvnet.basicjaxb.util.CustomizationUtils.findCustomization;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CElementInfo;
import com.sun.tools.xjc.model.CPluginCustomization;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.CTypeInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * A factory to create {@link BeanInfoCustomization} instances.
 */
public class BeanInfoCustomizationFactory
{
	private Outline outline;
	public Outline getOutline() { return outline; }
	public void setOutline(Outline outline) { this.outline = outline; }
	
	public Model getModel()
	{
		return getOutline().getModel();
	}

	private Map<QName, CPluginCustomization> elementCustomizationMap;
	public Map<QName, CPluginCustomization> getElementCustomizationMap()
	{
		if ( elementCustomizationMap == null )
			setElementCustomizationMap(new LinkedHashMap<>());
		return elementCustomizationMap;
	}
	public void setElementCustomizationMap(Map<QName, CPluginCustomization> elementCustomizationMap)
	{
		this.elementCustomizationMap = elementCustomizationMap;
	}
	
	private Map<CClassInfo, CPluginCustomization> classCustomizationMap;
	public Map<CClassInfo, CPluginCustomization> getClassCustomizationMap()
	{
		if ( classCustomizationMap == null )
			setClassCustomizationMap(new LinkedHashMap<>());
		return classCustomizationMap;
	}
	public void setClassCustomizationMap(Map<CClassInfo, CPluginCustomization> classCustomizationMap)
	{
		this.classCustomizationMap = classCustomizationMap;
	}
	
	public BeanInfoCustomization createBeanInfoCustomization(ClassOutline classOutline)
	{
		return new BeanInfoCustomization(classOutline, getElementCustomizationMap(), getClassCustomizationMap());
	}
	
	public BeanInfoCustomizationFactory(Outline outline)
	{
		setOutline(outline);
		initialize();
	}
	
	private void initialize()
	{
		for ( CElementInfo ei : getModel().getAllElements() )
		{
			CPluginCustomization eicu = null;
			if ( (eicu = findCustomization(ei, BEAN_ELEMENT_NAME)) != null )
				getElementCustomizationMap().put(ei.getContentType().getTypeName(), eicu);
			if ( (eicu = findCustomization(ei, PROPERTY_ELEMENT_NAME)) != null )
				getElementCustomizationMap().put(ei.getElementName(), eicu);
		}
		
		for ( CClassInfo ci : getModel().beans().values() )
		{
			CPluginCustomization cicu = null;
			if ( (cicu = findCustomization(ci, BEAN_ELEMENT_NAME)) != null )
				getClassCustomizationMap().put(ci, cicu);
			if ( (cicu = findCustomization(ci, PROPERTY_ELEMENT_NAME)) != null )
				getClassCustomizationMap().put(ci, cicu);
			CPluginCustomization picu = null;
			// Search for 'bean' customization(s) for anonymous types.
			for ( CPropertyInfo pi : ci.getProperties() )
			{
				if ( (picu = findCustomization(pi, BEAN_ELEMENT_NAME)) != null )
				{
					// List of {@link TypeInfo}s that this property references.
					for ( CTypeInfo ti : pi.ref() )
					{
						if ( ti instanceof CClassInfo )
						{
							// A reference with no name is anonymous?
							CClassInfo ciRef = (CClassInfo) ti;
							getClassCustomizationMap().put(ciRef, picu);
						}
					}
				}
			}
		}		
	}
}
