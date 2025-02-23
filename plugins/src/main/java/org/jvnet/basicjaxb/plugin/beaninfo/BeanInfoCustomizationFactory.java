package org.jvnet.basicjaxb.plugin.beaninfo;

import static org.jvnet.basicjaxb.plugin.beaninfo.Customizations.BEAN_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.beaninfo.Customizations.PROPERTY_ELEMENT_NAME;
import static org.jvnet.basicjaxb.util.CustomizationUtils.findCustomization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.plugin.util.Selector;

import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CElementInfo;
import com.sun.tools.xjc.model.CPluginCustomization;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.CTypeInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.xml.xsom.XSComplexType;
import com.sun.xml.xsom.XSElementDecl;
import com.sun.xml.xsom.XSIdentityConstraint;
import com.sun.xml.xsom.XSSchemaSet;
import com.sun.xml.xsom.XSType;

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

	private Map<ClassOutline, List<Selector>> selectorsMap;
	public Map<ClassOutline, List<Selector>> getSelectorsMap()
	{
		if ( selectorsMap == null)
			setSelectorsMap(new HashMap<ClassOutline, List<Selector>>());
		return selectorsMap;
	}
	public void setSelectorsMap(Map<ClassOutline, List<Selector>> selectorsMap)
	{
		this.selectorsMap = selectorsMap;
	}
	
	public BeanInfoCustomization createBeanInfoCustomization(ClassOutline classOutline)
	{
		return new BeanInfoCustomization(classOutline, getElementCustomizationMap(),
			getClassCustomizationMap(), getSelectorsMap().get(classOutline));
	}
	
	public BeanInfoCustomizationFactory(Outline outline)
	{
		setOutline(outline);
		setSelectorsMap(gatherSelectors(outline));
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
				// findCustomization(pi, PROPERTY_ELEMENT_NAME))
				// Handled in BeanInfoCustomization
			}
		}		
	}
	
	private Map<ClassOutline, List<Selector>> gatherSelectors(Outline outline)
	{
		Collection<? extends ClassOutline> classOutlines = outline.getClasses();
		Model model = outline.getModel();
		XSSchemaSet modelSchemaSet = model.schemaComponent;
		Map<ClassOutline, List<Selector>> selectorMap = new HashMap<ClassOutline, List<Selector>>();
		for ( final ClassOutline classOutline : classOutlines )
		{
			List<Selector> selectors = new ArrayList<>();
			selectorMap.put(classOutline, selectors);
			if ( classOutline.getTarget() instanceof CClassInfo  )
			{
				CClassInfo ciTarget = (CClassInfo) classOutline.getTarget();
				if ( ciTarget.getSchemaComponent() instanceof XSElementDecl )
				{
					XSElementDecl ciTargetED = (XSElementDecl) ciTarget.getSchemaComponent();
					for ( XSIdentityConstraint ic : ciTargetED.getIdentityConstraints() )
						selectors.add(new Selector(classOutline, ic));
				}
				else
				{
					XSComplexType ciTargetCT = (XSComplexType) ciTarget.getSchemaComponent();
					Iterator<XSIdentityConstraint> idConstraintsIter = modelSchemaSet.iterateIdentityConstraints();
					while ( idConstraintsIter.hasNext() )
					{
						XSIdentityConstraint ic = idConstraintsIter.next();
						XSElementDecl icParent = ic.getParent();
						XSType icParentType = icParent.getType();
						if ( ciTargetCT == icParentType )
							selectors.add(new Selector(classOutline, ic));
					}
				}
			}
		}
		return selectorMap;
	}
}
