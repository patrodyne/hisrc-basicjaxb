
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
 * <p>Java class for JProgressBar complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JProgressBar">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/swixml}JComponent">
 *       <sequence>
 *       </sequence>
 *       <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="borderPainted" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="indeterminate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="maximum" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="minimum" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="model" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="orientation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="string" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="stringPainted" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JProgressBar")
public class JProgressBar
    extends JComponent
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "UI")
    protected String ui;
    @XmlAttribute(name = "borderPainted")
    protected Boolean borderPainted;
    @XmlAttribute(name = "indeterminate")
    protected Boolean indeterminate;
    @XmlAttribute(name = "maximum")
    protected String maximum;
    @XmlAttribute(name = "minimum")
    protected String minimum;
    @XmlAttribute(name = "model")
    protected String model;
    @XmlAttribute(name = "orientation")
    protected String orientation;
    @XmlAttribute(name = "string")
    protected String string;
    @XmlAttribute(name = "stringPainted")
    protected Boolean stringPainted;
    @XmlAttribute(name = "value")
    protected String value;

    /**
     * Gets the value of the ui property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUI() {
        return ui;
    }

    /**
     * Sets the value of the ui property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUI(String value) {
        this.ui = value;
    }

    /**
     * Gets the value of the borderPainted property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBorderPainted() {
        return borderPainted;
    }

    /**
     * Sets the value of the borderPainted property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBorderPainted(Boolean value) {
        this.borderPainted = value;
    }

    /**
     * Gets the value of the indeterminate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIndeterminate() {
        return indeterminate;
    }

    /**
     * Sets the value of the indeterminate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIndeterminate(Boolean value) {
        this.indeterminate = value;
    }

    /**
     * Gets the value of the maximum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaximum() {
        return maximum;
    }

    /**
     * Sets the value of the maximum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaximum(String value) {
        this.maximum = value;
    }

    /**
     * Gets the value of the minimum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinimum() {
        return minimum;
    }

    /**
     * Sets the value of the minimum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinimum(String value) {
        this.minimum = value;
    }

    /**
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * Gets the value of the orientation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * Sets the value of the orientation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrientation(String value) {
        this.orientation = value;
    }

    /**
     * Gets the value of the string property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getString() {
        return string;
    }

    /**
     * Sets the value of the string property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setString(String value) {
        this.string = value;
    }

    /**
     * Gets the value of the stringPainted property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isStringPainted() {
        return stringPainted;
    }

    /**
     * Sets the value of the stringPainted property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStringPainted(Boolean value) {
        this.stringPainted = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
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
            boolean theFieldIsSet = (this.indeterminate!= null);
            Boolean theField;
            theField = this.isIndeterminate();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "indeterminate", theField);
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
            boolean theFieldIsSet = (this.string!= null);
            String theField;
            theField = this.getString();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "string", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.stringPainted!= null);
            Boolean theField;
            theField = this.isStringPainted();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "stringPainted", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.value!= null);
            String theField;
            theField = this.getValue();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "value", theField);
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
        final JProgressBar that = ((JProgressBar) object);
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
            boolean lhsFieldIsSet = (this.indeterminate!= null);
            boolean rhsFieldIsSet = (that.indeterminate!= null);
            Boolean lhsField;
            lhsField = this.isIndeterminate();
            Boolean rhsField;
            rhsField = that.isIndeterminate();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "indeterminate", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "indeterminate", rhsField);
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
            boolean lhsFieldIsSet = (this.string!= null);
            boolean rhsFieldIsSet = (that.string!= null);
            String lhsField;
            lhsField = this.getString();
            String rhsField;
            rhsField = that.getString();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "string", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "string", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.stringPainted!= null);
            boolean rhsFieldIsSet = (that.stringPainted!= null);
            Boolean lhsField;
            lhsField = this.isStringPainted();
            Boolean rhsField;
            rhsField = that.isStringPainted();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "stringPainted", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "stringPainted", rhsField);
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
            boolean theFieldIsSet = (this.indeterminate!= null);
            Boolean theField;
            theField = this.isIndeterminate();
            strategy.appendField(locator, this, "indeterminate", buffer, theField, theFieldIsSet);
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
            boolean theFieldIsSet = (this.string!= null);
            String theField;
            theField = this.getString();
            strategy.appendField(locator, this, "string", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.stringPainted!= null);
            Boolean theField;
            theField = this.isStringPainted();
            strategy.appendField(locator, this, "stringPainted", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.value!= null);
            String theField;
            theField = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
