
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
 * <p>Java class for JFrame complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JFrame">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}WFrame">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="JMenuBar" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="contentPane" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="defaultCloseOperation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="defaultLookAndFeelDecorated" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="glassPane" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="layeredPane" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="transferHandler" type="{http://www.w3.org/2001/XMLSchema}string" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JFrame")
@XmlSeeAlso({
	Frame.class
})
public class JFrame
	extends WFrame
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "JMenuBar")
	protected String jMenuBar;
	@XmlAttribute(name = "contentPane")
	protected String contentPane;
	@XmlAttribute(name = "defaultCloseOperation")
	protected String defaultCloseOperation;
	@XmlAttribute(name = "defaultLookAndFeelDecorated")
	protected Boolean defaultLookAndFeelDecorated;
	@XmlAttribute(name = "glassPane")
	protected String glassPane;
	@XmlAttribute(name = "layeredPane")
	protected String layeredPane;
	@XmlAttribute(name = "transferHandler")
	protected String transferHandler;

	/**
	 * Gets the value of the jMenuBar property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getJMenuBar() {
		return jMenuBar;
	}

	/**
	 * Sets the value of the jMenuBar property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setJMenuBar(String value) {
		this.jMenuBar = value;
	}

	/**
	 * Gets the value of the contentPane property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getContentPane() {
		return contentPane;
	}

	/**
	 * Sets the value of the contentPane property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setContentPane(String value) {
		this.contentPane = value;
	}

	/**
	 * Gets the value of the defaultCloseOperation property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getDefaultCloseOperation() {
		return defaultCloseOperation;
	}

	/**
	 * Sets the value of the defaultCloseOperation property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setDefaultCloseOperation(String value) {
		this.defaultCloseOperation = value;
	}

	/**
	 * Gets the value of the defaultLookAndFeelDecorated property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isDefaultLookAndFeelDecorated() {
		return defaultLookAndFeelDecorated;
	}

	/**
	 * Sets the value of the defaultLookAndFeelDecorated property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setDefaultLookAndFeelDecorated(Boolean value) {
		this.defaultLookAndFeelDecorated = value;
	}

	/**
	 * Gets the value of the glassPane property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getGlassPane() {
		return glassPane;
	}

	/**
	 * Sets the value of the glassPane property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setGlassPane(String value) {
		this.glassPane = value;
	}

	/**
	 * Gets the value of the layeredPane property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getLayeredPane() {
		return layeredPane;
	}

	/**
	 * Sets the value of the layeredPane property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setLayeredPane(String value) {
		this.layeredPane = value;
	}

	/**
	 * Gets the value of the transferHandler property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getTransferHandler() {
		return transferHandler;
	}

	/**
	 * Sets the value of the transferHandler property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setTransferHandler(String value) {
		this.transferHandler = value;
	}

	@Override
	public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
		int currentHashCode = super.hashCode(locator, strategy);
		{
			boolean theFieldIsSet = (this.jMenuBar!= null);
			String theField;
			theField = this.getJMenuBar();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "jMenuBar", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.contentPane!= null);
			String theField;
			theField = this.getContentPane();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "contentPane", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.defaultCloseOperation!= null);
			String theField;
			theField = this.getDefaultCloseOperation();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "defaultCloseOperation", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.defaultLookAndFeelDecorated!= null);
			Boolean theField;
			theField = this.isDefaultLookAndFeelDecorated();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "defaultLookAndFeelDecorated", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.glassPane!= null);
			String theField;
			theField = this.getGlassPane();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "glassPane", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.layeredPane!= null);
			String theField;
			theField = this.getLayeredPane();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "layeredPane", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.transferHandler!= null);
			String theField;
			theField = this.getTransferHandler();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "transferHandler", theField);
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
		final JFrame that = ((JFrame) object);
		{
			boolean lhsFieldIsSet = (this.jMenuBar!= null);
			boolean rhsFieldIsSet = (that.jMenuBar!= null);
			String lhsField;
			lhsField = this.getJMenuBar();
			String rhsField;
			rhsField = that.getJMenuBar();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "jMenuBar", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "jMenuBar", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.contentPane!= null);
			boolean rhsFieldIsSet = (that.contentPane!= null);
			String lhsField;
			lhsField = this.getContentPane();
			String rhsField;
			rhsField = that.getContentPane();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "contentPane", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "contentPane", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.defaultCloseOperation!= null);
			boolean rhsFieldIsSet = (that.defaultCloseOperation!= null);
			String lhsField;
			lhsField = this.getDefaultCloseOperation();
			String rhsField;
			rhsField = that.getDefaultCloseOperation();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "defaultCloseOperation", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "defaultCloseOperation", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.defaultLookAndFeelDecorated!= null);
			boolean rhsFieldIsSet = (that.defaultLookAndFeelDecorated!= null);
			Boolean lhsField;
			lhsField = this.isDefaultLookAndFeelDecorated();
			Boolean rhsField;
			rhsField = that.isDefaultLookAndFeelDecorated();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "defaultLookAndFeelDecorated", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "defaultLookAndFeelDecorated", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.glassPane!= null);
			boolean rhsFieldIsSet = (that.glassPane!= null);
			String lhsField;
			lhsField = this.getGlassPane();
			String rhsField;
			rhsField = that.getGlassPane();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "glassPane", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "glassPane", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.layeredPane!= null);
			boolean rhsFieldIsSet = (that.layeredPane!= null);
			String lhsField;
			lhsField = this.getLayeredPane();
			String rhsField;
			rhsField = that.getLayeredPane();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "layeredPane", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "layeredPane", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.transferHandler!= null);
			boolean rhsFieldIsSet = (that.transferHandler!= null);
			String lhsField;
			lhsField = this.getTransferHandler();
			String rhsField;
			rhsField = that.getTransferHandler();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "transferHandler", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "transferHandler", rhsField);
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
			boolean theFieldIsSet = (this.jMenuBar!= null);
			String theField;
			theField = this.getJMenuBar();
			strategy.appendField(locator, this, "jMenuBar", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.contentPane!= null);
			String theField;
			theField = this.getContentPane();
			strategy.appendField(locator, this, "contentPane", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.defaultCloseOperation!= null);
			String theField;
			theField = this.getDefaultCloseOperation();
			strategy.appendField(locator, this, "defaultCloseOperation", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.defaultLookAndFeelDecorated!= null);
			Boolean theField;
			theField = this.isDefaultLookAndFeelDecorated();
			strategy.appendField(locator, this, "defaultLookAndFeelDecorated", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.glassPane!= null);
			String theField;
			theField = this.getGlassPane();
			strategy.appendField(locator, this, "glassPane", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.layeredPane!= null);
			String theField;
			theField = this.getLayeredPane();
			strategy.appendField(locator, this, "layeredPane", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.transferHandler!= null);
			String theField;
			theField = this.getTransferHandler();
			strategy.appendField(locator, this, "transferHandler", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
