
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
 * <p>Java class for BoxFactoryHGapBox complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="BoxFactoryHGapBox">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/Swixml}Box">
 *       <sequence>
 *       </sequence>
 *       <attribute name="gap" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BoxFactoryHGapBox")
public class BoxFactoryHGapBox
    extends Box
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "gap")
    protected String gap;

    /**
     * Gets the value of the gap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGap() {
        return gap;
    }

    /**
     * Sets the value of the gap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGap(String value) {
        this.gap = value;
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            boolean theFieldIsSet = (this.gap!= null);
            String theField;
            theField = this.getGap();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "gap", theField);
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
        final BoxFactoryHGapBox that = ((BoxFactoryHGapBox) object);
        {
            boolean lhsFieldIsSet = (this.gap!= null);
            boolean rhsFieldIsSet = (that.gap!= null);
            String lhsField;
            lhsField = this.getGap();
            String rhsField;
            rhsField = that.getGap();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "gap", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "gap", rhsField);
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
            boolean theFieldIsSet = (this.gap!= null);
            String theField;
            theField = this.getGap();
            strategy.appendField(locator, this, "gap", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
