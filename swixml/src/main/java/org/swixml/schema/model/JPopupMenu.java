
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
 * <p>Java class for JPopupMenu complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JPopupMenu">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/Swixml}JComponent">
 *       <sequence>
 *       </sequence>
 *       <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="borderPainted" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="defaultLightWeightPopupEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="invoker" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="lightWeightPopupEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="popupSize" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selected" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionModel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JPopupMenu")
public class JPopupMenu
    extends JComponent
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "UI")
    protected String ui;
    @XmlAttribute(name = "borderPainted")
    protected Boolean borderPainted;
    @XmlAttribute(name = "defaultLightWeightPopupEnabled")
    protected Boolean defaultLightWeightPopupEnabled;
    @XmlAttribute(name = "invoker")
    protected String invoker;
    @XmlAttribute(name = "label")
    protected String label;
    @XmlAttribute(name = "lightWeightPopupEnabled")
    protected Boolean lightWeightPopupEnabled;
    @XmlAttribute(name = "popupSize")
    protected String popupSize;
    @XmlAttribute(name = "selected")
    protected String selected;
    @XmlAttribute(name = "selectionModel")
    protected String selectionModel;

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
     * Gets the value of the borderPainted property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBorderPainted() {
        return borderPainted;
    }

    /**
     * Sets the value of the borderPainted property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBorderPainted(Boolean value) {
        this.borderPainted = value;
    }

    /**
     * Gets the value of the defaultLightWeightPopupEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDefaultLightWeightPopupEnabled() {
        return defaultLightWeightPopupEnabled;
    }

    /**
     * Sets the value of the defaultLightWeightPopupEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDefaultLightWeightPopupEnabled(Boolean value) {
        this.defaultLightWeightPopupEnabled = value;
    }

    /**
     * Gets the value of the invoker property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoker() {
        return invoker;
    }

    /**
     * Sets the value of the invoker property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoker(String value) {
        this.invoker = value;
    }

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the lightWeightPopupEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLightWeightPopupEnabled() {
        return lightWeightPopupEnabled;
    }

    /**
     * Sets the value of the lightWeightPopupEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLightWeightPopupEnabled(Boolean value) {
        this.lightWeightPopupEnabled = value;
    }

    /**
     * Gets the value of the popupSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopupSize() {
        return popupSize;
    }

    /**
     * Sets the value of the popupSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopupSize(String value) {
        this.popupSize = value;
    }

    /**
     * Gets the value of the selected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelected() {
        return selected;
    }

    /**
     * Sets the value of the selected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelected(String value) {
        this.selected = value;
    }

    /**
     * Gets the value of the selectionModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectionModel() {
        return selectionModel;
    }

    /**
     * Sets the value of the selectionModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectionModel(String value) {
        this.selectionModel = value;
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
            boolean theFieldIsSet = (this.borderPainted!= null);
            Boolean theField;
            theField = this.isBorderPainted();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "borderPainted", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.defaultLightWeightPopupEnabled!= null);
            Boolean theField;
            theField = this.isDefaultLightWeightPopupEnabled();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "defaultLightWeightPopupEnabled", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.invoker!= null);
            String theField;
            theField = this.getInvoker();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "invoker", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.label!= null);
            String theField;
            theField = this.getLabel();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "label", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.lightWeightPopupEnabled!= null);
            Boolean theField;
            theField = this.isLightWeightPopupEnabled();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "lightWeightPopupEnabled", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.popupSize!= null);
            String theField;
            theField = this.getPopupSize();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "popupSize", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selected!= null);
            String theField;
            theField = this.getSelected();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selected", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionModel!= null);
            String theField;
            theField = this.getSelectionModel();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectionModel", theField);
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
        final JPopupMenu that = ((JPopupMenu) object);
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
            boolean lhsFieldIsSet = (this.borderPainted!= null);
            boolean rhsFieldIsSet = (that.borderPainted!= null);
            Boolean lhsField;
            lhsField = this.isBorderPainted();
            Boolean rhsField;
            rhsField = that.isBorderPainted();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "borderPainted", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "borderPainted", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.defaultLightWeightPopupEnabled!= null);
            boolean rhsFieldIsSet = (that.defaultLightWeightPopupEnabled!= null);
            Boolean lhsField;
            lhsField = this.isDefaultLightWeightPopupEnabled();
            Boolean rhsField;
            rhsField = that.isDefaultLightWeightPopupEnabled();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "defaultLightWeightPopupEnabled", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "defaultLightWeightPopupEnabled", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.invoker!= null);
            boolean rhsFieldIsSet = (that.invoker!= null);
            String lhsField;
            lhsField = this.getInvoker();
            String rhsField;
            rhsField = that.getInvoker();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "invoker", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "invoker", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.label!= null);
            boolean rhsFieldIsSet = (that.label!= null);
            String lhsField;
            lhsField = this.getLabel();
            String rhsField;
            rhsField = that.getLabel();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "label", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "label", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.lightWeightPopupEnabled!= null);
            boolean rhsFieldIsSet = (that.lightWeightPopupEnabled!= null);
            Boolean lhsField;
            lhsField = this.isLightWeightPopupEnabled();
            Boolean rhsField;
            rhsField = that.isLightWeightPopupEnabled();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "lightWeightPopupEnabled", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "lightWeightPopupEnabled", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.popupSize!= null);
            boolean rhsFieldIsSet = (that.popupSize!= null);
            String lhsField;
            lhsField = this.getPopupSize();
            String rhsField;
            rhsField = that.getPopupSize();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "popupSize", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "popupSize", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selected!= null);
            boolean rhsFieldIsSet = (that.selected!= null);
            String lhsField;
            lhsField = this.getSelected();
            String rhsField;
            rhsField = that.getSelected();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selected", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selected", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selectionModel!= null);
            boolean rhsFieldIsSet = (that.selectionModel!= null);
            String lhsField;
            lhsField = this.getSelectionModel();
            String rhsField;
            rhsField = that.getSelectionModel();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectionModel", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectionModel", rhsField);
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
            boolean theFieldIsSet = (this.borderPainted!= null);
            Boolean theField;
            theField = this.isBorderPainted();
            strategy.appendField(locator, this, "borderPainted", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.defaultLightWeightPopupEnabled!= null);
            Boolean theField;
            theField = this.isDefaultLightWeightPopupEnabled();
            strategy.appendField(locator, this, "defaultLightWeightPopupEnabled", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.invoker!= null);
            String theField;
            theField = this.getInvoker();
            strategy.appendField(locator, this, "invoker", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.label!= null);
            String theField;
            theField = this.getLabel();
            strategy.appendField(locator, this, "label", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.lightWeightPopupEnabled!= null);
            Boolean theField;
            theField = this.isLightWeightPopupEnabled();
            strategy.appendField(locator, this, "lightWeightPopupEnabled", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.popupSize!= null);
            String theField;
            theField = this.getPopupSize();
            strategy.appendField(locator, this, "popupSize", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selected!= null);
            String theField;
            theField = this.getSelected();
            strategy.appendField(locator, this, "selected", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionModel!= null);
            String theField;
            theField = this.getSelectionModel();
            strategy.appendField(locator, this, "selectionModel", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
