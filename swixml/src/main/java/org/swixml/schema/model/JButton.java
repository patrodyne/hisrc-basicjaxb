
package org.swixml.schema.model;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.basicjaxb.lang.EqualsStrategy;
import org.jvnet.basicjaxb.lang.HashCodeStrategy;
import org.jvnet.basicjaxb.lang.ToStringStrategy;
import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.jvnet.basicjaxb.locator.util.LocatorUtils;


/**
 * <p>Java class for JButton complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JButton">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}AbstractButton">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="defaultCapable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JButton")
public class JButton
	extends AbstractButton
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "defaultCapable")
	protected Boolean defaultCapable;

	/**
	 * Gets the value of the defaultCapable property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isDefaultCapable() {
		return defaultCapable;
	}

	/**
	 * Sets the value of the defaultCapable property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setDefaultCapable(Boolean value) {
		this.defaultCapable = value;
	}

	@Override
	public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
		int currentHashCode = super.hashCode(locator, strategy);
		{
			boolean theFieldIsSet = (this.defaultCapable!= null);
			Boolean theField;
			theField = this.isDefaultCapable();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "defaultCapable", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		return currentHashCode;
	}

	@Override
	public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
		if ((object == null)||(this.getClass()!= object.getClass())) {
			return false;
		}
		if (this == object) {
			return true;
		}
		if (!super.equals(thisLocator, thatLocator, object, strategy)) {
			return false;
		}
		final JButton that = ((JButton) object);
		{
			boolean lhsFieldIsSet = (this.defaultCapable!= null);
			boolean rhsFieldIsSet = (that.defaultCapable!= null);
			Boolean lhsField;
			lhsField = this.isDefaultCapable();
			Boolean rhsField;
			rhsField = that.isDefaultCapable();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "defaultCapable", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "defaultCapable", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
		super.appendFields(locator, buffer, strategy);
		{
			boolean theFieldIsSet = (this.defaultCapable!= null);
			Boolean theField;
			theField = this.isDefaultCapable();
			strategy.appendField(locator, this, "defaultCapable", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
