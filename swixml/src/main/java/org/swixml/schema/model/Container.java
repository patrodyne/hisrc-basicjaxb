
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
 * <p>Java class for Container complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Container">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}Component">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="focusCycleRoot" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="focusTraversalPolicy" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="focusTraversalPolicyProvider" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="layout" type="{http://www.w3.org/2001/XMLSchema}string" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Container")
@XmlSeeAlso({
	JComponent.class,
	Window.class
})
public class Container
	extends Component
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "focusCycleRoot")
	protected Boolean focusCycleRoot;
	@XmlAttribute(name = "focusTraversalPolicy")
	protected String focusTraversalPolicy;
	@XmlAttribute(name = "focusTraversalPolicyProvider")
	protected Boolean focusTraversalPolicyProvider;
	@XmlAttribute(name = "layout")
	protected String layout;

	/**
	 * Gets the value of the focusCycleRoot property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isFocusCycleRoot() {
		return focusCycleRoot;
	}

	/**
	 * Sets the value of the focusCycleRoot property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setFocusCycleRoot(Boolean value) {
		this.focusCycleRoot = value;
	}

	/**
	 * Gets the value of the focusTraversalPolicy property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getFocusTraversalPolicy() {
		return focusTraversalPolicy;
	}

	/**
	 * Sets the value of the focusTraversalPolicy property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setFocusTraversalPolicy(String value) {
		this.focusTraversalPolicy = value;
	}

	/**
	 * Gets the value of the focusTraversalPolicyProvider property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isFocusTraversalPolicyProvider() {
		return focusTraversalPolicyProvider;
	}

	/**
	 * Sets the value of the focusTraversalPolicyProvider property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setFocusTraversalPolicyProvider(Boolean value) {
		this.focusTraversalPolicyProvider = value;
	}

	/**
	 * Gets the value of the layout property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getLayout() {
		return layout;
	}

	/**
	 * Sets the value of the layout property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setLayout(String value) {
		this.layout = value;
	}

	@Override
	public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
		int currentHashCode = super.hashCode(locator, strategy);
		{
			boolean theFieldIsSet = (this.focusCycleRoot!= null);
			Boolean theField;
			theField = this.isFocusCycleRoot();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "focusCycleRoot", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.focusTraversalPolicy!= null);
			String theField;
			theField = this.getFocusTraversalPolicy();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "focusTraversalPolicy", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.focusTraversalPolicyProvider!= null);
			Boolean theField;
			theField = this.isFocusTraversalPolicyProvider();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "focusTraversalPolicyProvider", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.layout!= null);
			String theField;
			theField = this.getLayout();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "layout", theField);
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
		final Container that = ((Container) object);
		{
			boolean lhsFieldIsSet = (this.focusCycleRoot!= null);
			boolean rhsFieldIsSet = (that.focusCycleRoot!= null);
			Boolean lhsField;
			lhsField = this.isFocusCycleRoot();
			Boolean rhsField;
			rhsField = that.isFocusCycleRoot();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "focusCycleRoot", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "focusCycleRoot", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.focusTraversalPolicy!= null);
			boolean rhsFieldIsSet = (that.focusTraversalPolicy!= null);
			String lhsField;
			lhsField = this.getFocusTraversalPolicy();
			String rhsField;
			rhsField = that.getFocusTraversalPolicy();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "focusTraversalPolicy", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "focusTraversalPolicy", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.focusTraversalPolicyProvider!= null);
			boolean rhsFieldIsSet = (that.focusTraversalPolicyProvider!= null);
			Boolean lhsField;
			lhsField = this.isFocusTraversalPolicyProvider();
			Boolean rhsField;
			rhsField = that.isFocusTraversalPolicyProvider();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "focusTraversalPolicyProvider", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "focusTraversalPolicyProvider", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.layout!= null);
			boolean rhsFieldIsSet = (that.layout!= null);
			String lhsField;
			lhsField = this.getLayout();
			String rhsField;
			rhsField = that.getLayout();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "layout", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "layout", rhsField);
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
			boolean theFieldIsSet = (this.focusCycleRoot!= null);
			Boolean theField;
			theField = this.isFocusCycleRoot();
			strategy.appendField(locator, this, "focusCycleRoot", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.focusTraversalPolicy!= null);
			String theField;
			theField = this.getFocusTraversalPolicy();
			strategy.appendField(locator, this, "focusTraversalPolicy", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.focusTraversalPolicyProvider!= null);
			Boolean theField;
			theField = this.isFocusTraversalPolicyProvider();
			strategy.appendField(locator, this, "focusTraversalPolicyProvider", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.layout!= null);
			String theField;
			theField = this.getLayout();
			strategy.appendField(locator, this, "layout", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
