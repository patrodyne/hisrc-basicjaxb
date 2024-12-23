package org.jvnet.basicjaxb.plugin.beaninfo;

import static org.jvnet.basicjaxb.lang.FieldDescriptor.DEFAULT_ALIGNMENT;
import static org.jvnet.basicjaxb.lang.FieldDescriptor.DEFAULT_MIN_WIDTH;
import static org.jvnet.basicjaxb.lang.FieldDescriptor.alignByType;
import static org.jvnet.basicjaxb.lang.FieldDescriptor.widthByType;
import static org.jvnet.basicjaxb.plugin.beaninfo.FieldSource.ATTRIBUTE;
import static org.jvnet.basicjaxb.plugin.beaninfo.FieldSource.PARTICLE;
import static org.jvnet.basicjaxb.plugin.beaninfo.FieldSource.SIMPLE;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.lang.Access;
import org.jvnet.basicjaxb.lang.Alignment;
import org.jvnet.basicjaxb.plugin.beaninfo.model.Property;

import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.xml.xsom.XSAttributeUse;
import com.sun.xml.xsom.XSElementDecl;
import com.sun.xml.xsom.XSFacet;
import com.sun.xml.xsom.XSParticle;
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
	
	private boolean fieldHidden = false;
	public boolean isFieldHidden() { return fieldHidden; }
	public void setFieldHidden(boolean fieldHidden) { this.fieldHidden = fieldHidden; }
	
	private Integer fieldMinWidth;
	public Integer getFieldMinWidth()
	{
		if ( fieldMinWidth == null )
			setFieldMinWidth(DEFAULT_MIN_WIDTH);
		return fieldMinWidth;
	}
	public void setFieldMinWidth(Integer fieldMinWidth)
	{
		this.fieldMinWidth = fieldMinWidth;
	}
	
	private FieldSource fieldSource;
	public FieldSource getFieldSource()
	{
		return fieldSource;
	}
	public void setFieldSource(FieldSource fieldSource)
	{
		this.fieldSource = fieldSource;
	}

	private Access fieldAccess;
	public Access getFieldAccess()
	{
		return fieldAccess;
	}
	public void setFieldAccess(Access fieldAccess)
	{
		this.fieldAccess = fieldAccess;
	}

	private Alignment fieldAlignment;
	public Alignment getFieldAlignment()
	{
		if ( fieldAlignment == null )
			setFieldAlignment(DEFAULT_ALIGNMENT);

		return fieldAlignment;
	}
	public void setFieldAlignment(Alignment fieldAlignment)
	{
		this.fieldAlignment = fieldAlignment;
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
	
	public static FieldSource getSource(CPropertyInfo propertyInfo)
	{
		FieldSource fieldSource = null;
		if ( propertyInfo.getSchemaComponent() instanceof XSAttributeUse)
			fieldSource = ATTRIBUTE;
		else if ( propertyInfo.getSchemaComponent() instanceof XSParticle )
			fieldSource = PARTICLE;
		else if ( propertyInfo.getSchemaComponent() instanceof XSSimpleType )
			fieldSource = SIMPLE;
		return fieldSource;
	}
	
	/**
	 * Get the property type as an attribute use (XSSimpleType)
	 * or element declaration (XSType).
	 * 
	 * @param propertyInfo The {@link CPropertyInfo} to examine.
	 * 
	 * @return The properties type as {@link XSType} or {@link XSSimpleType}
	 */
	public static XSType getType(CPropertyInfo propertyInfo)
	{
		XSType type = null;
		if ( propertyInfo.getSchemaComponent() instanceof XSAttributeUse)
		{
			XSAttributeUse source = (XSAttributeUse) propertyInfo.getSchemaComponent();
			type = source.getDecl().getType();
		}
		else if ( propertyInfo.getSchemaComponent() instanceof XSParticle )
		{
			XSParticle source = (XSParticle) propertyInfo.getSchemaComponent();
			if ( source.getTerm() instanceof XSElementDecl )
			{
				XSElementDecl ed = (XSElementDecl) source.getTerm();
				type = ed.getType();
			}
		}
		else if ( propertyInfo.getSchemaComponent() instanceof XSSimpleType )
			type = (XSSimpleType) propertyInfo.getSchemaComponent();
		return type;
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
		setFieldSource(getSource(pi));
		setFieldType(getType(pi));
		
		if ( getFieldType() != null )
		{
			if ( getFieldType().isSimpleType() )
			{
				QName typeName = getFieldTypeName();
				setFieldMinWidth(widthByType(typeName).getMin());
				setFieldAlignment(alignByType(typeName));
			}
			else
				setFieldHidden(true);
		}
		
		if ( pi.isCollection() )
			setFieldAccess(Access.READ_ONLY);
		else
			setFieldAccess(Access.READ_WRITE);
	}
}
