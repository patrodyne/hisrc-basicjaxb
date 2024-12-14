
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
 * <p>Java class for JTableBind complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JTableBind">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/swixml}JTable">
 *       <sequence>
 *       </sequence>
 *       <attribute name="action" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="allPropertiesBound" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="bindClass" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="bindList" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="bindWith" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="binding" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="bindingGroup" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="converter" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="dblClickAction" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JTableBind")
public class JTableBind
    extends JTable
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "action")
    protected String action;
    @XmlAttribute(name = "allPropertiesBound")
    protected Boolean allPropertiesBound;
    @XmlAttribute(name = "bindClass")
    protected String bindClass;
    @XmlAttribute(name = "bindList")
    protected String bindList;
    @XmlAttribute(name = "bindWith")
    protected String bindWith;
    @XmlAttribute(name = "binding")
    protected String binding;
    @XmlAttribute(name = "bindingGroup")
    protected String bindingGroup;
    @XmlAttribute(name = "converter")
    protected String converter;
    @XmlAttribute(name = "dblClickAction")
    protected String dblClickAction;

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the allPropertiesBound property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllPropertiesBound() {
        return allPropertiesBound;
    }

    /**
     * Sets the value of the allPropertiesBound property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllPropertiesBound(Boolean value) {
        this.allPropertiesBound = value;
    }

    /**
     * Gets the value of the bindClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindClass() {
        return bindClass;
    }

    /**
     * Sets the value of the bindClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindClass(String value) {
        this.bindClass = value;
    }

    /**
     * Gets the value of the bindList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindList() {
        return bindList;
    }

    /**
     * Sets the value of the bindList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindList(String value) {
        this.bindList = value;
    }

    /**
     * Gets the value of the bindWith property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindWith() {
        return bindWith;
    }

    /**
     * Sets the value of the bindWith property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindWith(String value) {
        this.bindWith = value;
    }

    /**
     * Gets the value of the binding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBinding() {
        return binding;
    }

    /**
     * Sets the value of the binding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBinding(String value) {
        this.binding = value;
    }

    /**
     * Gets the value of the bindingGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindingGroup() {
        return bindingGroup;
    }

    /**
     * Sets the value of the bindingGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindingGroup(String value) {
        this.bindingGroup = value;
    }

    /**
     * Gets the value of the converter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConverter() {
        return converter;
    }

    /**
     * Sets the value of the converter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConverter(String value) {
        this.converter = value;
    }

    /**
     * Gets the value of the dblClickAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDblClickAction() {
        return dblClickAction;
    }

    /**
     * Sets the value of the dblClickAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDblClickAction(String value) {
        this.dblClickAction = value;
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            boolean theFieldIsSet = (this.action!= null);
            String theField;
            theField = this.getAction();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "action", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.allPropertiesBound!= null);
            Boolean theField;
            theField = this.isAllPropertiesBound();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "allPropertiesBound", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.bindClass!= null);
            String theField;
            theField = this.getBindClass();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "bindClass", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.bindList!= null);
            String theField;
            theField = this.getBindList();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "bindList", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.bindWith!= null);
            String theField;
            theField = this.getBindWith();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "bindWith", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.binding!= null);
            String theField;
            theField = this.getBinding();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "binding", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.bindingGroup!= null);
            String theField;
            theField = this.getBindingGroup();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "bindingGroup", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.converter!= null);
            String theField;
            theField = this.getConverter();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "converter", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.dblClickAction!= null);
            String theField;
            theField = this.getDblClickAction();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "dblClickAction", theField);
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
        final JTableBind that = ((JTableBind) object);
        {
            boolean lhsFieldIsSet = (this.action!= null);
            boolean rhsFieldIsSet = (that.action!= null);
            String lhsField;
            lhsField = this.getAction();
            String rhsField;
            rhsField = that.getAction();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "action", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "action", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.allPropertiesBound!= null);
            boolean rhsFieldIsSet = (that.allPropertiesBound!= null);
            Boolean lhsField;
            lhsField = this.isAllPropertiesBound();
            Boolean rhsField;
            rhsField = that.isAllPropertiesBound();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "allPropertiesBound", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "allPropertiesBound", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.bindClass!= null);
            boolean rhsFieldIsSet = (that.bindClass!= null);
            String lhsField;
            lhsField = this.getBindClass();
            String rhsField;
            rhsField = that.getBindClass();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "bindClass", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "bindClass", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.bindList!= null);
            boolean rhsFieldIsSet = (that.bindList!= null);
            String lhsField;
            lhsField = this.getBindList();
            String rhsField;
            rhsField = that.getBindList();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "bindList", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "bindList", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.bindWith!= null);
            boolean rhsFieldIsSet = (that.bindWith!= null);
            String lhsField;
            lhsField = this.getBindWith();
            String rhsField;
            rhsField = that.getBindWith();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "bindWith", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "bindWith", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.binding!= null);
            boolean rhsFieldIsSet = (that.binding!= null);
            String lhsField;
            lhsField = this.getBinding();
            String rhsField;
            rhsField = that.getBinding();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "binding", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "binding", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.bindingGroup!= null);
            boolean rhsFieldIsSet = (that.bindingGroup!= null);
            String lhsField;
            lhsField = this.getBindingGroup();
            String rhsField;
            rhsField = that.getBindingGroup();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "bindingGroup", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "bindingGroup", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.converter!= null);
            boolean rhsFieldIsSet = (that.converter!= null);
            String lhsField;
            lhsField = this.getConverter();
            String rhsField;
            rhsField = that.getConverter();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "converter", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "converter", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.dblClickAction!= null);
            boolean rhsFieldIsSet = (that.dblClickAction!= null);
            String lhsField;
            lhsField = this.getDblClickAction();
            String rhsField;
            rhsField = that.getDblClickAction();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "dblClickAction", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "dblClickAction", rhsField);
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
            boolean theFieldIsSet = (this.action!= null);
            String theField;
            theField = this.getAction();
            strategy.appendField(locator, this, "action", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.allPropertiesBound!= null);
            Boolean theField;
            theField = this.isAllPropertiesBound();
            strategy.appendField(locator, this, "allPropertiesBound", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.bindClass!= null);
            String theField;
            theField = this.getBindClass();
            strategy.appendField(locator, this, "bindClass", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.bindList!= null);
            String theField;
            theField = this.getBindList();
            strategy.appendField(locator, this, "bindList", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.bindWith!= null);
            String theField;
            theField = this.getBindWith();
            strategy.appendField(locator, this, "bindWith", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.binding!= null);
            String theField;
            theField = this.getBinding();
            strategy.appendField(locator, this, "binding", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.bindingGroup!= null);
            String theField;
            theField = this.getBindingGroup();
            strategy.appendField(locator, this, "bindingGroup", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.converter!= null);
            String theField;
            theField = this.getConverter();
            strategy.appendField(locator, this, "converter", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.dblClickAction!= null);
            String theField;
            theField = this.getDblClickAction();
            strategy.appendField(locator, this, "dblClickAction", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
