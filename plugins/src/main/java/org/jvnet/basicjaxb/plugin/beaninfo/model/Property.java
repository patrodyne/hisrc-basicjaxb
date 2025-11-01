
package org.jvnet.basicjaxb.plugin.beaninfo.model;

import java.io.Serializable;
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
 *         <element name="rendererClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="editorClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="readMethodName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="writeMethodName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="index" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       <attribute name="access" type="{urn:jvnet.org:basicjaxb:xjc:beaninfo}accessType" />
 *       <attribute name="alignment" type="{urn:jvnet.org:basicjaxb:xjc:beaninfo}alignmentType" />
 *       <attribute name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="resizable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="maxWidth" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       <attribute name="minWidth" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       <attribute name="preferredWidth" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       <attribute name="bound" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="constrained" type="{http://www.w3.org/2001/XMLSchema}boolean" />
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
    "rendererClass",
    "editorClass",
    "readMethodName",
    "writeMethodName"
})
@XmlRootElement(name = "property")
public class Property implements Serializable, Equals, HashCode, ToString
{

    private static final long serialVersionUID = 20241001L;
    protected String displayName;
    protected String description;
    protected String rendererClass;
    protected String editorClass;
    protected String readMethodName;
    protected String writeMethodName;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "index")
    protected Integer index;
    @XmlAttribute(name = "access")
    protected AccessType access;
    @XmlAttribute(name = "alignment")
    protected AlignmentType alignment;
    @XmlAttribute(name = "editable")
    protected Boolean editable;
    @XmlAttribute(name = "resizable")
    protected Boolean resizable;
    @XmlAttribute(name = "maxWidth")
    protected Integer maxWidth;
    @XmlAttribute(name = "minWidth")
    protected Integer minWidth;
    @XmlAttribute(name = "preferredWidth")
    protected Integer preferredWidth;
    @XmlAttribute(name = "bound")
    protected Boolean bound;
    @XmlAttribute(name = "constrained")
    protected Boolean constrained;
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
     * Gets the value of the rendererClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRendererClass() {
        return rendererClass;
    }

    /**
     * Sets the value of the rendererClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRendererClass(String value) {
        this.rendererClass = value;
    }

    /**
     * Gets the value of the editorClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEditorClass() {
        return editorClass;
    }

    /**
     * Sets the value of the editorClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEditorClass(String value) {
        this.editorClass = value;
    }

    /**
     * Gets the value of the readMethodName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReadMethodName() {
        return readMethodName;
    }

    /**
     * Sets the value of the readMethodName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReadMethodName(String value) {
        this.readMethodName = value;
    }

    /**
     * Gets the value of the writeMethodName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWriteMethodName() {
        return writeMethodName;
    }

    /**
     * Sets the value of the writeMethodName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWriteMethodName(String value) {
        this.writeMethodName = value;
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
     * Gets the value of the index property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Sets the value of the index property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIndex(Integer value) {
        this.index = value;
    }

    /**
     * Gets the value of the access property.
     * 
     * @return
     *     possible object is
     *     {@link AccessType }
     *     
     */
    public AccessType getAccess() {
        return access;
    }

    /**
     * Sets the value of the access property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessType }
     *     
     */
    public void setAccess(AccessType value) {
        this.access = value;
    }

    /**
     * Gets the value of the alignment property.
     * 
     * @return
     *     possible object is
     *     {@link AlignmentType }
     *     
     */
    public AlignmentType getAlignment() {
        return alignment;
    }

    /**
     * Sets the value of the alignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlignmentType }
     *     
     */
    public void setAlignment(AlignmentType value) {
        this.alignment = value;
    }

    /**
     * Gets the value of the editable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEditable() {
        return editable;
    }

    /**
     * Sets the value of the editable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEditable(Boolean value) {
        this.editable = value;
    }

    /**
     * Gets the value of the resizable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isResizable() {
        return resizable;
    }

    /**
     * Sets the value of the resizable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setResizable(Boolean value) {
        this.resizable = value;
    }

    /**
     * Gets the value of the maxWidth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxWidth() {
        return maxWidth;
    }

    /**
     * Sets the value of the maxWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxWidth(Integer value) {
        this.maxWidth = value;
    }

    /**
     * Gets the value of the minWidth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinWidth() {
        return minWidth;
    }

    /**
     * Sets the value of the minWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinWidth(Integer value) {
        this.minWidth = value;
    }

    /**
     * Gets the value of the preferredWidth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPreferredWidth() {
        return preferredWidth;
    }

    /**
     * Sets the value of the preferredWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPreferredWidth(Integer value) {
        this.preferredWidth = value;
    }

    /**
     * Gets the value of the bound property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBound() {
        return bound;
    }

    /**
     * Sets the value of the bound property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBound(Boolean value) {
        this.bound = value;
    }

    /**
     * Gets the value of the constrained property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isConstrained() {
        return constrained;
    }

    /**
     * Sets the value of the constrained property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setConstrained(Boolean value) {
        this.constrained = value;
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
            boolean theFieldIsSet = (this.rendererClass!= null);
            String theField;
            theField = this.getRendererClass();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "rendererClass", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.editorClass!= null);
            String theField;
            theField = this.getEditorClass();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "editorClass", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.readMethodName!= null);
            String theField;
            theField = this.getReadMethodName();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "readMethodName", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.writeMethodName!= null);
            String theField;
            theField = this.getWriteMethodName();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "writeMethodName", theField);
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
            boolean theFieldIsSet = (this.index!= null);
            Integer theField;
            theField = this.getIndex();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "index", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.access!= null);
            AccessType theField;
            theField = this.getAccess();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "access", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.alignment!= null);
            AlignmentType theField;
            theField = this.getAlignment();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "alignment", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.editable!= null);
            Boolean theField;
            theField = this.isEditable();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "editable", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.resizable!= null);
            Boolean theField;
            theField = this.isResizable();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "resizable", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.maxWidth!= null);
            Integer theField;
            theField = this.getMaxWidth();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "maxWidth", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.minWidth!= null);
            Integer theField;
            theField = this.getMinWidth();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "minWidth", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.preferredWidth!= null);
            Integer theField;
            theField = this.getPreferredWidth();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "preferredWidth", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.bound!= null);
            Boolean theField;
            theField = this.isBound();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "bound", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.constrained!= null);
            Boolean theField;
            theField = this.isConstrained();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "constrained", theField);
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
        final Property that = ((Property) object);
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
            boolean lhsFieldIsSet = (this.rendererClass!= null);
            boolean rhsFieldIsSet = (that.rendererClass!= null);
            String lhsField;
            lhsField = this.getRendererClass();
            String rhsField;
            rhsField = that.getRendererClass();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "rendererClass", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "rendererClass", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.editorClass!= null);
            boolean rhsFieldIsSet = (that.editorClass!= null);
            String lhsField;
            lhsField = this.getEditorClass();
            String rhsField;
            rhsField = that.getEditorClass();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "editorClass", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "editorClass", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.readMethodName!= null);
            boolean rhsFieldIsSet = (that.readMethodName!= null);
            String lhsField;
            lhsField = this.getReadMethodName();
            String rhsField;
            rhsField = that.getReadMethodName();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "readMethodName", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "readMethodName", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.writeMethodName!= null);
            boolean rhsFieldIsSet = (that.writeMethodName!= null);
            String lhsField;
            lhsField = this.getWriteMethodName();
            String rhsField;
            rhsField = that.getWriteMethodName();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "writeMethodName", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "writeMethodName", rhsField);
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
            boolean lhsFieldIsSet = (this.index!= null);
            boolean rhsFieldIsSet = (that.index!= null);
            Integer lhsField;
            lhsField = this.getIndex();
            Integer rhsField;
            rhsField = that.getIndex();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "index", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "index", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.access!= null);
            boolean rhsFieldIsSet = (that.access!= null);
            AccessType lhsField;
            lhsField = this.getAccess();
            AccessType rhsField;
            rhsField = that.getAccess();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "access", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "access", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.alignment!= null);
            boolean rhsFieldIsSet = (that.alignment!= null);
            AlignmentType lhsField;
            lhsField = this.getAlignment();
            AlignmentType rhsField;
            rhsField = that.getAlignment();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "alignment", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "alignment", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.editable!= null);
            boolean rhsFieldIsSet = (that.editable!= null);
            Boolean lhsField;
            lhsField = this.isEditable();
            Boolean rhsField;
            rhsField = that.isEditable();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "editable", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "editable", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.resizable!= null);
            boolean rhsFieldIsSet = (that.resizable!= null);
            Boolean lhsField;
            lhsField = this.isResizable();
            Boolean rhsField;
            rhsField = that.isResizable();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "resizable", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "resizable", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.maxWidth!= null);
            boolean rhsFieldIsSet = (that.maxWidth!= null);
            Integer lhsField;
            lhsField = this.getMaxWidth();
            Integer rhsField;
            rhsField = that.getMaxWidth();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "maxWidth", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "maxWidth", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.minWidth!= null);
            boolean rhsFieldIsSet = (that.minWidth!= null);
            Integer lhsField;
            lhsField = this.getMinWidth();
            Integer rhsField;
            rhsField = that.getMinWidth();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "minWidth", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "minWidth", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.preferredWidth!= null);
            boolean rhsFieldIsSet = (that.preferredWidth!= null);
            Integer lhsField;
            lhsField = this.getPreferredWidth();
            Integer rhsField;
            rhsField = that.getPreferredWidth();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "preferredWidth", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "preferredWidth", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.bound!= null);
            boolean rhsFieldIsSet = (that.bound!= null);
            Boolean lhsField;
            lhsField = this.isBound();
            Boolean rhsField;
            rhsField = that.isBound();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "bound", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "bound", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.constrained!= null);
            boolean rhsFieldIsSet = (that.constrained!= null);
            Boolean lhsField;
            lhsField = this.isConstrained();
            Boolean rhsField;
            rhsField = that.isConstrained();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "constrained", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "constrained", rhsField);
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
            boolean theFieldIsSet = (this.rendererClass!= null);
            String theField;
            theField = this.getRendererClass();
            strategy.appendField(locator, this, "rendererClass", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.editorClass!= null);
            String theField;
            theField = this.getEditorClass();
            strategy.appendField(locator, this, "editorClass", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.readMethodName!= null);
            String theField;
            theField = this.getReadMethodName();
            strategy.appendField(locator, this, "readMethodName", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.writeMethodName!= null);
            String theField;
            theField = this.getWriteMethodName();
            strategy.appendField(locator, this, "writeMethodName", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.name!= null);
            String theField;
            theField = this.getName();
            strategy.appendField(locator, this, "name", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.index!= null);
            Integer theField;
            theField = this.getIndex();
            strategy.appendField(locator, this, "index", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.access!= null);
            AccessType theField;
            theField = this.getAccess();
            strategy.appendField(locator, this, "access", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.alignment!= null);
            AlignmentType theField;
            theField = this.getAlignment();
            strategy.appendField(locator, this, "alignment", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.editable!= null);
            Boolean theField;
            theField = this.isEditable();
            strategy.appendField(locator, this, "editable", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.resizable!= null);
            Boolean theField;
            theField = this.isResizable();
            strategy.appendField(locator, this, "resizable", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.maxWidth!= null);
            Integer theField;
            theField = this.getMaxWidth();
            strategy.appendField(locator, this, "maxWidth", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.minWidth!= null);
            Integer theField;
            theField = this.getMinWidth();
            strategy.appendField(locator, this, "minWidth", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.preferredWidth!= null);
            Integer theField;
            theField = this.getPreferredWidth();
            strategy.appendField(locator, this, "preferredWidth", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.bound!= null);
            Boolean theField;
            theField = this.isBound();
            strategy.appendField(locator, this, "bound", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.constrained!= null);
            Boolean theField;
            theField = this.isConstrained();
            strategy.appendField(locator, this, "constrained", buffer, theField, theFieldIsSet);
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
