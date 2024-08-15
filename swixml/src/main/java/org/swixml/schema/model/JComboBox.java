
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
 * <p>Java class for JComboBox complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JComboBox">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/Swixml}JComponent">
 *       <sequence>
 *       </sequence>
 *       <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="action" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="actionCommand" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="editor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="keySelectionManager" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="lightWeightPopupEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="maximumRowCount" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="model" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="popupVisible" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="prototypeDisplayValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="renderer" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectedIndex" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectedItem" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JComboBox")
@XmlSeeAlso({
    JComboBoxBind.class
})
public class JComboBox
    extends JComponent
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "UI")
    protected String ui;
    @XmlAttribute(name = "action")
    protected String action;
    @XmlAttribute(name = "actionCommand")
    protected String actionCommand;
    @XmlAttribute(name = "editable")
    protected Boolean editable;
    @XmlAttribute(name = "editor")
    protected String editor;
    @XmlAttribute(name = "keySelectionManager")
    protected String keySelectionManager;
    @XmlAttribute(name = "lightWeightPopupEnabled")
    protected Boolean lightWeightPopupEnabled;
    @XmlAttribute(name = "maximumRowCount")
    protected String maximumRowCount;
    @XmlAttribute(name = "model")
    protected String model;
    @XmlAttribute(name = "popupVisible")
    protected Boolean popupVisible;
    @XmlAttribute(name = "prototypeDisplayValue")
    protected String prototypeDisplayValue;
    @XmlAttribute(name = "renderer")
    protected String renderer;
    @XmlAttribute(name = "selectedIndex")
    protected String selectedIndex;
    @XmlAttribute(name = "selectedItem")
    protected String selectedItem;

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
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the actionCommand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionCommand() {
        return actionCommand;
    }

    /**
     * Sets the value of the actionCommand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionCommand(String value) {
        this.actionCommand = value;
    }

    /**
     * Gets the value of the editable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEditable() {
        return editable;
    }

    /**
     * Sets the value of the editable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEditable(Boolean value) {
        this.editable = value;
    }

    /**
     * Gets the value of the editor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEditor() {
        return editor;
    }

    /**
     * Sets the value of the editor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEditor(String value) {
        this.editor = value;
    }

    /**
     * Gets the value of the keySelectionManager property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeySelectionManager() {
        return keySelectionManager;
    }

    /**
     * Sets the value of the keySelectionManager property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeySelectionManager(String value) {
        this.keySelectionManager = value;
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
     * Gets the value of the maximumRowCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaximumRowCount() {
        return maximumRowCount;
    }

    /**
     * Sets the value of the maximumRowCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaximumRowCount(String value) {
        this.maximumRowCount = value;
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
     * Gets the value of the popupVisible property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPopupVisible() {
        return popupVisible;
    }

    /**
     * Sets the value of the popupVisible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPopupVisible(Boolean value) {
        this.popupVisible = value;
    }

    /**
     * Gets the value of the prototypeDisplayValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrototypeDisplayValue() {
        return prototypeDisplayValue;
    }

    /**
     * Sets the value of the prototypeDisplayValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrototypeDisplayValue(String value) {
        this.prototypeDisplayValue = value;
    }

    /**
     * Gets the value of the renderer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenderer() {
        return renderer;
    }

    /**
     * Sets the value of the renderer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenderer(String value) {
        this.renderer = value;
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
     * Gets the value of the selectedItem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedItem() {
        return selectedItem;
    }

    /**
     * Sets the value of the selectedItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedItem(String value) {
        this.selectedItem = value;
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
            boolean theFieldIsSet = (this.action!= null);
            String theField;
            theField = this.getAction();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "action", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.actionCommand!= null);
            String theField;
            theField = this.getActionCommand();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "actionCommand", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.editable!= null);
            Boolean theField;
            theField = this.isEditable();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "editable", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.editor!= null);
            String theField;
            theField = this.getEditor();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "editor", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.keySelectionManager!= null);
            String theField;
            theField = this.getKeySelectionManager();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "keySelectionManager", theField);
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
            boolean theFieldIsSet = (this.maximumRowCount!= null);
            String theField;
            theField = this.getMaximumRowCount();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "maximumRowCount", theField);
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
            boolean theFieldIsSet = (this.popupVisible!= null);
            Boolean theField;
            theField = this.isPopupVisible();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "popupVisible", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.prototypeDisplayValue!= null);
            String theField;
            theField = this.getPrototypeDisplayValue();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "prototypeDisplayValue", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.renderer!= null);
            String theField;
            theField = this.getRenderer();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "renderer", theField);
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
            boolean theFieldIsSet = (this.selectedItem!= null);
            String theField;
            theField = this.getSelectedItem();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectedItem", theField);
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
        final JComboBox that = ((JComboBox) object);
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
            boolean lhsFieldIsSet = (this.action!= null);
            boolean rhsFieldIsSet = (that.action!= null);
            String lhsField;
            lhsField = this.getAction();
            String rhsField;
            rhsField = that.getAction();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "action", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "action", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.actionCommand!= null);
            boolean rhsFieldIsSet = (that.actionCommand!= null);
            String lhsField;
            lhsField = this.getActionCommand();
            String rhsField;
            rhsField = that.getActionCommand();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "actionCommand", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "actionCommand", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.editable!= null);
            boolean rhsFieldIsSet = (that.editable!= null);
            Boolean lhsField;
            lhsField = this.isEditable();
            Boolean rhsField;
            rhsField = that.isEditable();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "editable", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "editable", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.editor!= null);
            boolean rhsFieldIsSet = (that.editor!= null);
            String lhsField;
            lhsField = this.getEditor();
            String rhsField;
            rhsField = that.getEditor();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "editor", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "editor", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.keySelectionManager!= null);
            boolean rhsFieldIsSet = (that.keySelectionManager!= null);
            String lhsField;
            lhsField = this.getKeySelectionManager();
            String rhsField;
            rhsField = that.getKeySelectionManager();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "keySelectionManager", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "keySelectionManager", rhsField);
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
            boolean lhsFieldIsSet = (this.maximumRowCount!= null);
            boolean rhsFieldIsSet = (that.maximumRowCount!= null);
            String lhsField;
            lhsField = this.getMaximumRowCount();
            String rhsField;
            rhsField = that.getMaximumRowCount();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "maximumRowCount", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "maximumRowCount", rhsField);
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
            boolean lhsFieldIsSet = (this.popupVisible!= null);
            boolean rhsFieldIsSet = (that.popupVisible!= null);
            Boolean lhsField;
            lhsField = this.isPopupVisible();
            Boolean rhsField;
            rhsField = that.isPopupVisible();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "popupVisible", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "popupVisible", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.prototypeDisplayValue!= null);
            boolean rhsFieldIsSet = (that.prototypeDisplayValue!= null);
            String lhsField;
            lhsField = this.getPrototypeDisplayValue();
            String rhsField;
            rhsField = that.getPrototypeDisplayValue();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "prototypeDisplayValue", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "prototypeDisplayValue", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.renderer!= null);
            boolean rhsFieldIsSet = (that.renderer!= null);
            String lhsField;
            lhsField = this.getRenderer();
            String rhsField;
            rhsField = that.getRenderer();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "renderer", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "renderer", rhsField);
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
            boolean lhsFieldIsSet = (this.selectedItem!= null);
            boolean rhsFieldIsSet = (that.selectedItem!= null);
            String lhsField;
            lhsField = this.getSelectedItem();
            String rhsField;
            rhsField = that.getSelectedItem();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectedItem", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectedItem", rhsField);
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
            boolean theFieldIsSet = (this.action!= null);
            String theField;
            theField = this.getAction();
            strategy.appendField(locator, this, "action", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.actionCommand!= null);
            String theField;
            theField = this.getActionCommand();
            strategy.appendField(locator, this, "actionCommand", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.editable!= null);
            Boolean theField;
            theField = this.isEditable();
            strategy.appendField(locator, this, "editable", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.editor!= null);
            String theField;
            theField = this.getEditor();
            strategy.appendField(locator, this, "editor", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.keySelectionManager!= null);
            String theField;
            theField = this.getKeySelectionManager();
            strategy.appendField(locator, this, "keySelectionManager", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.lightWeightPopupEnabled!= null);
            Boolean theField;
            theField = this.isLightWeightPopupEnabled();
            strategy.appendField(locator, this, "lightWeightPopupEnabled", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.maximumRowCount!= null);
            String theField;
            theField = this.getMaximumRowCount();
            strategy.appendField(locator, this, "maximumRowCount", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.model!= null);
            String theField;
            theField = this.getModel();
            strategy.appendField(locator, this, "model", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.popupVisible!= null);
            Boolean theField;
            theField = this.isPopupVisible();
            strategy.appendField(locator, this, "popupVisible", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.prototypeDisplayValue!= null);
            String theField;
            theField = this.getPrototypeDisplayValue();
            strategy.appendField(locator, this, "prototypeDisplayValue", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.renderer!= null);
            String theField;
            theField = this.getRenderer();
            strategy.appendField(locator, this, "renderer", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectedIndex!= null);
            String theField;
            theField = this.getSelectedIndex();
            strategy.appendField(locator, this, "selectedIndex", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectedItem!= null);
            String theField;
            theField = this.getSelectedItem();
            strategy.appendField(locator, this, "selectedItem", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
