
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
 * <p>Java class for JRadioButtonBind complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JRadioButtonBind">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/Swixml}JRadioButton">
 *       <sequence>
 *       </sequence>
 *       <attribute name="bindWith" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="converter" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JRadioButtonBind")
public class JRadioButtonBind
    extends JRadioButton
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "bindWith")
    protected String bindWith;
    @XmlAttribute(name = "converter")
    protected String converter;

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
            boolean theFieldIsSet = (this.converter!= null);
            String theField;
            theField = this.getConverter();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "converter", theField);
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
        final JRadioButtonBind that = ((JRadioButtonBind) object);
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
            boolean theFieldIsSet = (this.converter!= null);
            String theField;
            theField = this.getConverter();
            strategy.appendField(locator, this, "converter", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}