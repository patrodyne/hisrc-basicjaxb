
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
 * <p>Java class for JOptionPane complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JOptionPane">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/swixml}JComponent">
 *       <sequence>
 *       </sequence>
 *       <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="icon" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="initialSelectionValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="initialValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="inputValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="message" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="messageType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="optionType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="options" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="rootFrame" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionValues" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="wantsInput" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JOptionPane")
public class JOptionPane
    extends JComponent
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "UI")
    protected String ui;
    @XmlAttribute(name = "icon")
    protected String icon;
    @XmlAttribute(name = "initialSelectionValue")
    protected String initialSelectionValue;
    @XmlAttribute(name = "initialValue")
    protected String initialValue;
    @XmlAttribute(name = "inputValue")
    protected String inputValue;
    @XmlAttribute(name = "message")
    protected String message;
    @XmlAttribute(name = "messageType")
    protected String messageType;
    @XmlAttribute(name = "optionType")
    protected String optionType;
    @XmlAttribute(name = "options")
    protected String options;
    @XmlAttribute(name = "rootFrame")
    protected String rootFrame;
    @XmlAttribute(name = "selectionValues")
    protected String selectionValues;
    @XmlAttribute(name = "value")
    protected String value;
    @XmlAttribute(name = "wantsInput")
    protected Boolean wantsInput;

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
     * Gets the value of the icon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets the value of the icon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIcon(String value) {
        this.icon = value;
    }

    /**
     * Gets the value of the initialSelectionValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitialSelectionValue() {
        return initialSelectionValue;
    }

    /**
     * Sets the value of the initialSelectionValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitialSelectionValue(String value) {
        this.initialSelectionValue = value;
    }

    /**
     * Gets the value of the initialValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitialValue() {
        return initialValue;
    }

    /**
     * Sets the value of the initialValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitialValue(String value) {
        this.initialValue = value;
    }

    /**
     * Gets the value of the inputValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputValue() {
        return inputValue;
    }

    /**
     * Sets the value of the inputValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputValue(String value) {
        this.inputValue = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the messageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * Sets the value of the messageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageType(String value) {
        this.messageType = value;
    }

    /**
     * Gets the value of the optionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptionType() {
        return optionType;
    }

    /**
     * Sets the value of the optionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptionType(String value) {
        this.optionType = value;
    }

    /**
     * Gets the value of the options property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptions() {
        return options;
    }

    /**
     * Sets the value of the options property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptions(String value) {
        this.options = value;
    }

    /**
     * Gets the value of the rootFrame property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRootFrame() {
        return rootFrame;
    }

    /**
     * Sets the value of the rootFrame property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRootFrame(String value) {
        this.rootFrame = value;
    }

    /**
     * Gets the value of the selectionValues property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectionValues() {
        return selectionValues;
    }

    /**
     * Sets the value of the selectionValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectionValues(String value) {
        this.selectionValues = value;
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

    /**
     * Gets the value of the wantsInput property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWantsInput() {
        return wantsInput;
    }

    /**
     * Sets the value of the wantsInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWantsInput(Boolean value) {
        this.wantsInput = value;
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
            boolean theFieldIsSet = (this.icon!= null);
            String theField;
            theField = this.getIcon();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "icon", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.initialSelectionValue!= null);
            String theField;
            theField = this.getInitialSelectionValue();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "initialSelectionValue", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.initialValue!= null);
            String theField;
            theField = this.getInitialValue();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "initialValue", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.inputValue!= null);
            String theField;
            theField = this.getInputValue();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "inputValue", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.message!= null);
            String theField;
            theField = this.getMessage();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "message", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.messageType!= null);
            String theField;
            theField = this.getMessageType();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "messageType", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.optionType!= null);
            String theField;
            theField = this.getOptionType();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "optionType", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.options!= null);
            String theField;
            theField = this.getOptions();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "options", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rootFrame!= null);
            String theField;
            theField = this.getRootFrame();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "rootFrame", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionValues!= null);
            String theField;
            theField = this.getSelectionValues();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectionValues", theField);
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
            boolean theFieldIsSet = (this.wantsInput!= null);
            Boolean theField;
            theField = this.isWantsInput();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "wantsInput", theField);
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
        final JOptionPane that = ((JOptionPane) object);
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
            boolean lhsFieldIsSet = (this.initialSelectionValue!= null);
            boolean rhsFieldIsSet = (that.initialSelectionValue!= null);
            String lhsField;
            lhsField = this.getInitialSelectionValue();
            String rhsField;
            rhsField = that.getInitialSelectionValue();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "initialSelectionValue", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "initialSelectionValue", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.initialValue!= null);
            boolean rhsFieldIsSet = (that.initialValue!= null);
            String lhsField;
            lhsField = this.getInitialValue();
            String rhsField;
            rhsField = that.getInitialValue();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "initialValue", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "initialValue", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.inputValue!= null);
            boolean rhsFieldIsSet = (that.inputValue!= null);
            String lhsField;
            lhsField = this.getInputValue();
            String rhsField;
            rhsField = that.getInputValue();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "inputValue", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "inputValue", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.message!= null);
            boolean rhsFieldIsSet = (that.message!= null);
            String lhsField;
            lhsField = this.getMessage();
            String rhsField;
            rhsField = that.getMessage();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "message", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "message", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.messageType!= null);
            boolean rhsFieldIsSet = (that.messageType!= null);
            String lhsField;
            lhsField = this.getMessageType();
            String rhsField;
            rhsField = that.getMessageType();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "messageType", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "messageType", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.optionType!= null);
            boolean rhsFieldIsSet = (that.optionType!= null);
            String lhsField;
            lhsField = this.getOptionType();
            String rhsField;
            rhsField = that.getOptionType();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "optionType", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "optionType", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.options!= null);
            boolean rhsFieldIsSet = (that.options!= null);
            String lhsField;
            lhsField = this.getOptions();
            String rhsField;
            rhsField = that.getOptions();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "options", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "options", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.rootFrame!= null);
            boolean rhsFieldIsSet = (that.rootFrame!= null);
            String lhsField;
            lhsField = this.getRootFrame();
            String rhsField;
            rhsField = that.getRootFrame();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "rootFrame", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "rootFrame", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selectionValues!= null);
            boolean rhsFieldIsSet = (that.selectionValues!= null);
            String lhsField;
            lhsField = this.getSelectionValues();
            String rhsField;
            rhsField = that.getSelectionValues();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectionValues", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectionValues", rhsField);
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
            boolean lhsFieldIsSet = (this.wantsInput!= null);
            boolean rhsFieldIsSet = (that.wantsInput!= null);
            Boolean lhsField;
            lhsField = this.isWantsInput();
            Boolean rhsField;
            rhsField = that.isWantsInput();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "wantsInput", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "wantsInput", rhsField);
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
            boolean theFieldIsSet = (this.icon!= null);
            String theField;
            theField = this.getIcon();
            strategy.appendField(locator, this, "icon", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.initialSelectionValue!= null);
            String theField;
            theField = this.getInitialSelectionValue();
            strategy.appendField(locator, this, "initialSelectionValue", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.initialValue!= null);
            String theField;
            theField = this.getInitialValue();
            strategy.appendField(locator, this, "initialValue", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.inputValue!= null);
            String theField;
            theField = this.getInputValue();
            strategy.appendField(locator, this, "inputValue", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.message!= null);
            String theField;
            theField = this.getMessage();
            strategy.appendField(locator, this, "message", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.messageType!= null);
            String theField;
            theField = this.getMessageType();
            strategy.appendField(locator, this, "messageType", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.optionType!= null);
            String theField;
            theField = this.getOptionType();
            strategy.appendField(locator, this, "optionType", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.options!= null);
            String theField;
            theField = this.getOptions();
            strategy.appendField(locator, this, "options", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rootFrame!= null);
            String theField;
            theField = this.getRootFrame();
            strategy.appendField(locator, this, "rootFrame", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionValues!= null);
            String theField;
            theField = this.getSelectionValues();
            strategy.appendField(locator, this, "selectionValues", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.value!= null);
            String theField;
            theField = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.wantsInput!= null);
            Boolean theField;
            theField = this.isWantsInput();
            strategy.appendField(locator, this, "wantsInput", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
