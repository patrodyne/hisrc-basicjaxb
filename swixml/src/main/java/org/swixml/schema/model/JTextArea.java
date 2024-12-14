
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
 * <p>Java class for JTextArea complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JTextArea">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/swixml}JTextComponent">
 *       <sequence>
 *       </sequence>
 *       <attribute name="columns" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="lineWrap" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="rows" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="tabSize" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="wrapStyleWord" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JTextArea")
@XmlSeeAlso({
    JTextAreaBind.class
})
public class JTextArea
    extends JTextComponent
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "columns")
    protected String columns;
    @XmlAttribute(name = "lineWrap")
    protected Boolean lineWrap;
    @XmlAttribute(name = "rows")
    protected String rows;
    @XmlAttribute(name = "tabSize")
    protected String tabSize;
    @XmlAttribute(name = "wrapStyleWord")
    protected Boolean wrapStyleWord;

    /**
     * Gets the value of the columns property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumns() {
        return columns;
    }

    /**
     * Sets the value of the columns property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumns(String value) {
        this.columns = value;
    }

    /**
     * Gets the value of the lineWrap property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLineWrap() {
        return lineWrap;
    }

    /**
     * Sets the value of the lineWrap property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLineWrap(Boolean value) {
        this.lineWrap = value;
    }

    /**
     * Gets the value of the rows property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRows() {
        return rows;
    }

    /**
     * Sets the value of the rows property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRows(String value) {
        this.rows = value;
    }

    /**
     * Gets the value of the tabSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTabSize() {
        return tabSize;
    }

    /**
     * Sets the value of the tabSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTabSize(String value) {
        this.tabSize = value;
    }

    /**
     * Gets the value of the wrapStyleWord property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWrapStyleWord() {
        return wrapStyleWord;
    }

    /**
     * Sets the value of the wrapStyleWord property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWrapStyleWord(Boolean value) {
        this.wrapStyleWord = value;
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            boolean theFieldIsSet = (this.columns!= null);
            String theField;
            theField = this.getColumns();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "columns", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.lineWrap!= null);
            Boolean theField;
            theField = this.isLineWrap();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "lineWrap", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rows!= null);
            String theField;
            theField = this.getRows();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "rows", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.tabSize!= null);
            String theField;
            theField = this.getTabSize();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "tabSize", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.wrapStyleWord!= null);
            Boolean theField;
            theField = this.isWrapStyleWord();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "wrapStyleWord", theField);
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
        final JTextArea that = ((JTextArea) object);
        {
            boolean lhsFieldIsSet = (this.columns!= null);
            boolean rhsFieldIsSet = (that.columns!= null);
            String lhsField;
            lhsField = this.getColumns();
            String rhsField;
            rhsField = that.getColumns();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "columns", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "columns", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.lineWrap!= null);
            boolean rhsFieldIsSet = (that.lineWrap!= null);
            Boolean lhsField;
            lhsField = this.isLineWrap();
            Boolean rhsField;
            rhsField = that.isLineWrap();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "lineWrap", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "lineWrap", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.rows!= null);
            boolean rhsFieldIsSet = (that.rows!= null);
            String lhsField;
            lhsField = this.getRows();
            String rhsField;
            rhsField = that.getRows();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "rows", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "rows", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.tabSize!= null);
            boolean rhsFieldIsSet = (that.tabSize!= null);
            String lhsField;
            lhsField = this.getTabSize();
            String rhsField;
            rhsField = that.getTabSize();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "tabSize", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "tabSize", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.wrapStyleWord!= null);
            boolean rhsFieldIsSet = (that.wrapStyleWord!= null);
            Boolean lhsField;
            lhsField = this.isWrapStyleWord();
            Boolean rhsField;
            rhsField = that.isWrapStyleWord();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "wrapStyleWord", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "wrapStyleWord", rhsField);
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
            boolean theFieldIsSet = (this.columns!= null);
            String theField;
            theField = this.getColumns();
            strategy.appendField(locator, this, "columns", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.lineWrap!= null);
            Boolean theField;
            theField = this.isLineWrap();
            strategy.appendField(locator, this, "lineWrap", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.rows!= null);
            String theField;
            theField = this.getRows();
            strategy.appendField(locator, this, "rows", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.tabSize!= null);
            String theField;
            theField = this.getTabSize();
            strategy.appendField(locator, this, "tabSize", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.wrapStyleWord!= null);
            Boolean theField;
            theField = this.isWrapStyleWord();
            strategy.appendField(locator, this, "wrapStyleWord", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
