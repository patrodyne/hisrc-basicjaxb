
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
 * <p>Java class for JLabel complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JLabel">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}JComponent">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="disabledIcon" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="displayedMnemonic" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="displayedMnemonicIndex" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="horizontalAlignment" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="horizontalTextPosition" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="icon" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="iconTextGap" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="labelFor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="text" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="verticalAlignment" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="verticalTextPosition" type="{http://www.w3.org/2001/XMLSchema}string" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JLabel")
@XmlSeeAlso({
	JLabelBind.class
})
public class JLabel
	extends JComponent
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "UI")
	protected String ui;
	@XmlAttribute(name = "disabledIcon")
	protected String disabledIcon;
	@XmlAttribute(name = "displayedMnemonic")
	protected String displayedMnemonic;
	@XmlAttribute(name = "displayedMnemonicIndex")
	protected String displayedMnemonicIndex;
	@XmlAttribute(name = "horizontalAlignment")
	protected String horizontalAlignment;
	@XmlAttribute(name = "horizontalTextPosition")
	protected String horizontalTextPosition;
	@XmlAttribute(name = "icon")
	protected String icon;
	@XmlAttribute(name = "iconTextGap")
	protected String iconTextGap;
	@XmlAttribute(name = "labelFor")
	protected String labelFor;
	@XmlAttribute(name = "text")
	protected String text;
	@XmlAttribute(name = "verticalAlignment")
	protected String verticalAlignment;
	@XmlAttribute(name = "verticalTextPosition")
	protected String verticalTextPosition;

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
	 * Gets the value of the disabledIcon property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getDisabledIcon() {
		return disabledIcon;
	}

	/**
	 * Sets the value of the disabledIcon property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setDisabledIcon(String value) {
		this.disabledIcon = value;
	}

	/**
	 * Gets the value of the displayedMnemonic property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getDisplayedMnemonic() {
		return displayedMnemonic;
	}

	/**
	 * Sets the value of the displayedMnemonic property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setDisplayedMnemonic(String value) {
		this.displayedMnemonic = value;
	}

	/**
	 * Gets the value of the displayedMnemonicIndex property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getDisplayedMnemonicIndex() {
		return displayedMnemonicIndex;
	}

	/**
	 * Sets the value of the displayedMnemonicIndex property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setDisplayedMnemonicIndex(String value) {
		this.displayedMnemonicIndex = value;
	}

	/**
	 * Gets the value of the horizontalAlignment property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getHorizontalAlignment() {
		return horizontalAlignment;
	}

	/**
	 * Sets the value of the horizontalAlignment property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setHorizontalAlignment(String value) {
		this.horizontalAlignment = value;
	}

	/**
	 * Gets the value of the horizontalTextPosition property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getHorizontalTextPosition() {
		return horizontalTextPosition;
	}

	/**
	 * Sets the value of the horizontalTextPosition property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setHorizontalTextPosition(String value) {
		this.horizontalTextPosition = value;
	}

	/**
	 * Gets the value of the icon property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Sets the value of the icon property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setIcon(String value) {
		this.icon = value;
	}

	/**
	 * Gets the value of the iconTextGap property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getIconTextGap() {
		return iconTextGap;
	}

	/**
	 * Sets the value of the iconTextGap property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setIconTextGap(String value) {
		this.iconTextGap = value;
	}

	/**
	 * Gets the value of the labelFor property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getLabelFor() {
		return labelFor;
	}

	/**
	 * Sets the value of the labelFor property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setLabelFor(String value) {
		this.labelFor = value;
	}

	/**
	 * Gets the value of the text property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the value of the text property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setText(String value) {
		this.text = value;
	}

	/**
	 * Gets the value of the verticalAlignment property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getVerticalAlignment() {
		return verticalAlignment;
	}

	/**
	 * Sets the value of the verticalAlignment property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setVerticalAlignment(String value) {
		this.verticalAlignment = value;
	}

	/**
	 * Gets the value of the verticalTextPosition property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getVerticalTextPosition() {
		return verticalTextPosition;
	}

	/**
	 * Sets the value of the verticalTextPosition property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setVerticalTextPosition(String value) {
		this.verticalTextPosition = value;
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
			boolean theFieldIsSet = (this.disabledIcon!= null);
			String theField;
			theField = this.getDisabledIcon();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "disabledIcon", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.displayedMnemonic!= null);
			String theField;
			theField = this.getDisplayedMnemonic();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "displayedMnemonic", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.displayedMnemonicIndex!= null);
			String theField;
			theField = this.getDisplayedMnemonicIndex();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "displayedMnemonicIndex", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.horizontalAlignment!= null);
			String theField;
			theField = this.getHorizontalAlignment();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "horizontalAlignment", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.horizontalTextPosition!= null);
			String theField;
			theField = this.getHorizontalTextPosition();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "horizontalTextPosition", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.icon!= null);
			String theField;
			theField = this.getIcon();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "icon", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.iconTextGap!= null);
			String theField;
			theField = this.getIconTextGap();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "iconTextGap", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.labelFor!= null);
			String theField;
			theField = this.getLabelFor();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "labelFor", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.text!= null);
			String theField;
			theField = this.getText();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "text", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.verticalAlignment!= null);
			String theField;
			theField = this.getVerticalAlignment();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "verticalAlignment", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.verticalTextPosition!= null);
			String theField;
			theField = this.getVerticalTextPosition();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "verticalTextPosition", theField);
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
		final JLabel that = ((JLabel) object);
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
			boolean lhsFieldIsSet = (this.disabledIcon!= null);
			boolean rhsFieldIsSet = (that.disabledIcon!= null);
			String lhsField;
			lhsField = this.getDisabledIcon();
			String rhsField;
			rhsField = that.getDisabledIcon();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "disabledIcon", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "disabledIcon", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.displayedMnemonic!= null);
			boolean rhsFieldIsSet = (that.displayedMnemonic!= null);
			String lhsField;
			lhsField = this.getDisplayedMnemonic();
			String rhsField;
			rhsField = that.getDisplayedMnemonic();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "displayedMnemonic", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "displayedMnemonic", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.displayedMnemonicIndex!= null);
			boolean rhsFieldIsSet = (that.displayedMnemonicIndex!= null);
			String lhsField;
			lhsField = this.getDisplayedMnemonicIndex();
			String rhsField;
			rhsField = that.getDisplayedMnemonicIndex();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "displayedMnemonicIndex", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "displayedMnemonicIndex", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.horizontalAlignment!= null);
			boolean rhsFieldIsSet = (that.horizontalAlignment!= null);
			String lhsField;
			lhsField = this.getHorizontalAlignment();
			String rhsField;
			rhsField = that.getHorizontalAlignment();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "horizontalAlignment", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "horizontalAlignment", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.horizontalTextPosition!= null);
			boolean rhsFieldIsSet = (that.horizontalTextPosition!= null);
			String lhsField;
			lhsField = this.getHorizontalTextPosition();
			String rhsField;
			rhsField = that.getHorizontalTextPosition();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "horizontalTextPosition", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "horizontalTextPosition", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.icon!= null);
			boolean rhsFieldIsSet = (that.icon!= null);
			String lhsField;
			lhsField = this.getIcon();
			String rhsField;
			rhsField = that.getIcon();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "icon", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "icon", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.iconTextGap!= null);
			boolean rhsFieldIsSet = (that.iconTextGap!= null);
			String lhsField;
			lhsField = this.getIconTextGap();
			String rhsField;
			rhsField = that.getIconTextGap();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "iconTextGap", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "iconTextGap", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.labelFor!= null);
			boolean rhsFieldIsSet = (that.labelFor!= null);
			String lhsField;
			lhsField = this.getLabelFor();
			String rhsField;
			rhsField = that.getLabelFor();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "labelFor", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "labelFor", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.text!= null);
			boolean rhsFieldIsSet = (that.text!= null);
			String lhsField;
			lhsField = this.getText();
			String rhsField;
			rhsField = that.getText();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "text", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "text", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.verticalAlignment!= null);
			boolean rhsFieldIsSet = (that.verticalAlignment!= null);
			String lhsField;
			lhsField = this.getVerticalAlignment();
			String rhsField;
			rhsField = that.getVerticalAlignment();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "verticalAlignment", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "verticalAlignment", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.verticalTextPosition!= null);
			boolean rhsFieldIsSet = (that.verticalTextPosition!= null);
			String lhsField;
			lhsField = this.getVerticalTextPosition();
			String rhsField;
			rhsField = that.getVerticalTextPosition();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "verticalTextPosition", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "verticalTextPosition", rhsField);
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
			boolean theFieldIsSet = (this.disabledIcon!= null);
			String theField;
			theField = this.getDisabledIcon();
			strategy.appendField(locator, this, "disabledIcon", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.displayedMnemonic!= null);
			String theField;
			theField = this.getDisplayedMnemonic();
			strategy.appendField(locator, this, "displayedMnemonic", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.displayedMnemonicIndex!= null);
			String theField;
			theField = this.getDisplayedMnemonicIndex();
			strategy.appendField(locator, this, "displayedMnemonicIndex", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.horizontalAlignment!= null);
			String theField;
			theField = this.getHorizontalAlignment();
			strategy.appendField(locator, this, "horizontalAlignment", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.horizontalTextPosition!= null);
			String theField;
			theField = this.getHorizontalTextPosition();
			strategy.appendField(locator, this, "horizontalTextPosition", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.icon!= null);
			String theField;
			theField = this.getIcon();
			strategy.appendField(locator, this, "icon", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.iconTextGap!= null);
			String theField;
			theField = this.getIconTextGap();
			strategy.appendField(locator, this, "iconTextGap", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.labelFor!= null);
			String theField;
			theField = this.getLabelFor();
			strategy.appendField(locator, this, "labelFor", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.text!= null);
			String theField;
			theField = this.getText();
			strategy.appendField(locator, this, "text", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.verticalAlignment!= null);
			String theField;
			theField = this.getVerticalAlignment();
			strategy.appendField(locator, this, "verticalAlignment", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.verticalTextPosition!= null);
			String theField;
			theField = this.getVerticalTextPosition();
			strategy.appendField(locator, this, "verticalTextPosition", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
