package org.jvnet.basicjaxb.plugin.beaninfo;

import java.util.ArrayList;
import java.util.List;

import org.jvnet.basicjaxb.plugin.beaninfo.model.Property;

import com.sun.xml.xsom.XSFacet;

public class FieldInfo
{
	private String fieldName;
	public String getFieldName() { return fieldName; }
	public void setFieldName(String fieldName) { this.fieldName = fieldName; }
	
	private Integer fieldIndex;
	public Integer getFieldIndex() { return fieldIndex; }
	public void setFieldIndex(Integer fieldIndex) { this.fieldIndex = fieldIndex; }

	private Property property;
	public Property getProperty() { return property; }
	public void setProperty(Property property) { this.property = property; }
	
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
	
	public FieldInfo(String fieldName)
	{
		setFieldName(fieldName);
	}
}
