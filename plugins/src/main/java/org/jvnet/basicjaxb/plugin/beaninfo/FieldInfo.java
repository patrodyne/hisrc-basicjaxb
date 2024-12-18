package org.jvnet.basicjaxb.plugin.beaninfo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.plugin.beaninfo.model.Property;

import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.xml.xsom.XSFacet;
import com.sun.xml.xsom.XSSimpleType;
import com.sun.xml.xsom.XSType;

/**
 * Field information including name, display name, index, type, type name,
 * {@link Property} and {@link XSFacet}s.
 */
public class FieldInfo
{
	private String fieldName;
	public String getFieldName() { return fieldName; }
	public void setFieldName(String fieldName) { this.fieldName = fieldName; }
	
	private String fieldDisplayName;
	public String getFieldDisplayName() { return fieldDisplayName; }
	public void setFieldDisplayName(String fieldDisplayName) { this.fieldDisplayName = fieldDisplayName; }
	
	private Integer fieldIndex;
	public Integer getFieldIndex() { return fieldIndex; }
	public void setFieldIndex(Integer fieldIndex) { this.fieldIndex = fieldIndex; }

	private XSType fieldType;
	public XSType getFieldType() { return fieldType; }
	public void setFieldType(XSType fieldType) { this.fieldType = fieldType; }
	
	private QName fieldTypeName;
	public QName getFieldTypeName()
	{
		if ( (fieldTypeName == null) && (getFieldType() != null) )
		{
			String ns = getFieldType().getTargetNamespace();
			String ln = getFieldType().getName();
			if ( ln == null )
			{
				XSType bt = getFieldType().getBaseType();
				ns = bt.getTargetNamespace();
				ln = bt.getName();
			}
			if ( ln != null )
				setFieldTypeName(new QName(ns, ln));
		}
		return fieldTypeName;
	}
	public void setFieldTypeName(QName fieldTypeName)
	{
		this.fieldTypeName = fieldTypeName;
	}

	private Property property;
	public Property getProperty() { return property; }
	public void setProperty(Property property) { this.property = property; }
	
	public boolean isSimpleType()
	{
		return getFieldType() instanceof XSSimpleType;
	}
	
	private List<XSFacet> facets;
	public List<XSFacet> getFacets()
	{
		if ( facets == null )
			setFacets(new ArrayList<>());
		return facets;
	}
	public void setFacets(List<XSFacet> facets)
	{
		this.facets = facets;
	}
	
	public boolean hasProperties()
	{
		return (getProperty() != null) || !getFacets().isEmpty();
	}
	
	/**
	 * Construct with a {@link CPropertyInfo}.
	 * 
	 * @param pi XJC model of a property to be generated.
	 */
	public FieldInfo(CPropertyInfo pi)
	{
		setFieldName(pi.getName(false));
		setFieldDisplayName(pi.getName(true));
	}
}
