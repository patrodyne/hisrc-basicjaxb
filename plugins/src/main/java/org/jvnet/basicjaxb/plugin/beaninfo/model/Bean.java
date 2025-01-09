
package org.jvnet.basicjaxb.plugin.beaninfo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
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
 *       <sequence>
 *         <element name="displayName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="constraint" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="source">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           <attribute name="fields" use="required" type="{http://jvnet.org/basicjaxb/xjc/beaninfo}fieldsType" />
 *                           <attribute name="unique" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="target" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           <attribute name="fields" use="required" type="{http://jvnet.org/basicjaxb/xjc/beaninfo}fieldsType" />
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                 </sequence>
 *                 <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 <attribute name="owner" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </sequence>
 *       <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="expert" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="hidden" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="preferred" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "displayName",
    "description",
    "constraint"
})
@XmlRootElement(name = "bean")
public class Bean implements Serializable, Equals, HashCode, ToString
{

    private static final long serialVersionUID = 20241001L;
    protected String displayName;
    protected String description;
    protected List<Constraint> constraint;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "expert")
    protected Boolean expert;
    @XmlAttribute(name = "hidden")
    protected Boolean hidden;
    @XmlAttribute(name = "preferred")
    protected Boolean preferred;

    /**
     * Gets the value of the displayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the value of the displayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayName(String value) {
        this.displayName = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the constraint property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the constraint property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getConstraint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Constraint }
     * </p>
     * 
     * 
     * @return
     *     The value of the constraint property.
     */
    public List<Constraint> getConstraint() {
        if (constraint == null) {
            constraint = new ArrayList<>();
        }
        return this.constraint;
    }

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
     * Gets the value of the expert property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExpert() {
        return expert;
    }

    /**
     * Sets the value of the expert property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExpert(Boolean value) {
        this.expert = value;
    }

    /**
     * Gets the value of the hidden property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHidden() {
        return hidden;
    }

    /**
     * Sets the value of the hidden property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHidden(Boolean value) {
        this.hidden = value;
    }

    /**
     * Gets the value of the preferred property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPreferred() {
        return preferred;
    }

    /**
     * Sets the value of the preferred property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPreferred(Boolean value) {
        this.preferred = value;
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
            boolean theFieldIsSet = (this.displayName!= null);
            String theField;
            theField = this.getDisplayName();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "displayName", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.description!= null);
            String theField;
            theField = this.getDescription();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "description", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = ((this.constraint!= null)&&(!this.constraint.isEmpty()));
            List<Constraint> theField;
            theField = (((this.constraint!= null)&&(!this.constraint.isEmpty()))?this.getConstraint():null);
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "constraint", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.name!= null);
            String theField;
            theField = this.getName();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "name", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.expert!= null);
            Boolean theField;
            theField = this.isExpert();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "expert", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.hidden!= null);
            Boolean theField;
            theField = this.isHidden();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "hidden", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.preferred!= null);
            Boolean theField;
            theField = this.isPreferred();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "preferred", theField);
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
        final Bean that = ((Bean) object);
        {
            boolean lhsFieldIsSet = (this.displayName!= null);
            boolean rhsFieldIsSet = (that.displayName!= null);
            String lhsField;
            lhsField = this.getDisplayName();
            String rhsField;
            rhsField = that.getDisplayName();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "displayName", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "displayName", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.description!= null);
            boolean rhsFieldIsSet = (that.description!= null);
            String lhsField;
            lhsField = this.getDescription();
            String rhsField;
            rhsField = that.getDescription();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "description", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "description", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = ((this.constraint!= null)&&(!this.constraint.isEmpty()));
            boolean rhsFieldIsSet = ((that.constraint!= null)&&(!that.constraint.isEmpty()));
            List<Constraint> lhsField;
            lhsField = (((this.constraint!= null)&&(!this.constraint.isEmpty()))?this.getConstraint():null);
            List<Constraint> rhsField;
            rhsField = (((that.constraint!= null)&&(!that.constraint.isEmpty()))?that.getConstraint():null);
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "constraint", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "constraint", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
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
            boolean lhsFieldIsSet = (this.expert!= null);
            boolean rhsFieldIsSet = (that.expert!= null);
            Boolean lhsField;
            lhsField = this.isExpert();
            Boolean rhsField;
            rhsField = that.isExpert();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "expert", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "expert", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.hidden!= null);
            boolean rhsFieldIsSet = (that.hidden!= null);
            Boolean lhsField;
            lhsField = this.isHidden();
            Boolean rhsField;
            rhsField = that.isHidden();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "hidden", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "hidden", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.preferred!= null);
            boolean rhsFieldIsSet = (that.preferred!= null);
            Boolean lhsField;
            lhsField = this.isPreferred();
            Boolean rhsField;
            rhsField = that.isPreferred();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "preferred", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "preferred", rhsField);
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
            boolean theFieldIsSet = (this.displayName!= null);
            String theField;
            theField = this.getDisplayName();
            strategy.appendField(locator, this, "displayName", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.description!= null);
            String theField;
            theField = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = ((this.constraint!= null)&&(!this.constraint.isEmpty()));
            List<Constraint> theField;
            theField = (((this.constraint!= null)&&(!this.constraint.isEmpty()))?this.getConstraint():null);
            strategy.appendField(locator, this, "constraint", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.name!= null);
            String theField;
            theField = this.getName();
            strategy.appendField(locator, this, "name", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.expert!= null);
            Boolean theField;
            theField = this.isExpert();
            strategy.appendField(locator, this, "expert", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.hidden!= null);
            Boolean theField;
            theField = this.isHidden();
            strategy.appendField(locator, this, "hidden", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.preferred!= null);
            Boolean theField;
            theField = this.isPreferred();
            strategy.appendField(locator, this, "preferred", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
