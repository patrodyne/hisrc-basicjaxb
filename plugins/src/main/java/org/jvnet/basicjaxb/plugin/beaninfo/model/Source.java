
package org.jvnet.basicjaxb.plugin.beaninfo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
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


/**
 * <p>Java class for anonymous complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="fields" use="required" type="{urn:jvnet.org:basicjaxb:xjc:beaninfo}fieldsType" />
 *       <attribute name="unique" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Source implements Serializable, Equals, HashCode, ToString
{

    private static final long serialVersionUID = 20241001L;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "fields", required = true)
    protected List<String> fields;
    @XmlAttribute(name = "unique", required = true)
    protected boolean unique;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the fields property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fields property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getFields().add(newItem);
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
     *     The value of the fields property.
     */
    public List<String> getFields() {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        return this.fields;
    }

    /**
     * Gets the value of the unique property.
     * 
     */
    public boolean isUnique() {
        return unique;
    }

    /**
     * Sets the value of the unique property.
     * 
     */
    public void setUnique(boolean value) {
        this.unique = value;
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
            boolean theFieldIsSet = (this.name!= null);
            String theField;
            theField = this.getName();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "name", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = ((this.fields!= null)&&(!this.fields.isEmpty()));
            List<String> theField;
            theField = (((this.fields!= null)&&(!this.fields.isEmpty()))?this.getFields():null);
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "fields", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = true;
            boolean theField;
            theField = this.isUnique();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "unique", theField);
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
        final Source that = ((Source) object);
        {
            boolean lhsFieldIsSet = (this.name!= null);
            boolean rhsFieldIsSet = (that.name!= null);
            String lhsField;
            lhsField = this.getName();
            String rhsField;
            rhsField = that.getName();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "name", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "name", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = ((this.fields!= null)&&(!this.fields.isEmpty()));
            boolean rhsFieldIsSet = ((that.fields!= null)&&(!that.fields.isEmpty()));
            List<String> lhsField;
            lhsField = (((this.fields!= null)&&(!this.fields.isEmpty()))?this.getFields():null);
            List<String> rhsField;
            rhsField = (((that.fields!= null)&&(!that.fields.isEmpty()))?that.getFields():null);
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "fields", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "fields", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = true;
            boolean rhsFieldIsSet = true;
            boolean lhsField;
            lhsField = this.isUnique();
            boolean rhsField;
            rhsField = that.isUnique();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "unique", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "unique", rhsField);
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
            boolean theFieldIsSet = (this.name!= null);
            String theField;
            theField = this.getName();
            strategy.appendField(locator, this, "name", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = ((this.fields!= null)&&(!this.fields.isEmpty()));
            List<String> theField;
            theField = (((this.fields!= null)&&(!this.fields.isEmpty()))?this.getFields():null);
            strategy.appendField(locator, this, "fields", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = true;
            boolean theField;
            theField = this.isUnique();
            strategy.appendField(locator, this, "unique", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
