
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
 * <p>Java class for JToolBar complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JToolBar">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}JComponent">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="borderPainted" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="floatable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="margin" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="orientation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="rollover" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JToolBar")
public class JToolBar
	extends JComponent
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "UI")
	protected String ui;
	@XmlAttribute(name = "borderPainted")
	protected Boolean borderPainted;
	@XmlAttribute(name = "floatable")
	protected Boolean floatable;
	@XmlAttribute(name = "margin")
	protected String margin;
	@XmlAttribute(name = "orientation")
	protected String orientation;
	@XmlAttribute(name = "rollover")
	protected Boolean rollover;

	/**
	 * Gets the value of the ui property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getUI() {
		return ui;
	}

	/**
	 * Sets the value of the ui property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setUI(String value) {
		this.ui = value;
	}

	/**
	 * Gets the value of the borderPainted property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isBorderPainted() {
		return borderPainted;
	}

	/**
	 * Sets the value of the borderPainted property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setBorderPainted(Boolean value) {
		this.borderPainted = value;
	}

	/**
	 * Gets the value of the floatable property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isFloatable() {
		return floatable;
	}

	/**
	 * Sets the value of the floatable property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setFloatable(Boolean value) {
		this.floatable = value;
	}

	/**
	 * Gets the value of the margin property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getMargin() {
		return margin;
	}

	/**
	 * Sets the value of the margin property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setMargin(String value) {
		this.margin = value;
	}

	/**
	 * Gets the value of the orientation property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getOrientation() {
		return orientation;
	}

	/**
	 * Sets the value of the orientation property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setOrientation(String value) {
		this.orientation = value;
	}

	/**
	 * Gets the value of the rollover property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isRollover() {
		return rollover;
	}

	/**
	 * Sets the value of the rollover property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setRollover(Boolean value) {
		this.rollover = value;
	}

	@Override
	public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
		int currentHashCode = super.hashCode(locator, strategy);
		{
			boolean theFieldIsSet = (this.ui!= null);
			String theField;
			theField = this.getUI();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "ui", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.borderPainted!= null);
			Boolean theField;
			theField = this.isBorderPainted();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "borderPainted", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.floatable!= null);
			Boolean theField;
			theField = this.isFloatable();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "floatable", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.margin!= null);
			String theField;
			theField = this.getMargin();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "margin", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.orientation!= null);
			String theField;
			theField = this.getOrientation();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "orientation", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.rollover!= null);
			Boolean theField;
			theField = this.isRollover();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "rollover", theField);
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
		final JToolBar that = ((JToolBar) object);
		{
			boolean lhsFieldIsSet = (this.ui!= null);
			boolean rhsFieldIsSet = (that.ui!= null);
			String lhsField;
			lhsField = this.getUI();
			String rhsField;
			rhsField = that.getUI();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "ui", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "ui", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.borderPainted!= null);
			boolean rhsFieldIsSet = (that.borderPainted!= null);
			Boolean lhsField;
			lhsField = this.isBorderPainted();
			Boolean rhsField;
			rhsField = that.isBorderPainted();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "borderPainted", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "borderPainted", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.floatable!= null);
			boolean rhsFieldIsSet = (that.floatable!= null);
			Boolean lhsField;
			lhsField = this.isFloatable();
			Boolean rhsField;
			rhsField = that.isFloatable();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "floatable", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "floatable", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.margin!= null);
			boolean rhsFieldIsSet = (that.margin!= null);
			String lhsField;
			lhsField = this.getMargin();
			String rhsField;
			rhsField = that.getMargin();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "margin", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "margin", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.orientation!= null);
			boolean rhsFieldIsSet = (that.orientation!= null);
			String lhsField;
			lhsField = this.getOrientation();
			String rhsField;
			rhsField = that.getOrientation();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "orientation", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "orientation", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.rollover!= null);
			boolean rhsFieldIsSet = (that.rollover!= null);
			Boolean lhsField;
			lhsField = this.isRollover();
			Boolean rhsField;
			rhsField = that.isRollover();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "rollover", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "rollover", rhsField);
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
			boolean theFieldIsSet = (this.ui!= null);
			String theField;
			theField = this.getUI();
			strategy.appendField(locator, this, "ui", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.borderPainted!= null);
			Boolean theField;
			theField = this.isBorderPainted();
			strategy.appendField(locator, this, "borderPainted", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.floatable!= null);
			Boolean theField;
			theField = this.isFloatable();
			strategy.appendField(locator, this, "floatable", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.margin!= null);
			String theField;
			theField = this.getMargin();
			strategy.appendField(locator, this, "margin", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.orientation!= null);
			String theField;
			theField = this.getOrientation();
			strategy.appendField(locator, this, "orientation", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.rollover!= null);
			Boolean theField;
			theField = this.isRollover();
			strategy.appendField(locator, this, "rollover", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
