
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
 * <p>Java class for JTree complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JTree">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/swixml}JComponent">
 *       <sequence>
 *       </sequence>
 *       <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="anchorSelectionPath" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="cellEditor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="cellRenderer" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="dragEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="dropMode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="expandsSelectedPaths" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="invokesStopCellEditing" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="largeModel" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="leadSelectionPath" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="model" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="rootVisible" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="rowHeight" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="scrollsOnExpand" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="selectionModel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionPath" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionPaths" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionRow" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionRows" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="showsRootHandles" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="toggleClickCount" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="visibleRowCount" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JTree")
@XmlSeeAlso({
    JTreeBind.class
})
public class JTree
    extends JComponent
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "UI")
    protected String ui;
    @XmlAttribute(name = "anchorSelectionPath")
    protected String anchorSelectionPath;
    @XmlAttribute(name = "cellEditor")
    protected String cellEditor;
    @XmlAttribute(name = "cellRenderer")
    protected String cellRenderer;
    @XmlAttribute(name = "dragEnabled")
    protected Boolean dragEnabled;
    @XmlAttribute(name = "dropMode")
    protected String dropMode;
    @XmlAttribute(name = "editable")
    protected Boolean editable;
    @XmlAttribute(name = "expandsSelectedPaths")
    protected Boolean expandsSelectedPaths;
    @XmlAttribute(name = "invokesStopCellEditing")
    protected Boolean invokesStopCellEditing;
    @XmlAttribute(name = "largeModel")
    protected Boolean largeModel;
    @XmlAttribute(name = "leadSelectionPath")
    protected String leadSelectionPath;
    @XmlAttribute(name = "model")
    protected String model;
    @XmlAttribute(name = "rootVisible")
    protected Boolean rootVisible;
    @XmlAttribute(name = "rowHeight")
    protected String rowHeight;
    @XmlAttribute(name = "scrollsOnExpand")
    protected Boolean scrollsOnExpand;
    @XmlAttribute(name = "selectionModel")
    protected String selectionModel;
    @XmlAttribute(name = "selectionPath")
    protected String selectionPath;
    @XmlAttribute(name = "selectionPaths")
    protected String selectionPaths;
    @XmlAttribute(name = "selectionRow")
    protected String selectionRow;
    @XmlAttribute(name = "selectionRows")
    protected String selectionRows;
    @XmlAttribute(name = "showsRootHandles")
    protected Boolean showsRootHandles;
    @XmlAttribute(name = "toggleClickCount")
    protected String toggleClickCount;
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
     * Gets the value of the anchorSelectionPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnchorSelectionPath() {
        return anchorSelectionPath;
    }

    /**
     * Sets the value of the anchorSelectionPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnchorSelectionPath(String value) {
        this.anchorSelectionPath = value;
    }

    /**
     * Gets the value of the cellEditor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellEditor() {
        return cellEditor;
    }

    /**
     * Sets the value of the cellEditor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellEditor(String value) {
        this.cellEditor = value;
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
     * Gets the value of the expandsSelectedPaths property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExpandsSelectedPaths() {
        return expandsSelectedPaths;
    }

    /**
     * Sets the value of the expandsSelectedPaths property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExpandsSelectedPaths(Boolean value) {
        this.expandsSelectedPaths = value;
    }

    /**
     * Gets the value of the invokesStopCellEditing property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInvokesStopCellEditing() {
        return invokesStopCellEditing;
    }

    /**
     * Sets the value of the invokesStopCellEditing property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInvokesStopCellEditing(Boolean value) {
        this.invokesStopCellEditing = value;
    }

    /**
     * Gets the value of the largeModel property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLargeModel() {
        return largeModel;
    }

    /**
     * Sets the value of the largeModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLargeModel(Boolean value) {
        this.largeModel = value;
    }

    /**
     * Gets the value of the leadSelectionPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeadSelectionPath() {
        return leadSelectionPath;
    }

    /**
     * Sets the value of the leadSelectionPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeadSelectionPath(String value) {
        this.leadSelectionPath = value;
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
     * Gets the value of the rootVisible property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRootVisible() {
        return rootVisible;
    }

    /**
     * Sets the value of the rootVisible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRootVisible(Boolean value) {
        this.rootVisible = value;
    }

    /**
     * Gets the value of the rowHeight property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRowHeight() {
        return rowHeight;
    }

    /**
     * Sets the value of the rowHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRowHeight(String value) {
        this.rowHeight = value;
    }

    /**
     * Gets the value of the scrollsOnExpand property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isScrollsOnExpand() {
        return scrollsOnExpand;
    }

    /**
     * Sets the value of the scrollsOnExpand property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setScrollsOnExpand(Boolean value) {
        this.scrollsOnExpand = value;
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
     * Gets the value of the selectionPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectionPath() {
        return selectionPath;
    }

    /**
     * Sets the value of the selectionPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectionPath(String value) {
        this.selectionPath = value;
    }

    /**
     * Gets the value of the selectionPaths property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectionPaths() {
        return selectionPaths;
    }

    /**
     * Sets the value of the selectionPaths property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectionPaths(String value) {
        this.selectionPaths = value;
    }

    /**
     * Gets the value of the selectionRow property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectionRow() {
        return selectionRow;
    }

    /**
     * Sets the value of the selectionRow property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectionRow(String value) {
        this.selectionRow = value;
    }

    /**
     * Gets the value of the selectionRows property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectionRows() {
        return selectionRows;
    }

    /**
     * Sets the value of the selectionRows property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectionRows(String value) {
        this.selectionRows = value;
    }

    /**
     * Gets the value of the showsRootHandles property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowsRootHandles() {
        return showsRootHandles;
    }

    /**
     * Sets the value of the showsRootHandles property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowsRootHandles(Boolean value) {
        this.showsRootHandles = value;
    }

    /**
     * Gets the value of the toggleClickCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToggleClickCount() {
        return toggleClickCount;
    }

    /**
     * Sets the value of the toggleClickCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToggleClickCount(String value) {
        this.toggleClickCount = value;
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
            boolean theFieldIsSet = (this.anchorSelectionPath!= null);
            String theField;
            theField = this.getAnchorSelectionPath();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "anchorSelectionPath", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.cellEditor!= null);
            String theField;
            theField = this.getCellEditor();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "cellEditor", theField);
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
            boolean theFieldIsSet = (this.editable!= null);
            Boolean theField;
            theField = this.isEditable();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "editable", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.expandsSelectedPaths!= null);
            Boolean theField;
            theField = this.isExpandsSelectedPaths();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "expandsSelectedPaths", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.invokesStopCellEditing!= null);
            Boolean theField;
            theField = this.isInvokesStopCellEditing();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "invokesStopCellEditing", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.largeModel!= null);
            Boolean theField;
            theField = this.isLargeModel();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "largeModel", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.leadSelectionPath!= null);
            String theField;
            theField = this.getLeadSelectionPath();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "leadSelectionPath", theField);
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
            boolean theFieldIsSet = (this.rootVisible!= null);
            Boolean theField;
            theField = this.isRootVisible();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "rootVisible", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rowHeight!= null);
            String theField;
            theField = this.getRowHeight();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "rowHeight", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.scrollsOnExpand!= null);
            Boolean theField;
            theField = this.isScrollsOnExpand();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "scrollsOnExpand", theField);
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
            boolean theFieldIsSet = (this.selectionPath!= null);
            String theField;
            theField = this.getSelectionPath();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectionPath", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionPaths!= null);
            String theField;
            theField = this.getSelectionPaths();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectionPaths", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionRow!= null);
            String theField;
            theField = this.getSelectionRow();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectionRow", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionRows!= null);
            String theField;
            theField = this.getSelectionRows();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selectionRows", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.showsRootHandles!= null);
            Boolean theField;
            theField = this.isShowsRootHandles();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "showsRootHandles", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.toggleClickCount!= null);
            String theField;
            theField = this.getToggleClickCount();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "toggleClickCount", theField);
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
        final JTree that = ((JTree) object);
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
            boolean lhsFieldIsSet = (this.anchorSelectionPath!= null);
            boolean rhsFieldIsSet = (that.anchorSelectionPath!= null);
            String lhsField;
            lhsField = this.getAnchorSelectionPath();
            String rhsField;
            rhsField = that.getAnchorSelectionPath();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "anchorSelectionPath", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "anchorSelectionPath", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.cellEditor!= null);
            boolean rhsFieldIsSet = (that.cellEditor!= null);
            String lhsField;
            lhsField = this.getCellEditor();
            String rhsField;
            rhsField = that.getCellEditor();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "cellEditor", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "cellEditor", rhsField);
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
            boolean lhsFieldIsSet = (this.expandsSelectedPaths!= null);
            boolean rhsFieldIsSet = (that.expandsSelectedPaths!= null);
            Boolean lhsField;
            lhsField = this.isExpandsSelectedPaths();
            Boolean rhsField;
            rhsField = that.isExpandsSelectedPaths();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "expandsSelectedPaths", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "expandsSelectedPaths", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.invokesStopCellEditing!= null);
            boolean rhsFieldIsSet = (that.invokesStopCellEditing!= null);
            Boolean lhsField;
            lhsField = this.isInvokesStopCellEditing();
            Boolean rhsField;
            rhsField = that.isInvokesStopCellEditing();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "invokesStopCellEditing", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "invokesStopCellEditing", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.largeModel!= null);
            boolean rhsFieldIsSet = (that.largeModel!= null);
            Boolean lhsField;
            lhsField = this.isLargeModel();
            Boolean rhsField;
            rhsField = that.isLargeModel();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "largeModel", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "largeModel", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.leadSelectionPath!= null);
            boolean rhsFieldIsSet = (that.leadSelectionPath!= null);
            String lhsField;
            lhsField = this.getLeadSelectionPath();
            String rhsField;
            rhsField = that.getLeadSelectionPath();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "leadSelectionPath", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "leadSelectionPath", rhsField);
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
            boolean lhsFieldIsSet = (this.rootVisible!= null);
            boolean rhsFieldIsSet = (that.rootVisible!= null);
            Boolean lhsField;
            lhsField = this.isRootVisible();
            Boolean rhsField;
            rhsField = that.isRootVisible();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "rootVisible", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "rootVisible", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.rowHeight!= null);
            boolean rhsFieldIsSet = (that.rowHeight!= null);
            String lhsField;
            lhsField = this.getRowHeight();
            String rhsField;
            rhsField = that.getRowHeight();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "rowHeight", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "rowHeight", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.scrollsOnExpand!= null);
            boolean rhsFieldIsSet = (that.scrollsOnExpand!= null);
            Boolean lhsField;
            lhsField = this.isScrollsOnExpand();
            Boolean rhsField;
            rhsField = that.isScrollsOnExpand();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "scrollsOnExpand", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "scrollsOnExpand", rhsField);
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
            boolean lhsFieldIsSet = (this.selectionPath!= null);
            boolean rhsFieldIsSet = (that.selectionPath!= null);
            String lhsField;
            lhsField = this.getSelectionPath();
            String rhsField;
            rhsField = that.getSelectionPath();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectionPath", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectionPath", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selectionPaths!= null);
            boolean rhsFieldIsSet = (that.selectionPaths!= null);
            String lhsField;
            lhsField = this.getSelectionPaths();
            String rhsField;
            rhsField = that.getSelectionPaths();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectionPaths", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectionPaths", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selectionRow!= null);
            boolean rhsFieldIsSet = (that.selectionRow!= null);
            String lhsField;
            lhsField = this.getSelectionRow();
            String rhsField;
            rhsField = that.getSelectionRow();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectionRow", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectionRow", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.selectionRows!= null);
            boolean rhsFieldIsSet = (that.selectionRows!= null);
            String lhsField;
            lhsField = this.getSelectionRows();
            String rhsField;
            rhsField = that.getSelectionRows();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selectionRows", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selectionRows", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.showsRootHandles!= null);
            boolean rhsFieldIsSet = (that.showsRootHandles!= null);
            Boolean lhsField;
            lhsField = this.isShowsRootHandles();
            Boolean rhsField;
            rhsField = that.isShowsRootHandles();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "showsRootHandles", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "showsRootHandles", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.toggleClickCount!= null);
            boolean rhsFieldIsSet = (that.toggleClickCount!= null);
            String lhsField;
            lhsField = this.getToggleClickCount();
            String rhsField;
            rhsField = that.getToggleClickCount();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "toggleClickCount", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "toggleClickCount", rhsField);
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
            boolean theFieldIsSet = (this.anchorSelectionPath!= null);
            String theField;
            theField = this.getAnchorSelectionPath();
            strategy.appendField(locator, this, "anchorSelectionPath", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.cellEditor!= null);
            String theField;
            theField = this.getCellEditor();
            strategy.appendField(locator, this, "cellEditor", buffer, theField, theFieldIsSet);
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
            boolean theFieldIsSet = (this.editable!= null);
            Boolean theField;
            theField = this.isEditable();
            strategy.appendField(locator, this, "editable", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.expandsSelectedPaths!= null);
            Boolean theField;
            theField = this.isExpandsSelectedPaths();
            strategy.appendField(locator, this, "expandsSelectedPaths", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.invokesStopCellEditing!= null);
            Boolean theField;
            theField = this.isInvokesStopCellEditing();
            strategy.appendField(locator, this, "invokesStopCellEditing", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.largeModel!= null);
            Boolean theField;
            theField = this.isLargeModel();
            strategy.appendField(locator, this, "largeModel", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.leadSelectionPath!= null);
            String theField;
            theField = this.getLeadSelectionPath();
            strategy.appendField(locator, this, "leadSelectionPath", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.model!= null);
            String theField;
            theField = this.getModel();
            strategy.appendField(locator, this, "model", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rootVisible!= null);
            Boolean theField;
            theField = this.isRootVisible();
            strategy.appendField(locator, this, "rootVisible", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rowHeight!= null);
            String theField;
            theField = this.getRowHeight();
            strategy.appendField(locator, this, "rowHeight", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.scrollsOnExpand!= null);
            Boolean theField;
            theField = this.isScrollsOnExpand();
            strategy.appendField(locator, this, "scrollsOnExpand", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionModel!= null);
            String theField;
            theField = this.getSelectionModel();
            strategy.appendField(locator, this, "selectionModel", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionPath!= null);
            String theField;
            theField = this.getSelectionPath();
            strategy.appendField(locator, this, "selectionPath", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionPaths!= null);
            String theField;
            theField = this.getSelectionPaths();
            strategy.appendField(locator, this, "selectionPaths", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionRow!= null);
            String theField;
            theField = this.getSelectionRow();
            strategy.appendField(locator, this, "selectionRow", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selectionRows!= null);
            String theField;
            theField = this.getSelectionRows();
            strategy.appendField(locator, this, "selectionRows", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.showsRootHandles!= null);
            Boolean theField;
            theField = this.isShowsRootHandles();
            strategy.appendField(locator, this, "showsRootHandles", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.toggleClickCount!= null);
            String theField;
            theField = this.getToggleClickCount();
            strategy.appendField(locator, this, "toggleClickCount", buffer, theField, theFieldIsSet);
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
