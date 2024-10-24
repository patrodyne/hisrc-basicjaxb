
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
 * <p>Java class for JSplitPane complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JSplitPane">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}JComponent">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="bottomComponent" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="continuousLayout" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="dividerLocation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="dividerSize" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="lastDividerLocation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="leftComponent" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="oneTouchExpandable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="orientation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="resizeWeight" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="rightComponent" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="topComponent" type="{http://www.w3.org/2001/XMLSchema}string" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JSplitPane")
@XmlSeeAlso({
	XSplitPane.class
})
public class JSplitPane
	extends JComponent
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "UI")
	protected String ui;
	@XmlAttribute(name = "bottomComponent")
	protected String bottomComponent;
	@XmlAttribute(name = "continuousLayout")
	protected Boolean continuousLayout;
	@XmlAttribute(name = "dividerLocation")
	protected String dividerLocation;
	@XmlAttribute(name = "dividerSize")
	protected String dividerSize;
	@XmlAttribute(name = "lastDividerLocation")
	protected String lastDividerLocation;
	@XmlAttribute(name = "leftComponent")
	protected String leftComponent;
	@XmlAttribute(name = "oneTouchExpandable")
	protected Boolean oneTouchExpandable;
	@XmlAttribute(name = "orientation")
	protected String orientation;
	@XmlAttribute(name = "resizeWeight")
	protected String resizeWeight;
	@XmlAttribute(name = "rightComponent")
	protected String rightComponent;
	@XmlAttribute(name = "topComponent")
	protected String topComponent;

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
	 * Gets the value of the bottomComponent property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getBottomComponent() {
		return bottomComponent;
	}

	/**
	 * Sets the value of the bottomComponent property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setBottomComponent(String value) {
		this.bottomComponent = value;
	}

	/**
	 * Gets the value of the continuousLayout property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isContinuousLayout() {
		return continuousLayout;
	}

	/**
	 * Sets the value of the continuousLayout property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setContinuousLayout(Boolean value) {
		this.continuousLayout = value;
	}

	/**
	 * Gets the value of the dividerLocation property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getDividerLocation() {
		return dividerLocation;
	}

	/**
	 * Sets the value of the dividerLocation property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setDividerLocation(String value) {
		this.dividerLocation = value;
	}

	/**
	 * Gets the value of the dividerSize property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getDividerSize() {
		return dividerSize;
	}

	/**
	 * Sets the value of the dividerSize property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setDividerSize(String value) {
		this.dividerSize = value;
	}

	/**
	 * Gets the value of the lastDividerLocation property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getLastDividerLocation() {
		return lastDividerLocation;
	}

	/**
	 * Sets the value of the lastDividerLocation property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setLastDividerLocation(String value) {
		this.lastDividerLocation = value;
	}

	/**
	 * Gets the value of the leftComponent property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getLeftComponent() {
		return leftComponent;
	}

	/**
	 * Sets the value of the leftComponent property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setLeftComponent(String value) {
		this.leftComponent = value;
	}

	/**
	 * Gets the value of the oneTouchExpandable property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isOneTouchExpandable() {
		return oneTouchExpandable;
	}

	/**
	 * Sets the value of the oneTouchExpandable property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setOneTouchExpandable(Boolean value) {
		this.oneTouchExpandable = value;
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
	 * Gets the value of the resizeWeight property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getResizeWeight() {
		return resizeWeight;
	}

	/**
	 * Sets the value of the resizeWeight property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setResizeWeight(String value) {
		this.resizeWeight = value;
	}

	/**
	 * Gets the value of the rightComponent property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getRightComponent() {
		return rightComponent;
	}

	/**
	 * Sets the value of the rightComponent property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setRightComponent(String value) {
		this.rightComponent = value;
	}

	/**
	 * Gets the value of the topComponent property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getTopComponent() {
		return topComponent;
	}

	/**
	 * Sets the value of the topComponent property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setTopComponent(String value) {
		this.topComponent = value;
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
			boolean theFieldIsSet = (this.bottomComponent!= null);
			String theField;
			theField = this.getBottomComponent();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "bottomComponent", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.continuousLayout!= null);
			Boolean theField;
			theField = this.isContinuousLayout();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "continuousLayout", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.dividerLocation!= null);
			String theField;
			theField = this.getDividerLocation();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "dividerLocation", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.dividerSize!= null);
			String theField;
			theField = this.getDividerSize();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "dividerSize", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.lastDividerLocation!= null);
			String theField;
			theField = this.getLastDividerLocation();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "lastDividerLocation", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.leftComponent!= null);
			String theField;
			theField = this.getLeftComponent();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "leftComponent", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.oneTouchExpandable!= null);
			Boolean theField;
			theField = this.isOneTouchExpandable();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "oneTouchExpandable", theField);
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
			boolean theFieldIsSet = (this.resizeWeight!= null);
			String theField;
			theField = this.getResizeWeight();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "resizeWeight", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.rightComponent!= null);
			String theField;
			theField = this.getRightComponent();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "rightComponent", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.topComponent!= null);
			String theField;
			theField = this.getTopComponent();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "topComponent", theField);
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
		final JSplitPane that = ((JSplitPane) object);
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
			boolean lhsFieldIsSet = (this.bottomComponent!= null);
			boolean rhsFieldIsSet = (that.bottomComponent!= null);
			String lhsField;
			lhsField = this.getBottomComponent();
			String rhsField;
			rhsField = that.getBottomComponent();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "bottomComponent", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "bottomComponent", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.continuousLayout!= null);
			boolean rhsFieldIsSet = (that.continuousLayout!= null);
			Boolean lhsField;
			lhsField = this.isContinuousLayout();
			Boolean rhsField;
			rhsField = that.isContinuousLayout();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "continuousLayout", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "continuousLayout", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.dividerLocation!= null);
			boolean rhsFieldIsSet = (that.dividerLocation!= null);
			String lhsField;
			lhsField = this.getDividerLocation();
			String rhsField;
			rhsField = that.getDividerLocation();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "dividerLocation", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "dividerLocation", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.dividerSize!= null);
			boolean rhsFieldIsSet = (that.dividerSize!= null);
			String lhsField;
			lhsField = this.getDividerSize();
			String rhsField;
			rhsField = that.getDividerSize();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "dividerSize", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "dividerSize", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.lastDividerLocation!= null);
			boolean rhsFieldIsSet = (that.lastDividerLocation!= null);
			String lhsField;
			lhsField = this.getLastDividerLocation();
			String rhsField;
			rhsField = that.getLastDividerLocation();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "lastDividerLocation", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "lastDividerLocation", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.leftComponent!= null);
			boolean rhsFieldIsSet = (that.leftComponent!= null);
			String lhsField;
			lhsField = this.getLeftComponent();
			String rhsField;
			rhsField = that.getLeftComponent();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "leftComponent", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "leftComponent", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.oneTouchExpandable!= null);
			boolean rhsFieldIsSet = (that.oneTouchExpandable!= null);
			Boolean lhsField;
			lhsField = this.isOneTouchExpandable();
			Boolean rhsField;
			rhsField = that.isOneTouchExpandable();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "oneTouchExpandable", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "oneTouchExpandable", rhsField);
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
			boolean lhsFieldIsSet = (this.resizeWeight!= null);
			boolean rhsFieldIsSet = (that.resizeWeight!= null);
			String lhsField;
			lhsField = this.getResizeWeight();
			String rhsField;
			rhsField = that.getResizeWeight();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "resizeWeight", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "resizeWeight", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.rightComponent!= null);
			boolean rhsFieldIsSet = (that.rightComponent!= null);
			String lhsField;
			lhsField = this.getRightComponent();
			String rhsField;
			rhsField = that.getRightComponent();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "rightComponent", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "rightComponent", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.topComponent!= null);
			boolean rhsFieldIsSet = (that.topComponent!= null);
			String lhsField;
			lhsField = this.getTopComponent();
			String rhsField;
			rhsField = that.getTopComponent();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "topComponent", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "topComponent", rhsField);
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
			boolean theFieldIsSet = (this.bottomComponent!= null);
			String theField;
			theField = this.getBottomComponent();
			strategy.appendField(locator, this, "bottomComponent", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.continuousLayout!= null);
			Boolean theField;
			theField = this.isContinuousLayout();
			strategy.appendField(locator, this, "continuousLayout", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.dividerLocation!= null);
			String theField;
			theField = this.getDividerLocation();
			strategy.appendField(locator, this, "dividerLocation", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.dividerSize!= null);
			String theField;
			theField = this.getDividerSize();
			strategy.appendField(locator, this, "dividerSize", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.lastDividerLocation!= null);
			String theField;
			theField = this.getLastDividerLocation();
			strategy.appendField(locator, this, "lastDividerLocation", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.leftComponent!= null);
			String theField;
			theField = this.getLeftComponent();
			strategy.appendField(locator, this, "leftComponent", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.oneTouchExpandable!= null);
			Boolean theField;
			theField = this.isOneTouchExpandable();
			strategy.appendField(locator, this, "oneTouchExpandable", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.orientation!= null);
			String theField;
			theField = this.getOrientation();
			strategy.appendField(locator, this, "orientation", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.resizeWeight!= null);
			String theField;
			theField = this.getResizeWeight();
			strategy.appendField(locator, this, "resizeWeight", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.rightComponent!= null);
			String theField;
			theField = this.getRightComponent();
			strategy.appendField(locator, this, "rightComponent", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.topComponent!= null);
			String theField;
			theField = this.getTopComponent();
			strategy.appendField(locator, this, "topComponent", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
