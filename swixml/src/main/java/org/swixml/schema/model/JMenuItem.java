
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
 * <p>Java class for JMenuItem complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JMenuItem">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}AbstractButton">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="accelerator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="armed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JMenuItem")
@XmlSeeAlso({
	JCheckBoxMenuItem.class,
	JMenu.class,
	JRadioButtonMenuItem.class
})
public class JMenuItem
	extends AbstractButton
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "accelerator")
	protected String accelerator;
	@XmlAttribute(name = "armed")
	protected Boolean armed;

	/**
	 * Gets the value of the accelerator property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getAccelerator() {
		return accelerator;
	}

	/**
	 * Sets the value of the accelerator property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setAccelerator(String value) {
		this.accelerator = value;
	}

	/**
	 * Gets the value of the armed property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isArmed() {
		return armed;
	}

	/**
	 * Sets the value of the armed property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setArmed(Boolean value) {
		this.armed = value;
	}

	@Override
	public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
		int currentHashCode = super.hashCode(locator, strategy);
		{
			boolean theFieldIsSet = (this.accelerator!= null);
			String theField;
			theField = this.getAccelerator();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "accelerator", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.armed!= null);
			Boolean theField;
			theField = this.isArmed();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "armed", theField);
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
		final JMenuItem that = ((JMenuItem) object);
		{
			boolean lhsFieldIsSet = (this.accelerator!= null);
			boolean rhsFieldIsSet = (that.accelerator!= null);
			String lhsField;
			lhsField = this.getAccelerator();
			String rhsField;
			rhsField = that.getAccelerator();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "accelerator", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "accelerator", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.armed!= null);
			boolean rhsFieldIsSet = (that.armed!= null);
			Boolean lhsField;
			lhsField = this.isArmed();
			Boolean rhsField;
			rhsField = that.isArmed();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "armed", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "armed", rhsField);
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
			boolean theFieldIsSet = (this.accelerator!= null);
			String theField;
			theField = this.getAccelerator();
			strategy.appendField(locator, this, "accelerator", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.armed!= null);
			Boolean theField;
			theField = this.isArmed();
			strategy.appendField(locator, this, "armed", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
