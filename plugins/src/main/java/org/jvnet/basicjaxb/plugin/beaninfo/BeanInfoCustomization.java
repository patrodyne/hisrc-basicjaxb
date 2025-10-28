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
import static java.beans.Introspector.decapitalize;
import static org.jvnet.basicjaxb.plugin.beaninfo.Customizations.BEAN_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.beaninfo.Customizations.NAMESPACE_URI;
import static org.jvnet.basicjaxb.plugin.beaninfo.Customizations.PROPERTY_ELEMENT_NAME;
import static org.jvnet.basicjaxb.util.CustomizationUtils.findCustomization;
import static org.jvnet.basicjaxb.util.CustomizationUtils.unmarshall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.plugin.beaninfo.model.Bean;
import org.jvnet.basicjaxb.plugin.beaninfo.model.Property;
import org.jvnet.basicjaxb.plugin.util.Selector;
import org.jvnet.basicjaxb.util.OutlineUtils;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CPluginCustomization;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIDeclaration;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIXPluginCustomization;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BindInfo;
import com.sun.xml.xsom.XSAnnotation;
import com.sun.xml.xsom.XSAttributeUse;
import com.sun.xml.xsom.XSComplexType;
import com.sun.xml.xsom.XSComponent;
import com.sun.xml.xsom.XSElementDecl;
import com.sun.xml.xsom.XSFacet;
import com.sun.xml.xsom.XSParticle;
import com.sun.xml.xsom.XSSimpleType;
import com.sun.xml.xsom.XSTerm;
import com.sun.xml.xsom.XSType;

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
	
	private List<Selector> selectorList;
	public List<Selector> getSelectorList()
	{
		if ( selectorList == null )
			setSelectorList(new ArrayList<Selector>());
		return selectorList;
	}
	public void setSelectorList(List<Selector> selectorList)
	{
		this.selectorList = selectorList;
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
	
	/* Set property customization by reference, if any. */
	private void setPropertyCustomizationByElement(CPropertyInfo propertyInfo)
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
	
	List<FieldInfo> fieldInfoList;
	public List<FieldInfo> getFieldInfoList()
	{
		if ( fieldInfoList == null )
			setFieldInfoList(new ArrayList<>());
		return fieldInfoList;
	}
	public void setFieldInfoList(List<FieldInfo> fieldInfoList)
	{
		this.fieldInfoList = fieldInfoList;
	}

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
			if ( getTargetClass().getElementName() != null )
				setTargetElementName(getTargetClass().getElementName());
			else if ( getTargetClass().getTypeName() != null )
				setTargetElementName(getTargetClass().getTypeName());
			else
			{
				XSComponent sc = getTargetClass().getSchemaComponent();
				if ( sc instanceof XSComplexType )
		        {
		        	XSComplexType source = (XSComplexType) sc;
		            if ( source.getScope() instanceof XSElementDecl )
		            {
		                XSElementDecl ed = (XSElementDecl) source.getScope();
		                setTargetElementName(new QName(source.getTargetNamespace(), ed.getName()));
		            }
		        }
			}
		}
		return targetElementName;
	}
	public void setTargetElementName(QName targetElementName)
	{
		this.targetElementName = targetElementName;
	}
	
	public List<CPropertyInfo> getTargetProperties()
	{
		return getTargetClass().getProperties();
	}
	
	public Class<?> getPackagedClass() throws ClassNotFoundException
	{
		
		return OutlineUtils.getPackagedClass(getClassOutline());
	}
	
	public String getClassFullName()
	{
		return getTargetClass().fullName();
	}
	
	public String getClassPublicName()
	{
		return getTargetClass().shortName;
	}
	
	public String getClassPrivateName()
	{
		return decapitalize(getClassPublicName());
	}
	
	public String getClassListName()
	{
		return getClassPrivateName()+"List";
	}

	public boolean hasCustomizations()
	{
		boolean bc = ( getBeanCustomization() != null);
		boolean pcm = !getPropertyCustomizationMap().isEmpty();
		boolean pfm = !getPropertyFacetMap().isEmpty();
		boolean sl = !getSelectorList().isEmpty();
		boolean fil = !getFieldInfoList().isEmpty();
						
		return bc || pcm || pfm || sl || fil;
	}
	
	/**
	 * Construct with the given {@link ClassOutline} instances. Create
	 * using the {@link BeanInfoCustomizationFactory} instance.
	 * 
	 * @param co Outline that provides per-{@link CClassInfo} information.
	 * @param ecm Map of customizations by element qualified name.
	 * @param ccm Map of customizations by class info.
	 */
	public BeanInfoCustomization(ClassOutline co,
		Map<QName, CPluginCustomization> ecm,
		Map<CClassInfo, CPluginCustomization> ccm,
//		Map<CPropertyInfo, CPluginCustomization> pcm,
		List<Selector> selectorList)
	{
		setClassOutline(co);
		setElementCustomizationMap(ecm);
		setClassCustomizationMap(ccm);
//		setPropertyCustomizationMap(pcm);
		setSelectorList(selectorList);
		
		// Find the first {@link CPluginCustomization} for the given class outline and bean element name;
		// otherwise, get the customization for the current target class.
		CPluginCustomization bc = findCustomization(getClassOutline(), BEAN_ELEMENT_NAME);
		if ( bc == null )
			bc = getElementCustomizationMap().get(getTargetElementName());
		if ( bc == null )
			bc = getClassCustomizationMap().get(getTargetClass());
		if ( bc == null )
		{
			XSComponent source = getTargetClass().getSchemaComponent();
			XSAnnotation sa = source.getAnnotation();
			if ( (sa != null) && sa.getAnnotation() instanceof BindInfo )
			{
				BindInfo bi = (BindInfo) sa.getAnnotation();
				for ( BIDeclaration decl : bi.getDecls() )
				{
					if ( decl instanceof BIXPluginCustomization )
					{
						BIXPluginCustomization bpc = (BIXPluginCustomization) decl;
						bc = new CPluginCustomization(bpc.element, bpc.getLocation());
						bc.markAsAcknowledged();
					}
				}
			}
		}
		
		if ( bc != null )
			setBeanCustomization(bc);

		// Gather (unmarshal) the Bean, if any
		gatherBean();

		// Note: The CClassInfo's 'ordered' property is TRUE by default and
		//       its 'getProperties()' method returns a 'List' (i.e. ordered)
		List<CPropertyInfo> piList = getTargetClass().getProperties();
		
		// Re-order attributes before other 'source' types.
		List<CPropertyInfo> auList = new ArrayList<>();
		for ( CPropertyInfo propertyInfo : piList )
		{
			if ( propertyInfo.getSchemaComponent() instanceof XSAttributeUse )
				auList.add(propertyInfo);
		}
		for ( CPropertyInfo propertyInfo : auList)
			piList.remove(propertyInfo);
		piList.addAll(0, auList);

		// Loop over all property infos for the current class outline.
		for ( int index=0; index < piList.size(); ++index )
		{
			CPropertyInfo propertyInfo = piList.get(index);
			getPropertyIndexMap().put(propertyInfo.getName(false), index);
			// Find the first {@link CPluginCustomization} for the given property info and property element name.
			CPluginCustomization pc = findCustomization(propertyInfo, PROPERTY_ELEMENT_NAME);
			if ( pc != null )
				getPropertyCustomizationMap().put(propertyInfo, pc);
			else
				setPropertyCustomizationByElement(propertyInfo);
			
			// Gather simple type facets for the current property info.
			gatherPropertyFacets(propertyInfo);
		}
		
		// Gather list of FieldInfo (contains Property).
		gatherFieldInfoList();
	}
	
	// Gather (unmarshal) the appinfo bean, if any
	private void gatherBean()
	{
		if ( getBeanCustomization() != null )
		{
			if ( NAMESPACE_URI.equals(getBeanCustomization().element.getNamespaceURI()))
				setBean((Bean) unmarshall(Customizations.getContext(), getBeanCustomization()));
		}
	}
	
	/* Gather list of FieldInfo. */
	private void gatherFieldInfoList()
	{
		final Map<String, FieldInfo> fieldInfoMap = new HashMap<>();
		
		// Properties with Customizations
		for ( Entry<CPropertyInfo, CPluginCustomization> entry : getPropertyCustomizationMap().entrySet())
		{
			CPropertyInfo propertyInfo = entry.getKey();
			String propertyInfoName = propertyInfo.getName(false);
			FieldInfo fieldInfo = new FieldInfo(getImplClass(), propertyInfo);

			CPluginCustomization propertyCustomization = entry.getValue();
			if (propertyCustomization != null)
			{
				final Property property =
					(Property) unmarshall(Customizations.getContext(), propertyCustomization);
				
				if ( property != null )
				{
					if ( property.getName() == null )
						property.setName(fieldInfo.getFieldName());
					if ( property.getDisplayName() == null )
						property.setDisplayName(fieldInfo.getFieldDisplayName());
					fieldInfo.setProperty(property);
				}
			}
			
			if ( fieldInfo.getProperty() != null )
				fieldInfoMap.put(propertyInfoName, fieldInfo);
		}
		
		// Properties with Facets
		for ( Entry<CPropertyInfo, List<XSFacet>> entry: getPropertyFacetMap().entrySet() )
		{
			CPropertyInfo propertyInfo = entry.getKey();
			String propertyInfoName = propertyInfo.getName(false);
			
			FieldInfo fieldInfo = fieldInfoMap.get(propertyInfoName);
			if ( fieldInfo == null )
				fieldInfo = new FieldInfo(getImplClass(), propertyInfo);
			
			fieldInfo.setFacets(entry.getValue());
			fieldInfoMap.put(propertyInfoName, fieldInfo);
		}
		
		// Properties without Customizations and without Facets
		// Thus, every property gets a fieldInfo for indexing, etc.
		for ( CPropertyInfo propertyInfo : getTargetProperties() )
		{
			if
			(
				!getPropertyCustomizationMap().containsKey(propertyInfo) &&
				!getPropertyFacetMap().containsKey(propertyInfo)
			)
			{
				String propertyInfoName = propertyInfo.getName(false);
				FieldInfo fieldInfo = fieldInfoMap.get(propertyInfoName);
				if ( fieldInfo == null )
					fieldInfo = new FieldInfo(getImplClass(), propertyInfo);

				fieldInfoMap.put(propertyInfoName, fieldInfo);
			}
		}
		
		// Assign raw type for the field.
		//
	    // This type can represent the entire value of this field; or,
	    // for fields that can carry multiple values, this is an array.
	    // This type allows the client of the outline to generate code
	    // to set/get values from a property.
		//
		for ( FieldOutline df : getClassOutline().getDeclaredFields() )
		{
			String propertyInfoName = df.getPropertyInfo().getName(false);
			FieldInfo fieldInfo = fieldInfoMap.get(propertyInfoName);
			if ( fieldInfo != null )
				fieldInfo.setFieldRawType(df.getRawType());
		}
		
		if ( !fieldInfoMap.isEmpty() )
		{
			// Order by index or alphabetically.
			TreeMap<Integer, FieldInfo> fiTreeMap1 = new TreeMap<>();
			TreeMap<String, FieldInfo> fiTreeMap2 = new TreeMap<>();
			
			// Index by given index or cache until later
			for ( FieldInfo fieldInfo : fieldInfoMap.values() )
			{
				Integer fiIndex = getPropertyIndexMap().get(fieldInfo.getFieldName());
				if ( fiIndex != null )
					fiTreeMap1.put(fiIndex, fieldInfo);
				else
					fiTreeMap2.put(fieldInfo.getFieldDisplayName(), fieldInfo);
			}
			
			// Index cached FieldInfo(s) alphabetically by display name.
			// Prioritize attributes.
			if ( !fiTreeMap2.isEmpty() )
			{
				// Convert to list of FieldInfo(s).
				List<FieldInfo> fiList = new ArrayList<>(fiTreeMap2.values());
				// Re-order attributes before other 'source' types.
				List<FieldInfo> auList = new ArrayList<>();
				for ( FieldInfo fieldInfo : fiList )
				{
					if ( FieldSource.ATTRIBUTE.equals(fieldInfo.getFieldSource()) )
						auList.add(fieldInfo);
				}
				for ( FieldInfo fieldInfo : auList)
					fiList.remove(fieldInfo);
				fiList.addAll(0, auList);
				
				Integer index = fiTreeMap1.isEmpty() ? 0 : fiTreeMap1.lastKey();
				for ( FieldInfo fieldInfo : fiList )
					fiTreeMap1.put(++index, fieldInfo);
			}
			
			// Re-index, in sequence without gaps, zero-based.
			// Note: The fiTreeMap1's value iterator returns the values in
			//       ascending order of the corresponding keys.
			List<FieldInfo> fiList = new ArrayList<>(fiTreeMap1.values());
			for ( int index = 0; index < fiList.size(); ++index )
				fiList.get(index).setFieldIndex(index);
			setFieldInfoList(fiList);
		}
	}
	
	/* Gather simple type facets for the current property info. */
	private void gatherPropertyFacets(CPropertyInfo propertyInfo)
	{
		XSSimpleType pst = null;
		XSType pt = FieldInfo.getType(propertyInfo);
		if ( pt instanceof XSSimpleType )
			pst = (XSSimpleType) pt;
		
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

			if ( !facetList.isEmpty() )
			{
				// Put a non-empty facet list into the map.
				getPropertyFacetMap().put(propertyInfo, facetList);
			}
		}
	}
}
