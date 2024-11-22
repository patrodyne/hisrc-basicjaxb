package org.jvnet.basicjaxb.plugin.beaninfo;

import static org.jvnet.basicjaxb.plugin.beaninfo.Customizations.BEAN_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.beaninfo.Customizations.PROPERTY_ELEMENT_NAME;
import static org.jvnet.basicjaxb.util.CustomizationUtils.findCustomization;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.plugin.beaninfo.model.Bean;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CPluginCustomization;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.xml.xsom.XSComplexType;
import com.sun.xml.xsom.XSElementDecl;
import com.sun.xml.xsom.XSParticle;
import com.sun.xml.xsom.XSTerm;

/**
 * This class collects all bean and property {@link CPluginCustomization} instances in the 
 * {@link Customizations} namespace.
 */
public class BeanInfoCustomization
{
	private ClassOutline classOutline;
	public ClassOutline getClassOutline() { return classOutline; }
	public void setClassOutline(ClassOutline classOutline) { this.classOutline = classOutline; }

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
	
	private Map<CPropertyInfo, CPluginCustomization> propertyCustomizationMap;
	public Map<CPropertyInfo, CPluginCustomization> getPropertyCustomizationMap()
	{
		if ( propertyCustomizationMap == null )
			setPropertyCustomizationMap(new LinkedHashMap<>());
		return propertyCustomizationMap;
	}
	public void setPropertyCustomizationMap(Map<CPropertyInfo, CPluginCustomization> propertyCustomizationMap)
	{
		this.propertyCustomizationMap = propertyCustomizationMap;
	}
	
	private CPluginCustomization beanCustomization;
	public CPluginCustomization getBeanCustomization()
	{
		return beanCustomization;
	}
	public void setBeanCustomization(CPluginCustomization beanCustomization)
	{
		this.beanCustomization = beanCustomization;
	}
	
	private JDefinedClass beanInfoClass;
	public JDefinedClass getBeanInfoClass() { return beanInfoClass; }
	public void setBeanInfoClass(JDefinedClass beanInfoClass) { this.beanInfoClass = beanInfoClass; }
	
	private Bean bean;
	public Bean getBean() { return bean; }
	public void setBean(Bean bean) { this.bean = bean; }
	
	public Outline getOutline()
	{
		return getClassOutline().parent();
	}
	
	public Model getModel()
	{
		return getOutline().getModel();
	}
	
	public JCodeModel getCodeModel()
	{
		return getOutline().getCodeModel();
	}
	
	public JDefinedClass getImplClass()
	{
		return getClassOutline().implClass;
	}
	
	public CClassInfo getTargetClass()
	{
		return getClassOutline().target;
	}
	
	private QName targetElementName;
	public QName getTargetElementName()
	{
		if ( targetElementName == null )
		{
			if ( getTargetClass().getSchemaComponent() instanceof XSComplexType )
			{
				XSComplexType source = (XSComplexType) getTargetClass().getSchemaComponent();
				if ( (source.getName() != null) )
					targetElementName = new QName(source.getTargetNamespace(), source.getName());
				else
					targetElementName = null;
			}
		}
		return targetElementName;
	}
	
	public List<CPropertyInfo> getTargetProperties()
	{
		return getTargetClass().getProperties();
	}
	
	public boolean hasCustomizations()
	{
		return (getBeanCustomization() != null) || !getPropertyCustomizationMap().isEmpty();
	}
	
	/**
	 * Construct with the given {@link ClassOutline} instances. Create
	 * with a {@link BeanInfoCustomizationFactory} instance.
	 * 
	 * @param co Outline that provides per-{@link CClassInfo} information.
	 * @param ecm Map of customizations by element qualified name.
	 * @param ccm Map of customizations by class info.
	 */
	public BeanInfoCustomization(ClassOutline co,
		Map<QName, CPluginCustomization> ecm,
		Map<CClassInfo, CPluginCustomization> ccm)
	{
		setClassOutline(co);
		setElementCustomizationMap(ecm);
		setClassCustomizationMap(ccm);
		
		// Find the first {@link CPluginCustomization} for the given class outline and bean element name;
		// otherwise, get the customization for the current target class.
		CPluginCustomization bc = findCustomization(getClassOutline(), BEAN_ELEMENT_NAME);
		if ( bc == null )
			bc = getElementCustomizationMap().get(getTargetElementName());
		if ( bc == null )
			bc = getClassCustomizationMap().get(getTargetClass());
		
		if ( bc != null )
			setBeanCustomization(bc);
		
		// Loop over all property infos for the current class outline.
		for (CPropertyInfo propertyInfo : getClassOutline().target.getProperties())
		{
			// Find the first {@link CPluginCustomization} for the given property info and property element name.
			CPluginCustomization pc = findCustomization(propertyInfo, PROPERTY_ELEMENT_NAME);
			if ( pc != null )
				getPropertyCustomizationMap().put(propertyInfo, pc);
			else
				setCustomizationByElement(propertyInfo);
		}
	}

	/* Set property customization by reference, if any. */
	private void setCustomizationByElement(CPropertyInfo propertyInfo)
	{
		if ( !getPropertyCustomizationMap().containsKey(propertyInfo) )
		{
			// Look for a matching element reference for the current property info.
			if ( propertyInfo.getSchemaComponent() instanceof XSParticle)
			{
				XSParticle source = (XSParticle) propertyInfo.getSchemaComponent();
				XSTerm term = source.getTerm();
				if ( term.isElementDecl() )
				{
					XSElementDecl ed = term.asElementDecl();
					QName refName = new QName(ed.getTargetNamespace(), ed.getName());
					CPluginCustomization pcRef = getElementCustomizationMap().get(refName);
					if ( pcRef != null )
						getPropertyCustomizationMap().put(propertyInfo, pcRef);
				}
			}	
		}
	}
}
