package org.jvnet.basicjaxb.plugin.inheritance.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * This JAXB class is used to direct the InheritancePlugin to
 * add interfaces to any schema-derived class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <element name="implements">
 *	 <complexType>
 *	   <simpleContent>
 *		 <extension base="<http://www.w3.org/2001/XMLSchema>NMTOKEN">
 *		 </extension>
 *	   </simpleContent>
 *	 </complexType>
 * </element>
 * }</pre>
 * 
 * <pre>
 * &lt;inh:implements&gt;org.example.my_base.MyInterface1&lt;/inh:implements&gt;
 * &lt;inh:implements&gt;org.example.my_base.MyInterface2&lt;/inh:implements&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "value" })
@XmlRootElement(name = "implements")
public class ImplementsInterface
{
	@XmlValue
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NMTOKEN")
	private String value;

	/**
	 * Gets the value of the value property.
	 * 
	 * @return Possible object is {@link String }
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Sets the value of the value property.
	 * 
	 * @param value Allowed object is {@link String }
	 *	   
	 */
	public void setValue(String value)
	{
		this.value = value;
	}
}
