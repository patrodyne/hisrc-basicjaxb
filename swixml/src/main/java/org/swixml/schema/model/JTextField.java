
package org.swixml.schema.model;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.basicjaxb.lang.EqualsStrategy;
import org.jvnet.basicjaxb.lang.HashCodeStrategy;
import org.jvnet.basicjaxb.lang.ToStringStrategy;
import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.jvnet.basicjaxb.locator.util.LocatorUtils;


/**
 * <p>Java class for JTextField complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JTextField">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}JTextComponent">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="action" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="actionCommand" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="columns" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="horizontalAlignment" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="scrollOffset" type="{http://www.w3.org/2001/XMLSchema}string" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JTextField")
@XmlSeeAlso({
	JFormattedTextField.class,
	JTextFieldBind.class,
	JPasswordField.class
})
public class JTextField
	extends JTextComponent
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "action")
	protected String action;
	@XmlAttribute(name = "actionCommand")
	protected String actionCommand;
	@XmlAttribute(name = "columns")
	protected String columns;
	@XmlAttribute(name = "horizontalAlignment")
	protected String horizontalAlignment;
	@XmlAttribute(name = "scrollOffset")
	protected String scrollOffset;

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
	 * Gets the value of the actionCommand property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getActionCommand() {
		return actionCommand;
	}

	/**
	 * Sets the value of the actionCommand property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setActionCommand(String value) {
		this.actionCommand = value;
	}

	/**
	 * Gets the value of the columns property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getColumns() {
		return columns;
	}

	/**
	 * Sets the value of the columns property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setColumns(String value) {
		this.columns = value;
	}

	/**
	 * Gets the value of the horizontalAlignment property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getHorizontalAlignment() {
		return horizontalAlignment;
	}

	/**
	 * Sets the value of the horizontalAlignment property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setHorizontalAlignment(String value) {
		this.horizontalAlignment = value;
	}

	/**
	 * Gets the value of the scrollOffset property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getScrollOffset() {
		return scrollOffset;
	}

	/**
	 * Sets the value of the scrollOffset property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setScrollOffset(String value) {
		this.scrollOffset = value;
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
			boolean theFieldIsSet = (this.actionCommand!= null);
			String theField;
			theField = this.getActionCommand();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "actionCommand", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.columns!= null);
			String theField;
			theField = this.getColumns();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "columns", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.horizontalAlignment!= null);
			String theField;
			theField = this.getHorizontalAlignment();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "horizontalAlignment", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.scrollOffset!= null);
			String theField;
			theField = this.getScrollOffset();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "scrollOffset", theField);
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
		final JTextField that = ((JTextField) object);
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
			boolean lhsFieldIsSet = (this.actionCommand!= null);
			boolean rhsFieldIsSet = (that.actionCommand!= null);
			String lhsField;
			lhsField = this.getActionCommand();
			String rhsField;
			rhsField = that.getActionCommand();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "actionCommand", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "actionCommand", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.columns!= null);
			boolean rhsFieldIsSet = (that.columns!= null);
			String lhsField;
			lhsField = this.getColumns();
			String rhsField;
			rhsField = that.getColumns();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "columns", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "columns", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.horizontalAlignment!= null);
			boolean rhsFieldIsSet = (that.horizontalAlignment!= null);
			String lhsField;
			lhsField = this.getHorizontalAlignment();
			String rhsField;
			rhsField = that.getHorizontalAlignment();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "horizontalAlignment", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "horizontalAlignment", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.scrollOffset!= null);
			boolean rhsFieldIsSet = (that.scrollOffset!= null);
			String lhsField;
			lhsField = this.getScrollOffset();
			String rhsField;
			rhsField = that.getScrollOffset();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "scrollOffset", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "scrollOffset", rhsField);
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
			boolean theFieldIsSet = (this.actionCommand!= null);
			String theField;
			theField = this.getActionCommand();
			strategy.appendField(locator, this, "actionCommand", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.columns!= null);
			String theField;
			theField = this.getColumns();
			strategy.appendField(locator, this, "columns", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.horizontalAlignment!= null);
			String theField;
			theField = this.getHorizontalAlignment();
			strategy.appendField(locator, this, "horizontalAlignment", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.scrollOffset!= null);
			String theField;
			theField = this.getScrollOffset();
			strategy.appendField(locator, this, "scrollOffset", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
