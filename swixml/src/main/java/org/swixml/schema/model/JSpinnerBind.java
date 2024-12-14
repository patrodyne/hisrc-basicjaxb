
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
 * <p>Java class for JSpinnerBind complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JSpinnerBind">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/swixml}JSpinner">
 *       <sequence>
 *       </sequence>
 *       <attribute name="bindWith" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="binding" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="bindingGroup" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="columns" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="converter" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="horizontalAlignment" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JSpinnerBind")
@XmlSeeAlso({
    JSpinnerBindDate.class
})
public class JSpinnerBind
    extends JSpinner
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "bindWith")
    protected String bindWith;
    @XmlAttribute(name = "binding")
    protected String binding;
    @XmlAttribute(name = "bindingGroup")
    protected String bindingGroup;
    @XmlAttribute(name = "columns")
    protected String columns;
    @XmlAttribute(name = "converter")
    protected String converter;
    @XmlAttribute(name = "horizontalAlignment")
    protected String horizontalAlignment;

    /**
     * Gets the value of the bindWith property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindWith() {
        return bindWith;
    }

    /**
     * Sets the value of the bindWith property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindWith(String value) {
        this.bindWith = value;
    }

    /**
     * Gets the value of the binding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBinding() {
        return binding;
    }

    /**
     * Sets the value of the binding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBinding(String value) {
        this.binding = value;
    }

    /**
     * Gets the value of the bindingGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindingGroup() {
        return bindingGroup;
    }

    /**
     * Sets the value of the bindingGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindingGroup(String value) {
        this.bindingGroup = value;
    }

    /**
     * Gets the value of the columns property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumns() {
        return columns;
    }

    /**
     * Sets the value of the columns property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumns(String value) {
        this.columns = value;
    }

    /**
     * Gets the value of the converter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConverter() {
        return converter;
    }

    /**
     * Sets the value of the converter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConverter(String value) {
        this.converter = value;
    }

    /**
     * Gets the value of the horizontalAlignment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorizontalAlignment() {
        return horizontalAlignment;
    }

    /**
     * Sets the value of the horizontalAlignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorizontalAlignment(String value) {
        this.horizontalAlignment = value;
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            boolean theFieldIsSet = (this.bindWith!= null);
            String theField;
            theField = this.getBindWith();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "bindWith", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.binding!= null);
            String theField;
            theField = this.getBinding();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "binding", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.bindingGroup!= null);
            String theField;
            theField = this.getBindingGroup();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "bindingGroup", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.columns!= null);
            String theField;
            theField = this.getColumns();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "columns", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.converter!= null);
            String theField;
            theField = this.getConverter();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "converter", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.horizontalAlignment!= null);
            String theField;
            theField = this.getHorizontalAlignment();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "horizontalAlignment", theField);
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
        final JSpinnerBind that = ((JSpinnerBind) object);
        {
            boolean lhsFieldIsSet = (this.bindWith!= null);
            boolean rhsFieldIsSet = (that.bindWith!= null);
            String lhsField;
            lhsField = this.getBindWith();
            String rhsField;
            rhsField = that.getBindWith();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "bindWith", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "bindWith", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.binding!= null);
            boolean rhsFieldIsSet = (that.binding!= null);
            String lhsField;
            lhsField = this.getBinding();
            String rhsField;
            rhsField = that.getBinding();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "binding", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "binding", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.bindingGroup!= null);
            boolean rhsFieldIsSet = (that.bindingGroup!= null);
            String lhsField;
            lhsField = this.getBindingGroup();
            String rhsField;
            rhsField = that.getBindingGroup();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "bindingGroup", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "bindingGroup", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.columns!= null);
            boolean rhsFieldIsSet = (that.columns!= null);
            String lhsField;
            lhsField = this.getColumns();
            String rhsField;
            rhsField = that.getColumns();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "columns", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "columns", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.converter!= null);
            boolean rhsFieldIsSet = (that.converter!= null);
            String lhsField;
            lhsField = this.getConverter();
            String rhsField;
            rhsField = that.getConverter();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "converter", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "converter", rhsField);
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
        return true;
    }

    @Override
    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            boolean theFieldIsSet = (this.bindWith!= null);
            String theField;
            theField = this.getBindWith();
            strategy.appendField(locator, this, "bindWith", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.binding!= null);
            String theField;
            theField = this.getBinding();
            strategy.appendField(locator, this, "binding", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.bindingGroup!= null);
            String theField;
            theField = this.getBindingGroup();
            strategy.appendField(locator, this, "bindingGroup", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.columns!= null);
            String theField;
            theField = this.getColumns();
            strategy.appendField(locator, this, "columns", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.converter!= null);
            String theField;
            theField = this.getConverter();
            strategy.appendField(locator, this, "converter", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.horizontalAlignment!= null);
            String theField;
            theField = this.getHorizontalAlignment();
            strategy.appendField(locator, this, "horizontalAlignment", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
