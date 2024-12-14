
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
 * <p>Java class for JTable complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JTable">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/swixml}JComponent">
 *       <sequence>
 *       </sequence>
 *       <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="autoCreateColumnsFromModel" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="autoCreateRowSorter" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="autoResizeMode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="cellEditor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="cellSelectionEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="columnModel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="columnSelectionAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="dragEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="dropMode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="editingColumn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="editingRow" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="fillsViewportHeight" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="gridColor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="intercellSpacing" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="model" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="preferredScrollableViewportSize" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="rowHeight" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="rowMargin" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="rowSelectionAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="rowSorter" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionBackground" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionForeground" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionMode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="selectionModel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="showGrid" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="showHorizontalLines" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="showVerticalLines" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="surrendersFocusOnKeystroke" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="tableHeader" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="updateSelectionOnSort" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JTable")
@XmlSeeAlso({
    JTableBind.class
})
public class JTable
    extends JComponent
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "UI")
    protected String ui;
    @XmlAttribute(name = "autoCreateColumnsFromModel")
    protected Boolean autoCreateColumnsFromModel;
    @XmlAttribute(name = "autoCreateRowSorter")
    protected Boolean autoCreateRowSorter;
    @XmlAttribute(name = "autoResizeMode")
    protected String autoResizeMode;
    @XmlAttribute(name = "cellEditor")
    protected String cellEditor;
    @XmlAttribute(name = "cellSelectionEnabled")
    protected Boolean cellSelectionEnabled;
    @XmlAttribute(name = "columnModel")
    protected String columnModel;
    @XmlAttribute(name = "columnSelectionAllowed")
    protected Boolean columnSelectionAllowed;
    @XmlAttribute(name = "dragEnabled")
    protected Boolean dragEnabled;
    @XmlAttribute(name = "dropMode")
    protected String dropMode;
    @XmlAttribute(name = "editingColumn")
    protected String editingColumn;
    @XmlAttribute(name = "editingRow")
    protected String editingRow;
    @XmlAttribute(name = "fillsViewportHeight")
    protected Boolean fillsViewportHeight;
    @XmlAttribute(name = "gridColor")
    protected String gridColor;
    @XmlAttribute(name = "intercellSpacing")
    protected String intercellSpacing;
    @XmlAttribute(name = "model")
    protected String model;
    @XmlAttribute(name = "preferredScrollableViewportSize")
    protected String preferredScrollableViewportSize;
    @XmlAttribute(name = "rowHeight")
    protected String rowHeight;
    @XmlAttribute(name = "rowMargin")
    protected String rowMargin;
    @XmlAttribute(name = "rowSelectionAllowed")
    protected Boolean rowSelectionAllowed;
    @XmlAttribute(name = "rowSorter")
    protected String rowSorter;
    @XmlAttribute(name = "selectionBackground")
    protected String selectionBackground;
    @XmlAttribute(name = "selectionForeground")
    protected String selectionForeground;
    @XmlAttribute(name = "selectionMode")
    protected String selectionMode;
    @XmlAttribute(name = "selectionModel")
    protected String selectionModel;
    @XmlAttribute(name = "showGrid")
    protected Boolean showGrid;
    @XmlAttribute(name = "showHorizontalLines")
    protected Boolean showHorizontalLines;
    @XmlAttribute(name = "showVerticalLines")
    protected Boolean showVerticalLines;
    @XmlAttribute(name = "surrendersFocusOnKeystroke")
    protected Boolean surrendersFocusOnKeystroke;
    @XmlAttribute(name = "tableHeader")
    protected String tableHeader;
    @XmlAttribute(name = "updateSelectionOnSort")
    protected Boolean updateSelectionOnSort;

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
     * Gets the value of the autoCreateColumnsFromModel property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoCreateColumnsFromModel() {
        return autoCreateColumnsFromModel;
    }

    /**
     * Sets the value of the autoCreateColumnsFromModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoCreateColumnsFromModel(Boolean value) {
        this.autoCreateColumnsFromModel = value;
    }

    /**
     * Gets the value of the autoCreateRowSorter property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoCreateRowSorter() {
        return autoCreateRowSorter;
    }

    /**
     * Sets the value of the autoCreateRowSorter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoCreateRowSorter(Boolean value) {
        this.autoCreateRowSorter = value;
    }

    /**
     * Gets the value of the autoResizeMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoResizeMode() {
        return autoResizeMode;
    }

    /**
     * Sets the value of the autoResizeMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoResizeMode(String value) {
        this.autoResizeMode = value;
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
     * Gets the value of the cellSelectionEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCellSelectionEnabled() {
        return cellSelectionEnabled;
    }

    /**
     * Sets the value of the cellSelectionEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCellSelectionEnabled(Boolean value) {
        this.cellSelectionEnabled = value;
    }

    /**
     * Gets the value of the columnModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumnModel() {
        return columnModel;
    }

    /**
     * Sets the value of the columnModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumnModel(String value) {
        this.columnModel = value;
    }

    /**
     * Gets the value of the columnSelectionAllowed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isColumnSelectionAllowed() {
        return columnSelectionAllowed;
    }

    /**
     * Sets the value of the columnSelectionAllowed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setColumnSelectionAllowed(Boolean value) {
        this.columnSelectionAllowed = value;
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
     * Gets the value of the editingColumn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEditingColumn() {
        return editingColumn;
    }

    /**
     * Sets the value of the editingColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEditingColumn(String value) {
        this.editingColumn = value;
    }

    /**
     * Gets the value of the editingRow property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEditingRow() {
        return editingRow;
    }

    /**
     * Sets the value of the editingRow property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEditingRow(String value) {
        this.editingRow = value;
    }

    /**
     * Gets the value of the fillsViewportHeight property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFillsViewportHeight() {
        return fillsViewportHeight;
    }

    /**
     * Sets the value of the fillsViewportHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFillsViewportHeight(Boolean value) {
        this.fillsViewportHeight = value;
    }

    /**
     * Gets the value of the gridColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGridColor() {
        return gridColor;
    }

    /**
     * Sets the value of the gridColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGridColor(String value) {
        this.gridColor = value;
    }

    /**
     * Gets the value of the intercellSpacing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntercellSpacing() {
        return intercellSpacing;
    }

    /**
     * Sets the value of the intercellSpacing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntercellSpacing(String value) {
        this.intercellSpacing = value;
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
     * Gets the value of the preferredScrollableViewportSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredScrollableViewportSize() {
        return preferredScrollableViewportSize;
    }

    /**
     * Sets the value of the preferredScrollableViewportSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredScrollableViewportSize(String value) {
        this.preferredScrollableViewportSize = value;
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
     * Gets the value of the rowMargin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRowMargin() {
        return rowMargin;
    }

    /**
     * Sets the value of the rowMargin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRowMargin(String value) {
        this.rowMargin = value;
    }

    /**
     * Gets the value of the rowSelectionAllowed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRowSelectionAllowed() {
        return rowSelectionAllowed;
    }

    /**
     * Sets the value of the rowSelectionAllowed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRowSelectionAllowed(Boolean value) {
        this.rowSelectionAllowed = value;
    }

    /**
     * Gets the value of the rowSorter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRowSorter() {
        return rowSorter;
    }

    /**
     * Sets the value of the rowSorter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRowSorter(String value) {
        this.rowSorter = value;
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
     * Gets the value of the showGrid property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowGrid() {
        return showGrid;
    }

    /**
     * Sets the value of the showGrid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowGrid(Boolean value) {
        this.showGrid = value;
    }

    /**
     * Gets the value of the showHorizontalLines property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowHorizontalLines() {
        return showHorizontalLines;
    }

    /**
     * Sets the value of the showHorizontalLines property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowHorizontalLines(Boolean value) {
        this.showHorizontalLines = value;
    }

    /**
     * Gets the value of the showVerticalLines property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowVerticalLines() {
        return showVerticalLines;
    }

    /**
     * Sets the value of the showVerticalLines property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowVerticalLines(Boolean value) {
        this.showVerticalLines = value;
    }

    /**
     * Gets the value of the surrendersFocusOnKeystroke property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSurrendersFocusOnKeystroke() {
        return surrendersFocusOnKeystroke;
    }

    /**
     * Sets the value of the surrendersFocusOnKeystroke property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSurrendersFocusOnKeystroke(Boolean value) {
        this.surrendersFocusOnKeystroke = value;
    }

    /**
     * Gets the value of the tableHeader property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTableHeader() {
        return tableHeader;
    }

    /**
     * Sets the value of the tableHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTableHeader(String value) {
        this.tableHeader = value;
    }

    /**
     * Gets the value of the updateSelectionOnSort property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUpdateSelectionOnSort() {
        return updateSelectionOnSort;
    }

    /**
     * Sets the value of the updateSelectionOnSort property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUpdateSelectionOnSort(Boolean value) {
        this.updateSelectionOnSort = value;
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
            boolean theFieldIsSet = (this.autoCreateColumnsFromModel!= null);
            Boolean theField;
            theField = this.isAutoCreateColumnsFromModel();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "autoCreateColumnsFromModel", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.autoCreateRowSorter!= null);
            Boolean theField;
            theField = this.isAutoCreateRowSorter();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "autoCreateRowSorter", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.autoResizeMode!= null);
            String theField;
            theField = this.getAutoResizeMode();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "autoResizeMode", theField);
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
            boolean theFieldIsSet = (this.cellSelectionEnabled!= null);
            Boolean theField;
            theField = this.isCellSelectionEnabled();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "cellSelectionEnabled", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.columnModel!= null);
            String theField;
            theField = this.getColumnModel();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "columnModel", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.columnSelectionAllowed!= null);
            Boolean theField;
            theField = this.isColumnSelectionAllowed();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "columnSelectionAllowed", theField);
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
            boolean theFieldIsSet = (this.editingColumn!= null);
            String theField;
            theField = this.getEditingColumn();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "editingColumn", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.editingRow!= null);
            String theField;
            theField = this.getEditingRow();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "editingRow", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.fillsViewportHeight!= null);
            Boolean theField;
            theField = this.isFillsViewportHeight();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "fillsViewportHeight", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.gridColor!= null);
            String theField;
            theField = this.getGridColor();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "gridColor", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.intercellSpacing!= null);
            String theField;
            theField = this.getIntercellSpacing();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "intercellSpacing", theField);
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
            boolean theFieldIsSet = (this.preferredScrollableViewportSize!= null);
            String theField;
            theField = this.getPreferredScrollableViewportSize();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "preferredScrollableViewportSize", theField);
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
            boolean theFieldIsSet = (this.rowMargin!= null);
            String theField;
            theField = this.getRowMargin();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "rowMargin", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rowSelectionAllowed!= null);
            Boolean theField;
            theField = this.isRowSelectionAllowed();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "rowSelectionAllowed", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rowSorter!= null);
            String theField;
            theField = this.getRowSorter();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "rowSorter", theField);
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
            boolean theFieldIsSet = (this.showGrid!= null);
            Boolean theField;
            theField = this.isShowGrid();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "showGrid", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.showHorizontalLines!= null);
            Boolean theField;
            theField = this.isShowHorizontalLines();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "showHorizontalLines", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.showVerticalLines!= null);
            Boolean theField;
            theField = this.isShowVerticalLines();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "showVerticalLines", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.surrendersFocusOnKeystroke!= null);
            Boolean theField;
            theField = this.isSurrendersFocusOnKeystroke();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "surrendersFocusOnKeystroke", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.tableHeader!= null);
            String theField;
            theField = this.getTableHeader();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "tableHeader", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.updateSelectionOnSort!= null);
            Boolean theField;
            theField = this.isUpdateSelectionOnSort();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "updateSelectionOnSort", theField);
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
        final JTable that = ((JTable) object);
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
            boolean lhsFieldIsSet = (this.autoCreateColumnsFromModel!= null);
            boolean rhsFieldIsSet = (that.autoCreateColumnsFromModel!= null);
            Boolean lhsField;
            lhsField = this.isAutoCreateColumnsFromModel();
            Boolean rhsField;
            rhsField = that.isAutoCreateColumnsFromModel();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "autoCreateColumnsFromModel", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "autoCreateColumnsFromModel", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.autoCreateRowSorter!= null);
            boolean rhsFieldIsSet = (that.autoCreateRowSorter!= null);
            Boolean lhsField;
            lhsField = this.isAutoCreateRowSorter();
            Boolean rhsField;
            rhsField = that.isAutoCreateRowSorter();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "autoCreateRowSorter", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "autoCreateRowSorter", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.autoResizeMode!= null);
            boolean rhsFieldIsSet = (that.autoResizeMode!= null);
            String lhsField;
            lhsField = this.getAutoResizeMode();
            String rhsField;
            rhsField = that.getAutoResizeMode();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "autoResizeMode", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "autoResizeMode", rhsField);
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
            boolean lhsFieldIsSet = (this.cellSelectionEnabled!= null);
            boolean rhsFieldIsSet = (that.cellSelectionEnabled!= null);
            Boolean lhsField;
            lhsField = this.isCellSelectionEnabled();
            Boolean rhsField;
            rhsField = that.isCellSelectionEnabled();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "cellSelectionEnabled", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "cellSelectionEnabled", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.columnModel!= null);
            boolean rhsFieldIsSet = (that.columnModel!= null);
            String lhsField;
            lhsField = this.getColumnModel();
            String rhsField;
            rhsField = that.getColumnModel();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "columnModel", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "columnModel", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.columnSelectionAllowed!= null);
            boolean rhsFieldIsSet = (that.columnSelectionAllowed!= null);
            Boolean lhsField;
            lhsField = this.isColumnSelectionAllowed();
            Boolean rhsField;
            rhsField = that.isColumnSelectionAllowed();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "columnSelectionAllowed", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "columnSelectionAllowed", rhsField);
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
            boolean lhsFieldIsSet = (this.editingColumn!= null);
            boolean rhsFieldIsSet = (that.editingColumn!= null);
            String lhsField;
            lhsField = this.getEditingColumn();
            String rhsField;
            rhsField = that.getEditingColumn();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "editingColumn", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "editingColumn", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.editingRow!= null);
            boolean rhsFieldIsSet = (that.editingRow!= null);
            String lhsField;
            lhsField = this.getEditingRow();
            String rhsField;
            rhsField = that.getEditingRow();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "editingRow", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "editingRow", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.fillsViewportHeight!= null);
            boolean rhsFieldIsSet = (that.fillsViewportHeight!= null);
            Boolean lhsField;
            lhsField = this.isFillsViewportHeight();
            Boolean rhsField;
            rhsField = that.isFillsViewportHeight();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "fillsViewportHeight", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "fillsViewportHeight", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.gridColor!= null);
            boolean rhsFieldIsSet = (that.gridColor!= null);
            String lhsField;
            lhsField = this.getGridColor();
            String rhsField;
            rhsField = that.getGridColor();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "gridColor", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "gridColor", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.intercellSpacing!= null);
            boolean rhsFieldIsSet = (that.intercellSpacing!= null);
            String lhsField;
            lhsField = this.getIntercellSpacing();
            String rhsField;
            rhsField = that.getIntercellSpacing();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "intercellSpacing", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "intercellSpacing", rhsField);
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
            boolean lhsFieldIsSet = (this.preferredScrollableViewportSize!= null);
            boolean rhsFieldIsSet = (that.preferredScrollableViewportSize!= null);
            String lhsField;
            lhsField = this.getPreferredScrollableViewportSize();
            String rhsField;
            rhsField = that.getPreferredScrollableViewportSize();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "preferredScrollableViewportSize", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "preferredScrollableViewportSize", rhsField);
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
            boolean lhsFieldIsSet = (this.rowMargin!= null);
            boolean rhsFieldIsSet = (that.rowMargin!= null);
            String lhsField;
            lhsField = this.getRowMargin();
            String rhsField;
            rhsField = that.getRowMargin();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "rowMargin", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "rowMargin", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.rowSelectionAllowed!= null);
            boolean rhsFieldIsSet = (that.rowSelectionAllowed!= null);
            Boolean lhsField;
            lhsField = this.isRowSelectionAllowed();
            Boolean rhsField;
            rhsField = that.isRowSelectionAllowed();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "rowSelectionAllowed", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "rowSelectionAllowed", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.rowSorter!= null);
            boolean rhsFieldIsSet = (that.rowSorter!= null);
            String lhsField;
            lhsField = this.getRowSorter();
            String rhsField;
            rhsField = that.getRowSorter();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "rowSorter", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "rowSorter", rhsField);
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
            boolean lhsFieldIsSet = (this.showGrid!= null);
            boolean rhsFieldIsSet = (that.showGrid!= null);
            Boolean lhsField;
            lhsField = this.isShowGrid();
            Boolean rhsField;
            rhsField = that.isShowGrid();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "showGrid", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "showGrid", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.showHorizontalLines!= null);
            boolean rhsFieldIsSet = (that.showHorizontalLines!= null);
            Boolean lhsField;
            lhsField = this.isShowHorizontalLines();
            Boolean rhsField;
            rhsField = that.isShowHorizontalLines();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "showHorizontalLines", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "showHorizontalLines", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.showVerticalLines!= null);
            boolean rhsFieldIsSet = (that.showVerticalLines!= null);
            Boolean lhsField;
            lhsField = this.isShowVerticalLines();
            Boolean rhsField;
            rhsField = that.isShowVerticalLines();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "showVerticalLines", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "showVerticalLines", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.surrendersFocusOnKeystroke!= null);
            boolean rhsFieldIsSet = (that.surrendersFocusOnKeystroke!= null);
            Boolean lhsField;
            lhsField = this.isSurrendersFocusOnKeystroke();
            Boolean rhsField;
            rhsField = that.isSurrendersFocusOnKeystroke();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "surrendersFocusOnKeystroke", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "surrendersFocusOnKeystroke", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.tableHeader!= null);
            boolean rhsFieldIsSet = (that.tableHeader!= null);
            String lhsField;
            lhsField = this.getTableHeader();
            String rhsField;
            rhsField = that.getTableHeader();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "tableHeader", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "tableHeader", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.updateSelectionOnSort!= null);
            boolean rhsFieldIsSet = (that.updateSelectionOnSort!= null);
            Boolean lhsField;
            lhsField = this.isUpdateSelectionOnSort();
            Boolean rhsField;
            rhsField = that.isUpdateSelectionOnSort();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "updateSelectionOnSort", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "updateSelectionOnSort", rhsField);
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
            boolean theFieldIsSet = (this.autoCreateColumnsFromModel!= null);
            Boolean theField;
            theField = this.isAutoCreateColumnsFromModel();
            strategy.appendField(locator, this, "autoCreateColumnsFromModel", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.autoCreateRowSorter!= null);
            Boolean theField;
            theField = this.isAutoCreateRowSorter();
            strategy.appendField(locator, this, "autoCreateRowSorter", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.autoResizeMode!= null);
            String theField;
            theField = this.getAutoResizeMode();
            strategy.appendField(locator, this, "autoResizeMode", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.cellEditor!= null);
            String theField;
            theField = this.getCellEditor();
            strategy.appendField(locator, this, "cellEditor", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.cellSelectionEnabled!= null);
            Boolean theField;
            theField = this.isCellSelectionEnabled();
            strategy.appendField(locator, this, "cellSelectionEnabled", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.columnModel!= null);
            String theField;
            theField = this.getColumnModel();
            strategy.appendField(locator, this, "columnModel", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.columnSelectionAllowed!= null);
            Boolean theField;
            theField = this.isColumnSelectionAllowed();
            strategy.appendField(locator, this, "columnSelectionAllowed", buffer, theField, theFieldIsSet);
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
            boolean theFieldIsSet = (this.editingColumn!= null);
            String theField;
            theField = this.getEditingColumn();
            strategy.appendField(locator, this, "editingColumn", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.editingRow!= null);
            String theField;
            theField = this.getEditingRow();
            strategy.appendField(locator, this, "editingRow", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.fillsViewportHeight!= null);
            Boolean theField;
            theField = this.isFillsViewportHeight();
            strategy.appendField(locator, this, "fillsViewportHeight", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.gridColor!= null);
            String theField;
            theField = this.getGridColor();
            strategy.appendField(locator, this, "gridColor", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.intercellSpacing!= null);
            String theField;
            theField = this.getIntercellSpacing();
            strategy.appendField(locator, this, "intercellSpacing", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.model!= null);
            String theField;
            theField = this.getModel();
            strategy.appendField(locator, this, "model", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.preferredScrollableViewportSize!= null);
            String theField;
            theField = this.getPreferredScrollableViewportSize();
            strategy.appendField(locator, this, "preferredScrollableViewportSize", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rowHeight!= null);
            String theField;
            theField = this.getRowHeight();
            strategy.appendField(locator, this, "rowHeight", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rowMargin!= null);
            String theField;
            theField = this.getRowMargin();
            strategy.appendField(locator, this, "rowMargin", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rowSelectionAllowed!= null);
            Boolean theField;
            theField = this.isRowSelectionAllowed();
            strategy.appendField(locator, this, "rowSelectionAllowed", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rowSorter!= null);
            String theField;
            theField = this.getRowSorter();
            strategy.appendField(locator, this, "rowSorter", buffer, theField, theFieldIsSet);
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
            boolean theFieldIsSet = (this.showGrid!= null);
            Boolean theField;
            theField = this.isShowGrid();
            strategy.appendField(locator, this, "showGrid", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.showHorizontalLines!= null);
            Boolean theField;
            theField = this.isShowHorizontalLines();
            strategy.appendField(locator, this, "showHorizontalLines", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.showVerticalLines!= null);
            Boolean theField;
            theField = this.isShowVerticalLines();
            strategy.appendField(locator, this, "showVerticalLines", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.surrendersFocusOnKeystroke!= null);
            Boolean theField;
            theField = this.isSurrendersFocusOnKeystroke();
            strategy.appendField(locator, this, "surrendersFocusOnKeystroke", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.tableHeader!= null);
            String theField;
            theField = this.getTableHeader();
            strategy.appendField(locator, this, "tableHeader", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.updateSelectionOnSort!= null);
            Boolean theField;
            theField = this.isUpdateSelectionOnSort();
            strategy.appendField(locator, this, "updateSelectionOnSort", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
