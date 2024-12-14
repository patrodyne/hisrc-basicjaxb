
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
 * <p>Java class for JCheckBoxMenuItem complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JCheckBoxMenuItem">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/swixml}JMenuItem">
 *       <sequence>
 *       </sequence>
 *       <attribute name="state" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JCheckBoxMenuItem")
public class JCheckBoxMenuItem
    extends JMenuItem
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "state")
    protected Boolean state;

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setState(Boolean value) {
        this.state = value;
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            boolean theFieldIsSet = (this.state!= null);
            Boolean theField;
            theField = this.isState();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "state", theField);
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
        final JCheckBoxMenuItem that = ((JCheckBoxMenuItem) object);
        {
            boolean lhsFieldIsSet = (this.state!= null);
            boolean rhsFieldIsSet = (that.state!= null);
            Boolean lhsField;
            lhsField = this.isState();
            Boolean rhsField;
            rhsField = that.isState();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "state", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "state", rhsField);
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
            boolean theFieldIsSet = (this.state!= null);
            Boolean theField;
            theField = this.isState();
            strategy.appendField(locator, this, "state", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
