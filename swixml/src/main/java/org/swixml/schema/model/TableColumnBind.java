
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
 * <p>Java class for TableColumnBind complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="TableColumnBind">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}TableColumn">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="bindWith" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TableColumnBind")
public class TableColumnBind
	extends TableColumn
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "bindWith")
	protected String bindWith;
	@XmlAttribute(name = "editable")
	protected Boolean editable;
	@XmlAttribute(name = "type")
	protected String type;

	/**
	 * Gets the value of the bindWith property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getBindWith() {
		return bindWith;
	}

	/**
	 * Sets the value of the bindWith property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setBindWith(String value) {
		this.bindWith = value;
	}

	/**
	 * Gets the value of the editable property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isEditable() {
		return editable;
	}

	/**
	 * Sets the value of the editable property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setEditable(Boolean value) {
		this.editable = value;
	}

	/**
	 * Gets the value of the type property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setType(String value) {
		this.type = value;
	}

	@Override
	public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
		int currentHashCode = super.hashCode(locator, strategy);
		{
			boolean theFieldIsSet = (this.bindWith!= null);
			String theField;
			theField = this.getBindWith();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "bindWith", theField);
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
			boolean theFieldIsSet = (this.type!= null);
			String theField;
			theField = this.getType();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "type", theField);
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
		final TableColumnBind that = ((TableColumnBind) object);
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
			boolean lhsFieldIsSet = (this.type!= null);
			boolean rhsFieldIsSet = (that.type!= null);
			String lhsField;
			lhsField = this.getType();
			String rhsField;
			rhsField = that.getType();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "type", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "type", rhsField);
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
			boolean theFieldIsSet = (this.bindWith!= null);
			String theField;
			theField = this.getBindWith();
			strategy.appendField(locator, this, "bindWith", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.editable!= null);
			Boolean theField;
			theField = this.isEditable();
			strategy.appendField(locator, this, "editable", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.type!= null);
			String theField;
			theField = this.getType();
			strategy.appendField(locator, this, "type", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
