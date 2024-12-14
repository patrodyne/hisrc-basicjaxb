
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
 * <p>Java class for WDialog complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="WDialog">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/swixml}Window">
 *       <sequence>
 *       </sequence>
 *       <attribute name="modal" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="modalityType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="resizable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="undecorated" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WDialog")
@XmlSeeAlso({
    JDialog.class
})
public class WDialog
    extends Window
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "modal")
    protected Boolean modal;
    @XmlAttribute(name = "modalityType")
    protected String modalityType;
    @XmlAttribute(name = "resizable")
    protected Boolean resizable;
    @XmlAttribute(name = "title")
    protected String title;
    @XmlAttribute(name = "undecorated")
    protected Boolean undecorated;

    /**
     * Gets the value of the modal property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isModal() {
        return modal;
    }

    /**
     * Sets the value of the modal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setModal(Boolean value) {
        this.modal = value;
    }

    /**
     * Gets the value of the modalityType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModalityType() {
        return modalityType;
    }

    /**
     * Sets the value of the modalityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModalityType(String value) {
        this.modalityType = value;
    }

    /**
     * Gets the value of the resizable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isResizable() {
        return resizable;
    }

    /**
     * Sets the value of the resizable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setResizable(Boolean value) {
        this.resizable = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the undecorated property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUndecorated() {
        return undecorated;
    }

    /**
     * Sets the value of the undecorated property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUndecorated(Boolean value) {
        this.undecorated = value;
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            boolean theFieldIsSet = (this.modal!= null);
            Boolean theField;
            theField = this.isModal();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "modal", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.modalityType!= null);
            String theField;
            theField = this.getModalityType();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "modalityType", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.resizable!= null);
            Boolean theField;
            theField = this.isResizable();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "resizable", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.title!= null);
            String theField;
            theField = this.getTitle();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "title", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.undecorated!= null);
            Boolean theField;
            theField = this.isUndecorated();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "undecorated", theField);
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
        final WDialog that = ((WDialog) object);
        {
            boolean lhsFieldIsSet = (this.modal!= null);
            boolean rhsFieldIsSet = (that.modal!= null);
            Boolean lhsField;
            lhsField = this.isModal();
            Boolean rhsField;
            rhsField = that.isModal();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "modal", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "modal", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.modalityType!= null);
            boolean rhsFieldIsSet = (that.modalityType!= null);
            String lhsField;
            lhsField = this.getModalityType();
            String rhsField;
            rhsField = that.getModalityType();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "modalityType", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "modalityType", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.resizable!= null);
            boolean rhsFieldIsSet = (that.resizable!= null);
            Boolean lhsField;
            lhsField = this.isResizable();
            Boolean rhsField;
            rhsField = that.isResizable();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "resizable", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "resizable", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.title!= null);
            boolean rhsFieldIsSet = (that.title!= null);
            String lhsField;
            lhsField = this.getTitle();
            String rhsField;
            rhsField = that.getTitle();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "title", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "title", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.undecorated!= null);
            boolean rhsFieldIsSet = (that.undecorated!= null);
            Boolean lhsField;
            lhsField = this.isUndecorated();
            Boolean rhsField;
            rhsField = that.isUndecorated();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "undecorated", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "undecorated", rhsField);
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
            boolean theFieldIsSet = (this.modal!= null);
            Boolean theField;
            theField = this.isModal();
            strategy.appendField(locator, this, "modal", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.modalityType!= null);
            String theField;
            theField = this.getModalityType();
            strategy.appendField(locator, this, "modalityType", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.resizable!= null);
            Boolean theField;
            theField = this.isResizable();
            strategy.appendField(locator, this, "resizable", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.title!= null);
            String theField;
            theField = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.undecorated!= null);
            Boolean theField;
            theField = this.isUndecorated();
            strategy.appendField(locator, this, "undecorated", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
