
package org.swixml.schema.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Java class for TableColumn complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="TableColumn">
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
 *		 <attribute name="refid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="cellEditor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="cellRenderer" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="headerRenderer" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="headerValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="identifier" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="maxWidth" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="minWidth" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="modelIndex" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="preferredWidth" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="resizable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="width" type="{http://www.w3.org/2001/XMLSchema}string" />
 *	   </restriction>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TableColumn", propOrder = {
	"content"
})
@XmlSeeAlso({
	TableColumnBind.class
})
public class TableColumn implements Serializable, Equals, HashCode, ToString
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
	@XmlAttribute(name = "refid")
	protected String refid;
	@XmlAttribute(name = "cellEditor")
	protected String cellEditor;
	@XmlAttribute(name = "cellRenderer")
	protected String cellRenderer;
	@XmlAttribute(name = "headerRenderer")
	protected String headerRenderer;
	@XmlAttribute(name = "headerValue")
	protected String headerValue;
	@XmlAttribute(name = "identifier")
	protected String identifier;
	@XmlAttribute(name = "maxWidth")
	protected String maxWidth;
	@XmlAttribute(name = "minWidth")
	protected String minWidth;
	@XmlAttribute(name = "modelIndex")
	protected String modelIndex;
	@XmlAttribute(name = "preferredWidth")
	protected String preferredWidth;
	@XmlAttribute(name = "resizable")
	protected Boolean resizable;
	@XmlAttribute(name = "width")
	protected String width;

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

	/**
	 * Gets the value of the cellEditor property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getCellEditor() {
		return cellEditor;
	}

	/**
	 * Sets the value of the cellEditor property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setCellEditor(String value) {
		this.cellEditor = value;
	}

	/**
	 * Gets the value of the cellRenderer property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getCellRenderer() {
		return cellRenderer;
	}

	/**
	 * Sets the value of the cellRenderer property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setCellRenderer(String value) {
		this.cellRenderer = value;
	}

	/**
	 * Gets the value of the headerRenderer property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getHeaderRenderer() {
		return headerRenderer;
	}

	/**
	 * Sets the value of the headerRenderer property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setHeaderRenderer(String value) {
		this.headerRenderer = value;
	}

	/**
	 * Gets the value of the headerValue property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getHeaderValue() {
		return headerValue;
	}

	/**
	 * Sets the value of the headerValue property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setHeaderValue(String value) {
		this.headerValue = value;
	}

	/**
	 * Gets the value of the identifier property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the value of the identifier property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setIdentifier(String value) {
		this.identifier = value;
	}

	/**
	 * Gets the value of the maxWidth property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getMaxWidth() {
		return maxWidth;
	}

	/**
	 * Sets the value of the maxWidth property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setMaxWidth(String value) {
		this.maxWidth = value;
	}

	/**
	 * Gets the value of the minWidth property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getMinWidth() {
		return minWidth;
	}

	/**
	 * Sets the value of the minWidth property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setMinWidth(String value) {
		this.minWidth = value;
	}

	/**
	 * Gets the value of the modelIndex property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getModelIndex() {
		return modelIndex;
	}

	/**
	 * Sets the value of the modelIndex property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setModelIndex(String value) {
		this.modelIndex = value;
	}

	/**
	 * Gets the value of the preferredWidth property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getPreferredWidth() {
		return preferredWidth;
	}

	/**
	 * Sets the value of the preferredWidth property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setPreferredWidth(String value) {
		this.preferredWidth = value;
	}

	/**
	 * Gets the value of the resizable property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isResizable() {
		return resizable;
	}

	/**
	 * Sets the value of the resizable property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setResizable(Boolean value) {
		this.resizable = value;
	}

	/**
	 * Gets the value of the width property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * Sets the value of the width property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setWidth(String value) {
		this.width = value;
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
			boolean theFieldIsSet = (this.refid!= null);
			String theField;
			theField = this.getRefid();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "refid", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.cellEditor!= null);
			String theField;
			theField = this.getCellEditor();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "cellEditor", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.cellRenderer!= null);
			String theField;
			theField = this.getCellRenderer();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "cellRenderer", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.headerRenderer!= null);
			String theField;
			theField = this.getHeaderRenderer();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "headerRenderer", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.headerValue!= null);
			String theField;
			theField = this.getHeaderValue();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "headerValue", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.identifier!= null);
			String theField;
			theField = this.getIdentifier();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "identifier", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.maxWidth!= null);
			String theField;
			theField = this.getMaxWidth();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "maxWidth", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.minWidth!= null);
			String theField;
			theField = this.getMinWidth();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "minWidth", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.modelIndex!= null);
			String theField;
			theField = this.getModelIndex();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "modelIndex", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.preferredWidth!= null);
			String theField;
			theField = this.getPreferredWidth();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "preferredWidth", theField);
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
			boolean theFieldIsSet = (this.width!= null);
			String theField;
			theField = this.getWidth();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "width", theField);
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
		final TableColumn that = ((TableColumn) object);
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
		{
			boolean lhsFieldIsSet = (this.cellEditor!= null);
			boolean rhsFieldIsSet = (that.cellEditor!= null);
			String lhsField;
			lhsField = this.getCellEditor();
			String rhsField;
			rhsField = that.getCellEditor();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "cellEditor", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "cellEditor", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.cellRenderer!= null);
			boolean rhsFieldIsSet = (that.cellRenderer!= null);
			String lhsField;
			lhsField = this.getCellRenderer();
			String rhsField;
			rhsField = that.getCellRenderer();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "cellRenderer", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "cellRenderer", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.headerRenderer!= null);
			boolean rhsFieldIsSet = (that.headerRenderer!= null);
			String lhsField;
			lhsField = this.getHeaderRenderer();
			String rhsField;
			rhsField = that.getHeaderRenderer();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "headerRenderer", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "headerRenderer", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.headerValue!= null);
			boolean rhsFieldIsSet = (that.headerValue!= null);
			String lhsField;
			lhsField = this.getHeaderValue();
			String rhsField;
			rhsField = that.getHeaderValue();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "headerValue", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "headerValue", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.identifier!= null);
			boolean rhsFieldIsSet = (that.identifier!= null);
			String lhsField;
			lhsField = this.getIdentifier();
			String rhsField;
			rhsField = that.getIdentifier();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "identifier", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "identifier", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.maxWidth!= null);
			boolean rhsFieldIsSet = (that.maxWidth!= null);
			String lhsField;
			lhsField = this.getMaxWidth();
			String rhsField;
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
			String lhsField;
			lhsField = this.getMinWidth();
			String rhsField;
			rhsField = that.getMinWidth();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "minWidth", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "minWidth", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.modelIndex!= null);
			boolean rhsFieldIsSet = (that.modelIndex!= null);
			String lhsField;
			lhsField = this.getModelIndex();
			String rhsField;
			rhsField = that.getModelIndex();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "modelIndex", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "modelIndex", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.preferredWidth!= null);
			boolean rhsFieldIsSet = (that.preferredWidth!= null);
			String lhsField;
			lhsField = this.getPreferredWidth();
			String rhsField;
			rhsField = that.getPreferredWidth();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "preferredWidth", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "preferredWidth", rhsField);
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
			boolean lhsFieldIsSet = (this.width!= null);
			boolean rhsFieldIsSet = (that.width!= null);
			String lhsField;
			lhsField = this.getWidth();
			String rhsField;
			rhsField = that.getWidth();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "width", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "width", rhsField);
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
			boolean theFieldIsSet = (this.refid!= null);
			String theField;
			theField = this.getRefid();
			strategy.appendField(locator, this, "refid", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.cellEditor!= null);
			String theField;
			theField = this.getCellEditor();
			strategy.appendField(locator, this, "cellEditor", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.cellRenderer!= null);
			String theField;
			theField = this.getCellRenderer();
			strategy.appendField(locator, this, "cellRenderer", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.headerRenderer!= null);
			String theField;
			theField = this.getHeaderRenderer();
			strategy.appendField(locator, this, "headerRenderer", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.headerValue!= null);
			String theField;
			theField = this.getHeaderValue();
			strategy.appendField(locator, this, "headerValue", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.identifier!= null);
			String theField;
			theField = this.getIdentifier();
			strategy.appendField(locator, this, "identifier", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.maxWidth!= null);
			String theField;
			theField = this.getMaxWidth();
			strategy.appendField(locator, this, "maxWidth", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.minWidth!= null);
			String theField;
			theField = this.getMinWidth();
			strategy.appendField(locator, this, "minWidth", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.modelIndex!= null);
			String theField;
			theField = this.getModelIndex();
			strategy.appendField(locator, this, "modelIndex", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.preferredWidth!= null);
			String theField;
			theField = this.getPreferredWidth();
			strategy.appendField(locator, this, "preferredWidth", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.resizable!= null);
			Boolean theField;
			theField = this.isResizable();
			strategy.appendField(locator, this, "resizable", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.width!= null);
			String theField;
			theField = this.getWidth();
			strategy.appendField(locator, this, "width", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
