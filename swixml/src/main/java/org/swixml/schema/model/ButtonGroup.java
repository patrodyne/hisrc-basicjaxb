
package org.swixml.schema.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlMixed;
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
 * <p>Java class for ButtonGroup complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="ButtonGroup">
 *	 <complexContent>
 *	   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *		 <sequence maxOccurs="unbounded" minOccurs="0">
 *		   <any/>
 *		 </sequence>
 *		 <attribute name="bundle" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="constraints" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="include" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="locale" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="plaf" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="plafTheme" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="refid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *	   </restriction>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ButtonGroup", propOrder = {
	"content"
})
public class ButtonGroup implements Serializable, Equals, HashCode, ToString
{

	private static final long serialVersionUID = 20240701L;
	@XmlMixed
	@XmlAnyElement(lax = true)
	protected List<Object> content;
	@XmlAttribute(name = "bundle")
	protected String bundle;
	@XmlAttribute(name = "constraints")
	protected String constraints;
	@XmlAttribute(name = "id")
	protected String id;
	@XmlAttribute(name = "include")
	protected String include;
	@XmlAttribute(name = "locale")
	protected String locale;
	@XmlAttribute(name = "plaf")
	protected String plaf;
	@XmlAttribute(name = "plafTheme")
	protected String plafTheme;
	@XmlAttribute(name = "refid")
	protected String refid;

	/**
	 * Gets the value of the content property.
	 * 
	 * <p>This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the content property.</p>
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * </p>
	 * <pre>
	 * getContent().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Object }
	 * {@link String }
	 * </p>
	 * 
	 * 
	 * @return
	 *	   The value of the content property.
	 */
	public List<Object> getContent() {
		if (content == null) {
			content = new ArrayList<>();
		}
		return this.content;
	}

	/**
	 * Gets the value of the bundle property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getBundle() {
		return bundle;
	}

	/**
	 * Sets the value of the bundle property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setBundle(String value) {
		this.bundle = value;
	}

	/**
	 * Gets the value of the constraints property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getConstraints() {
		return constraints;
	}

	/**
	 * Sets the value of the constraints property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setConstraints(String value) {
		this.constraints = value;
	}

	/**
	 * Gets the value of the id property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Gets the value of the include property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getInclude() {
		return include;
	}

	/**
	 * Sets the value of the include property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setInclude(String value) {
		this.include = value;
	}

	/**
	 * Gets the value of the locale property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * Sets the value of the locale property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setLocale(String value) {
		this.locale = value;
	}

	/**
	 * Gets the value of the plaf property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getPlaf() {
		return plaf;
	}

	/**
	 * Sets the value of the plaf property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setPlaf(String value) {
		this.plaf = value;
	}

	/**
	 * Gets the value of the plafTheme property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getPlafTheme() {
		return plafTheme;
	}

	/**
	 * Sets the value of the plafTheme property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setPlafTheme(String value) {
		this.plafTheme = value;
	}

	/**
	 * Gets the value of the refid property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getRefid() {
		return refid;
	}

	/**
	 * Sets the value of the refid property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setRefid(String value) {
		this.refid = value;
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
			boolean theFieldIsSet = ((this.content!= null)&&(!this.content.isEmpty()));
			List<Object> theField;
			theField = (((this.content!= null)&&(!this.content.isEmpty()))?this.getContent():null);
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "content", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.bundle!= null);
			String theField;
			theField = this.getBundle();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "bundle", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.constraints!= null);
			String theField;
			theField = this.getConstraints();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "constraints", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.id!= null);
			String theField;
			theField = this.getId();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "id", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.include!= null);
			String theField;
			theField = this.getInclude();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "include", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.locale!= null);
			String theField;
			theField = this.getLocale();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "locale", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.plaf!= null);
			String theField;
			theField = this.getPlaf();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "plaf", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.plafTheme!= null);
			String theField;
			theField = this.getPlafTheme();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "plafTheme", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.refid!= null);
			String theField;
			theField = this.getRefid();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "refid", theField);
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
		final ButtonGroup that = ((ButtonGroup) object);
		{
			boolean lhsFieldIsSet = ((this.content!= null)&&(!this.content.isEmpty()));
			boolean rhsFieldIsSet = ((that.content!= null)&&(!that.content.isEmpty()));
			List<Object> lhsField;
			lhsField = (((this.content!= null)&&(!this.content.isEmpty()))?this.getContent():null);
			List<Object> rhsField;
			rhsField = (((that.content!= null)&&(!that.content.isEmpty()))?that.getContent():null);
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "content", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "content", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.bundle!= null);
			boolean rhsFieldIsSet = (that.bundle!= null);
			String lhsField;
			lhsField = this.getBundle();
			String rhsField;
			rhsField = that.getBundle();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "bundle", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "bundle", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.constraints!= null);
			boolean rhsFieldIsSet = (that.constraints!= null);
			String lhsField;
			lhsField = this.getConstraints();
			String rhsField;
			rhsField = that.getConstraints();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "constraints", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "constraints", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.id!= null);
			boolean rhsFieldIsSet = (that.id!= null);
			String lhsField;
			lhsField = this.getId();
			String rhsField;
			rhsField = that.getId();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "id", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "id", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.include!= null);
			boolean rhsFieldIsSet = (that.include!= null);
			String lhsField;
			lhsField = this.getInclude();
			String rhsField;
			rhsField = that.getInclude();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "include", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "include", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.locale!= null);
			boolean rhsFieldIsSet = (that.locale!= null);
			String lhsField;
			lhsField = this.getLocale();
			String rhsField;
			rhsField = that.getLocale();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "locale", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "locale", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.plaf!= null);
			boolean rhsFieldIsSet = (that.plaf!= null);
			String lhsField;
			lhsField = this.getPlaf();
			String rhsField;
			rhsField = that.getPlaf();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "plaf", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "plaf", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.plafTheme!= null);
			boolean rhsFieldIsSet = (that.plafTheme!= null);
			String lhsField;
			lhsField = this.getPlafTheme();
			String rhsField;
			rhsField = that.getPlafTheme();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "plafTheme", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "plafTheme", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.refid!= null);
			boolean rhsFieldIsSet = (that.refid!= null);
			String lhsField;
			lhsField = this.getRefid();
			String rhsField;
			rhsField = that.getRefid();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "refid", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "refid", rhsField);
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
			boolean theFieldIsSet = ((this.content!= null)&&(!this.content.isEmpty()));
			List<Object> theField;
			theField = (((this.content!= null)&&(!this.content.isEmpty()))?this.getContent():null);
			strategy.appendField(locator, this, "content", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.bundle!= null);
			String theField;
			theField = this.getBundle();
			strategy.appendField(locator, this, "bundle", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.constraints!= null);
			String theField;
			theField = this.getConstraints();
			strategy.appendField(locator, this, "constraints", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.id!= null);
			String theField;
			theField = this.getId();
			strategy.appendField(locator, this, "id", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.include!= null);
			String theField;
			theField = this.getInclude();
			strategy.appendField(locator, this, "include", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.locale!= null);
			String theField;
			theField = this.getLocale();
			strategy.appendField(locator, this, "locale", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.plaf!= null);
			String theField;
			theField = this.getPlaf();
			strategy.appendField(locator, this, "plaf", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.plafTheme!= null);
			String theField;
			theField = this.getPlafTheme();
			strategy.appendField(locator, this, "plafTheme", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.refid!= null);
			String theField;
			theField = this.getRefid();
			strategy.appendField(locator, this, "refid", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
