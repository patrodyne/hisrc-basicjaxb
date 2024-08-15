
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
 * <p>Java class for JTextComponent complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JTextComponent">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/Swixml}JComponent">
 *       <sequence>
 *       </sequence>
 *       <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="caret" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="caretColor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="caretPosition" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="disabledTextColor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="document" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="dragEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="dropMode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="focusAccelerator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="highlighter" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="keymap" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="margin" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="navigationFilter" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectedTextColor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionColor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionEnd" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionStart" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="text" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JTextComponent")
@XmlSeeAlso({
    JEditorPane.class,
    JTextField.class,
    JTextArea.class
})
public class JTextComponent
    extends JComponent
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "UI")
    protected String ui;
    @XmlAttribute(name = "caret")
    protected String caret;
    @XmlAttribute(name = "caretColor")
    protected String caretColor;
    @XmlAttribute(name = "caretPosition")
    protected String caretPosition;
    @XmlAttribute(name = "disabledTextColor")
    protected String disabledTextColor;
    @XmlAttribute(name = "document")
    protected String document;
    @XmlAttribute(name = "dragEnabled")
    protected Boolean dragEnabled;
    @XmlAttribute(name = "dropMode")
    protected String dropMode;
    @XmlAttribute(name = "editable")
    protected Boolean editable;
    @XmlAttribute(name = "focusAccelerator")
    protected String focusAccelerator;
    @XmlAttribute(name = "highlighter")
    protected String highlighter;
    @XmlAttribute(name = "keymap")
    protected String keymap;
    @XmlAttribute(name = "margin")
    protected String margin;
    @XmlAttribute(name = "navigationFilter")
    protected String navigationFilter;
    @XmlAttribute(name = "selectedTextColor")
    protected String selectedTextColor;
    @XmlAttribute(name = "selectionColor")
    protected String selectionColor;
    @XmlAttribute(name = "selectionEnd")
    protected String selectionEnd;
    @XmlAttribute(name = "selectionStart")
    protected String selectionStart;
    @XmlAttribute(name = "text")
    protected String text;

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
     * Gets the value of the caret property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaret() {
        return caret;
    }

    /**
     * Sets the value of the caret property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaret(String value) {
        this.caret = value;
    }

    /**
     * Gets the value of the caretColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaretColor() {
        return caretColor;
    }

    /**
     * Sets the value of the caretColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaretColor(String value) {
        this.caretColor = value;
    }

    /**
     * Gets the value of the caretPosition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaretPosition() {
        return caretPosition;
    }

    /**
     * Sets the value of the caretPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaretPosition(String value) {
        this.caretPosition = value;
    }

    /**
     * Gets the value of the disabledTextColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisabledTextColor() {
        return disabledTextColor;
    }

    /**
     * Sets the value of the disabledTextColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisabledTextColor(String value) {
        this.disabledTextColor = value;
    }

    /**
     * Gets the value of the document property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocument() {
        return document;
    }

    /**
     * Sets the value of the document property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocument(String value) {
        this.document = value;
    }

    /**
     * Gets the value of the dragEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDragEnabled() {
        return dragEnabled;
    }

    /**
     * Sets the value of the dragEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDragEnabled(Boolean value) {
        this.dragEnabled = value;
    }

    /**
     * Gets the value of the dropMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDropMode() {
        return dropMode;
    }

    /**
     * Sets the value of the dropMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDropMode(String value) {
        this.dropMode = value;
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
     * Gets the value of the focusAccelerator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFocusAccelerator() {
        return focusAccelerator;
    }

    /**
     * Sets the value of the focusAccelerator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFocusAccelerator(String value) {
        this.focusAccelerator = value;
    }

    /**
     * Gets the value of the highlighter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHighlighter() {
        return highlighter;
    }

    /**
     * Sets the value of the highlighter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHighlighter(String value) {
        this.highlighter = value;
    }

    /**
     * Gets the value of the keymap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeymap() {
        return keymap;
    }

    /**
     * Sets the value of the keymap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeymap(String value) {
        this.keymap = value;
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
     * Gets the value of the navigationFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNavigationFilter() {
        return navigationFilter;
    }

    /**
     * Sets the value of the navigationFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNavigationFilter(String value) {
        this.navigationFilter = value;
    }

    /**
     * Gets the value of the selectedTextColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedTextColor() {
        return selectedTextColor;
    }

    /**
     * Sets the value of the selectedTextColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedTextColor(String value) {
        this.selectedTextColor = value;
    }

    /**
     * Gets the value of the selectionColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectionColor() {
        return selectionColor;
    }

    /**
     * Sets the value of the selectionColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectionColor(String value) {
        this.selectionColor = value;
    }

    /**
     * Gets the value of the selectionEnd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectionEnd() {
        return selectionEnd;
    }

    /**
     * Sets the value of the selectionEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectionEnd(String value) {
        this.selectionEnd = value;
    }

    /**
     * Gets the value of the selectionStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectionStart() {
        return selectionStart;
    }

    /**
     * Sets the value of the selectionStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectionStart(String value) {
        this.selectionStart = value;
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
            boolean theFieldIsSet = (this.caret!= null);
            String theField;
            theField = this.getCaret();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "caret", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.caretColor!= null);
            String theField;
            theField = this.getCaretColor();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "caretColor", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.caretPosition!= null);
            String theField;
            theField = this.getCaretPosition();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "caretPosition", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.disabledTextColor!= null);
            String theField;
            theField = this.getDisabledTextColor();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "disabledTextColor", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.document!= null);
            String theField;
            theField = this.getDocument();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "document", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.dragEnabled!= null);
            Boolean theField;
            theField = this.isDragEnabled();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "dragEnabled", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.dropMode!= null);
            String theField;
            theField = this.getDropMode();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "dropMode", theField);
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
            boolean theFieldIsSet = (this.focusAccelerator!= null);
            String theField;
            theField = this.getFocusAccelerator();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "focusAccelerator", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.highlighter!= null);
            String theField;
            theField = this.getHighlighter();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "highlighter", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.keymap!= null);
            String theField;
            theField = this.getKeymap();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "keymap", theField);
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
            boolean theFieldIsSet = (this.navigationFilter!= null);
            String theField;
            theField = this.getNavigationFilter();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "navigationFilter", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectedTextColor!= null);
            String theField;
            theField = this.getSelectedTextColor();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectedTextColor", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionColor!= null);
            String theField;
            theField = this.getSelectionColor();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectionColor", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionEnd!= null);
            String theField;
            theField = this.getSelectionEnd();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectionEnd", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionStart!= null);
            String theField;
            theField = this.getSelectionStart();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectionStart", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.text!= null);
            String theField;
            theField = this.getText();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "text", theField);
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
        final JTextComponent that = ((JTextComponent) object);
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
            boolean lhsFieldIsSet = (this.caret!= null);
            boolean rhsFieldIsSet = (that.caret!= null);
            String lhsField;
            lhsField = this.getCaret();
            String rhsField;
            rhsField = that.getCaret();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "caret", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "caret", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.caretColor!= null);
            boolean rhsFieldIsSet = (that.caretColor!= null);
            String lhsField;
            lhsField = this.getCaretColor();
            String rhsField;
            rhsField = that.getCaretColor();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "caretColor", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "caretColor", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.caretPosition!= null);
            boolean rhsFieldIsSet = (that.caretPosition!= null);
            String lhsField;
            lhsField = this.getCaretPosition();
            String rhsField;
            rhsField = that.getCaretPosition();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "caretPosition", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "caretPosition", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.disabledTextColor!= null);
            boolean rhsFieldIsSet = (that.disabledTextColor!= null);
            String lhsField;
            lhsField = this.getDisabledTextColor();
            String rhsField;
            rhsField = that.getDisabledTextColor();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "disabledTextColor", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "disabledTextColor", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.document!= null);
            boolean rhsFieldIsSet = (that.document!= null);
            String lhsField;
            lhsField = this.getDocument();
            String rhsField;
            rhsField = that.getDocument();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "document", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "document", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.dragEnabled!= null);
            boolean rhsFieldIsSet = (that.dragEnabled!= null);
            Boolean lhsField;
            lhsField = this.isDragEnabled();
            Boolean rhsField;
            rhsField = that.isDragEnabled();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "dragEnabled", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "dragEnabled", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.dropMode!= null);
            boolean rhsFieldIsSet = (that.dropMode!= null);
            String lhsField;
            lhsField = this.getDropMode();
            String rhsField;
            rhsField = that.getDropMode();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "dropMode", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "dropMode", rhsField);
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
            boolean lhsFieldIsSet = (this.focusAccelerator!= null);
            boolean rhsFieldIsSet = (that.focusAccelerator!= null);
            String lhsField;
            lhsField = this.getFocusAccelerator();
            String rhsField;
            rhsField = that.getFocusAccelerator();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "focusAccelerator", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "focusAccelerator", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.highlighter!= null);
            boolean rhsFieldIsSet = (that.highlighter!= null);
            String lhsField;
            lhsField = this.getHighlighter();
            String rhsField;
            rhsField = that.getHighlighter();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "highlighter", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "highlighter", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.keymap!= null);
            boolean rhsFieldIsSet = (that.keymap!= null);
            String lhsField;
            lhsField = this.getKeymap();
            String rhsField;
            rhsField = that.getKeymap();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "keymap", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "keymap", rhsField);
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
            boolean lhsFieldIsSet = (this.navigationFilter!= null);
            boolean rhsFieldIsSet = (that.navigationFilter!= null);
            String lhsField;
            lhsField = this.getNavigationFilter();
            String rhsField;
            rhsField = that.getNavigationFilter();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "navigationFilter", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "navigationFilter", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selectedTextColor!= null);
            boolean rhsFieldIsSet = (that.selectedTextColor!= null);
            String lhsField;
            lhsField = this.getSelectedTextColor();
            String rhsField;
            rhsField = that.getSelectedTextColor();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectedTextColor", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectedTextColor", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selectionColor!= null);
            boolean rhsFieldIsSet = (that.selectionColor!= null);
            String lhsField;
            lhsField = this.getSelectionColor();
            String rhsField;
            rhsField = that.getSelectionColor();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectionColor", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectionColor", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selectionEnd!= null);
            boolean rhsFieldIsSet = (that.selectionEnd!= null);
            String lhsField;
            lhsField = this.getSelectionEnd();
            String rhsField;
            rhsField = that.getSelectionEnd();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectionEnd", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectionEnd", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selectionStart!= null);
            boolean rhsFieldIsSet = (that.selectionStart!= null);
            String lhsField;
            lhsField = this.getSelectionStart();
            String rhsField;
            rhsField = that.getSelectionStart();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectionStart", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectionStart", rhsField);
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
            boolean theFieldIsSet = (this.caret!= null);
            String theField;
            theField = this.getCaret();
            strategy.appendField(locator, this, "caret", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.caretColor!= null);
            String theField;
            theField = this.getCaretColor();
            strategy.appendField(locator, this, "caretColor", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.caretPosition!= null);
            String theField;
            theField = this.getCaretPosition();
            strategy.appendField(locator, this, "caretPosition", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.disabledTextColor!= null);
            String theField;
            theField = this.getDisabledTextColor();
            strategy.appendField(locator, this, "disabledTextColor", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.document!= null);
            String theField;
            theField = this.getDocument();
            strategy.appendField(locator, this, "document", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.dragEnabled!= null);
            Boolean theField;
            theField = this.isDragEnabled();
            strategy.appendField(locator, this, "dragEnabled", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.dropMode!= null);
            String theField;
            theField = this.getDropMode();
            strategy.appendField(locator, this, "dropMode", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.editable!= null);
            Boolean theField;
            theField = this.isEditable();
            strategy.appendField(locator, this, "editable", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.focusAccelerator!= null);
            String theField;
            theField = this.getFocusAccelerator();
            strategy.appendField(locator, this, "focusAccelerator", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.highlighter!= null);
            String theField;
            theField = this.getHighlighter();
            strategy.appendField(locator, this, "highlighter", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.keymap!= null);
            String theField;
            theField = this.getKeymap();
            strategy.appendField(locator, this, "keymap", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.margin!= null);
            String theField;
            theField = this.getMargin();
            strategy.appendField(locator, this, "margin", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.navigationFilter!= null);
            String theField;
            theField = this.getNavigationFilter();
            strategy.appendField(locator, this, "navigationFilter", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectedTextColor!= null);
            String theField;
            theField = this.getSelectedTextColor();
            strategy.appendField(locator, this, "selectedTextColor", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionColor!= null);
            String theField;
            theField = this.getSelectionColor();
            strategy.appendField(locator, this, "selectionColor", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionEnd!= null);
            String theField;
            theField = this.getSelectionEnd();
            strategy.appendField(locator, this, "selectionEnd", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionStart!= null);
            String theField;
            theField = this.getSelectionStart();
            strategy.appendField(locator, this, "selectionStart", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.text!= null);
            String theField;
            theField = this.getText();
            strategy.appendField(locator, this, "text", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
