
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
 * <p>Java class for JPasswordField complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JPasswordField">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/swixml}JTextField">
 *       <sequence>
 *       </sequence>
 *       <attribute name="echoChar" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JPasswordField")
@XmlSeeAlso({
    JPasswordFieldBind.class
})
public class JPasswordField
    extends JTextField
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "echoChar")
    protected String echoChar;

    /**
     * Gets the value of the echoChar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEchoChar() {
        return echoChar;
    }

    /**
     * Sets the value of the echoChar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEchoChar(String value) {
        this.echoChar = value;
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            boolean theFieldIsSet = (this.echoChar!= null);
            String theField;
            theField = this.getEchoChar();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "echoChar", theField);
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
        final JPasswordField that = ((JPasswordField) object);
        {
            boolean lhsFieldIsSet = (this.echoChar!= null);
            boolean rhsFieldIsSet = (that.echoChar!= null);
            String lhsField;
            lhsField = this.getEchoChar();
            String rhsField;
            rhsField = that.getEchoChar();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "echoChar", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "echoChar", rhsField);
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
            boolean theFieldIsSet = (this.echoChar!= null);
            String theField;
            theField = this.getEchoChar();
            strategy.appendField(locator, this, "echoChar", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
