
package org.jvnet.basicjaxb.plugin.beaninfo.model;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
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
 *         <element name="source">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 <attribute name="fields" use="required" type="{http://jvnet.org/basicjaxb/xjc/beaninfo}fieldsType" />
 *                 <attribute name="unique" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="target" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 <attribute name="fields" use="required" type="{http://jvnet.org/basicjaxb/xjc/beaninfo}fieldsType" />
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </sequence>
 *       <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="owner" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "source",
    "target"
})
public class Constraint implements Serializable, Equals, HashCode, ToString
{

    private static final long serialVersionUID = 20241001L;
    @XmlElement(required = true)
    protected Source source;
    protected Target target;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "owner")
    protected String owner;

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link Source }
     *     
     */
    public Source getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link Source }
     *     
     */
    public void setSource(Source value) {
        this.source = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link Target }
     *     
     */
    public Target getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link Target }
     *     
     */
    public void setTarget(Target value) {
        this.target = value;
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
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
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
            boolean theFieldIsSet = (this.source!= null);
            Source theField;
            theField = this.getSource();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "source", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.target!= null);
            Target theField;
            theField = this.getTarget();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "target", theField);
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
            boolean theFieldIsSet = (this.owner!= null);
            String theField;
            theField = this.getOwner();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "owner", theField);
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
        final Constraint that = ((Constraint) object);
        {
            boolean lhsFieldIsSet = (this.source!= null);
            boolean rhsFieldIsSet = (that.source!= null);
            Source lhsField;
            lhsField = this.getSource();
            Source rhsField;
            rhsField = that.getSource();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "source", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "source", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.target!= null);
            boolean rhsFieldIsSet = (that.target!= null);
            Target lhsField;
            lhsField = this.getTarget();
            Target rhsField;
            rhsField = that.getTarget();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "target", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "target", rhsField);
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
            boolean lhsFieldIsSet = (this.owner!= null);
            boolean rhsFieldIsSet = (that.owner!= null);
            String lhsField;
            lhsField = this.getOwner();
            String rhsField;
            rhsField = that.getOwner();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "owner", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "owner", rhsField);
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
            boolean theFieldIsSet = (this.source!= null);
            Source theField;
            theField = this.getSource();
            strategy.appendField(locator, this, "source", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.target!= null);
            Target theField;
            theField = this.getTarget();
            strategy.appendField(locator, this, "target", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.name!= null);
            String theField;
            theField = this.getName();
            strategy.appendField(locator, this, "name", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.owner!= null);
            String theField;
            theField = this.getOwner();
            strategy.appendField(locator, this, "owner", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
