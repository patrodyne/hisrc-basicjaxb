
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
 * <p>Java class for JTabbedPane complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JTabbedPane">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/swixml}JComponent">
 *       <sequence>
 *       </sequence>
 *       <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="model" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectedComponent" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectedIndex" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="tabLayoutPolicy" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="tabPlacement" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JTabbedPane")
@XmlSeeAlso({
    XTabbedPane.class
})
public class JTabbedPane
    extends JComponent
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "UI")
    protected String ui;
    @XmlAttribute(name = "model")
    protected String model;
    @XmlAttribute(name = "selectedComponent")
    protected String selectedComponent;
    @XmlAttribute(name = "selectedIndex")
    protected String selectedIndex;
    @XmlAttribute(name = "tabLayoutPolicy")
    protected String tabLayoutPolicy;
    @XmlAttribute(name = "tabPlacement")
    protected String tabPlacement;

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
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * Gets the value of the selectedComponent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedComponent() {
        return selectedComponent;
    }

    /**
     * Sets the value of the selectedComponent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedComponent(String value) {
        this.selectedComponent = value;
    }

    /**
     * Gets the value of the selectedIndex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * Sets the value of the selectedIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedIndex(String value) {
        this.selectedIndex = value;
    }

    /**
     * Gets the value of the tabLayoutPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTabLayoutPolicy() {
        return tabLayoutPolicy;
    }

    /**
     * Sets the value of the tabLayoutPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTabLayoutPolicy(String value) {
        this.tabLayoutPolicy = value;
    }

    /**
     * Gets the value of the tabPlacement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTabPlacement() {
        return tabPlacement;
    }

    /**
     * Sets the value of the tabPlacement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTabPlacement(String value) {
        this.tabPlacement = value;
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
            boolean theFieldIsSet = (this.model!= null);
            String theField;
            theField = this.getModel();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "model", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectedComponent!= null);
            String theField;
            theField = this.getSelectedComponent();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectedComponent", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectedIndex!= null);
            String theField;
            theField = this.getSelectedIndex();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectedIndex", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.tabLayoutPolicy!= null);
            String theField;
            theField = this.getTabLayoutPolicy();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "tabLayoutPolicy", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.tabPlacement!= null);
            String theField;
            theField = this.getTabPlacement();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "tabPlacement", theField);
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
        final JTabbedPane that = ((JTabbedPane) object);
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
            boolean lhsFieldIsSet = (this.model!= null);
            boolean rhsFieldIsSet = (that.model!= null);
            String lhsField;
            lhsField = this.getModel();
            String rhsField;
            rhsField = that.getModel();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "model", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "model", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selectedComponent!= null);
            boolean rhsFieldIsSet = (that.selectedComponent!= null);
            String lhsField;
            lhsField = this.getSelectedComponent();
            String rhsField;
            rhsField = that.getSelectedComponent();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectedComponent", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectedComponent", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selectedIndex!= null);
            boolean rhsFieldIsSet = (that.selectedIndex!= null);
            String lhsField;
            lhsField = this.getSelectedIndex();
            String rhsField;
            rhsField = that.getSelectedIndex();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectedIndex", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectedIndex", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.tabLayoutPolicy!= null);
            boolean rhsFieldIsSet = (that.tabLayoutPolicy!= null);
            String lhsField;
            lhsField = this.getTabLayoutPolicy();
            String rhsField;
            rhsField = that.getTabLayoutPolicy();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "tabLayoutPolicy", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "tabLayoutPolicy", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.tabPlacement!= null);
            boolean rhsFieldIsSet = (that.tabPlacement!= null);
            String lhsField;
            lhsField = this.getTabPlacement();
            String rhsField;
            rhsField = that.getTabPlacement();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "tabPlacement", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "tabPlacement", rhsField);
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
            boolean theFieldIsSet = (this.model!= null);
            String theField;
            theField = this.getModel();
            strategy.appendField(locator, this, "model", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectedComponent!= null);
            String theField;
            theField = this.getSelectedComponent();
            strategy.appendField(locator, this, "selectedComponent", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectedIndex!= null);
            String theField;
            theField = this.getSelectedIndex();
            strategy.appendField(locator, this, "selectedIndex", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.tabLayoutPolicy!= null);
            String theField;
            theField = this.getTabLayoutPolicy();
            strategy.appendField(locator, this, "tabLayoutPolicy", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.tabPlacement!= null);
            String theField;
            theField = this.getTabPlacement();
            strategy.appendField(locator, this, "tabPlacement", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
