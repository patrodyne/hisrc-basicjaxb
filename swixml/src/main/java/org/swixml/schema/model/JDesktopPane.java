
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
 * <p>Java class for JDesktopPane complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JDesktopPane">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/Swixml}JLayeredPane">
 *       <sequence>
 *       </sequence>
 *       <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="desktopManager" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="dragMode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectedFrame" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JDesktopPane")
public class JDesktopPane
    extends JLayeredPane
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "UI")
    protected String ui;
    @XmlAttribute(name = "desktopManager")
    protected String desktopManager;
    @XmlAttribute(name = "dragMode")
    protected String dragMode;
    @XmlAttribute(name = "selectedFrame")
    protected String selectedFrame;

    /**
     * Gets the value of the ui property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUI() {
        return ui;
    }

    /**
     * Sets the value of the ui property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUI(String value) {
        this.ui = value;
    }

    /**
     * Gets the value of the desktopManager property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesktopManager() {
        return desktopManager;
    }

    /**
     * Sets the value of the desktopManager property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesktopManager(String value) {
        this.desktopManager = value;
    }

    /**
     * Gets the value of the dragMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDragMode() {
        return dragMode;
    }

    /**
     * Sets the value of the dragMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDragMode(String value) {
        this.dragMode = value;
    }

    /**
     * Gets the value of the selectedFrame property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedFrame() {
        return selectedFrame;
    }

    /**
     * Sets the value of the selectedFrame property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedFrame(String value) {
        this.selectedFrame = value;
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            boolean theFieldIsSet = (this.ui!= null);
            String theField;
            theField = this.getUI();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "ui", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.desktopManager!= null);
            String theField;
            theField = this.getDesktopManager();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "desktopManager", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.dragMode!= null);
            String theField;
            theField = this.getDragMode();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "dragMode", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectedFrame!= null);
            String theField;
            theField = this.getSelectedFrame();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectedFrame", theField);
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
        final JDesktopPane that = ((JDesktopPane) object);
        {
            boolean lhsFieldIsSet = (this.ui!= null);
            boolean rhsFieldIsSet = (that.ui!= null);
            String lhsField;
            lhsField = this.getUI();
            String rhsField;
            rhsField = that.getUI();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "ui", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "ui", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.desktopManager!= null);
            boolean rhsFieldIsSet = (that.desktopManager!= null);
            String lhsField;
            lhsField = this.getDesktopManager();
            String rhsField;
            rhsField = that.getDesktopManager();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "desktopManager", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "desktopManager", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.dragMode!= null);
            boolean rhsFieldIsSet = (that.dragMode!= null);
            String lhsField;
            lhsField = this.getDragMode();
            String rhsField;
            rhsField = that.getDragMode();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "dragMode", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "dragMode", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selectedFrame!= null);
            boolean rhsFieldIsSet = (that.selectedFrame!= null);
            String lhsField;
            lhsField = this.getSelectedFrame();
            String rhsField;
            rhsField = that.getSelectedFrame();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectedFrame", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectedFrame", rhsField);
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
            boolean theFieldIsSet = (this.ui!= null);
            String theField;
            theField = this.getUI();
            strategy.appendField(locator, this, "ui", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.desktopManager!= null);
            String theField;
            theField = this.getDesktopManager();
            strategy.appendField(locator, this, "desktopManager", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.dragMode!= null);
            String theField;
            theField = this.getDragMode();
            strategy.appendField(locator, this, "dragMode", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectedFrame!= null);
            String theField;
            theField = this.getSelectedFrame();
            strategy.appendField(locator, this, "selectedFrame", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
