
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
 * <p>Java class for JTreeBind complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JTreeBind">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}JTree">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="action" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="bindWith" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="closedIcon" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="dblClickAction" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="leafIcon" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="openIcon" type="{http://www.w3.org/2001/XMLSchema}string" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JTreeBind")
public class JTreeBind
	extends JTree
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "action")
	protected String action;
	@XmlAttribute(name = "bindWith")
	protected String bindWith;
	@XmlAttribute(name = "closedIcon")
	protected String closedIcon;
	@XmlAttribute(name = "dblClickAction")
	protected String dblClickAction;
	@XmlAttribute(name = "leafIcon")
	protected String leafIcon;
	@XmlAttribute(name = "openIcon")
	protected String openIcon;

	/**
	 * Gets the value of the action property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the value of the action property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setAction(String value) {
		this.action = value;
	}

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
	 * Gets the value of the closedIcon property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getClosedIcon() {
		return closedIcon;
	}

	/**
	 * Sets the value of the closedIcon property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setClosedIcon(String value) {
		this.closedIcon = value;
	}

	/**
	 * Gets the value of the dblClickAction property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getDblClickAction() {
		return dblClickAction;
	}

	/**
	 * Sets the value of the dblClickAction property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setDblClickAction(String value) {
		this.dblClickAction = value;
	}

	/**
	 * Gets the value of the leafIcon property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getLeafIcon() {
		return leafIcon;
	}

	/**
	 * Sets the value of the leafIcon property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setLeafIcon(String value) {
		this.leafIcon = value;
	}

	/**
	 * Gets the value of the openIcon property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getOpenIcon() {
		return openIcon;
	}

	/**
	 * Sets the value of the openIcon property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setOpenIcon(String value) {
		this.openIcon = value;
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
			boolean theFieldIsSet = (this.bindWith!= null);
			String theField;
			theField = this.getBindWith();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "bindWith", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.closedIcon!= null);
			String theField;
			theField = this.getClosedIcon();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "closedIcon", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.dblClickAction!= null);
			String theField;
			theField = this.getDblClickAction();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "dblClickAction", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.leafIcon!= null);
			String theField;
			theField = this.getLeafIcon();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "leafIcon", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.openIcon!= null);
			String theField;
			theField = this.getOpenIcon();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "openIcon", theField);
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
		final JTreeBind that = ((JTreeBind) object);
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
			boolean lhsFieldIsSet = (this.closedIcon!= null);
			boolean rhsFieldIsSet = (that.closedIcon!= null);
			String lhsField;
			lhsField = this.getClosedIcon();
			String rhsField;
			rhsField = that.getClosedIcon();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "closedIcon", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "closedIcon", rhsField);
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
		{
			boolean lhsFieldIsSet = (this.leafIcon!= null);
			boolean rhsFieldIsSet = (that.leafIcon!= null);
			String lhsField;
			lhsField = this.getLeafIcon();
			String rhsField;
			rhsField = that.getLeafIcon();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "leafIcon", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "leafIcon", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.openIcon!= null);
			boolean rhsFieldIsSet = (that.openIcon!= null);
			String lhsField;
			lhsField = this.getOpenIcon();
			String rhsField;
			rhsField = that.getOpenIcon();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "openIcon", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "openIcon", rhsField);
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
			boolean theFieldIsSet = (this.bindWith!= null);
			String theField;
			theField = this.getBindWith();
			strategy.appendField(locator, this, "bindWith", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.closedIcon!= null);
			String theField;
			theField = this.getClosedIcon();
			strategy.appendField(locator, this, "closedIcon", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.dblClickAction!= null);
			String theField;
			theField = this.getDblClickAction();
			strategy.appendField(locator, this, "dblClickAction", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.leafIcon!= null);
			String theField;
			theField = this.getLeafIcon();
			strategy.appendField(locator, this, "leafIcon", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.openIcon!= null);
			String theField;
			theField = this.getOpenIcon();
			strategy.appendField(locator, this, "openIcon", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
