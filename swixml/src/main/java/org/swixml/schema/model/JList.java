
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
 * <p>Java class for JList complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JList">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/Swixml}JComponent">
 *       <sequence>
 *       </sequence>
 *       <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="cellRenderer" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="dragEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="dropMode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="fixedCellHeight" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="fixedCellWidth" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="layoutOrientation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="listData" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="model" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="prototypeCellValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectedIndex" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectedIndices" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionBackground" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionForeground" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionMode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionModel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="valueIsAdjusting" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="visibleRowCount" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JList")
@XmlSeeAlso({
    JListBind.class
})
public class JList
    extends JComponent
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "UI")
    protected String ui;
    @XmlAttribute(name = "cellRenderer")
    protected String cellRenderer;
    @XmlAttribute(name = "dragEnabled")
    protected Boolean dragEnabled;
    @XmlAttribute(name = "dropMode")
    protected String dropMode;
    @XmlAttribute(name = "fixedCellHeight")
    protected String fixedCellHeight;
    @XmlAttribute(name = "fixedCellWidth")
    protected String fixedCellWidth;
    @XmlAttribute(name = "layoutOrientation")
    protected String layoutOrientation;
    @XmlAttribute(name = "listData")
    protected String listData;
    @XmlAttribute(name = "model")
    protected String model;
    @XmlAttribute(name = "prototypeCellValue")
    protected String prototypeCellValue;
    @XmlAttribute(name = "selectedIndex")
    protected String selectedIndex;
    @XmlAttribute(name = "selectedIndices")
    protected String selectedIndices;
    @XmlAttribute(name = "selectionBackground")
    protected String selectionBackground;
    @XmlAttribute(name = "selectionForeground")
    protected String selectionForeground;
    @XmlAttribute(name = "selectionMode")
    protected String selectionMode;
    @XmlAttribute(name = "selectionModel")
    protected String selectionModel;
    @XmlAttribute(name = "valueIsAdjusting")
    protected Boolean valueIsAdjusting;
    @XmlAttribute(name = "visibleRowCount")
    protected String visibleRowCount;

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
     * Gets the value of the cellRenderer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellRenderer() {
        return cellRenderer;
    }

    /**
     * Sets the value of the cellRenderer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellRenderer(String value) {
        this.cellRenderer = value;
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
     * Gets the value of the fixedCellHeight property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixedCellHeight() {
        return fixedCellHeight;
    }

    /**
     * Sets the value of the fixedCellHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixedCellHeight(String value) {
        this.fixedCellHeight = value;
    }

    /**
     * Gets the value of the fixedCellWidth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixedCellWidth() {
        return fixedCellWidth;
    }

    /**
     * Sets the value of the fixedCellWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixedCellWidth(String value) {
        this.fixedCellWidth = value;
    }

    /**
     * Gets the value of the layoutOrientation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLayoutOrientation() {
        return layoutOrientation;
    }

    /**
     * Sets the value of the layoutOrientation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLayoutOrientation(String value) {
        this.layoutOrientation = value;
    }

    /**
     * Gets the value of the listData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListData() {
        return listData;
    }

    /**
     * Sets the value of the listData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListData(String value) {
        this.listData = value;
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
     * Gets the value of the prototypeCellValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrototypeCellValue() {
        return prototypeCellValue;
    }

    /**
     * Sets the value of the prototypeCellValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrototypeCellValue(String value) {
        this.prototypeCellValue = value;
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
     * Gets the value of the selectedIndices property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedIndices() {
        return selectedIndices;
    }

    /**
     * Sets the value of the selectedIndices property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedIndices(String value) {
        this.selectedIndices = value;
    }

    /**
     * Gets the value of the selectionBackground property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectionBackground() {
        return selectionBackground;
    }

    /**
     * Sets the value of the selectionBackground property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectionBackground(String value) {
        this.selectionBackground = value;
    }

    /**
     * Gets the value of the selectionForeground property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectionForeground() {
        return selectionForeground;
    }

    /**
     * Sets the value of the selectionForeground property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectionForeground(String value) {
        this.selectionForeground = value;
    }

    /**
     * Gets the value of the selectionMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectionMode() {
        return selectionMode;
    }

    /**
     * Sets the value of the selectionMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectionMode(String value) {
        this.selectionMode = value;
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

    /**
     * Gets the value of the valueIsAdjusting property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isValueIsAdjusting() {
        return valueIsAdjusting;
    }

    /**
     * Sets the value of the valueIsAdjusting property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setValueIsAdjusting(Boolean value) {
        this.valueIsAdjusting = value;
    }

    /**
     * Gets the value of the visibleRowCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisibleRowCount() {
        return visibleRowCount;
    }

    /**
     * Sets the value of the visibleRowCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisibleRowCount(String value) {
        this.visibleRowCount = value;
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
            boolean theFieldIsSet = (this.cellRenderer!= null);
            String theField;
            theField = this.getCellRenderer();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "cellRenderer", theField);
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
            boolean theFieldIsSet = (this.fixedCellHeight!= null);
            String theField;
            theField = this.getFixedCellHeight();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "fixedCellHeight", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.fixedCellWidth!= null);
            String theField;
            theField = this.getFixedCellWidth();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "fixedCellWidth", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.layoutOrientation!= null);
            String theField;
            theField = this.getLayoutOrientation();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "layoutOrientation", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.listData!= null);
            String theField;
            theField = this.getListData();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "listData", theField);
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
            boolean theFieldIsSet = (this.prototypeCellValue!= null);
            String theField;
            theField = this.getPrototypeCellValue();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "prototypeCellValue", theField);
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
            boolean theFieldIsSet = (this.selectedIndices!= null);
            String theField;
            theField = this.getSelectedIndices();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectedIndices", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionBackground!= null);
            String theField;
            theField = this.getSelectionBackground();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectionBackground", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionForeground!= null);
            String theField;
            theField = this.getSelectionForeground();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectionForeground", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionMode!= null);
            String theField;
            theField = this.getSelectionMode();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectionMode", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionModel!= null);
            String theField;
            theField = this.getSelectionModel();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectionModel", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.valueIsAdjusting!= null);
            Boolean theField;
            theField = this.isValueIsAdjusting();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "valueIsAdjusting", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.visibleRowCount!= null);
            String theField;
            theField = this.getVisibleRowCount();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "visibleRowCount", theField);
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
        final JList that = ((JList) object);
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
            boolean lhsFieldIsSet = (this.cellRenderer!= null);
            boolean rhsFieldIsSet = (that.cellRenderer!= null);
            String lhsField;
            lhsField = this.getCellRenderer();
            String rhsField;
            rhsField = that.getCellRenderer();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "cellRenderer", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "cellRenderer", rhsField);
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
            boolean lhsFieldIsSet = (this.fixedCellHeight!= null);
            boolean rhsFieldIsSet = (that.fixedCellHeight!= null);
            String lhsField;
            lhsField = this.getFixedCellHeight();
            String rhsField;
            rhsField = that.getFixedCellHeight();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "fixedCellHeight", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "fixedCellHeight", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.fixedCellWidth!= null);
            boolean rhsFieldIsSet = (that.fixedCellWidth!= null);
            String lhsField;
            lhsField = this.getFixedCellWidth();
            String rhsField;
            rhsField = that.getFixedCellWidth();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "fixedCellWidth", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "fixedCellWidth", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.layoutOrientation!= null);
            boolean rhsFieldIsSet = (that.layoutOrientation!= null);
            String lhsField;
            lhsField = this.getLayoutOrientation();
            String rhsField;
            rhsField = that.getLayoutOrientation();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "layoutOrientation", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "layoutOrientation", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.listData!= null);
            boolean rhsFieldIsSet = (that.listData!= null);
            String lhsField;
            lhsField = this.getListData();
            String rhsField;
            rhsField = that.getListData();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "listData", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "listData", rhsField);
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
            boolean lhsFieldIsSet = (this.prototypeCellValue!= null);
            boolean rhsFieldIsSet = (that.prototypeCellValue!= null);
            String lhsField;
            lhsField = this.getPrototypeCellValue();
            String rhsField;
            rhsField = that.getPrototypeCellValue();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "prototypeCellValue", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "prototypeCellValue", rhsField);
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
            boolean lhsFieldIsSet = (this.selectedIndices!= null);
            boolean rhsFieldIsSet = (that.selectedIndices!= null);
            String lhsField;
            lhsField = this.getSelectedIndices();
            String rhsField;
            rhsField = that.getSelectedIndices();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectedIndices", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectedIndices", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selectionBackground!= null);
            boolean rhsFieldIsSet = (that.selectionBackground!= null);
            String lhsField;
            lhsField = this.getSelectionBackground();
            String rhsField;
            rhsField = that.getSelectionBackground();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectionBackground", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectionBackground", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selectionForeground!= null);
            boolean rhsFieldIsSet = (that.selectionForeground!= null);
            String lhsField;
            lhsField = this.getSelectionForeground();
            String rhsField;
            rhsField = that.getSelectionForeground();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectionForeground", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectionForeground", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selectionMode!= null);
            boolean rhsFieldIsSet = (that.selectionMode!= null);
            String lhsField;
            lhsField = this.getSelectionMode();
            String rhsField;
            rhsField = that.getSelectionMode();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectionMode", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectionMode", rhsField);
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
        {
            boolean lhsFieldIsSet = (this.valueIsAdjusting!= null);
            boolean rhsFieldIsSet = (that.valueIsAdjusting!= null);
            Boolean lhsField;
            lhsField = this.isValueIsAdjusting();
            Boolean rhsField;
            rhsField = that.isValueIsAdjusting();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "valueIsAdjusting", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "valueIsAdjusting", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.visibleRowCount!= null);
            boolean rhsFieldIsSet = (that.visibleRowCount!= null);
            String lhsField;
            lhsField = this.getVisibleRowCount();
            String rhsField;
            rhsField = that.getVisibleRowCount();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "visibleRowCount", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "visibleRowCount", rhsField);
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
            boolean theFieldIsSet = (this.cellRenderer!= null);
            String theField;
            theField = this.getCellRenderer();
            strategy.appendField(locator, this, "cellRenderer", buffer, theField, theFieldIsSet);
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
            boolean theFieldIsSet = (this.fixedCellHeight!= null);
            String theField;
            theField = this.getFixedCellHeight();
            strategy.appendField(locator, this, "fixedCellHeight", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.fixedCellWidth!= null);
            String theField;
            theField = this.getFixedCellWidth();
            strategy.appendField(locator, this, "fixedCellWidth", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.layoutOrientation!= null);
            String theField;
            theField = this.getLayoutOrientation();
            strategy.appendField(locator, this, "layoutOrientation", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.listData!= null);
            String theField;
            theField = this.getListData();
            strategy.appendField(locator, this, "listData", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.model!= null);
            String theField;
            theField = this.getModel();
            strategy.appendField(locator, this, "model", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.prototypeCellValue!= null);
            String theField;
            theField = this.getPrototypeCellValue();
            strategy.appendField(locator, this, "prototypeCellValue", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectedIndex!= null);
            String theField;
            theField = this.getSelectedIndex();
            strategy.appendField(locator, this, "selectedIndex", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectedIndices!= null);
            String theField;
            theField = this.getSelectedIndices();
            strategy.appendField(locator, this, "selectedIndices", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionBackground!= null);
            String theField;
            theField = this.getSelectionBackground();
            strategy.appendField(locator, this, "selectionBackground", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionForeground!= null);
            String theField;
            theField = this.getSelectionForeground();
            strategy.appendField(locator, this, "selectionForeground", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionMode!= null);
            String theField;
            theField = this.getSelectionMode();
            strategy.appendField(locator, this, "selectionMode", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionModel!= null);
            String theField;
            theField = this.getSelectionModel();
            strategy.appendField(locator, this, "selectionModel", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.valueIsAdjusting!= null);
            Boolean theField;
            theField = this.isValueIsAdjusting();
            strategy.appendField(locator, this, "valueIsAdjusting", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.visibleRowCount!= null);
            String theField;
            theField = this.getVisibleRowCount();
            strategy.appendField(locator, this, "visibleRowCount", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
