
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
 * <p>Java class for JComponent complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JComponent">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}Container">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="actionMap" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="alignmentX" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="alignmentY" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="autoscrolls" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="border" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="componentPopupMenu" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="debugGraphicsOptions" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="defaultLocale" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="doubleBuffered" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="inheritsPopupMenu" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="inputVerifier" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="opaque" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="requestFocusEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="toolTipText" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="transferHandler" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="verifyInputWhenFocusTarget" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JComponent")
@XmlSeeAlso({
	JInternalFrame.class,
	JMenuBar.class,
	JOptionPane.class,
	JPanel.class,
	JPopupMenu.class,
	JProgressBar.class,
	JSeparator.class,
	JTableHeader.class,
	JToolBar.class,
	JComboBox.class,
	JLayeredPane.class,
	Box.class,
	JLabel.class,
	JList.class,
	AbstractButton.class,
	JScrollPane.class,
	JSlider.class,
	JSpinner.class,
	JSplitPane.class,
	JTabbedPane.class,
	JTable.class,
	JTextComponent.class,
	JTree.class,
	BoxFiller.class
})
public class JComponent
	extends Container
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "actionMap")
	protected String actionMap;
	@XmlAttribute(name = "alignmentX")
	protected String alignmentX;
	@XmlAttribute(name = "alignmentY")
	protected String alignmentY;
	@XmlAttribute(name = "autoscrolls")
	protected Boolean autoscrolls;
	@XmlAttribute(name = "border")
	protected String border;
	@XmlAttribute(name = "componentPopupMenu")
	protected String componentPopupMenu;
	@XmlAttribute(name = "debugGraphicsOptions")
	protected String debugGraphicsOptions;
	@XmlAttribute(name = "defaultLocale")
	protected String defaultLocale;
	@XmlAttribute(name = "doubleBuffered")
	protected Boolean doubleBuffered;
	@XmlAttribute(name = "inheritsPopupMenu")
	protected Boolean inheritsPopupMenu;
	@XmlAttribute(name = "inputVerifier")
	protected String inputVerifier;
	@XmlAttribute(name = "opaque")
	protected Boolean opaque;
	@XmlAttribute(name = "requestFocusEnabled")
	protected Boolean requestFocusEnabled;
	@XmlAttribute(name = "toolTipText")
	protected String toolTipText;
	@XmlAttribute(name = "transferHandler")
	protected String transferHandler;
	@XmlAttribute(name = "verifyInputWhenFocusTarget")
	protected Boolean verifyInputWhenFocusTarget;

	/**
	 * Gets the value of the actionMap property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getActionMap() {
		return actionMap;
	}

	/**
	 * Sets the value of the actionMap property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setActionMap(String value) {
		this.actionMap = value;
	}

	/**
	 * Gets the value of the alignmentX property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getAlignmentX() {
		return alignmentX;
	}

	/**
	 * Sets the value of the alignmentX property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setAlignmentX(String value) {
		this.alignmentX = value;
	}

	/**
	 * Gets the value of the alignmentY property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getAlignmentY() {
		return alignmentY;
	}

	/**
	 * Sets the value of the alignmentY property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setAlignmentY(String value) {
		this.alignmentY = value;
	}

	/**
	 * Gets the value of the autoscrolls property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isAutoscrolls() {
		return autoscrolls;
	}

	/**
	 * Sets the value of the autoscrolls property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setAutoscrolls(Boolean value) {
		this.autoscrolls = value;
	}

	/**
	 * Gets the value of the border property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getBorder() {
		return border;
	}

	/**
	 * Sets the value of the border property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setBorder(String value) {
		this.border = value;
	}

	/**
	 * Gets the value of the componentPopupMenu property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getComponentPopupMenu() {
		return componentPopupMenu;
	}

	/**
	 * Sets the value of the componentPopupMenu property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setComponentPopupMenu(String value) {
		this.componentPopupMenu = value;
	}

	/**
	 * Gets the value of the debugGraphicsOptions property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getDebugGraphicsOptions() {
		return debugGraphicsOptions;
	}

	/**
	 * Sets the value of the debugGraphicsOptions property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setDebugGraphicsOptions(String value) {
		this.debugGraphicsOptions = value;
	}

	/**
	 * Gets the value of the defaultLocale property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getDefaultLocale() {
		return defaultLocale;
	}

	/**
	 * Sets the value of the defaultLocale property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setDefaultLocale(String value) {
		this.defaultLocale = value;
	}

	/**
	 * Gets the value of the doubleBuffered property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isDoubleBuffered() {
		return doubleBuffered;
	}

	/**
	 * Sets the value of the doubleBuffered property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setDoubleBuffered(Boolean value) {
		this.doubleBuffered = value;
	}

	/**
	 * Gets the value of the inheritsPopupMenu property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isInheritsPopupMenu() {
		return inheritsPopupMenu;
	}

	/**
	 * Sets the value of the inheritsPopupMenu property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setInheritsPopupMenu(Boolean value) {
		this.inheritsPopupMenu = value;
	}

	/**
	 * Gets the value of the inputVerifier property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getInputVerifier() {
		return inputVerifier;
	}

	/**
	 * Sets the value of the inputVerifier property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setInputVerifier(String value) {
		this.inputVerifier = value;
	}

	/**
	 * Gets the value of the opaque property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isOpaque() {
		return opaque;
	}

	/**
	 * Sets the value of the opaque property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setOpaque(Boolean value) {
		this.opaque = value;
	}

	/**
	 * Gets the value of the requestFocusEnabled property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isRequestFocusEnabled() {
		return requestFocusEnabled;
	}

	/**
	 * Sets the value of the requestFocusEnabled property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setRequestFocusEnabled(Boolean value) {
		this.requestFocusEnabled = value;
	}

	/**
	 * Gets the value of the toolTipText property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getToolTipText() {
		return toolTipText;
	}

	/**
	 * Sets the value of the toolTipText property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setToolTipText(String value) {
		this.toolTipText = value;
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

	/**
	 * Gets the value of the verifyInputWhenFocusTarget property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isVerifyInputWhenFocusTarget() {
		return verifyInputWhenFocusTarget;
	}

	/**
	 * Sets the value of the verifyInputWhenFocusTarget property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setVerifyInputWhenFocusTarget(Boolean value) {
		this.verifyInputWhenFocusTarget = value;
	}

	@Override
	public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
		int currentHashCode = super.hashCode(locator, strategy);
		{
			boolean theFieldIsSet = (this.actionMap!= null);
			String theField;
			theField = this.getActionMap();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "actionMap", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.alignmentX!= null);
			String theField;
			theField = this.getAlignmentX();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "alignmentX", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.alignmentY!= null);
			String theField;
			theField = this.getAlignmentY();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "alignmentY", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.autoscrolls!= null);
			Boolean theField;
			theField = this.isAutoscrolls();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "autoscrolls", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.border!= null);
			String theField;
			theField = this.getBorder();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "border", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.componentPopupMenu!= null);
			String theField;
			theField = this.getComponentPopupMenu();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "componentPopupMenu", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.debugGraphicsOptions!= null);
			String theField;
			theField = this.getDebugGraphicsOptions();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "debugGraphicsOptions", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.defaultLocale!= null);
			String theField;
			theField = this.getDefaultLocale();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "defaultLocale", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.doubleBuffered!= null);
			Boolean theField;
			theField = this.isDoubleBuffered();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "doubleBuffered", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.inheritsPopupMenu!= null);
			Boolean theField;
			theField = this.isInheritsPopupMenu();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "inheritsPopupMenu", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.inputVerifier!= null);
			String theField;
			theField = this.getInputVerifier();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "inputVerifier", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.opaque!= null);
			Boolean theField;
			theField = this.isOpaque();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "opaque", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.requestFocusEnabled!= null);
			Boolean theField;
			theField = this.isRequestFocusEnabled();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "requestFocusEnabled", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.toolTipText!= null);
			String theField;
			theField = this.getToolTipText();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "toolTipText", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.transferHandler!= null);
			String theField;
			theField = this.getTransferHandler();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "transferHandler", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.verifyInputWhenFocusTarget!= null);
			Boolean theField;
			theField = this.isVerifyInputWhenFocusTarget();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "verifyInputWhenFocusTarget", theField);
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
		final JComponent that = ((JComponent) object);
		{
			boolean lhsFieldIsSet = (this.actionMap!= null);
			boolean rhsFieldIsSet = (that.actionMap!= null);
			String lhsField;
			lhsField = this.getActionMap();
			String rhsField;
			rhsField = that.getActionMap();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "actionMap", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "actionMap", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.alignmentX!= null);
			boolean rhsFieldIsSet = (that.alignmentX!= null);
			String lhsField;
			lhsField = this.getAlignmentX();
			String rhsField;
			rhsField = that.getAlignmentX();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "alignmentX", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "alignmentX", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.alignmentY!= null);
			boolean rhsFieldIsSet = (that.alignmentY!= null);
			String lhsField;
			lhsField = this.getAlignmentY();
			String rhsField;
			rhsField = that.getAlignmentY();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "alignmentY", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "alignmentY", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.autoscrolls!= null);
			boolean rhsFieldIsSet = (that.autoscrolls!= null);
			Boolean lhsField;
			lhsField = this.isAutoscrolls();
			Boolean rhsField;
			rhsField = that.isAutoscrolls();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "autoscrolls", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "autoscrolls", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.border!= null);
			boolean rhsFieldIsSet = (that.border!= null);
			String lhsField;
			lhsField = this.getBorder();
			String rhsField;
			rhsField = that.getBorder();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "border", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "border", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.componentPopupMenu!= null);
			boolean rhsFieldIsSet = (that.componentPopupMenu!= null);
			String lhsField;
			lhsField = this.getComponentPopupMenu();
			String rhsField;
			rhsField = that.getComponentPopupMenu();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "componentPopupMenu", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "componentPopupMenu", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.debugGraphicsOptions!= null);
			boolean rhsFieldIsSet = (that.debugGraphicsOptions!= null);
			String lhsField;
			lhsField = this.getDebugGraphicsOptions();
			String rhsField;
			rhsField = that.getDebugGraphicsOptions();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "debugGraphicsOptions", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "debugGraphicsOptions", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.defaultLocale!= null);
			boolean rhsFieldIsSet = (that.defaultLocale!= null);
			String lhsField;
			lhsField = this.getDefaultLocale();
			String rhsField;
			rhsField = that.getDefaultLocale();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "defaultLocale", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "defaultLocale", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.doubleBuffered!= null);
			boolean rhsFieldIsSet = (that.doubleBuffered!= null);
			Boolean lhsField;
			lhsField = this.isDoubleBuffered();
			Boolean rhsField;
			rhsField = that.isDoubleBuffered();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "doubleBuffered", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "doubleBuffered", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.inheritsPopupMenu!= null);
			boolean rhsFieldIsSet = (that.inheritsPopupMenu!= null);
			Boolean lhsField;
			lhsField = this.isInheritsPopupMenu();
			Boolean rhsField;
			rhsField = that.isInheritsPopupMenu();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "inheritsPopupMenu", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "inheritsPopupMenu", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.inputVerifier!= null);
			boolean rhsFieldIsSet = (that.inputVerifier!= null);
			String lhsField;
			lhsField = this.getInputVerifier();
			String rhsField;
			rhsField = that.getInputVerifier();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "inputVerifier", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "inputVerifier", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.opaque!= null);
			boolean rhsFieldIsSet = (that.opaque!= null);
			Boolean lhsField;
			lhsField = this.isOpaque();
			Boolean rhsField;
			rhsField = that.isOpaque();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "opaque", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "opaque", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.requestFocusEnabled!= null);
			boolean rhsFieldIsSet = (that.requestFocusEnabled!= null);
			Boolean lhsField;
			lhsField = this.isRequestFocusEnabled();
			Boolean rhsField;
			rhsField = that.isRequestFocusEnabled();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "requestFocusEnabled", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "requestFocusEnabled", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.toolTipText!= null);
			boolean rhsFieldIsSet = (that.toolTipText!= null);
			String lhsField;
			lhsField = this.getToolTipText();
			String rhsField;
			rhsField = that.getToolTipText();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "toolTipText", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "toolTipText", rhsField);
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
		{
			boolean lhsFieldIsSet = (this.verifyInputWhenFocusTarget!= null);
			boolean rhsFieldIsSet = (that.verifyInputWhenFocusTarget!= null);
			Boolean lhsField;
			lhsField = this.isVerifyInputWhenFocusTarget();
			Boolean rhsField;
			rhsField = that.isVerifyInputWhenFocusTarget();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "verifyInputWhenFocusTarget", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "verifyInputWhenFocusTarget", rhsField);
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
			boolean theFieldIsSet = (this.actionMap!= null);
			String theField;
			theField = this.getActionMap();
			strategy.appendField(locator, this, "actionMap", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.alignmentX!= null);
			String theField;
			theField = this.getAlignmentX();
			strategy.appendField(locator, this, "alignmentX", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.alignmentY!= null);
			String theField;
			theField = this.getAlignmentY();
			strategy.appendField(locator, this, "alignmentY", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.autoscrolls!= null);
			Boolean theField;
			theField = this.isAutoscrolls();
			strategy.appendField(locator, this, "autoscrolls", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.border!= null);
			String theField;
			theField = this.getBorder();
			strategy.appendField(locator, this, "border", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.componentPopupMenu!= null);
			String theField;
			theField = this.getComponentPopupMenu();
			strategy.appendField(locator, this, "componentPopupMenu", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.debugGraphicsOptions!= null);
			String theField;
			theField = this.getDebugGraphicsOptions();
			strategy.appendField(locator, this, "debugGraphicsOptions", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.defaultLocale!= null);
			String theField;
			theField = this.getDefaultLocale();
			strategy.appendField(locator, this, "defaultLocale", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.doubleBuffered!= null);
			Boolean theField;
			theField = this.isDoubleBuffered();
			strategy.appendField(locator, this, "doubleBuffered", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.inheritsPopupMenu!= null);
			Boolean theField;
			theField = this.isInheritsPopupMenu();
			strategy.appendField(locator, this, "inheritsPopupMenu", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.inputVerifier!= null);
			String theField;
			theField = this.getInputVerifier();
			strategy.appendField(locator, this, "inputVerifier", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.opaque!= null);
			Boolean theField;
			theField = this.isOpaque();
			strategy.appendField(locator, this, "opaque", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.requestFocusEnabled!= null);
			Boolean theField;
			theField = this.isRequestFocusEnabled();
			strategy.appendField(locator, this, "requestFocusEnabled", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.toolTipText!= null);
			String theField;
			theField = this.getToolTipText();
			strategy.appendField(locator, this, "toolTipText", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.transferHandler!= null);
			String theField;
			theField = this.getTransferHandler();
			strategy.appendField(locator, this, "transferHandler", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.verifyInputWhenFocusTarget!= null);
			Boolean theField;
			theField = this.isVerifyInputWhenFocusTarget();
			strategy.appendField(locator, this, "verifyInputWhenFocusTarget", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
