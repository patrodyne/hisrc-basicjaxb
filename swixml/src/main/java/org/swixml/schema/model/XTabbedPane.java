
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
 * <p>Java class for XTabbedPane complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="XTabbedPane">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/Swixml}JTabbedPane">
 *       <sequence>
 *       </sequence>
 *       <attribute name="disabledIcons" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="displayedMnemonics" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="icons" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="mnemonics" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="titles" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="toolTipTexts" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XTabbedPane")
public class XTabbedPane
    extends JTabbedPane
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "disabledIcons")
    protected String disabledIcons;
    @XmlAttribute(name = "displayedMnemonics")
    protected String displayedMnemonics;
    @XmlAttribute(name = "icons")
    protected String icons;
    @XmlAttribute(name = "mnemonics")
    protected String mnemonics;
    @XmlAttribute(name = "titles")
    protected String titles;
    @XmlAttribute(name = "toolTipTexts")
    protected String toolTipTexts;

    /**
     * Gets the value of the disabledIcons property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisabledIcons() {
        return disabledIcons;
    }

    /**
     * Sets the value of the disabledIcons property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisabledIcons(String value) {
        this.disabledIcons = value;
    }

    /**
     * Gets the value of the displayedMnemonics property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayedMnemonics() {
        return displayedMnemonics;
    }

    /**
     * Sets the value of the displayedMnemonics property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayedMnemonics(String value) {
        this.displayedMnemonics = value;
    }

    /**
     * Gets the value of the icons property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIcons() {
        return icons;
    }

    /**
     * Sets the value of the icons property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIcons(String value) {
        this.icons = value;
    }

    /**
     * Gets the value of the mnemonics property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMnemonics() {
        return mnemonics;
    }

    /**
     * Sets the value of the mnemonics property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMnemonics(String value) {
        this.mnemonics = value;
    }

    /**
     * Gets the value of the titles property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitles() {
        return titles;
    }

    /**
     * Sets the value of the titles property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitles(String value) {
        this.titles = value;
    }

    /**
     * Gets the value of the toolTipTexts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToolTipTexts() {
        return toolTipTexts;
    }

    /**
     * Sets the value of the toolTipTexts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToolTipTexts(String value) {
        this.toolTipTexts = value;
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            boolean theFieldIsSet = (this.disabledIcons!= null);
            String theField;
            theField = this.getDisabledIcons();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "disabledIcons", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.displayedMnemonics!= null);
            String theField;
            theField = this.getDisplayedMnemonics();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "displayedMnemonics", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.icons!= null);
            String theField;
            theField = this.getIcons();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "icons", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.mnemonics!= null);
            String theField;
            theField = this.getMnemonics();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "mnemonics", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.titles!= null);
            String theField;
            theField = this.getTitles();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "titles", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.toolTipTexts!= null);
            String theField;
            theField = this.getToolTipTexts();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "toolTipTexts", theField);
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
        final XTabbedPane that = ((XTabbedPane) object);
        {
            boolean lhsFieldIsSet = (this.disabledIcons!= null);
            boolean rhsFieldIsSet = (that.disabledIcons!= null);
            String lhsField;
            lhsField = this.getDisabledIcons();
            String rhsField;
            rhsField = that.getDisabledIcons();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "disabledIcons", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "disabledIcons", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.displayedMnemonics!= null);
            boolean rhsFieldIsSet = (that.displayedMnemonics!= null);
            String lhsField;
            lhsField = this.getDisplayedMnemonics();
            String rhsField;
            rhsField = that.getDisplayedMnemonics();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "displayedMnemonics", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "displayedMnemonics", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.icons!= null);
            boolean rhsFieldIsSet = (that.icons!= null);
            String lhsField;
            lhsField = this.getIcons();
            String rhsField;
            rhsField = that.getIcons();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "icons", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "icons", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.mnemonics!= null);
            boolean rhsFieldIsSet = (that.mnemonics!= null);
            String lhsField;
            lhsField = this.getMnemonics();
            String rhsField;
            rhsField = that.getMnemonics();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "mnemonics", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "mnemonics", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.titles!= null);
            boolean rhsFieldIsSet = (that.titles!= null);
            String lhsField;
            lhsField = this.getTitles();
            String rhsField;
            rhsField = that.getTitles();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "titles", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "titles", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.toolTipTexts!= null);
            boolean rhsFieldIsSet = (that.toolTipTexts!= null);
            String lhsField;
            lhsField = this.getToolTipTexts();
            String rhsField;
            rhsField = that.getToolTipTexts();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "toolTipTexts", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "toolTipTexts", rhsField);
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
            boolean theFieldIsSet = (this.disabledIcons!= null);
            String theField;
            theField = this.getDisabledIcons();
            strategy.appendField(locator, this, "disabledIcons", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.displayedMnemonics!= null);
            String theField;
            theField = this.getDisplayedMnemonics();
            strategy.appendField(locator, this, "displayedMnemonics", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.icons!= null);
            String theField;
            theField = this.getIcons();
            strategy.appendField(locator, this, "icons", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.mnemonics!= null);
            String theField;
            theField = this.getMnemonics();
            strategy.appendField(locator, this, "mnemonics", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.titles!= null);
            String theField;
            theField = this.getTitles();
            strategy.appendField(locator, this, "titles", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.toolTipTexts!= null);
            String theField;
            theField = this.getToolTipTexts();
            strategy.appendField(locator, this, "toolTipTexts", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
