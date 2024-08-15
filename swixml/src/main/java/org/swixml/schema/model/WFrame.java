
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
 * <p>Java class for WFrame complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="WFrame">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/Swixml}Window">
 *       <sequence>
 *       </sequence>
 *       <attribute name="extendedState" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="maximizedBounds" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="menuBar" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="resizable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "WFrame")
@XmlSeeAlso({
    JFrame.class,
    Frame.class
})
public class WFrame
    extends Window
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "extendedState")
    protected String extendedState;
    @XmlAttribute(name = "maximizedBounds")
    protected String maximizedBounds;
    @XmlAttribute(name = "menuBar")
    protected String menuBar;
    @XmlAttribute(name = "resizable")
    protected Boolean resizable;
    @XmlAttribute(name = "state")
    protected String state;
    @XmlAttribute(name = "title")
    protected String title;
    @XmlAttribute(name = "undecorated")
    protected Boolean undecorated;

    /**
     * Gets the value of the extendedState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendedState() {
        return extendedState;
    }

    /**
     * Sets the value of the extendedState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendedState(String value) {
        this.extendedState = value;
    }

    /**
     * Gets the value of the maximizedBounds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaximizedBounds() {
        return maximizedBounds;
    }

    /**
     * Sets the value of the maximizedBounds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaximizedBounds(String value) {
        this.maximizedBounds = value;
    }

    /**
     * Gets the value of the menuBar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMenuBar() {
        return menuBar;
    }

    /**
     * Sets the value of the menuBar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMenuBar(String value) {
        this.menuBar = value;
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
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
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
            boolean theFieldIsSet = (this.extendedState!= null);
            String theField;
            theField = this.getExtendedState();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "extendedState", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.maximizedBounds!= null);
            String theField;
            theField = this.getMaximizedBounds();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "maximizedBounds", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.menuBar!= null);
            String theField;
            theField = this.getMenuBar();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "menuBar", theField);
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
            boolean theFieldIsSet = (this.state!= null);
            String theField;
            theField = this.getState();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "state", theField);
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
        final WFrame that = ((WFrame) object);
        {
            boolean lhsFieldIsSet = (this.extendedState!= null);
            boolean rhsFieldIsSet = (that.extendedState!= null);
            String lhsField;
            lhsField = this.getExtendedState();
            String rhsField;
            rhsField = that.getExtendedState();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "extendedState", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "extendedState", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.maximizedBounds!= null);
            boolean rhsFieldIsSet = (that.maximizedBounds!= null);
            String lhsField;
            lhsField = this.getMaximizedBounds();
            String rhsField;
            rhsField = that.getMaximizedBounds();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "maximizedBounds", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "maximizedBounds", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.menuBar!= null);
            boolean rhsFieldIsSet = (that.menuBar!= null);
            String lhsField;
            lhsField = this.getMenuBar();
            String rhsField;
            rhsField = that.getMenuBar();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "menuBar", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "menuBar", rhsField);
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
            boolean lhsFieldIsSet = (this.state!= null);
            boolean rhsFieldIsSet = (that.state!= null);
            String lhsField;
            lhsField = this.getState();
            String rhsField;
            rhsField = that.getState();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "state", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "state", rhsField);
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
            boolean theFieldIsSet = (this.extendedState!= null);
            String theField;
            theField = this.getExtendedState();
            strategy.appendField(locator, this, "extendedState", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.maximizedBounds!= null);
            String theField;
            theField = this.getMaximizedBounds();
            strategy.appendField(locator, this, "maximizedBounds", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.menuBar!= null);
            String theField;
            theField = this.getMenuBar();
            strategy.appendField(locator, this, "menuBar", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.resizable!= null);
            Boolean theField;
            theField = this.isResizable();
            strategy.appendField(locator, this, "resizable", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.state!= null);
            String theField;
            theField = this.getState();
            strategy.appendField(locator, this, "state", buffer, theField, theFieldIsSet);
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
