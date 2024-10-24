
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
 * <p>Java class for Component complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Component">
 *	 <complexContent>
 *	   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *		 <sequence maxOccurs="unbounded" minOccurs="0">
 *		   <any/>
 *		 </sequence>
 *		 <attribute name="background" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="bounds" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="componentOrientation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="cursor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="dropTarget" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="focusTraversalKeysEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="focusable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="font" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="foreground" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="ignoreRepaint" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="locale" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="location" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="maximumSize" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="minimumSize" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="mixingCutoutShape" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="preferredSize" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="size" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="visible" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="bundle" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="constraints" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="include" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="plaf" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="refid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *	   </restriction>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Component", propOrder = {
	"content"
})
@XmlSeeAlso({
	Container.class
})
public class Component implements Serializable, Equals, HashCode, ToString
{

	private static final long serialVersionUID = 20240701L;
	@XmlMixed
	@XmlAnyElement(lax = true)
	protected List<Object> content;
	@XmlAttribute(name = "background")
	protected String background;
	@XmlAttribute(name = "bounds")
	protected String bounds;
	@XmlAttribute(name = "componentOrientation")
	protected String componentOrientation;
	@XmlAttribute(name = "cursor")
	protected String cursor;
	@XmlAttribute(name = "dropTarget")
	protected String dropTarget;
	@XmlAttribute(name = "enabled")
	protected Boolean enabled;
	@XmlAttribute(name = "focusTraversalKeysEnabled")
	protected Boolean focusTraversalKeysEnabled;
	@XmlAttribute(name = "focusable")
	protected Boolean focusable;
	@XmlAttribute(name = "font")
	protected String font;
	@XmlAttribute(name = "foreground")
	protected String foreground;
	@XmlAttribute(name = "ignoreRepaint")
	protected Boolean ignoreRepaint;
	@XmlAttribute(name = "locale")
	protected String locale;
	@XmlAttribute(name = "location")
	protected String location;
	@XmlAttribute(name = "maximumSize")
	protected String maximumSize;
	@XmlAttribute(name = "minimumSize")
	protected String minimumSize;
	@XmlAttribute(name = "mixingCutoutShape")
	protected String mixingCutoutShape;
	@XmlAttribute(name = "name")
	protected String name;
	@XmlAttribute(name = "preferredSize")
	protected String preferredSize;
	@XmlAttribute(name = "size")
	protected String size;
	@XmlAttribute(name = "visible")
	protected Boolean visible;
	@XmlAttribute(name = "bundle")
	protected String bundle;
	@XmlAttribute(name = "constraints")
	protected String constraints;
	@XmlAttribute(name = "id")
	protected String id;
	@XmlAttribute(name = "include")
	protected String include;
	@XmlAttribute(name = "plaf")
	protected String plaf;
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
	 * Gets the value of the background property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getBackground() {
		return background;
	}

	/**
	 * Sets the value of the background property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setBackground(String value) {
		this.background = value;
	}

	/**
	 * Gets the value of the bounds property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getBounds() {
		return bounds;
	}

	/**
	 * Sets the value of the bounds property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setBounds(String value) {
		this.bounds = value;
	}

	/**
	 * Gets the value of the componentOrientation property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getComponentOrientation() {
		return componentOrientation;
	}

	/**
	 * Sets the value of the componentOrientation property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setComponentOrientation(String value) {
		this.componentOrientation = value;
	}

	/**
	 * Gets the value of the cursor property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getCursor() {
		return cursor;
	}

	/**
	 * Sets the value of the cursor property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setCursor(String value) {
		this.cursor = value;
	}

	/**
	 * Gets the value of the dropTarget property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getDropTarget() {
		return dropTarget;
	}

	/**
	 * Sets the value of the dropTarget property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setDropTarget(String value) {
		this.dropTarget = value;
	}

	/**
	 * Gets the value of the enabled property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the value of the enabled property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setEnabled(Boolean value) {
		this.enabled = value;
	}

	/**
	 * Gets the value of the focusTraversalKeysEnabled property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isFocusTraversalKeysEnabled() {
		return focusTraversalKeysEnabled;
	}

	/**
	 * Sets the value of the focusTraversalKeysEnabled property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setFocusTraversalKeysEnabled(Boolean value) {
		this.focusTraversalKeysEnabled = value;
	}

	/**
	 * Gets the value of the focusable property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isFocusable() {
		return focusable;
	}

	/**
	 * Sets the value of the focusable property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setFocusable(Boolean value) {
		this.focusable = value;
	}

	/**
	 * Gets the value of the font property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getFont() {
		return font;
	}

	/**
	 * Sets the value of the font property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setFont(String value) {
		this.font = value;
	}

	/**
	 * Gets the value of the foreground property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getForeground() {
		return foreground;
	}

	/**
	 * Sets the value of the foreground property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setForeground(String value) {
		this.foreground = value;
	}

	/**
	 * Gets the value of the ignoreRepaint property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isIgnoreRepaint() {
		return ignoreRepaint;
	}

	/**
	 * Sets the value of the ignoreRepaint property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setIgnoreRepaint(Boolean value) {
		this.ignoreRepaint = value;
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
	 * Gets the value of the location property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the value of the location property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setLocation(String value) {
		this.location = value;
	}

	/**
	 * Gets the value of the maximumSize property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getMaximumSize() {
		return maximumSize;
	}

	/**
	 * Sets the value of the maximumSize property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setMaximumSize(String value) {
		this.maximumSize = value;
	}

	/**
	 * Gets the value of the minimumSize property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getMinimumSize() {
		return minimumSize;
	}

	/**
	 * Sets the value of the minimumSize property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setMinimumSize(String value) {
		this.minimumSize = value;
	}

	/**
	 * Gets the value of the mixingCutoutShape property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getMixingCutoutShape() {
		return mixingCutoutShape;
	}

	/**
	 * Sets the value of the mixingCutoutShape property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setMixingCutoutShape(String value) {
		this.mixingCutoutShape = value;
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the value of the preferredSize property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getPreferredSize() {
		return preferredSize;
	}

	/**
	 * Sets the value of the preferredSize property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setPreferredSize(String value) {
		this.preferredSize = value;
	}

	/**
	 * Gets the value of the size property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getSize() {
		return size;
	}

	/**
	 * Sets the value of the size property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setSize(String value) {
		this.size = value;
	}

	/**
	 * Gets the value of the visible property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isVisible() {
		return visible;
	}

	/**
	 * Sets the value of the visible property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setVisible(Boolean value) {
		this.visible = value;
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
			boolean theFieldIsSet = (this.background!= null);
			String theField;
			theField = this.getBackground();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "background", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.bounds!= null);
			String theField;
			theField = this.getBounds();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "bounds", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.componentOrientation!= null);
			String theField;
			theField = this.getComponentOrientation();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "componentOrientation", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.cursor!= null);
			String theField;
			theField = this.getCursor();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "cursor", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.dropTarget!= null);
			String theField;
			theField = this.getDropTarget();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "dropTarget", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.enabled!= null);
			Boolean theField;
			theField = this.isEnabled();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "enabled", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.focusTraversalKeysEnabled!= null);
			Boolean theField;
			theField = this.isFocusTraversalKeysEnabled();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "focusTraversalKeysEnabled", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.focusable!= null);
			Boolean theField;
			theField = this.isFocusable();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "focusable", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.font!= null);
			String theField;
			theField = this.getFont();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "font", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.foreground!= null);
			String theField;
			theField = this.getForeground();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "foreground", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.ignoreRepaint!= null);
			Boolean theField;
			theField = this.isIgnoreRepaint();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "ignoreRepaint", theField);
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
			boolean theFieldIsSet = (this.location!= null);
			String theField;
			theField = this.getLocation();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "location", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.maximumSize!= null);
			String theField;
			theField = this.getMaximumSize();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "maximumSize", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.minimumSize!= null);
			String theField;
			theField = this.getMinimumSize();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "minimumSize", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.mixingCutoutShape!= null);
			String theField;
			theField = this.getMixingCutoutShape();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "mixingCutoutShape", theField);
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
			boolean theFieldIsSet = (this.preferredSize!= null);
			String theField;
			theField = this.getPreferredSize();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "preferredSize", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.size!= null);
			String theField;
			theField = this.getSize();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "size", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.visible!= null);
			Boolean theField;
			theField = this.isVisible();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "visible", theField);
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
		final Component that = ((Component) object);
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
			boolean lhsFieldIsSet = (this.background!= null);
			boolean rhsFieldIsSet = (that.background!= null);
			String lhsField;
			lhsField = this.getBackground();
			String rhsField;
			rhsField = that.getBackground();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "background", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "background", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.bounds!= null);
			boolean rhsFieldIsSet = (that.bounds!= null);
			String lhsField;
			lhsField = this.getBounds();
			String rhsField;
			rhsField = that.getBounds();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "bounds", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "bounds", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.componentOrientation!= null);
			boolean rhsFieldIsSet = (that.componentOrientation!= null);
			String lhsField;
			lhsField = this.getComponentOrientation();
			String rhsField;
			rhsField = that.getComponentOrientation();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "componentOrientation", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "componentOrientation", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.cursor!= null);
			boolean rhsFieldIsSet = (that.cursor!= null);
			String lhsField;
			lhsField = this.getCursor();
			String rhsField;
			rhsField = that.getCursor();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "cursor", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "cursor", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.dropTarget!= null);
			boolean rhsFieldIsSet = (that.dropTarget!= null);
			String lhsField;
			lhsField = this.getDropTarget();
			String rhsField;
			rhsField = that.getDropTarget();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "dropTarget", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "dropTarget", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.enabled!= null);
			boolean rhsFieldIsSet = (that.enabled!= null);
			Boolean lhsField;
			lhsField = this.isEnabled();
			Boolean rhsField;
			rhsField = that.isEnabled();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "enabled", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "enabled", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.focusTraversalKeysEnabled!= null);
			boolean rhsFieldIsSet = (that.focusTraversalKeysEnabled!= null);
			Boolean lhsField;
			lhsField = this.isFocusTraversalKeysEnabled();
			Boolean rhsField;
			rhsField = that.isFocusTraversalKeysEnabled();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "focusTraversalKeysEnabled", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "focusTraversalKeysEnabled", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.focusable!= null);
			boolean rhsFieldIsSet = (that.focusable!= null);
			Boolean lhsField;
			lhsField = this.isFocusable();
			Boolean rhsField;
			rhsField = that.isFocusable();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "focusable", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "focusable", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.font!= null);
			boolean rhsFieldIsSet = (that.font!= null);
			String lhsField;
			lhsField = this.getFont();
			String rhsField;
			rhsField = that.getFont();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "font", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "font", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.foreground!= null);
			boolean rhsFieldIsSet = (that.foreground!= null);
			String lhsField;
			lhsField = this.getForeground();
			String rhsField;
			rhsField = that.getForeground();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "foreground", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "foreground", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.ignoreRepaint!= null);
			boolean rhsFieldIsSet = (that.ignoreRepaint!= null);
			Boolean lhsField;
			lhsField = this.isIgnoreRepaint();
			Boolean rhsField;
			rhsField = that.isIgnoreRepaint();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "ignoreRepaint", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "ignoreRepaint", rhsField);
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
			boolean lhsFieldIsSet = (this.location!= null);
			boolean rhsFieldIsSet = (that.location!= null);
			String lhsField;
			lhsField = this.getLocation();
			String rhsField;
			rhsField = that.getLocation();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "location", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "location", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.maximumSize!= null);
			boolean rhsFieldIsSet = (that.maximumSize!= null);
			String lhsField;
			lhsField = this.getMaximumSize();
			String rhsField;
			rhsField = that.getMaximumSize();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "maximumSize", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "maximumSize", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.minimumSize!= null);
			boolean rhsFieldIsSet = (that.minimumSize!= null);
			String lhsField;
			lhsField = this.getMinimumSize();
			String rhsField;
			rhsField = that.getMinimumSize();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "minimumSize", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "minimumSize", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.mixingCutoutShape!= null);
			boolean rhsFieldIsSet = (that.mixingCutoutShape!= null);
			String lhsField;
			lhsField = this.getMixingCutoutShape();
			String rhsField;
			rhsField = that.getMixingCutoutShape();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "mixingCutoutShape", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "mixingCutoutShape", rhsField);
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
			boolean lhsFieldIsSet = (this.preferredSize!= null);
			boolean rhsFieldIsSet = (that.preferredSize!= null);
			String lhsField;
			lhsField = this.getPreferredSize();
			String rhsField;
			rhsField = that.getPreferredSize();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "preferredSize", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "preferredSize", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.size!= null);
			boolean rhsFieldIsSet = (that.size!= null);
			String lhsField;
			lhsField = this.getSize();
			String rhsField;
			rhsField = that.getSize();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "size", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "size", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.visible!= null);
			boolean rhsFieldIsSet = (that.visible!= null);
			Boolean lhsField;
			lhsField = this.isVisible();
			Boolean rhsField;
			rhsField = that.isVisible();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "visible", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "visible", rhsField);
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
			boolean theFieldIsSet = (this.background!= null);
			String theField;
			theField = this.getBackground();
			strategy.appendField(locator, this, "background", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.bounds!= null);
			String theField;
			theField = this.getBounds();
			strategy.appendField(locator, this, "bounds", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.componentOrientation!= null);
			String theField;
			theField = this.getComponentOrientation();
			strategy.appendField(locator, this, "componentOrientation", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.cursor!= null);
			String theField;
			theField = this.getCursor();
			strategy.appendField(locator, this, "cursor", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.dropTarget!= null);
			String theField;
			theField = this.getDropTarget();
			strategy.appendField(locator, this, "dropTarget", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.enabled!= null);
			Boolean theField;
			theField = this.isEnabled();
			strategy.appendField(locator, this, "enabled", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.focusTraversalKeysEnabled!= null);
			Boolean theField;
			theField = this.isFocusTraversalKeysEnabled();
			strategy.appendField(locator, this, "focusTraversalKeysEnabled", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.focusable!= null);
			Boolean theField;
			theField = this.isFocusable();
			strategy.appendField(locator, this, "focusable", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.font!= null);
			String theField;
			theField = this.getFont();
			strategy.appendField(locator, this, "font", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.foreground!= null);
			String theField;
			theField = this.getForeground();
			strategy.appendField(locator, this, "foreground", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.ignoreRepaint!= null);
			Boolean theField;
			theField = this.isIgnoreRepaint();
			strategy.appendField(locator, this, "ignoreRepaint", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.locale!= null);
			String theField;
			theField = this.getLocale();
			strategy.appendField(locator, this, "locale", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.location!= null);
			String theField;
			theField = this.getLocation();
			strategy.appendField(locator, this, "location", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.maximumSize!= null);
			String theField;
			theField = this.getMaximumSize();
			strategy.appendField(locator, this, "maximumSize", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.minimumSize!= null);
			String theField;
			theField = this.getMinimumSize();
			strategy.appendField(locator, this, "minimumSize", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.mixingCutoutShape!= null);
			String theField;
			theField = this.getMixingCutoutShape();
			strategy.appendField(locator, this, "mixingCutoutShape", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.name!= null);
			String theField;
			theField = this.getName();
			strategy.appendField(locator, this, "name", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.preferredSize!= null);
			String theField;
			theField = this.getPreferredSize();
			strategy.appendField(locator, this, "preferredSize", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.size!= null);
			String theField;
			theField = this.getSize();
			strategy.appendField(locator, this, "size", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.visible!= null);
			Boolean theField;
			theField = this.isVisible();
			strategy.appendField(locator, this, "visible", buffer, theField, theFieldIsSet);
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
		return buffer;
	}

}
