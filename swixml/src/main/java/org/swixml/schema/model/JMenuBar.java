
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
 * <p>Java class for JMenuBar complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JMenuBar">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}JComponent">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="borderPainted" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="helpMenu" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="margin" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="selected" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="selectionModel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JMenuBar")
public class JMenuBar
	extends JComponent
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "UI")
	protected String ui;
	@XmlAttribute(name = "borderPainted")
	protected Boolean borderPainted;
	@XmlAttribute(name = "helpMenu")
	protected String helpMenu;
	@XmlAttribute(name = "margin")
	protected String margin;
	@XmlAttribute(name = "selected")
	protected String selected;
	@XmlAttribute(name = "selectionModel")
	protected String selectionModel;

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
	 * Gets the value of the helpMenu property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getHelpMenu() {
		return helpMenu;
	}

	/**
	 * Sets the value of the helpMenu property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setHelpMenu(String value) {
		this.helpMenu = value;
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
	 * Gets the value of the selected property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getSelected() {
		return selected;
	}

	/**
	 * Sets the value of the selected property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setSelected(String value) {
		this.selected = value;
	}

	/**
	 * Gets the value of the selectionModel property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getSelectionModel() {
		return selectionModel;
	}

	/**
	 * Sets the value of the selectionModel property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setSelectionModel(String value) {
		this.selectionModel = value;
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
			boolean theFieldIsSet = (this.helpMenu!= null);
			String theField;
			theField = this.getHelpMenu();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "helpMenu", theField);
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
			boolean theFieldIsSet = (this.selected!= null);
			String theField;
			theField = this.getSelected();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selected", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.selectionModel!= null);
			String theField;
			theField = this.getSelectionModel();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectionModel", theField);
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
		final JMenuBar that = ((JMenuBar) object);
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
			boolean lhsFieldIsSet = (this.helpMenu!= null);
			boolean rhsFieldIsSet = (that.helpMenu!= null);
			String lhsField;
			lhsField = this.getHelpMenu();
			String rhsField;
			rhsField = that.getHelpMenu();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "helpMenu", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "helpMenu", rhsField);
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
			boolean lhsFieldIsSet = (this.selected!= null);
			boolean rhsFieldIsSet = (that.selected!= null);
			String lhsField;
			lhsField = this.getSelected();
			String rhsField;
			rhsField = that.getSelected();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selected", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selected", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.selectionModel!= null);
			boolean rhsFieldIsSet = (that.selectionModel!= null);
			String lhsField;
			lhsField = this.getSelectionModel();
			String rhsField;
			rhsField = that.getSelectionModel();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectionModel", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectionModel", rhsField);
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
			boolean theFieldIsSet = (this.helpMenu!= null);
			String theField;
			theField = this.getHelpMenu();
			strategy.appendField(locator, this, "helpMenu", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.margin!= null);
			String theField;
			theField = this.getMargin();
			strategy.appendField(locator, this, "margin", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.selected!= null);
			String theField;
			theField = this.getSelected();
			strategy.appendField(locator, this, "selected", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.selectionModel!= null);
			String theField;
			theField = this.getSelectionModel();
			strategy.appendField(locator, this, "selectionModel", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
