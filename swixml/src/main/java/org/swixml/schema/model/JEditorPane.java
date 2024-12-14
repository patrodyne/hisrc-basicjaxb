
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
 * <p>Java class for JEditorPane complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JEditorPane">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/swixml}JTextComponent">
 *       <sequence>
 *       </sequence>
 *       <attribute name="contentType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="editorKit" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="page" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JEditorPane")
@XmlSeeAlso({
    JTextPane.class
})
public class JEditorPane
    extends JTextComponent
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "contentType")
    protected String contentType;
    @XmlAttribute(name = "editorKit")
    protected String editorKit;
    @XmlAttribute(name = "page")
    protected String page;

    /**
     * Gets the value of the contentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the value of the contentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentType(String value) {
        this.contentType = value;
    }

    /**
     * Gets the value of the editorKit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEditorKit() {
        return editorKit;
    }

    /**
     * Sets the value of the editorKit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEditorKit(String value) {
        this.editorKit = value;
    }

    /**
     * Gets the value of the page property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets the value of the page property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPage(String value) {
        this.page = value;
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            boolean theFieldIsSet = (this.contentType!= null);
            String theField;
            theField = this.getContentType();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "contentType", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.editorKit!= null);
            String theField;
            theField = this.getEditorKit();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "editorKit", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.page!= null);
            String theField;
            theField = this.getPage();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "page", theField);
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
        final JEditorPane that = ((JEditorPane) object);
        {
            boolean lhsFieldIsSet = (this.contentType!= null);
            boolean rhsFieldIsSet = (that.contentType!= null);
            String lhsField;
            lhsField = this.getContentType();
            String rhsField;
            rhsField = that.getContentType();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "contentType", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "contentType", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.editorKit!= null);
            boolean rhsFieldIsSet = (that.editorKit!= null);
            String lhsField;
            lhsField = this.getEditorKit();
            String rhsField;
            rhsField = that.getEditorKit();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "editorKit", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "editorKit", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.page!= null);
            boolean rhsFieldIsSet = (that.page!= null);
            String lhsField;
            lhsField = this.getPage();
            String rhsField;
            rhsField = that.getPage();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "page", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "page", rhsField);
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
            boolean theFieldIsSet = (this.contentType!= null);
            String theField;
            theField = this.getContentType();
            strategy.appendField(locator, this, "contentType", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.editorKit!= null);
            String theField;
            theField = this.getEditorKit();
            strategy.appendField(locator, this, "editorKit", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.page!= null);
            String theField;
            theField = this.getPage();
            strategy.appendField(locator, this, "page", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
