
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
 * <p>Java class for JSpinnerBindDate complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JSpinnerBindDate">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}JSpinnerBind">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="dateFormat" type="{http://www.w3.org/2001/XMLSchema}string" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JSpinnerBindDate")
public class JSpinnerBindDate
	extends JSpinnerBind
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "dateFormat")
	protected String dateFormat;

	/**
	 * Gets the value of the dateFormat property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getDateFormat() {
		return dateFormat;
	}

	/**
	 * Sets the value of the dateFormat property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setDateFormat(String value) {
		this.dateFormat = value;
	}

	@Override
	public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
		int currentHashCode = super.hashCode(locator, strategy);
		{
			boolean theFieldIsSet = (this.dateFormat!= null);
			String theField;
			theField = this.getDateFormat();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "dateFormat", theField);
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
		final JSpinnerBindDate that = ((JSpinnerBindDate) object);
		{
			boolean lhsFieldIsSet = (this.dateFormat!= null);
			boolean rhsFieldIsSet = (that.dateFormat!= null);
			String lhsField;
			lhsField = this.getDateFormat();
			String rhsField;
			rhsField = that.getDateFormat();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "dateFormat", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "dateFormat", rhsField);
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
			boolean theFieldIsSet = (this.dateFormat!= null);
			String theField;
			theField = this.getDateFormat();
			strategy.appendField(locator, this, "dateFormat", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
