
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
 * <p>Java class for Window complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Window">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}Container">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="alwaysOnTop" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="autoRequestFocus" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="focusableWindowState" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="iconImage" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="iconImages" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="locationByPlatform" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="locationRelativeTo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="modalExclusionType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="opacity" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="shape" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Window")
@XmlSeeAlso({
	WFrame.class,
	WDialog.class
})
public class Window
	extends Container
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "alwaysOnTop")
	protected Boolean alwaysOnTop;
	@XmlAttribute(name = "autoRequestFocus")
	protected Boolean autoRequestFocus;
	@XmlAttribute(name = "focusableWindowState")
	protected Boolean focusableWindowState;
	@XmlAttribute(name = "iconImage")
	protected String iconImage;
	@XmlAttribute(name = "iconImages")
	protected String iconImages;
	@XmlAttribute(name = "locationByPlatform")
	protected Boolean locationByPlatform;
	@XmlAttribute(name = "locationRelativeTo")
	protected String locationRelativeTo;
	@XmlAttribute(name = "modalExclusionType")
	protected String modalExclusionType;
	@XmlAttribute(name = "opacity")
	protected String opacity;
	@XmlAttribute(name = "shape")
	protected String shape;
	@XmlAttribute(name = "type")
	protected String type;

	/**
	 * Gets the value of the alwaysOnTop property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isAlwaysOnTop() {
		return alwaysOnTop;
	}

	/**
	 * Sets the value of the alwaysOnTop property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setAlwaysOnTop(Boolean value) {
		this.alwaysOnTop = value;
	}

	/**
	 * Gets the value of the autoRequestFocus property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isAutoRequestFocus() {
		return autoRequestFocus;
	}

	/**
	 * Sets the value of the autoRequestFocus property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setAutoRequestFocus(Boolean value) {
		this.autoRequestFocus = value;
	}

	/**
	 * Gets the value of the focusableWindowState property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isFocusableWindowState() {
		return focusableWindowState;
	}

	/**
	 * Sets the value of the focusableWindowState property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setFocusableWindowState(Boolean value) {
		this.focusableWindowState = value;
	}

	/**
	 * Gets the value of the iconImage property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getIconImage() {
		return iconImage;
	}

	/**
	 * Sets the value of the iconImage property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setIconImage(String value) {
		this.iconImage = value;
	}

	/**
	 * Gets the value of the iconImages property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getIconImages() {
		return iconImages;
	}

	/**
	 * Sets the value of the iconImages property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setIconImages(String value) {
		this.iconImages = value;
	}

	/**
	 * Gets the value of the locationByPlatform property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isLocationByPlatform() {
		return locationByPlatform;
	}

	/**
	 * Sets the value of the locationByPlatform property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setLocationByPlatform(Boolean value) {
		this.locationByPlatform = value;
	}

	/**
	 * Gets the value of the locationRelativeTo property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getLocationRelativeTo() {
		return locationRelativeTo;
	}

	/**
	 * Sets the value of the locationRelativeTo property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setLocationRelativeTo(String value) {
		this.locationRelativeTo = value;
	}

	/**
	 * Gets the value of the modalExclusionType property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getModalExclusionType() {
		return modalExclusionType;
	}

	/**
	 * Sets the value of the modalExclusionType property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setModalExclusionType(String value) {
		this.modalExclusionType = value;
	}

	/**
	 * Gets the value of the opacity property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getOpacity() {
		return opacity;
	}

	/**
	 * Sets the value of the opacity property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setOpacity(String value) {
		this.opacity = value;
	}

	/**
	 * Gets the value of the shape property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getShape() {
		return shape;
	}

	/**
	 * Sets the value of the shape property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setShape(String value) {
		this.shape = value;
	}

	/**
	 * Gets the value of the type property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setType(String value) {
		this.type = value;
	}

	@Override
	public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
		int currentHashCode = super.hashCode(locator, strategy);
		{
			boolean theFieldIsSet = (this.alwaysOnTop!= null);
			Boolean theField;
			theField = this.isAlwaysOnTop();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "alwaysOnTop", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.autoRequestFocus!= null);
			Boolean theField;
			theField = this.isAutoRequestFocus();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "autoRequestFocus", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.focusableWindowState!= null);
			Boolean theField;
			theField = this.isFocusableWindowState();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "focusableWindowState", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.iconImage!= null);
			String theField;
			theField = this.getIconImage();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "iconImage", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.iconImages!= null);
			String theField;
			theField = this.getIconImages();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "iconImages", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.locationByPlatform!= null);
			Boolean theField;
			theField = this.isLocationByPlatform();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "locationByPlatform", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.locationRelativeTo!= null);
			String theField;
			theField = this.getLocationRelativeTo();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "locationRelativeTo", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.modalExclusionType!= null);
			String theField;
			theField = this.getModalExclusionType();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "modalExclusionType", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.opacity!= null);
			String theField;
			theField = this.getOpacity();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "opacity", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.shape!= null);
			String theField;
			theField = this.getShape();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "shape", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.type!= null);
			String theField;
			theField = this.getType();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "type", theField);
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
		final Window that = ((Window) object);
		{
			boolean lhsFieldIsSet = (this.alwaysOnTop!= null);
			boolean rhsFieldIsSet = (that.alwaysOnTop!= null);
			Boolean lhsField;
			lhsField = this.isAlwaysOnTop();
			Boolean rhsField;
			rhsField = that.isAlwaysOnTop();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "alwaysOnTop", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "alwaysOnTop", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.autoRequestFocus!= null);
			boolean rhsFieldIsSet = (that.autoRequestFocus!= null);
			Boolean lhsField;
			lhsField = this.isAutoRequestFocus();
			Boolean rhsField;
			rhsField = that.isAutoRequestFocus();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "autoRequestFocus", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "autoRequestFocus", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.focusableWindowState!= null);
			boolean rhsFieldIsSet = (that.focusableWindowState!= null);
			Boolean lhsField;
			lhsField = this.isFocusableWindowState();
			Boolean rhsField;
			rhsField = that.isFocusableWindowState();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "focusableWindowState", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "focusableWindowState", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.iconImage!= null);
			boolean rhsFieldIsSet = (that.iconImage!= null);
			String lhsField;
			lhsField = this.getIconImage();
			String rhsField;
			rhsField = that.getIconImage();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "iconImage", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "iconImage", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.iconImages!= null);
			boolean rhsFieldIsSet = (that.iconImages!= null);
			String lhsField;
			lhsField = this.getIconImages();
			String rhsField;
			rhsField = that.getIconImages();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "iconImages", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "iconImages", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.locationByPlatform!= null);
			boolean rhsFieldIsSet = (that.locationByPlatform!= null);
			Boolean lhsField;
			lhsField = this.isLocationByPlatform();
			Boolean rhsField;
			rhsField = that.isLocationByPlatform();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "locationByPlatform", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "locationByPlatform", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.locationRelativeTo!= null);
			boolean rhsFieldIsSet = (that.locationRelativeTo!= null);
			String lhsField;
			lhsField = this.getLocationRelativeTo();
			String rhsField;
			rhsField = that.getLocationRelativeTo();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "locationRelativeTo", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "locationRelativeTo", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.modalExclusionType!= null);
			boolean rhsFieldIsSet = (that.modalExclusionType!= null);
			String lhsField;
			lhsField = this.getModalExclusionType();
			String rhsField;
			rhsField = that.getModalExclusionType();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "modalExclusionType", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "modalExclusionType", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.opacity!= null);
			boolean rhsFieldIsSet = (that.opacity!= null);
			String lhsField;
			lhsField = this.getOpacity();
			String rhsField;
			rhsField = that.getOpacity();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "opacity", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "opacity", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.shape!= null);
			boolean rhsFieldIsSet = (that.shape!= null);
			String lhsField;
			lhsField = this.getShape();
			String rhsField;
			rhsField = that.getShape();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "shape", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "shape", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.type!= null);
			boolean rhsFieldIsSet = (that.type!= null);
			String lhsField;
			lhsField = this.getType();
			String rhsField;
			rhsField = that.getType();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "type", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "type", rhsField);
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
			boolean theFieldIsSet = (this.alwaysOnTop!= null);
			Boolean theField;
			theField = this.isAlwaysOnTop();
			strategy.appendField(locator, this, "alwaysOnTop", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.autoRequestFocus!= null);
			Boolean theField;
			theField = this.isAutoRequestFocus();
			strategy.appendField(locator, this, "autoRequestFocus", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.focusableWindowState!= null);
			Boolean theField;
			theField = this.isFocusableWindowState();
			strategy.appendField(locator, this, "focusableWindowState", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.iconImage!= null);
			String theField;
			theField = this.getIconImage();
			strategy.appendField(locator, this, "iconImage", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.iconImages!= null);
			String theField;
			theField = this.getIconImages();
			strategy.appendField(locator, this, "iconImages", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.locationByPlatform!= null);
			Boolean theField;
			theField = this.isLocationByPlatform();
			strategy.appendField(locator, this, "locationByPlatform", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.locationRelativeTo!= null);
			String theField;
			theField = this.getLocationRelativeTo();
			strategy.appendField(locator, this, "locationRelativeTo", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.modalExclusionType!= null);
			String theField;
			theField = this.getModalExclusionType();
			strategy.appendField(locator, this, "modalExclusionType", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.opacity!= null);
			String theField;
			theField = this.getOpacity();
			strategy.appendField(locator, this, "opacity", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.shape!= null);
			String theField;
			theField = this.getShape();
			strategy.appendField(locator, this, "shape", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.type!= null);
			String theField;
			theField = this.getType();
			strategy.appendField(locator, this, "type", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
