package org.jvnet.basicjaxb.tests.simple.customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Customer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * &lt;complexType name="Customer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="blueEyes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="familyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="givenName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="middleInitials" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="postCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="single" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="photo" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
public class Customer2 {

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
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the middleInitials property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMiddleInitials().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
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
    public boolean equals(Object object) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Customer2 that = ((Customer2) object);
        {
            String lhsAddress;
            lhsAddress = this.getAddress();
            String rhsAddress;
            rhsAddress = that.getAddress();
            if (this.isSetAddress()) {
                if (that.isSetAddress()) {
                    if (!lhsAddress.equals(rhsAddress)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.isSetAddress()) {
                    return false;
                }
            }
        }
        {
            Boolean lhsBlueEyes;
            lhsBlueEyes = this.isBlueEyes();
            Boolean rhsBlueEyes;
            rhsBlueEyes = that.isBlueEyes();
            if (this.isSetBlueEyes()) {
                if (that.isSetBlueEyes()) {
                    if (!lhsBlueEyes.equals(rhsBlueEyes)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.isSetBlueEyes()) {
                    return false;
                }
            }
        }
        {
            String lhsFamilyName;
            lhsFamilyName = this.getFamilyName();
            String rhsFamilyName;
            rhsFamilyName = that.getFamilyName();
            if (this.isSetFamilyName()) {
                if (that.isSetFamilyName()) {
                    if (!lhsFamilyName.equals(rhsFamilyName)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.isSetFamilyName()) {
                    return false;
                }
            }
        }
        {
            String lhsGivenName;
            lhsGivenName = this.getGivenName();
            String rhsGivenName;
            rhsGivenName = that.getGivenName();
            if (this.isSetGivenName()) {
                if (that.isSetGivenName()) {
                    if (!lhsGivenName.equals(rhsGivenName)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.isSetGivenName()) {
                    return false;
                }
            }
        }
        {
            List<String> lhsMiddleInitials;
            lhsMiddleInitials = (this.isSetMiddleInitials()?this.getMiddleInitials():null);
            List<String> rhsMiddleInitials;
            rhsMiddleInitials = (that.isSetMiddleInitials()?that.getMiddleInitials():null);
            if (this.isSetMiddleInitials()) {
                if (that.isSetMiddleInitials()) {
                    if (!lhsMiddleInitials.equals(rhsMiddleInitials)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.isSetMiddleInitials()) {
                    return false;
                }
            }
        }
        {
            String lhsPostCode;
            lhsPostCode = this.getPostCode();
            String rhsPostCode;
            rhsPostCode = that.getPostCode();
            if (this.isSetPostCode()) {
                if (that.isSetPostCode()) {
                    if (!lhsPostCode.equals(rhsPostCode)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.isSetPostCode()) {
                    return false;
                }
            }
        }
        {
            boolean lhsSingle;
            lhsSingle = this.isSingle();
            boolean rhsSingle;
            rhsSingle = that.isSingle();
            if (lhsSingle!= rhsSingle) {
                return false;
            }
        }
        {
            byte[] lhsPhoto;
            lhsPhoto = this.getPhoto();
            byte[] rhsPhoto;
            rhsPhoto = that.getPhoto();
            if (this.isSetPhoto()) {
                if (that.isSetPhoto()) {
                    if (!Arrays.equals(lhsPhoto, rhsPhoto)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (that.isSetPhoto()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int currentHashCode = 1;
        {
            currentHashCode = (currentHashCode* 31);
            String theAddress;
            theAddress = this.getAddress();
            if (this.isSetAddress()) {
                currentHashCode += theAddress.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            Boolean theBlueEyes;
            theBlueEyes = this.isBlueEyes();
            if (this.isSetBlueEyes()) {
                currentHashCode += theBlueEyes.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            String theFamilyName;
            theFamilyName = this.getFamilyName();
            if (this.isSetFamilyName()) {
                currentHashCode += theFamilyName.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            String theGivenName;
            theGivenName = this.getGivenName();
            if (this.isSetGivenName()) {
                currentHashCode += theGivenName.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            List<String> theMiddleInitials;
            theMiddleInitials = (this.isSetMiddleInitials()?this.getMiddleInitials():null);
            if (this.isSetMiddleInitials()) {
                currentHashCode += theMiddleInitials.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            String thePostCode;
            thePostCode = this.getPostCode();
            if (this.isSetPostCode()) {
                currentHashCode += thePostCode.hashCode();
            }
        }
        {
            currentHashCode = (currentHashCode* 31);
            boolean theSingle;
            theSingle = this.isSingle();
            currentHashCode += (theSingle? 1231 : 1237);
        }
        {
            currentHashCode = (currentHashCode* 31);
            byte[] thePhoto;
            thePhoto = this.getPhoto();
            currentHashCode += Arrays.hashCode(thePhoto);
        }
        return currentHashCode;
    }

    public void toStringFields(StringBuilder stringBuilder) {
        {
            String theAddress;
            theAddress = this.getAddress();
            stringBuilder.append("address=");
            stringBuilder.append(theAddress);
        }
        {
            Boolean theBlueEyes;
            theBlueEyes = this.isBlueEyes();
            stringBuilder.append(", ");
            stringBuilder.append("blueEyes=");
            stringBuilder.append(theBlueEyes);
        }
        {
            String theFamilyName;
            theFamilyName = this.getFamilyName();
            stringBuilder.append(", ");
            stringBuilder.append("familyName=");
            stringBuilder.append(theFamilyName);
        }
        {
            String theGivenName;
            theGivenName = this.getGivenName();
            stringBuilder.append(", ");
            stringBuilder.append("givenName=");
            stringBuilder.append(theGivenName);
        }
        {
            List<String> theMiddleInitials;
            theMiddleInitials = (this.isSetMiddleInitials()?this.getMiddleInitials():null);
            stringBuilder.append(", ");
            stringBuilder.append("middleInitials=");
            stringBuilder.append("<size=");
            stringBuilder.append((this.isSetMiddleInitials()?theMiddleInitials.size():"null"));
            stringBuilder.append(">");
        }
        {
            String thePostCode;
            thePostCode = this.getPostCode();
            stringBuilder.append(", ");
            stringBuilder.append("postCode=");
            stringBuilder.append(thePostCode);
        }
        {
            boolean theSingle;
            theSingle = this.isSingle();
            stringBuilder.append(", ");
            stringBuilder.append("single=");
            stringBuilder.append(theSingle);
        }
        {
            byte[] thePhoto;
            thePhoto = this.getPhoto();
            stringBuilder.append(", ");
            stringBuilder.append("photo=");
            stringBuilder.append("<length=");
            stringBuilder.append((this.isSetPhoto()?thePhoto.length:"null"));
            stringBuilder.append(">");
        }
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getSimpleName());
        stringBuilder.append('@');
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append("[");
        toStringFields(stringBuilder);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

}
