
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
 * <p>Java class for JTextPane complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JTextPane">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/swixml}JEditorPane">
 *       <sequence>
 *       </sequence>
 *       <attribute name="logicalStyle" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="styledDocument" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JTextPane")
public class JTextPane
    extends JEditorPane
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "logicalStyle")
    protected String logicalStyle;
    @XmlAttribute(name = "styledDocument")
    protected String styledDocument;

    /**
     * Gets the value of the logicalStyle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogicalStyle() {
        return logicalStyle;
    }

    /**
     * Sets the value of the logicalStyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogicalStyle(String value) {
        this.logicalStyle = value;
    }

    /**
     * Gets the value of the styledDocument property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStyledDocument() {
        return styledDocument;
    }

    /**
     * Sets the value of the styledDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStyledDocument(String value) {
        this.styledDocument = value;
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            boolean theFieldIsSet = (this.logicalStyle!= null);
            String theField;
            theField = this.getLogicalStyle();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "logicalStyle", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.styledDocument!= null);
            String theField;
            theField = this.getStyledDocument();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "styledDocument", theField);
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
        final JTextPane that = ((JTextPane) object);
        {
            boolean lhsFieldIsSet = (this.logicalStyle!= null);
            boolean rhsFieldIsSet = (that.logicalStyle!= null);
            String lhsField;
            lhsField = this.getLogicalStyle();
            String rhsField;
            rhsField = that.getLogicalStyle();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "logicalStyle", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "logicalStyle", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.styledDocument!= null);
            boolean rhsFieldIsSet = (that.styledDocument!= null);
            String lhsField;
            lhsField = this.getStyledDocument();
            String rhsField;
            rhsField = that.getStyledDocument();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "styledDocument", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "styledDocument", rhsField);
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
            boolean theFieldIsSet = (this.logicalStyle!= null);
            String theField;
            theField = this.getLogicalStyle();
            strategy.appendField(locator, this, "logicalStyle", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.styledDocument!= null);
            String theField;
            theField = this.getStyledDocument();
            strategy.appendField(locator, this, "styledDocument", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
