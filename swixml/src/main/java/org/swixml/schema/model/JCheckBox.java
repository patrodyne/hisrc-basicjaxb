
package org.swixml.schema.model;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.basicjaxb.lang.EqualsStrategy;
import org.jvnet.basicjaxb.lang.HashCodeStrategy;
import org.jvnet.basicjaxb.lang.ToStringStrategy;
import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.jvnet.basicjaxb.locator.util.LocatorUtils;


/**
 * <p>Java class for JCheckBox complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JCheckBox">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}JToggleButton">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="borderPaintedFlat" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JCheckBox")
@XmlSeeAlso({
	JCheckBoxBind.class
})
public class JCheckBox
	extends JToggleButton
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "borderPaintedFlat")
	protected Boolean borderPaintedFlat;

	/**
	 * Gets the value of the borderPaintedFlat property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isBorderPaintedFlat() {
		return borderPaintedFlat;
	}

	/**
	 * Sets the value of the borderPaintedFlat property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setBorderPaintedFlat(Boolean value) {
		this.borderPaintedFlat = value;
	}

	@Override
	public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
		int currentHashCode = super.hashCode(locator, strategy);
		{
			boolean theFieldIsSet = (this.borderPaintedFlat!= null);
			Boolean theField;
			theField = this.isBorderPaintedFlat();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "borderPaintedFlat", theField);
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
		final JCheckBox that = ((JCheckBox) object);
		{
			boolean lhsFieldIsSet = (this.borderPaintedFlat!= null);
			boolean rhsFieldIsSet = (that.borderPaintedFlat!= null);
			Boolean lhsField;
			lhsField = this.isBorderPaintedFlat();
			Boolean rhsField;
			rhsField = that.isBorderPaintedFlat();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "borderPaintedFlat", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "borderPaintedFlat", rhsField);
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
			boolean theFieldIsSet = (this.borderPaintedFlat!= null);
			Boolean theField;
			theField = this.isBorderPaintedFlat();
			strategy.appendField(locator, this, "borderPaintedFlat", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
