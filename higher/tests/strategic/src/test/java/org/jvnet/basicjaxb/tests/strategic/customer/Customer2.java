package org.jvnet.basicjaxb.tests.strategic.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jvnet.basicjaxb.lang.Equals;
import org.jvnet.basicjaxb.lang.EqualsStrategy;
import org.jvnet.basicjaxb.lang.HashCode;
import org.jvnet.basicjaxb.lang.HashCodeStrategy;
import org.jvnet.basicjaxb.lang.JAXBEqualsStrategy;
import org.jvnet.basicjaxb.lang.JAXBHashCodeStrategy;
import org.jvnet.basicjaxb.lang.JAXBToStringStrategy;
import org.jvnet.basicjaxb.lang.ToString;
import org.jvnet.basicjaxb.lang.ToStringStrategy;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;
import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.jvnet.basicjaxb.locator.util.LocatorUtils;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Customer complex type</p>.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 *
 * <pre>{@code
 * <complexType name="Customer">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="address" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="blueEyes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         <element name="familyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="givenName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="middleInitials" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="postCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="single" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         <element name="photo" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", propOrder = {
    "address",
    "blueEyes",
    "familyName",
    "givenName",
    "middleInitials",
    "postCode",
    "single",
    "photo"
})
public class Customer2 implements Serializable, Equals, HashCode, ToString
{

    private static final long serialVersionUID = 20230201L;
    @XmlElement(required = true)
    protected String address;
    protected Boolean blueEyes;
    @XmlElement(required = true)
    protected String familyName;
    @XmlElement(required = true)
    protected String givenName;
    protected List<String> middleInitials;
    @XmlElement(required = true)
    protected String postCode;
    protected boolean single;
    @XmlElement(required = true)
    protected byte[] photo;

    /**
     * Gets the value of the address property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAddress(String value) {
        this.address = value;
    }

    public boolean isSetAddress() {
        return (this.address!= null);
    }

    /**
     * Gets the value of the blueEyes property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isBlueEyes() {
        return blueEyes;
    }

    /**
     * Sets the value of the blueEyes property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setBlueEyes(Boolean value) {
        this.blueEyes = value;
    }

    public boolean isSetBlueEyes() {
        return (this.blueEyes!= null);
    }

    /**
     * Gets the value of the familyName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Sets the value of the familyName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFamilyName(String value) {
        this.familyName = value;
    }

    public boolean isSetFamilyName() {
        return (this.familyName!= null);
    }

    /**
     * Gets the value of the givenName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the value of the givenName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setGivenName(String value) {
        this.givenName = value;
    }

    public boolean isSetGivenName() {
        return (this.givenName!= null);
    }

    /**
     * Gets the value of the middleInitials property.
     *
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the middleInitials property.</p>
     *
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getMiddleInitials().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     *
     *
     * @return
     *     The value of the middleInitials property.
     */
    public List<String> getMiddleInitials() {
        if (middleInitials == null) {
            middleInitials = new ArrayList<>();
        }
        return this.middleInitials;
    }

    public boolean isSetMiddleInitials() {
        return ((this.middleInitials!= null)&&(!this.middleInitials.isEmpty()));
    }

    public void unsetMiddleInitials() {
        this.middleInitials = null;
    }

    /**
     * Gets the value of the postCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Sets the value of the postCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPostCode(String value) {
        this.postCode = value;
    }

    public boolean isSetPostCode() {
        return (this.postCode!= null);
    }

    /**
     * Gets the value of the single property.
     *
     */
    public boolean isSingle() {
        return single;
    }

    /**
     * Sets the value of the single property.
     *
     */
    public void setSingle(boolean value) {
        this.single = value;
    }

    public boolean isSetSingle() {
        return true;
    }

    /**
     * Gets the value of the photo property.
     *
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * Sets the value of the photo property.
     *
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPhoto(byte[] value) {
        this.photo = value;
    }

    public boolean isSetPhoto() {
        return (this.photo!= null);
    }

    @Override
    public int hashCode() {
        ObjectLocator theLocator = null;
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.getInstance();
        if (strategy.isDebugEnabled()) {
            theLocator = new DefaultRootObjectLocator(this);
        }
        return this.hashCode(theLocator, strategy);
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            boolean theFieldIsSet = this.isSetAddress();
            String theField;
            theField = this.getAddress();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "address", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = this.isSetBlueEyes();
            Boolean theField;
            theField = this.isBlueEyes();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "blueEyes", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = this.isSetFamilyName();
            String theField;
            theField = this.getFamilyName();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "familyName", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = this.isSetGivenName();
            String theField;
            theField = this.getGivenName();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "givenName", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = this.isSetMiddleInitials();
            List<String> theField;
            theField = (this.isSetMiddleInitials()?this.getMiddleInitials():null);
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "middleInitials", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = this.isSetPostCode();
            String theField;
            theField = this.getPostCode();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "postCode", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = true;
            boolean theField;
            theField = this.isSingle();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "single", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = this.isSetPhoto();
            byte[] theField;
            theField = this.getPhoto();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "photo", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        return currentHashCode;
    }

    @Override
    public boolean equals(Object object) {
        ObjectLocator thisLocator = null;
        ObjectLocator thatLocator = null;
        final EqualsStrategy strategy = JAXBEqualsStrategy.getInstance();
        if (strategy.isDebugEnabled()) {
            thisLocator = new DefaultRootObjectLocator(this);
            thatLocator = new DefaultRootObjectLocator(object);
        }
        return equals(thisLocator, thatLocator, object, strategy);
    }

    @Override
    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Customer2 that = ((Customer2) object);
        {
            boolean lhsFieldIsSet = this.isSetAddress();
            boolean rhsFieldIsSet = that.isSetAddress();
            String lhsField;
            lhsField = this.getAddress();
            String rhsField;
            rhsField = that.getAddress();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "address", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "address", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = this.isSetBlueEyes();
            boolean rhsFieldIsSet = that.isSetBlueEyes();
            Boolean lhsField;
            lhsField = this.isBlueEyes();
            Boolean rhsField;
            rhsField = that.isBlueEyes();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "blueEyes", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "blueEyes", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = this.isSetFamilyName();
            boolean rhsFieldIsSet = that.isSetFamilyName();
            String lhsField;
            lhsField = this.getFamilyName();
            String rhsField;
            rhsField = that.getFamilyName();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "familyName", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "familyName", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = this.isSetGivenName();
            boolean rhsFieldIsSet = that.isSetGivenName();
            String lhsField;
            lhsField = this.getGivenName();
            String rhsField;
            rhsField = that.getGivenName();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "givenName", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "givenName", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = this.isSetMiddleInitials();
            boolean rhsFieldIsSet = that.isSetMiddleInitials();
            List<String> lhsField;
            lhsField = (this.isSetMiddleInitials()?this.getMiddleInitials():null);
            List<String> rhsField;
            rhsField = (that.isSetMiddleInitials()?that.getMiddleInitials():null);
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "middleInitials", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "middleInitials", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = this.isSetPostCode();
            boolean rhsFieldIsSet = that.isSetPostCode();
            String lhsField;
            lhsField = this.getPostCode();
            String rhsField;
            rhsField = that.getPostCode();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "postCode", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "postCode", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = true;
            boolean rhsFieldIsSet = true;
            boolean lhsField;
            lhsField = this.isSingle();
            boolean rhsField;
            rhsField = that.isSingle();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "single", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "single", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = this.isSetPhoto();
            boolean rhsFieldIsSet = that.isSetPhoto();
            byte[] lhsField;
            lhsField = this.getPhoto();
            byte[] rhsField;
            rhsField = that.getPhoto();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "photo", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "photo", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        ObjectLocator theLocator = null;
        final ToStringStrategy strategy = JAXBToStringStrategy.getInstance();
        if (strategy.isTraceEnabled()) {
            theLocator = new DefaultRootObjectLocator(this);
        }
        final StringBuilder buffer = new StringBuilder();
        append(theLocator, buffer, strategy);
        return buffer.toString();
    }

    @Override
    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    @Override
    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            boolean theFieldIsSet = this.isSetAddress();
            String theField;
            theField = this.getAddress();
            strategy.appendField(locator, this, "address", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = this.isSetBlueEyes();
            Boolean theField;
            theField = this.isBlueEyes();
            strategy.appendField(locator, this, "blueEyes", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = this.isSetFamilyName();
            String theField;
            theField = this.getFamilyName();
            strategy.appendField(locator, this, "familyName", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = this.isSetGivenName();
            String theField;
            theField = this.getGivenName();
            strategy.appendField(locator, this, "givenName", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = this.isSetMiddleInitials();
            List<String> theField;
            theField = (this.isSetMiddleInitials()?this.getMiddleInitials():null);
            strategy.appendField(locator, this, "middleInitials", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = this.isSetPostCode();
            String theField;
            theField = this.getPostCode();
            strategy.appendField(locator, this, "postCode", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = true;
            boolean theField;
            theField = this.isSingle();
            strategy.appendField(locator, this, "single", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = this.isSetPhoto();
            byte[] theField;
            theField = this.getPhoto();
            strategy.appendField(locator, this, "photo", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
