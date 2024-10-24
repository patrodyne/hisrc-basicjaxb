
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
 * <p>Java class for JMenu complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JMenu">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}JMenuItem">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="delay" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="popupMenuVisible" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JMenu")
public class JMenu
	extends JMenuItem
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "delay")
	protected String delay;
	@XmlAttribute(name = "popupMenuVisible")
	protected Boolean popupMenuVisible;

	/**
	 * Gets the value of the delay property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getDelay() {
		return delay;
	}

	/**
	 * Sets the value of the delay property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setDelay(String value) {
		this.delay = value;
	}

	/**
	 * Gets the value of the popupMenuVisible property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isPopupMenuVisible() {
		return popupMenuVisible;
	}

	/**
	 * Sets the value of the popupMenuVisible property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setPopupMenuVisible(Boolean value) {
		this.popupMenuVisible = value;
	}

	@Override
	public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
		int currentHashCode = super.hashCode(locator, strategy);
		{
			boolean theFieldIsSet = (this.delay!= null);
			String theField;
			theField = this.getDelay();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "delay", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.popupMenuVisible!= null);
			Boolean theField;
			theField = this.isPopupMenuVisible();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "popupMenuVisible", theField);
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
		final JMenu that = ((JMenu) object);
		{
			boolean lhsFieldIsSet = (this.delay!= null);
			boolean rhsFieldIsSet = (that.delay!= null);
			String lhsField;
			lhsField = this.getDelay();
			String rhsField;
			rhsField = that.getDelay();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "delay", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "delay", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.popupMenuVisible!= null);
			boolean rhsFieldIsSet = (that.popupMenuVisible!= null);
			Boolean lhsField;
			lhsField = this.isPopupMenuVisible();
			Boolean rhsField;
			rhsField = that.isPopupMenuVisible();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "popupMenuVisible", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "popupMenuVisible", rhsField);
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
			boolean theFieldIsSet = (this.delay!= null);
			String theField;
			theField = this.getDelay();
			strategy.appendField(locator, this, "delay", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.popupMenuVisible!= null);
			Boolean theField;
			theField = this.isPopupMenuVisible();
			strategy.appendField(locator, this, "popupMenuVisible", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
