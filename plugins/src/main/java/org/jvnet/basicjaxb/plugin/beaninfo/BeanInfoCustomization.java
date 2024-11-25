package org.jvnet.basicjaxb.plugin.beaninfo;

import static com.sun.xml.xsom.XSFacet.FACET_FRACTIONDIGITS;
import static com.sun.xml.xsom.XSFacet.FACET_LENGTH;
import static com.sun.xml.xsom.XSFacet.FACET_MAXEXCLUSIVE;
import static com.sun.xml.xsom.XSFacet.FACET_MAXINCLUSIVE;
import static com.sun.xml.xsom.XSFacet.FACET_MAXLENGTH;
import static com.sun.xml.xsom.XSFacet.FACET_MINEXCLUSIVE;
import static com.sun.xml.xsom.XSFacet.FACET_MININCLUSIVE;
import static com.sun.xml.xsom.XSFacet.FACET_MINLENGTH;
import static com.sun.xml.xsom.XSFacet.FACET_PATTERN;
import static com.sun.xml.xsom.XSFacet.FACET_TOTALDIGITS;
import static org.jvnet.basicjaxb.plugin.beaninfo.Customizations.BEAN_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.beaninfo.Customizations.PROPERTY_ELEMENT_NAME;
import static org.jvnet.basicjaxb.util.CustomizationUtils.findCustomization;

import java.util.ArrayList;
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
import com.sun.xml.xsom.XSAttributeUse;
import com.sun.xml.xsom.XSComplexType;
import com.sun.xml.xsom.XSElementDecl;
import com.sun.xml.xsom.XSFacet;
import com.sun.xml.xsom.XSParticle;
import com.sun.xml.xsom.XSSimpleType;
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

	private Map<String, Integer> propertyIndexMap;
	public Map<String, Integer> getPropertyIndexMap()
	{
		if ( propertyIndexMap == null )
			setPropertyIndexMap(new LinkedHashMap<>());
		return propertyIndexMap;
	}
	public void setPropertyIndexMap(Map<String, Integer> propertyIndexMap)
	{
		this.propertyIndexMap = propertyIndexMap;
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
	
	private Map<CPropertyInfo, List<XSFacet>> propertyFacetMap;
	public Map<CPropertyInfo, List<XSFacet>> getPropertyFacetMap()
	{
		if ( propertyFacetMap == null )
			setPropertyFacetMap(new LinkedHashMap<>());
		return propertyFacetMap;
	}
	public void setPropertyFacetMap(Map<CPropertyInfo, List<XSFacet>> propertyFacetMap)
	{
		this.propertyFacetMap = propertyFacetMap;
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
		return
			(getBeanCustomization() != null) ||
			!getPropertyCustomizationMap().isEmpty()|| 
			!getPropertyFacetMap().isEmpty();
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
		// Note: The CClassInfo's 'ordered' property is TRUE by default and
		//       its 'getProperties()' method returns a 'List' (i.e. ordered)
		List<CPropertyInfo> piList = getTargetClass().getProperties();
		for ( int index=0; index < piList.size(); ++index )
		{
			CPropertyInfo propertyInfo = piList.get(index);
			getPropertyIndexMap().put(propertyInfo.getName(false), index);
			// Find the first {@link CPluginCustomization} for the given property info and property element name.
			CPluginCustomization pc = findCustomization(propertyInfo, PROPERTY_ELEMENT_NAME);
			if ( pc != null )
				getPropertyCustomizationMap().put(propertyInfo, pc);
			else
				setCustomizationByElement(propertyInfo);
			
			// Gather simple type facets for the current property info.
			gatherPropertyFacets(propertyInfo);
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
	
	/* Gather simple type facets for the current property info. */
	private void gatherPropertyFacets(CPropertyInfo propertyInfo)
	{
		XSSimpleType pst = null;
		if ( propertyInfo.getSchemaComponent() instanceof XSAttributeUse)
		{
			XSAttributeUse source = (XSAttributeUse) propertyInfo.getSchemaComponent();
			pst = source.getDecl().getType();
		}
		else if ( propertyInfo.getSchemaComponent() instanceof XSParticle )
		{
			XSParticle source = (XSParticle) propertyInfo.getSchemaComponent();
			if ( source.getTerm() instanceof XSElementDecl )
			{
				XSElementDecl ed = (XSElementDecl) source.getTerm();
				if ( ed.getType() instanceof XSSimpleType )
					pst = (XSSimpleType) ed.getType();
			}
		}
		
		if ( pst != null )
		{
			List<XSFacet> facetList = new ArrayList<>();
			XSFacet facet = null;
			
			if ( (facet = pst.getFacet(FACET_PATTERN)) != null )
				facetList.add(facet);
			
			if ( (facet = pst.getFacet(FACET_LENGTH)) != null )
				facetList.add(facet);
			
			if ( (facet = pst.getFacet(FACET_MAXLENGTH)) != null )
				facetList.add(facet);
			
			if ( (facet = pst.getFacet(FACET_MINLENGTH)) != null )
				facetList.add(facet);
			
			if ( (facet = pst.getFacet(FACET_MAXEXCLUSIVE)) != null )
				facetList.add(facet);
			
			if ( (facet = pst.getFacet(FACET_MINEXCLUSIVE)) != null )
				facetList.add(facet);
			
			if ( (facet = pst.getFacet(FACET_MAXINCLUSIVE)) != null )
				facetList.add(facet);
			
			if ( (facet = pst.getFacet(FACET_MININCLUSIVE)) != null )
				facetList.add(facet);
			
			if ( (facet = pst.getFacet(FACET_TOTALDIGITS)) != null )
				facetList.add(facet);
			
			if ( (facet = pst.getFacet(FACET_FRACTIONDIGITS)) != null )
				facetList.add(facet);

			// Put a facet list into the map, can be empty.
			getPropertyFacetMap().put(propertyInfo, facetList);
		}
	}
}
