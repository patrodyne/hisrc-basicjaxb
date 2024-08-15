
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
 * <p>Java class for XGridBagConstraints complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="XGridBagConstraints">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/Swixml}GridBagConstraints">
 *       <sequence>
 *       </sequence>
 *       <attribute name="anchor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="fill" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="gridheight" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="gridwidth" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="gridx" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="gridy" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="insets" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="ipadx" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="ipady" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="weightx" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="weighty" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XGridBagConstraints")
public class XGridBagConstraints
    extends GridBagConstraints
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "anchor")
    protected String anchor;
    @XmlAttribute(name = "fill")
    protected String fill;
    @XmlAttribute(name = "gridheight")
    protected String gridheight;
    @XmlAttribute(name = "gridwidth")
    protected String gridwidth;
    @XmlAttribute(name = "gridx")
    protected String gridx;
    @XmlAttribute(name = "gridy")
    protected String gridy;
    @XmlAttribute(name = "insets")
    protected String insets;
    @XmlAttribute(name = "ipadx")
    protected String ipadx;
    @XmlAttribute(name = "ipady")
    protected String ipady;
    @XmlAttribute(name = "weightx")
    protected String weightx;
    @XmlAttribute(name = "weighty")
    protected String weighty;

    /**
     * Gets the value of the anchor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnchor() {
        return anchor;
    }

    /**
     * Sets the value of the anchor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnchor(String value) {
        this.anchor = value;
    }

    /**
     * Gets the value of the fill property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFill() {
        return fill;
    }

    /**
     * Sets the value of the fill property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFill(String value) {
        this.fill = value;
    }

    /**
     * Gets the value of the gridheight property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGridheight() {
        return gridheight;
    }

    /**
     * Sets the value of the gridheight property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGridheight(String value) {
        this.gridheight = value;
    }

    /**
     * Gets the value of the gridwidth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGridwidth() {
        return gridwidth;
    }

    /**
     * Sets the value of the gridwidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGridwidth(String value) {
        this.gridwidth = value;
    }

    /**
     * Gets the value of the gridx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGridx() {
        return gridx;
    }

    /**
     * Sets the value of the gridx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGridx(String value) {
        this.gridx = value;
    }

    /**
     * Gets the value of the gridy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGridy() {
        return gridy;
    }

    /**
     * Sets the value of the gridy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGridy(String value) {
        this.gridy = value;
    }

    /**
     * Gets the value of the insets property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsets() {
        return insets;
    }

    /**
     * Sets the value of the insets property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsets(String value) {
        this.insets = value;
    }

    /**
     * Gets the value of the ipadx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpadx() {
        return ipadx;
    }

    /**
     * Sets the value of the ipadx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpadx(String value) {
        this.ipadx = value;
    }

    /**
     * Gets the value of the ipady property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpady() {
        return ipady;
    }

    /**
     * Sets the value of the ipady property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpady(String value) {
        this.ipady = value;
    }

    /**
     * Gets the value of the weightx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeightx() {
        return weightx;
    }

    /**
     * Sets the value of the weightx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeightx(String value) {
        this.weightx = value;
    }

    /**
     * Gets the value of the weighty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeighty() {
        return weighty;
    }

    /**
     * Sets the value of the weighty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeighty(String value) {
        this.weighty = value;
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            boolean theFieldIsSet = (this.anchor!= null);
            String theField;
            theField = this.getAnchor();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "anchor", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.fill!= null);
            String theField;
            theField = this.getFill();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "fill", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.gridheight!= null);
            String theField;
            theField = this.getGridheight();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "gridheight", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.gridwidth!= null);
            String theField;
            theField = this.getGridwidth();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "gridwidth", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.gridx!= null);
            String theField;
            theField = this.getGridx();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "gridx", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.gridy!= null);
            String theField;
            theField = this.getGridy();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "gridy", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.insets!= null);
            String theField;
            theField = this.getInsets();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "insets", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.ipadx!= null);
            String theField;
            theField = this.getIpadx();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "ipadx", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.ipady!= null);
            String theField;
            theField = this.getIpady();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "ipady", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.weightx!= null);
            String theField;
            theField = this.getWeightx();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "weightx", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.weighty!= null);
            String theField;
            theField = this.getWeighty();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "weighty", theField);
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
        final XGridBagConstraints that = ((XGridBagConstraints) object);
        {
            boolean lhsFieldIsSet = (this.anchor!= null);
            boolean rhsFieldIsSet = (that.anchor!= null);
            String lhsField;
            lhsField = this.getAnchor();
            String rhsField;
            rhsField = that.getAnchor();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "anchor", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "anchor", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.fill!= null);
            boolean rhsFieldIsSet = (that.fill!= null);
            String lhsField;
            lhsField = this.getFill();
            String rhsField;
            rhsField = that.getFill();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "fill", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "fill", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.gridheight!= null);
            boolean rhsFieldIsSet = (that.gridheight!= null);
            String lhsField;
            lhsField = this.getGridheight();
            String rhsField;
            rhsField = that.getGridheight();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "gridheight", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "gridheight", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.gridwidth!= null);
            boolean rhsFieldIsSet = (that.gridwidth!= null);
            String lhsField;
            lhsField = this.getGridwidth();
            String rhsField;
            rhsField = that.getGridwidth();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "gridwidth", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "gridwidth", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.gridx!= null);
            boolean rhsFieldIsSet = (that.gridx!= null);
            String lhsField;
            lhsField = this.getGridx();
            String rhsField;
            rhsField = that.getGridx();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "gridx", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "gridx", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.gridy!= null);
            boolean rhsFieldIsSet = (that.gridy!= null);
            String lhsField;
            lhsField = this.getGridy();
            String rhsField;
            rhsField = that.getGridy();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "gridy", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "gridy", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.insets!= null);
            boolean rhsFieldIsSet = (that.insets!= null);
            String lhsField;
            lhsField = this.getInsets();
            String rhsField;
            rhsField = that.getInsets();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "insets", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "insets", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.ipadx!= null);
            boolean rhsFieldIsSet = (that.ipadx!= null);
            String lhsField;
            lhsField = this.getIpadx();
            String rhsField;
            rhsField = that.getIpadx();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "ipadx", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "ipadx", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.ipady!= null);
            boolean rhsFieldIsSet = (that.ipady!= null);
            String lhsField;
            lhsField = this.getIpady();
            String rhsField;
            rhsField = that.getIpady();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "ipady", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "ipady", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.weightx!= null);
            boolean rhsFieldIsSet = (that.weightx!= null);
            String lhsField;
            lhsField = this.getWeightx();
            String rhsField;
            rhsField = that.getWeightx();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "weightx", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "weightx", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.weighty!= null);
            boolean rhsFieldIsSet = (that.weighty!= null);
            String lhsField;
            lhsField = this.getWeighty();
            String rhsField;
            rhsField = that.getWeighty();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "weighty", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "weighty", rhsField);
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
            boolean theFieldIsSet = (this.anchor!= null);
            String theField;
            theField = this.getAnchor();
            strategy.appendField(locator, this, "anchor", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.fill!= null);
            String theField;
            theField = this.getFill();
            strategy.appendField(locator, this, "fill", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.gridheight!= null);
            String theField;
            theField = this.getGridheight();
            strategy.appendField(locator, this, "gridheight", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.gridwidth!= null);
            String theField;
            theField = this.getGridwidth();
            strategy.appendField(locator, this, "gridwidth", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.gridx!= null);
            String theField;
            theField = this.getGridx();
            strategy.appendField(locator, this, "gridx", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.gridy!= null);
            String theField;
            theField = this.getGridy();
            strategy.appendField(locator, this, "gridy", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.insets!= null);
            String theField;
            theField = this.getInsets();
            strategy.appendField(locator, this, "insets", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.ipadx!= null);
            String theField;
            theField = this.getIpadx();
            strategy.appendField(locator, this, "ipadx", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.ipady!= null);
            String theField;
            theField = this.getIpady();
            strategy.appendField(locator, this, "ipady", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.weightx!= null);
            String theField;
            theField = this.getWeightx();
            strategy.appendField(locator, this, "weightx", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.weighty!= null);
            String theField;
            theField = this.getWeighty();
            strategy.appendField(locator, this, "weighty", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
