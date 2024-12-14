
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
 * <p>Java class for AbstractButton complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractButton">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/swixml}JComponent">
 *       <sequence>
 *       </sequence>
 *       <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="action" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="actionCommand" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="borderPainted" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="contentAreaFilled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="disabledIcon" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="disabledSelectedIcon" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="displayedMnemonicIndex" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="focusPainted" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="hideActionText" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="horizontalAlignment" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="horizontalTextPosition" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="icon" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="iconTextGap" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="margin" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="mnemonic" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="model" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="multiClickThreshhold" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="pressedIcon" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="rolloverEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="rolloverIcon" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="rolloverSelectedIcon" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selected" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="selectedIcon" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="text" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="verticalAlignment" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="verticalTextPosition" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractButton")
@XmlSeeAlso({
    JButton.class,
    JMenuItem.class,
    JToggleButton.class
})
public class AbstractButton
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
    @XmlAttribute(name = "borderPainted")
    protected Boolean borderPainted;
    @XmlAttribute(name = "contentAreaFilled")
    protected Boolean contentAreaFilled;
    @XmlAttribute(name = "disabledIcon")
    protected String disabledIcon;
    @XmlAttribute(name = "disabledSelectedIcon")
    protected String disabledSelectedIcon;
    @XmlAttribute(name = "displayedMnemonicIndex")
    protected String displayedMnemonicIndex;
    @XmlAttribute(name = "focusPainted")
    protected Boolean focusPainted;
    @XmlAttribute(name = "hideActionText")
    protected Boolean hideActionText;
    @XmlAttribute(name = "horizontalAlignment")
    protected String horizontalAlignment;
    @XmlAttribute(name = "horizontalTextPosition")
    protected String horizontalTextPosition;
    @XmlAttribute(name = "icon")
    protected String icon;
    @XmlAttribute(name = "iconTextGap")
    protected String iconTextGap;
    @XmlAttribute(name = "margin")
    protected String margin;
    @XmlAttribute(name = "mnemonic")
    protected String mnemonic;
    @XmlAttribute(name = "model")
    protected String model;
    @XmlAttribute(name = "multiClickThreshhold")
    protected String multiClickThreshhold;
    @XmlAttribute(name = "pressedIcon")
    protected String pressedIcon;
    @XmlAttribute(name = "rolloverEnabled")
    protected Boolean rolloverEnabled;
    @XmlAttribute(name = "rolloverIcon")
    protected String rolloverIcon;
    @XmlAttribute(name = "rolloverSelectedIcon")
    protected String rolloverSelectedIcon;
    @XmlAttribute(name = "selected")
    protected Boolean selected;
    @XmlAttribute(name = "selectedIcon")
    protected String selectedIcon;
    @XmlAttribute(name = "text")
    protected String text;
    @XmlAttribute(name = "verticalAlignment")
    protected String verticalAlignment;
    @XmlAttribute(name = "verticalTextPosition")
    protected String verticalTextPosition;

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
     * Gets the value of the contentAreaFilled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isContentAreaFilled() {
        return contentAreaFilled;
    }

    /**
     * Sets the value of the contentAreaFilled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setContentAreaFilled(Boolean value) {
        this.contentAreaFilled = value;
    }

    /**
     * Gets the value of the disabledIcon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisabledIcon() {
        return disabledIcon;
    }

    /**
     * Sets the value of the disabledIcon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisabledIcon(String value) {
        this.disabledIcon = value;
    }

    /**
     * Gets the value of the disabledSelectedIcon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisabledSelectedIcon() {
        return disabledSelectedIcon;
    }

    /**
     * Sets the value of the disabledSelectedIcon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisabledSelectedIcon(String value) {
        this.disabledSelectedIcon = value;
    }

    /**
     * Gets the value of the displayedMnemonicIndex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayedMnemonicIndex() {
        return displayedMnemonicIndex;
    }

    /**
     * Sets the value of the displayedMnemonicIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayedMnemonicIndex(String value) {
        this.displayedMnemonicIndex = value;
    }

    /**
     * Gets the value of the focusPainted property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFocusPainted() {
        return focusPainted;
    }

    /**
     * Sets the value of the focusPainted property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFocusPainted(Boolean value) {
        this.focusPainted = value;
    }

    /**
     * Gets the value of the hideActionText property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHideActionText() {
        return hideActionText;
    }

    /**
     * Sets the value of the hideActionText property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHideActionText(Boolean value) {
        this.hideActionText = value;
    }

    /**
     * Gets the value of the horizontalAlignment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorizontalAlignment() {
        return horizontalAlignment;
    }

    /**
     * Sets the value of the horizontalAlignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorizontalAlignment(String value) {
        this.horizontalAlignment = value;
    }

    /**
     * Gets the value of the horizontalTextPosition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorizontalTextPosition() {
        return horizontalTextPosition;
    }

    /**
     * Sets the value of the horizontalTextPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorizontalTextPosition(String value) {
        this.horizontalTextPosition = value;
    }

    /**
     * Gets the value of the icon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets the value of the icon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIcon(String value) {
        this.icon = value;
    }

    /**
     * Gets the value of the iconTextGap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIconTextGap() {
        return iconTextGap;
    }

    /**
     * Sets the value of the iconTextGap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIconTextGap(String value) {
        this.iconTextGap = value;
    }

    /**
     * Gets the value of the margin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMargin() {
        return margin;
    }

    /**
     * Sets the value of the margin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMargin(String value) {
        this.margin = value;
    }

    /**
     * Gets the value of the mnemonic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMnemonic() {
        return mnemonic;
    }

    /**
     * Sets the value of the mnemonic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMnemonic(String value) {
        this.mnemonic = value;
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
     * Gets the value of the multiClickThreshhold property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMultiClickThreshhold() {
        return multiClickThreshhold;
    }

    /**
     * Sets the value of the multiClickThreshhold property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMultiClickThreshhold(String value) {
        this.multiClickThreshhold = value;
    }

    /**
     * Gets the value of the pressedIcon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPressedIcon() {
        return pressedIcon;
    }

    /**
     * Sets the value of the pressedIcon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPressedIcon(String value) {
        this.pressedIcon = value;
    }

    /**
     * Gets the value of the rolloverEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRolloverEnabled() {
        return rolloverEnabled;
    }

    /**
     * Sets the value of the rolloverEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRolloverEnabled(Boolean value) {
        this.rolloverEnabled = value;
    }

    /**
     * Gets the value of the rolloverIcon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRolloverIcon() {
        return rolloverIcon;
    }

    /**
     * Sets the value of the rolloverIcon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRolloverIcon(String value) {
        this.rolloverIcon = value;
    }

    /**
     * Gets the value of the rolloverSelectedIcon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRolloverSelectedIcon() {
        return rolloverSelectedIcon;
    }

    /**
     * Sets the value of the rolloverSelectedIcon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRolloverSelectedIcon(String value) {
        this.rolloverSelectedIcon = value;
    }

    /**
     * Gets the value of the selected property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSelected() {
        return selected;
    }

    /**
     * Sets the value of the selected property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSelected(Boolean value) {
        this.selected = value;
    }

    /**
     * Gets the value of the selectedIcon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedIcon() {
        return selectedIcon;
    }

    /**
     * Sets the value of the selectedIcon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedIcon(String value) {
        this.selectedIcon = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the verticalAlignment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerticalAlignment() {
        return verticalAlignment;
    }

    /**
     * Sets the value of the verticalAlignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerticalAlignment(String value) {
        this.verticalAlignment = value;
    }

    /**
     * Gets the value of the verticalTextPosition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerticalTextPosition() {
        return verticalTextPosition;
    }

    /**
     * Sets the value of the verticalTextPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerticalTextPosition(String value) {
        this.verticalTextPosition = value;
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
            boolean theFieldIsSet = (this.borderPainted!= null);
            Boolean theField;
            theField = this.isBorderPainted();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "borderPainted", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.contentAreaFilled!= null);
            Boolean theField;
            theField = this.isContentAreaFilled();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "contentAreaFilled", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.disabledIcon!= null);
            String theField;
            theField = this.getDisabledIcon();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "disabledIcon", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.disabledSelectedIcon!= null);
            String theField;
            theField = this.getDisabledSelectedIcon();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "disabledSelectedIcon", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.displayedMnemonicIndex!= null);
            String theField;
            theField = this.getDisplayedMnemonicIndex();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "displayedMnemonicIndex", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.focusPainted!= null);
            Boolean theField;
            theField = this.isFocusPainted();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "focusPainted", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.hideActionText!= null);
            Boolean theField;
            theField = this.isHideActionText();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "hideActionText", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.horizontalAlignment!= null);
            String theField;
            theField = this.getHorizontalAlignment();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "horizontalAlignment", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.horizontalTextPosition!= null);
            String theField;
            theField = this.getHorizontalTextPosition();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "horizontalTextPosition", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.icon!= null);
            String theField;
            theField = this.getIcon();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "icon", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.iconTextGap!= null);
            String theField;
            theField = this.getIconTextGap();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "iconTextGap", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.margin!= null);
            String theField;
            theField = this.getMargin();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "margin", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.mnemonic!= null);
            String theField;
            theField = this.getMnemonic();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "mnemonic", theField);
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
            boolean theFieldIsSet = (this.multiClickThreshhold!= null);
            String theField;
            theField = this.getMultiClickThreshhold();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "multiClickThreshhold", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.pressedIcon!= null);
            String theField;
            theField = this.getPressedIcon();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "pressedIcon", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rolloverEnabled!= null);
            Boolean theField;
            theField = this.isRolloverEnabled();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "rolloverEnabled", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rolloverIcon!= null);
            String theField;
            theField = this.getRolloverIcon();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "rolloverIcon", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rolloverSelectedIcon!= null);
            String theField;
            theField = this.getRolloverSelectedIcon();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "rolloverSelectedIcon", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selected!= null);
            Boolean theField;
            theField = this.isSelected();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selected", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectedIcon!= null);
            String theField;
            theField = this.getSelectedIcon();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectedIcon", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.text!= null);
            String theField;
            theField = this.getText();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "text", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.verticalAlignment!= null);
            String theField;
            theField = this.getVerticalAlignment();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "verticalAlignment", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.verticalTextPosition!= null);
            String theField;
            theField = this.getVerticalTextPosition();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "verticalTextPosition", theField);
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
        final AbstractButton that = ((AbstractButton) object);
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
            boolean lhsFieldIsSet = (this.contentAreaFilled!= null);
            boolean rhsFieldIsSet = (that.contentAreaFilled!= null);
            Boolean lhsField;
            lhsField = this.isContentAreaFilled();
            Boolean rhsField;
            rhsField = that.isContentAreaFilled();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "contentAreaFilled", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "contentAreaFilled", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.disabledIcon!= null);
            boolean rhsFieldIsSet = (that.disabledIcon!= null);
            String lhsField;
            lhsField = this.getDisabledIcon();
            String rhsField;
            rhsField = that.getDisabledIcon();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "disabledIcon", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "disabledIcon", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.disabledSelectedIcon!= null);
            boolean rhsFieldIsSet = (that.disabledSelectedIcon!= null);
            String lhsField;
            lhsField = this.getDisabledSelectedIcon();
            String rhsField;
            rhsField = that.getDisabledSelectedIcon();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "disabledSelectedIcon", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "disabledSelectedIcon", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.displayedMnemonicIndex!= null);
            boolean rhsFieldIsSet = (that.displayedMnemonicIndex!= null);
            String lhsField;
            lhsField = this.getDisplayedMnemonicIndex();
            String rhsField;
            rhsField = that.getDisplayedMnemonicIndex();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "displayedMnemonicIndex", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "displayedMnemonicIndex", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.focusPainted!= null);
            boolean rhsFieldIsSet = (that.focusPainted!= null);
            Boolean lhsField;
            lhsField = this.isFocusPainted();
            Boolean rhsField;
            rhsField = that.isFocusPainted();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "focusPainted", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "focusPainted", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.hideActionText!= null);
            boolean rhsFieldIsSet = (that.hideActionText!= null);
            Boolean lhsField;
            lhsField = this.isHideActionText();
            Boolean rhsField;
            rhsField = that.isHideActionText();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "hideActionText", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "hideActionText", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.horizontalAlignment!= null);
            boolean rhsFieldIsSet = (that.horizontalAlignment!= null);
            String lhsField;
            lhsField = this.getHorizontalAlignment();
            String rhsField;
            rhsField = that.getHorizontalAlignment();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "horizontalAlignment", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "horizontalAlignment", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.horizontalTextPosition!= null);
            boolean rhsFieldIsSet = (that.horizontalTextPosition!= null);
            String lhsField;
            lhsField = this.getHorizontalTextPosition();
            String rhsField;
            rhsField = that.getHorizontalTextPosition();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "horizontalTextPosition", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "horizontalTextPosition", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.icon!= null);
            boolean rhsFieldIsSet = (that.icon!= null);
            String lhsField;
            lhsField = this.getIcon();
            String rhsField;
            rhsField = that.getIcon();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "icon", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "icon", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.iconTextGap!= null);
            boolean rhsFieldIsSet = (that.iconTextGap!= null);
            String lhsField;
            lhsField = this.getIconTextGap();
            String rhsField;
            rhsField = that.getIconTextGap();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "iconTextGap", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "iconTextGap", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.margin!= null);
            boolean rhsFieldIsSet = (that.margin!= null);
            String lhsField;
            lhsField = this.getMargin();
            String rhsField;
            rhsField = that.getMargin();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "margin", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "margin", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.mnemonic!= null);
            boolean rhsFieldIsSet = (that.mnemonic!= null);
            String lhsField;
            lhsField = this.getMnemonic();
            String rhsField;
            rhsField = that.getMnemonic();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "mnemonic", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "mnemonic", rhsField);
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
            boolean lhsFieldIsSet = (this.multiClickThreshhold!= null);
            boolean rhsFieldIsSet = (that.multiClickThreshhold!= null);
            String lhsField;
            lhsField = this.getMultiClickThreshhold();
            String rhsField;
            rhsField = that.getMultiClickThreshhold();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "multiClickThreshhold", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "multiClickThreshhold", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.pressedIcon!= null);
            boolean rhsFieldIsSet = (that.pressedIcon!= null);
            String lhsField;
            lhsField = this.getPressedIcon();
            String rhsField;
            rhsField = that.getPressedIcon();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "pressedIcon", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "pressedIcon", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.rolloverEnabled!= null);
            boolean rhsFieldIsSet = (that.rolloverEnabled!= null);
            Boolean lhsField;
            lhsField = this.isRolloverEnabled();
            Boolean rhsField;
            rhsField = that.isRolloverEnabled();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "rolloverEnabled", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "rolloverEnabled", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.rolloverIcon!= null);
            boolean rhsFieldIsSet = (that.rolloverIcon!= null);
            String lhsField;
            lhsField = this.getRolloverIcon();
            String rhsField;
            rhsField = that.getRolloverIcon();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "rolloverIcon", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "rolloverIcon", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.rolloverSelectedIcon!= null);
            boolean rhsFieldIsSet = (that.rolloverSelectedIcon!= null);
            String lhsField;
            lhsField = this.getRolloverSelectedIcon();
            String rhsField;
            rhsField = that.getRolloverSelectedIcon();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "rolloverSelectedIcon", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "rolloverSelectedIcon", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selected!= null);
            boolean rhsFieldIsSet = (that.selected!= null);
            Boolean lhsField;
            lhsField = this.isSelected();
            Boolean rhsField;
            rhsField = that.isSelected();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selected", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selected", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selectedIcon!= null);
            boolean rhsFieldIsSet = (that.selectedIcon!= null);
            String lhsField;
            lhsField = this.getSelectedIcon();
            String rhsField;
            rhsField = that.getSelectedIcon();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectedIcon", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectedIcon", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.text!= null);
            boolean rhsFieldIsSet = (that.text!= null);
            String lhsField;
            lhsField = this.getText();
            String rhsField;
            rhsField = that.getText();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "text", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "text", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.verticalAlignment!= null);
            boolean rhsFieldIsSet = (that.verticalAlignment!= null);
            String lhsField;
            lhsField = this.getVerticalAlignment();
            String rhsField;
            rhsField = that.getVerticalAlignment();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "verticalAlignment", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "verticalAlignment", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.verticalTextPosition!= null);
            boolean rhsFieldIsSet = (that.verticalTextPosition!= null);
            String lhsField;
            lhsField = this.getVerticalTextPosition();
            String rhsField;
            rhsField = that.getVerticalTextPosition();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "verticalTextPosition", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "verticalTextPosition", rhsField);
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
            boolean theFieldIsSet = (this.borderPainted!= null);
            Boolean theField;
            theField = this.isBorderPainted();
            strategy.appendField(locator, this, "borderPainted", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.contentAreaFilled!= null);
            Boolean theField;
            theField = this.isContentAreaFilled();
            strategy.appendField(locator, this, "contentAreaFilled", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.disabledIcon!= null);
            String theField;
            theField = this.getDisabledIcon();
            strategy.appendField(locator, this, "disabledIcon", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.disabledSelectedIcon!= null);
            String theField;
            theField = this.getDisabledSelectedIcon();
            strategy.appendField(locator, this, "disabledSelectedIcon", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.displayedMnemonicIndex!= null);
            String theField;
            theField = this.getDisplayedMnemonicIndex();
            strategy.appendField(locator, this, "displayedMnemonicIndex", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.focusPainted!= null);
            Boolean theField;
            theField = this.isFocusPainted();
            strategy.appendField(locator, this, "focusPainted", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.hideActionText!= null);
            Boolean theField;
            theField = this.isHideActionText();
            strategy.appendField(locator, this, "hideActionText", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.horizontalAlignment!= null);
            String theField;
            theField = this.getHorizontalAlignment();
            strategy.appendField(locator, this, "horizontalAlignment", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.horizontalTextPosition!= null);
            String theField;
            theField = this.getHorizontalTextPosition();
            strategy.appendField(locator, this, "horizontalTextPosition", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.icon!= null);
            String theField;
            theField = this.getIcon();
            strategy.appendField(locator, this, "icon", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.iconTextGap!= null);
            String theField;
            theField = this.getIconTextGap();
            strategy.appendField(locator, this, "iconTextGap", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.margin!= null);
            String theField;
            theField = this.getMargin();
            strategy.appendField(locator, this, "margin", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.mnemonic!= null);
            String theField;
            theField = this.getMnemonic();
            strategy.appendField(locator, this, "mnemonic", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.model!= null);
            String theField;
            theField = this.getModel();
            strategy.appendField(locator, this, "model", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.multiClickThreshhold!= null);
            String theField;
            theField = this.getMultiClickThreshhold();
            strategy.appendField(locator, this, "multiClickThreshhold", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.pressedIcon!= null);
            String theField;
            theField = this.getPressedIcon();
            strategy.appendField(locator, this, "pressedIcon", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rolloverEnabled!= null);
            Boolean theField;
            theField = this.isRolloverEnabled();
            strategy.appendField(locator, this, "rolloverEnabled", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rolloverIcon!= null);
            String theField;
            theField = this.getRolloverIcon();
            strategy.appendField(locator, this, "rolloverIcon", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rolloverSelectedIcon!= null);
            String theField;
            theField = this.getRolloverSelectedIcon();
            strategy.appendField(locator, this, "rolloverSelectedIcon", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selected!= null);
            Boolean theField;
            theField = this.isSelected();
            strategy.appendField(locator, this, "selected", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectedIcon!= null);
            String theField;
            theField = this.getSelectedIcon();
            strategy.appendField(locator, this, "selectedIcon", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.text!= null);
            String theField;
            theField = this.getText();
            strategy.appendField(locator, this, "text", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.verticalAlignment!= null);
            String theField;
            theField = this.getVerticalAlignment();
            strategy.appendField(locator, this, "verticalAlignment", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.verticalTextPosition!= null);
            String theField;
            theField = this.getVerticalTextPosition();
            strategy.appendField(locator, this, "verticalTextPosition", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
