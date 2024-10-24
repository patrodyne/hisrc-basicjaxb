
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
 * <p>Java class for JSlider complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JSlider">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}JComponent">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="extent" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="inverted" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="labelTable" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="majorTickSpacing" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="maximum" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="minimum" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="minorTickSpacing" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="model" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="orientation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="paintLabels" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="paintTicks" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="paintTrack" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="snapToTicks" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="valueIsAdjusting" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JSlider")
@XmlSeeAlso({
	JSliderBind.class
})
public class JSlider
	extends JComponent
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "UI")
	protected String ui;
	@XmlAttribute(name = "extent")
	protected String extent;
	@XmlAttribute(name = "inverted")
	protected Boolean inverted;
	@XmlAttribute(name = "labelTable")
	protected String labelTable;
	@XmlAttribute(name = "majorTickSpacing")
	protected String majorTickSpacing;
	@XmlAttribute(name = "maximum")
	protected String maximum;
	@XmlAttribute(name = "minimum")
	protected String minimum;
	@XmlAttribute(name = "minorTickSpacing")
	protected String minorTickSpacing;
	@XmlAttribute(name = "model")
	protected String model;
	@XmlAttribute(name = "orientation")
	protected String orientation;
	@XmlAttribute(name = "paintLabels")
	protected Boolean paintLabels;
	@XmlAttribute(name = "paintTicks")
	protected Boolean paintTicks;
	@XmlAttribute(name = "paintTrack")
	protected Boolean paintTrack;
	@XmlAttribute(name = "snapToTicks")
	protected Boolean snapToTicks;
	@XmlAttribute(name = "value")
	protected String value;
	@XmlAttribute(name = "valueIsAdjusting")
	protected Boolean valueIsAdjusting;

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
	 * Gets the value of the extent property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getExtent() {
		return extent;
	}

	/**
	 * Sets the value of the extent property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setExtent(String value) {
		this.extent = value;
	}

	/**
	 * Gets the value of the inverted property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isInverted() {
		return inverted;
	}

	/**
	 * Sets the value of the inverted property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setInverted(Boolean value) {
		this.inverted = value;
	}

	/**
	 * Gets the value of the labelTable property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getLabelTable() {
		return labelTable;
	}

	/**
	 * Sets the value of the labelTable property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setLabelTable(String value) {
		this.labelTable = value;
	}

	/**
	 * Gets the value of the majorTickSpacing property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getMajorTickSpacing() {
		return majorTickSpacing;
	}

	/**
	 * Sets the value of the majorTickSpacing property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setMajorTickSpacing(String value) {
		this.majorTickSpacing = value;
	}

	/**
	 * Gets the value of the maximum property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getMaximum() {
		return maximum;
	}

	/**
	 * Sets the value of the maximum property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setMaximum(String value) {
		this.maximum = value;
	}

	/**
	 * Gets the value of the minimum property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getMinimum() {
		return minimum;
	}

	/**
	 * Sets the value of the minimum property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setMinimum(String value) {
		this.minimum = value;
	}

	/**
	 * Gets the value of the minorTickSpacing property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getMinorTickSpacing() {
		return minorTickSpacing;
	}

	/**
	 * Sets the value of the minorTickSpacing property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setMinorTickSpacing(String value) {
		this.minorTickSpacing = value;
	}

	/**
	 * Gets the value of the model property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Sets the value of the model property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setModel(String value) {
		this.model = value;
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
	 * Gets the value of the paintLabels property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isPaintLabels() {
		return paintLabels;
	}

	/**
	 * Sets the value of the paintLabels property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setPaintLabels(Boolean value) {
		this.paintLabels = value;
	}

	/**
	 * Gets the value of the paintTicks property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isPaintTicks() {
		return paintTicks;
	}

	/**
	 * Sets the value of the paintTicks property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setPaintTicks(Boolean value) {
		this.paintTicks = value;
	}

	/**
	 * Gets the value of the paintTrack property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isPaintTrack() {
		return paintTrack;
	}

	/**
	 * Sets the value of the paintTrack property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setPaintTrack(Boolean value) {
		this.paintTrack = value;
	}

	/**
	 * Gets the value of the snapToTicks property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isSnapToTicks() {
		return snapToTicks;
	}

	/**
	 * Sets the value of the snapToTicks property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setSnapToTicks(Boolean value) {
		this.snapToTicks = value;
	}

	/**
	 * Gets the value of the value property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value of the value property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the value of the valueIsAdjusting property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isValueIsAdjusting() {
		return valueIsAdjusting;
	}

	/**
	 * Sets the value of the valueIsAdjusting property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setValueIsAdjusting(Boolean value) {
		this.valueIsAdjusting = value;
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
			boolean theFieldIsSet = (this.extent!= null);
			String theField;
			theField = this.getExtent();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "extent", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.inverted!= null);
			Boolean theField;
			theField = this.isInverted();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "inverted", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.labelTable!= null);
			String theField;
			theField = this.getLabelTable();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "labelTable", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.majorTickSpacing!= null);
			String theField;
			theField = this.getMajorTickSpacing();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "majorTickSpacing", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.maximum!= null);
			String theField;
			theField = this.getMaximum();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "maximum", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.minimum!= null);
			String theField;
			theField = this.getMinimum();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "minimum", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.minorTickSpacing!= null);
			String theField;
			theField = this.getMinorTickSpacing();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "minorTickSpacing", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.model!= null);
			String theField;
			theField = this.getModel();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "model", theField);
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
			boolean theFieldIsSet = (this.paintLabels!= null);
			Boolean theField;
			theField = this.isPaintLabels();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "paintLabels", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.paintTicks!= null);
			Boolean theField;
			theField = this.isPaintTicks();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "paintTicks", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.paintTrack!= null);
			Boolean theField;
			theField = this.isPaintTrack();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "paintTrack", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.snapToTicks!= null);
			Boolean theField;
			theField = this.isSnapToTicks();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "snapToTicks", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.value!= null);
			String theField;
			theField = this.getValue();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "value", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.valueIsAdjusting!= null);
			Boolean theField;
			theField = this.isValueIsAdjusting();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "valueIsAdjusting", theField);
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
		final JSlider that = ((JSlider) object);
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
			boolean lhsFieldIsSet = (this.extent!= null);
			boolean rhsFieldIsSet = (that.extent!= null);
			String lhsField;
			lhsField = this.getExtent();
			String rhsField;
			rhsField = that.getExtent();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "extent", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "extent", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.inverted!= null);
			boolean rhsFieldIsSet = (that.inverted!= null);
			Boolean lhsField;
			lhsField = this.isInverted();
			Boolean rhsField;
			rhsField = that.isInverted();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "inverted", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "inverted", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.labelTable!= null);
			boolean rhsFieldIsSet = (that.labelTable!= null);
			String lhsField;
			lhsField = this.getLabelTable();
			String rhsField;
			rhsField = that.getLabelTable();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "labelTable", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "labelTable", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.majorTickSpacing!= null);
			boolean rhsFieldIsSet = (that.majorTickSpacing!= null);
			String lhsField;
			lhsField = this.getMajorTickSpacing();
			String rhsField;
			rhsField = that.getMajorTickSpacing();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "majorTickSpacing", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "majorTickSpacing", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.maximum!= null);
			boolean rhsFieldIsSet = (that.maximum!= null);
			String lhsField;
			lhsField = this.getMaximum();
			String rhsField;
			rhsField = that.getMaximum();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "maximum", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "maximum", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.minimum!= null);
			boolean rhsFieldIsSet = (that.minimum!= null);
			String lhsField;
			lhsField = this.getMinimum();
			String rhsField;
			rhsField = that.getMinimum();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "minimum", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "minimum", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.minorTickSpacing!= null);
			boolean rhsFieldIsSet = (that.minorTickSpacing!= null);
			String lhsField;
			lhsField = this.getMinorTickSpacing();
			String rhsField;
			rhsField = that.getMinorTickSpacing();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "minorTickSpacing", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "minorTickSpacing", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.model!= null);
			boolean rhsFieldIsSet = (that.model!= null);
			String lhsField;
			lhsField = this.getModel();
			String rhsField;
			rhsField = that.getModel();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "model", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "model", rhsField);
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
			boolean lhsFieldIsSet = (this.paintLabels!= null);
			boolean rhsFieldIsSet = (that.paintLabels!= null);
			Boolean lhsField;
			lhsField = this.isPaintLabels();
			Boolean rhsField;
			rhsField = that.isPaintLabels();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "paintLabels", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "paintLabels", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.paintTicks!= null);
			boolean rhsFieldIsSet = (that.paintTicks!= null);
			Boolean lhsField;
			lhsField = this.isPaintTicks();
			Boolean rhsField;
			rhsField = that.isPaintTicks();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "paintTicks", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "paintTicks", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.paintTrack!= null);
			boolean rhsFieldIsSet = (that.paintTrack!= null);
			Boolean lhsField;
			lhsField = this.isPaintTrack();
			Boolean rhsField;
			rhsField = that.isPaintTrack();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "paintTrack", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "paintTrack", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.snapToTicks!= null);
			boolean rhsFieldIsSet = (that.snapToTicks!= null);
			Boolean lhsField;
			lhsField = this.isSnapToTicks();
			Boolean rhsField;
			rhsField = that.isSnapToTicks();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "snapToTicks", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "snapToTicks", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.value!= null);
			boolean rhsFieldIsSet = (that.value!= null);
			String lhsField;
			lhsField = this.getValue();
			String rhsField;
			rhsField = that.getValue();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "value", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "value", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.valueIsAdjusting!= null);
			boolean rhsFieldIsSet = (that.valueIsAdjusting!= null);
			Boolean lhsField;
			lhsField = this.isValueIsAdjusting();
			Boolean rhsField;
			rhsField = that.isValueIsAdjusting();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "valueIsAdjusting", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "valueIsAdjusting", rhsField);
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
			boolean theFieldIsSet = (this.extent!= null);
			String theField;
			theField = this.getExtent();
			strategy.appendField(locator, this, "extent", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.inverted!= null);
			Boolean theField;
			theField = this.isInverted();
			strategy.appendField(locator, this, "inverted", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.labelTable!= null);
			String theField;
			theField = this.getLabelTable();
			strategy.appendField(locator, this, "labelTable", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.majorTickSpacing!= null);
			String theField;
			theField = this.getMajorTickSpacing();
			strategy.appendField(locator, this, "majorTickSpacing", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.maximum!= null);
			String theField;
			theField = this.getMaximum();
			strategy.appendField(locator, this, "maximum", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.minimum!= null);
			String theField;
			theField = this.getMinimum();
			strategy.appendField(locator, this, "minimum", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.minorTickSpacing!= null);
			String theField;
			theField = this.getMinorTickSpacing();
			strategy.appendField(locator, this, "minorTickSpacing", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.model!= null);
			String theField;
			theField = this.getModel();
			strategy.appendField(locator, this, "model", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.orientation!= null);
			String theField;
			theField = this.getOrientation();
			strategy.appendField(locator, this, "orientation", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.paintLabels!= null);
			Boolean theField;
			theField = this.isPaintLabels();
			strategy.appendField(locator, this, "paintLabels", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.paintTicks!= null);
			Boolean theField;
			theField = this.isPaintTicks();
			strategy.appendField(locator, this, "paintTicks", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.paintTrack!= null);
			Boolean theField;
			theField = this.isPaintTrack();
			strategy.appendField(locator, this, "paintTrack", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.snapToTicks!= null);
			Boolean theField;
			theField = this.isSnapToTicks();
			strategy.appendField(locator, this, "snapToTicks", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.value!= null);
			String theField;
			theField = this.getValue();
			strategy.appendField(locator, this, "value", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.valueIsAdjusting!= null);
			Boolean theField;
			theField = this.isValueIsAdjusting();
			strategy.appendField(locator, this, "valueIsAdjusting", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
