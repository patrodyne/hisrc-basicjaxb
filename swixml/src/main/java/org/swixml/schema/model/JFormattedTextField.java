
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
 * <p>Java class for JFormattedTextField complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JFormattedTextField">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/swixml}JTextField">
 *       <sequence>
 *       </sequence>
 *       <attribute name="focusLostBehavior" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="formatterFactory" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JFormattedTextField")
public class JFormattedTextField
    extends JTextField
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "focusLostBehavior")
    protected String focusLostBehavior;
    @XmlAttribute(name = "formatterFactory")
    protected String formatterFactory;
    @XmlAttribute(name = "value")
    protected String value;

    /**
     * Gets the value of the focusLostBehavior property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFocusLostBehavior() {
        return focusLostBehavior;
    }

    /**
     * Sets the value of the focusLostBehavior property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFocusLostBehavior(String value) {
        this.focusLostBehavior = value;
    }

    /**
     * Gets the value of the formatterFactory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatterFactory() {
        return formatterFactory;
    }

    /**
     * Sets the value of the formatterFactory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatterFactory(String value) {
        this.formatterFactory = value;
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
            boolean theFieldIsSet = (this.focusLostBehavior!= null);
            String theField;
            theField = this.getFocusLostBehavior();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "focusLostBehavior", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.formatterFactory!= null);
            String theField;
            theField = this.getFormatterFactory();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "formatterFactory", theField);
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
        final JFormattedTextField that = ((JFormattedTextField) object);
        {
            boolean lhsFieldIsSet = (this.focusLostBehavior!= null);
            boolean rhsFieldIsSet = (that.focusLostBehavior!= null);
            String lhsField;
            lhsField = this.getFocusLostBehavior();
            String rhsField;
            rhsField = that.getFocusLostBehavior();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "focusLostBehavior", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "focusLostBehavior", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.formatterFactory!= null);
            boolean rhsFieldIsSet = (that.formatterFactory!= null);
            String lhsField;
            lhsField = this.getFormatterFactory();
            String rhsField;
            rhsField = that.getFormatterFactory();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "formatterFactory", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "formatterFactory", rhsField);
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
            boolean theFieldIsSet = (this.focusLostBehavior!= null);
            String theField;
            theField = this.getFocusLostBehavior();
            strategy.appendField(locator, this, "focusLostBehavior", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.formatterFactory!= null);
            String theField;
            theField = this.getFormatterFactory();
            strategy.appendField(locator, this, "formatterFactory", buffer, theField, theFieldIsSet);
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
