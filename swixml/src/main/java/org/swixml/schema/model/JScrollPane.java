
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
 * <p>Java class for JScrollPane complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JScrollPane">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/swixml}JComponent">
 *       <sequence>
 *       </sequence>
 *       <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="columnHeader" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="columnHeaderView" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="horizontalScrollBar" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="horizontalScrollBarPolicy" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="rowHeader" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="rowHeaderView" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="verticalScrollBar" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="verticalScrollBarPolicy" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="viewport" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="viewportBorder" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="viewportView" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="wheelScrollingEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JScrollPane")
@XmlSeeAlso({
    XScrollPane.class
})
public class JScrollPane
    extends JComponent
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "UI")
    protected String ui;
    @XmlAttribute(name = "columnHeader")
    protected String columnHeader;
    @XmlAttribute(name = "columnHeaderView")
    protected String columnHeaderView;
    @XmlAttribute(name = "horizontalScrollBar")
    protected String horizontalScrollBar;
    @XmlAttribute(name = "horizontalScrollBarPolicy")
    protected String horizontalScrollBarPolicy;
    @XmlAttribute(name = "rowHeader")
    protected String rowHeader;
    @XmlAttribute(name = "rowHeaderView")
    protected String rowHeaderView;
    @XmlAttribute(name = "verticalScrollBar")
    protected String verticalScrollBar;
    @XmlAttribute(name = "verticalScrollBarPolicy")
    protected String verticalScrollBarPolicy;
    @XmlAttribute(name = "viewport")
    protected String viewport;
    @XmlAttribute(name = "viewportBorder")
    protected String viewportBorder;
    @XmlAttribute(name = "viewportView")
    protected String viewportView;
    @XmlAttribute(name = "wheelScrollingEnabled")
    protected Boolean wheelScrollingEnabled;

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
     * Gets the value of the columnHeader property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumnHeader() {
        return columnHeader;
    }

    /**
     * Sets the value of the columnHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumnHeader(String value) {
        this.columnHeader = value;
    }

    /**
     * Gets the value of the columnHeaderView property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumnHeaderView() {
        return columnHeaderView;
    }

    /**
     * Sets the value of the columnHeaderView property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumnHeaderView(String value) {
        this.columnHeaderView = value;
    }

    /**
     * Gets the value of the horizontalScrollBar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorizontalScrollBar() {
        return horizontalScrollBar;
    }

    /**
     * Sets the value of the horizontalScrollBar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorizontalScrollBar(String value) {
        this.horizontalScrollBar = value;
    }

    /**
     * Gets the value of the horizontalScrollBarPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorizontalScrollBarPolicy() {
        return horizontalScrollBarPolicy;
    }

    /**
     * Sets the value of the horizontalScrollBarPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorizontalScrollBarPolicy(String value) {
        this.horizontalScrollBarPolicy = value;
    }

    /**
     * Gets the value of the rowHeader property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRowHeader() {
        return rowHeader;
    }

    /**
     * Sets the value of the rowHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRowHeader(String value) {
        this.rowHeader = value;
    }

    /**
     * Gets the value of the rowHeaderView property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRowHeaderView() {
        return rowHeaderView;
    }

    /**
     * Sets the value of the rowHeaderView property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRowHeaderView(String value) {
        this.rowHeaderView = value;
    }

    /**
     * Gets the value of the verticalScrollBar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerticalScrollBar() {
        return verticalScrollBar;
    }

    /**
     * Sets the value of the verticalScrollBar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerticalScrollBar(String value) {
        this.verticalScrollBar = value;
    }

    /**
     * Gets the value of the verticalScrollBarPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerticalScrollBarPolicy() {
        return verticalScrollBarPolicy;
    }

    /**
     * Sets the value of the verticalScrollBarPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerticalScrollBarPolicy(String value) {
        this.verticalScrollBarPolicy = value;
    }

    /**
     * Gets the value of the viewport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViewport() {
        return viewport;
    }

    /**
     * Sets the value of the viewport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViewport(String value) {
        this.viewport = value;
    }

    /**
     * Gets the value of the viewportBorder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViewportBorder() {
        return viewportBorder;
    }

    /**
     * Sets the value of the viewportBorder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViewportBorder(String value) {
        this.viewportBorder = value;
    }

    /**
     * Gets the value of the viewportView property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViewportView() {
        return viewportView;
    }

    /**
     * Sets the value of the viewportView property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViewportView(String value) {
        this.viewportView = value;
    }

    /**
     * Gets the value of the wheelScrollingEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWheelScrollingEnabled() {
        return wheelScrollingEnabled;
    }

    /**
     * Sets the value of the wheelScrollingEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWheelScrollingEnabled(Boolean value) {
        this.wheelScrollingEnabled = value;
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
            boolean theFieldIsSet = (this.columnHeader!= null);
            String theField;
            theField = this.getColumnHeader();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "columnHeader", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.columnHeaderView!= null);
            String theField;
            theField = this.getColumnHeaderView();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "columnHeaderView", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.horizontalScrollBar!= null);
            String theField;
            theField = this.getHorizontalScrollBar();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "horizontalScrollBar", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.horizontalScrollBarPolicy!= null);
            String theField;
            theField = this.getHorizontalScrollBarPolicy();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "horizontalScrollBarPolicy", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rowHeader!= null);
            String theField;
            theField = this.getRowHeader();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "rowHeader", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rowHeaderView!= null);
            String theField;
            theField = this.getRowHeaderView();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "rowHeaderView", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.verticalScrollBar!= null);
            String theField;
            theField = this.getVerticalScrollBar();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "verticalScrollBar", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.verticalScrollBarPolicy!= null);
            String theField;
            theField = this.getVerticalScrollBarPolicy();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "verticalScrollBarPolicy", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.viewport!= null);
            String theField;
            theField = this.getViewport();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "viewport", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.viewportBorder!= null);
            String theField;
            theField = this.getViewportBorder();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "viewportBorder", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.viewportView!= null);
            String theField;
            theField = this.getViewportView();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "viewportView", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.wheelScrollingEnabled!= null);
            Boolean theField;
            theField = this.isWheelScrollingEnabled();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "wheelScrollingEnabled", theField);
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
        final JScrollPane that = ((JScrollPane) object);
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
            boolean lhsFieldIsSet = (this.columnHeader!= null);
            boolean rhsFieldIsSet = (that.columnHeader!= null);
            String lhsField;
            lhsField = this.getColumnHeader();
            String rhsField;
            rhsField = that.getColumnHeader();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "columnHeader", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "columnHeader", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.columnHeaderView!= null);
            boolean rhsFieldIsSet = (that.columnHeaderView!= null);
            String lhsField;
            lhsField = this.getColumnHeaderView();
            String rhsField;
            rhsField = that.getColumnHeaderView();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "columnHeaderView", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "columnHeaderView", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.horizontalScrollBar!= null);
            boolean rhsFieldIsSet = (that.horizontalScrollBar!= null);
            String lhsField;
            lhsField = this.getHorizontalScrollBar();
            String rhsField;
            rhsField = that.getHorizontalScrollBar();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "horizontalScrollBar", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "horizontalScrollBar", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.horizontalScrollBarPolicy!= null);
            boolean rhsFieldIsSet = (that.horizontalScrollBarPolicy!= null);
            String lhsField;
            lhsField = this.getHorizontalScrollBarPolicy();
            String rhsField;
            rhsField = that.getHorizontalScrollBarPolicy();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "horizontalScrollBarPolicy", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "horizontalScrollBarPolicy", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.rowHeader!= null);
            boolean rhsFieldIsSet = (that.rowHeader!= null);
            String lhsField;
            lhsField = this.getRowHeader();
            String rhsField;
            rhsField = that.getRowHeader();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "rowHeader", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "rowHeader", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.rowHeaderView!= null);
            boolean rhsFieldIsSet = (that.rowHeaderView!= null);
            String lhsField;
            lhsField = this.getRowHeaderView();
            String rhsField;
            rhsField = that.getRowHeaderView();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "rowHeaderView", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "rowHeaderView", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.verticalScrollBar!= null);
            boolean rhsFieldIsSet = (that.verticalScrollBar!= null);
            String lhsField;
            lhsField = this.getVerticalScrollBar();
            String rhsField;
            rhsField = that.getVerticalScrollBar();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "verticalScrollBar", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "verticalScrollBar", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.verticalScrollBarPolicy!= null);
            boolean rhsFieldIsSet = (that.verticalScrollBarPolicy!= null);
            String lhsField;
            lhsField = this.getVerticalScrollBarPolicy();
            String rhsField;
            rhsField = that.getVerticalScrollBarPolicy();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "verticalScrollBarPolicy", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "verticalScrollBarPolicy", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.viewport!= null);
            boolean rhsFieldIsSet = (that.viewport!= null);
            String lhsField;
            lhsField = this.getViewport();
            String rhsField;
            rhsField = that.getViewport();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "viewport", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "viewport", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.viewportBorder!= null);
            boolean rhsFieldIsSet = (that.viewportBorder!= null);
            String lhsField;
            lhsField = this.getViewportBorder();
            String rhsField;
            rhsField = that.getViewportBorder();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "viewportBorder", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "viewportBorder", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.viewportView!= null);
            boolean rhsFieldIsSet = (that.viewportView!= null);
            String lhsField;
            lhsField = this.getViewportView();
            String rhsField;
            rhsField = that.getViewportView();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "viewportView", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "viewportView", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.wheelScrollingEnabled!= null);
            boolean rhsFieldIsSet = (that.wheelScrollingEnabled!= null);
            Boolean lhsField;
            lhsField = this.isWheelScrollingEnabled();
            Boolean rhsField;
            rhsField = that.isWheelScrollingEnabled();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "wheelScrollingEnabled", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "wheelScrollingEnabled", rhsField);
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
            boolean theFieldIsSet = (this.columnHeader!= null);
            String theField;
            theField = this.getColumnHeader();
            strategy.appendField(locator, this, "columnHeader", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.columnHeaderView!= null);
            String theField;
            theField = this.getColumnHeaderView();
            strategy.appendField(locator, this, "columnHeaderView", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.horizontalScrollBar!= null);
            String theField;
            theField = this.getHorizontalScrollBar();
            strategy.appendField(locator, this, "horizontalScrollBar", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.horizontalScrollBarPolicy!= null);
            String theField;
            theField = this.getHorizontalScrollBarPolicy();
            strategy.appendField(locator, this, "horizontalScrollBarPolicy", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rowHeader!= null);
            String theField;
            theField = this.getRowHeader();
            strategy.appendField(locator, this, "rowHeader", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rowHeaderView!= null);
            String theField;
            theField = this.getRowHeaderView();
            strategy.appendField(locator, this, "rowHeaderView", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.verticalScrollBar!= null);
            String theField;
            theField = this.getVerticalScrollBar();
            strategy.appendField(locator, this, "verticalScrollBar", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.verticalScrollBarPolicy!= null);
            String theField;
            theField = this.getVerticalScrollBarPolicy();
            strategy.appendField(locator, this, "verticalScrollBarPolicy", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.viewport!= null);
            String theField;
            theField = this.getViewport();
            strategy.appendField(locator, this, "viewport", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.viewportBorder!= null);
            String theField;
            theField = this.getViewportBorder();
            strategy.appendField(locator, this, "viewportBorder", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.viewportView!= null);
            String theField;
            theField = this.getViewportView();
            strategy.appendField(locator, this, "viewportView", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.wheelScrollingEnabled!= null);
            Boolean theField;
            theField = this.isWheelScrollingEnabled();
            strategy.appendField(locator, this, "wheelScrollingEnabled", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
