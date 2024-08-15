
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
 * <p>Java class for JInternalFrame complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JInternalFrame">
 *   <complexContent>
 *     <extension base="{http://www.swixml.org/2007/Swixml}JComponent">
 *       <sequence>
 *       </sequence>
 *       <attribute name="JMenuBar" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="closable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="closed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="contentPane" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="defaultCloseOperation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="desktopIcon" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="frameIcon" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="glassPane" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="icon" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="iconifiable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="layer" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="layeredPane" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="maximizable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="maximum" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="normalBounds" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="resizable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="selected" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JInternalFrame")
public class JInternalFrame
    extends JComponent
    implements Serializable
{

    private static final long serialVersionUID = 20240701L;
    @XmlAttribute(name = "JMenuBar")
    protected String jMenuBar;
    @XmlAttribute(name = "UI")
    protected String ui;
    @XmlAttribute(name = "closable")
    protected Boolean closable;
    @XmlAttribute(name = "closed")
    protected Boolean closed;
    @XmlAttribute(name = "contentPane")
    protected String contentPane;
    @XmlAttribute(name = "defaultCloseOperation")
    protected String defaultCloseOperation;
    @XmlAttribute(name = "desktopIcon")
    protected String desktopIcon;
    @XmlAttribute(name = "frameIcon")
    protected String frameIcon;
    @XmlAttribute(name = "glassPane")
    protected String glassPane;
    @XmlAttribute(name = "icon")
    protected Boolean icon;
    @XmlAttribute(name = "iconifiable")
    protected Boolean iconifiable;
    @XmlAttribute(name = "layer")
    protected String layer;
    @XmlAttribute(name = "layeredPane")
    protected String layeredPane;
    @XmlAttribute(name = "maximizable")
    protected Boolean maximizable;
    @XmlAttribute(name = "maximum")
    protected Boolean maximum;
    @XmlAttribute(name = "normalBounds")
    protected String normalBounds;
    @XmlAttribute(name = "resizable")
    protected Boolean resizable;
    @XmlAttribute(name = "selected")
    protected Boolean selected;
    @XmlAttribute(name = "title")
    protected String title;

    /**
     * Gets the value of the jMenuBar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJMenuBar() {
        return jMenuBar;
    }

    /**
     * Sets the value of the jMenuBar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJMenuBar(String value) {
        this.jMenuBar = value;
    }

    /**
     * Gets the value of the ui property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUI() {
        return ui;
    }

    /**
     * Sets the value of the ui property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUI(String value) {
        this.ui = value;
    }

    /**
     * Gets the value of the closable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isClosable() {
        return closable;
    }

    /**
     * Sets the value of the closable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setClosable(Boolean value) {
        this.closable = value;
    }

    /**
     * Gets the value of the closed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isClosed() {
        return closed;
    }

    /**
     * Sets the value of the closed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setClosed(Boolean value) {
        this.closed = value;
    }

    /**
     * Gets the value of the contentPane property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentPane() {
        return contentPane;
    }

    /**
     * Sets the value of the contentPane property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentPane(String value) {
        this.contentPane = value;
    }

    /**
     * Gets the value of the defaultCloseOperation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultCloseOperation() {
        return defaultCloseOperation;
    }

    /**
     * Sets the value of the defaultCloseOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultCloseOperation(String value) {
        this.defaultCloseOperation = value;
    }

    /**
     * Gets the value of the desktopIcon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesktopIcon() {
        return desktopIcon;
    }

    /**
     * Sets the value of the desktopIcon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesktopIcon(String value) {
        this.desktopIcon = value;
    }

    /**
     * Gets the value of the frameIcon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrameIcon() {
        return frameIcon;
    }

    /**
     * Sets the value of the frameIcon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrameIcon(String value) {
        this.frameIcon = value;
    }

    /**
     * Gets the value of the glassPane property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlassPane() {
        return glassPane;
    }

    /**
     * Sets the value of the glassPane property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlassPane(String value) {
        this.glassPane = value;
    }

    /**
     * Gets the value of the icon property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIcon() {
        return icon;
    }

    /**
     * Sets the value of the icon property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIcon(Boolean value) {
        this.icon = value;
    }

    /**
     * Gets the value of the iconifiable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIconifiable() {
        return iconifiable;
    }

    /**
     * Sets the value of the iconifiable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIconifiable(Boolean value) {
        this.iconifiable = value;
    }

    /**
     * Gets the value of the layer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLayer() {
        return layer;
    }

    /**
     * Sets the value of the layer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLayer(String value) {
        this.layer = value;
    }

    /**
     * Gets the value of the layeredPane property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLayeredPane() {
        return layeredPane;
    }

    /**
     * Sets the value of the layeredPane property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLayeredPane(String value) {
        this.layeredPane = value;
    }

    /**
     * Gets the value of the maximizable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMaximizable() {
        return maximizable;
    }

    /**
     * Sets the value of the maximizable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMaximizable(Boolean value) {
        this.maximizable = value;
    }

    /**
     * Gets the value of the maximum property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMaximum() {
        return maximum;
    }

    /**
     * Sets the value of the maximum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMaximum(Boolean value) {
        this.maximum = value;
    }

    /**
     * Gets the value of the normalBounds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNormalBounds() {
        return normalBounds;
    }

    /**
     * Sets the value of the normalBounds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNormalBounds(String value) {
        this.normalBounds = value;
    }

    /**
     * Gets the value of the resizable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isResizable() {
        return resizable;
    }

    /**
     * Sets the value of the resizable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setResizable(Boolean value) {
        this.resizable = value;
    }

    /**
     * Gets the value of the selected property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSelected() {
        return selected;
    }

    /**
     * Sets the value of the selected property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSelected(Boolean value) {
        this.selected = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
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
            boolean theFieldIsSet = (this.ui!= null);
            String theField;
            theField = this.getUI();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "ui", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.closable!= null);
            Boolean theField;
            theField = this.isClosable();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "closable", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.closed!= null);
            Boolean theField;
            theField = this.isClosed();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "closed", theField);
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
            boolean theFieldIsSet = (this.desktopIcon!= null);
            String theField;
            theField = this.getDesktopIcon();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "desktopIcon", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.frameIcon!= null);
            String theField;
            theField = this.getFrameIcon();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "frameIcon", theField);
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
            boolean theFieldIsSet = (this.icon!= null);
            Boolean theField;
            theField = this.isIcon();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "icon", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.iconifiable!= null);
            Boolean theField;
            theField = this.isIconifiable();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "iconifiable", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.layer!= null);
            String theField;
            theField = this.getLayer();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "layer", theField);
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
            boolean theFieldIsSet = (this.maximizable!= null);
            Boolean theField;
            theField = this.isMaximizable();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "maximizable", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.maximum!= null);
            Boolean theField;
            theField = this.isMaximum();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "maximum", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.normalBounds!= null);
            String theField;
            theField = this.getNormalBounds();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "normalBounds", theField);
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
            boolean theFieldIsSet = (this.selected!= null);
            Boolean theField;
            theField = this.isSelected();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "selected", theField);
            currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.title!= null);
            String theField;
            theField = this.getTitle();
            ObjectLocator theFieldLocator = LocatorUtils.property(locator, "title", theField);
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
        final JInternalFrame that = ((JInternalFrame) object);
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
            boolean lhsFieldIsSet = (this.ui!= null);
            boolean rhsFieldIsSet = (that.ui!= null);
            String lhsField;
            lhsField = this.getUI();
            String rhsField;
            rhsField = that.getUI();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "ui", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "ui", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.closable!= null);
            boolean rhsFieldIsSet = (that.closable!= null);
            Boolean lhsField;
            lhsField = this.isClosable();
            Boolean rhsField;
            rhsField = that.isClosable();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "closable", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "closable", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.closed!= null);
            boolean rhsFieldIsSet = (that.closed!= null);
            Boolean lhsField;
            lhsField = this.isClosed();
            Boolean rhsField;
            rhsField = that.isClosed();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "closed", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "closed", rhsField);
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
            boolean lhsFieldIsSet = (this.desktopIcon!= null);
            boolean rhsFieldIsSet = (that.desktopIcon!= null);
            String lhsField;
            lhsField = this.getDesktopIcon();
            String rhsField;
            rhsField = that.getDesktopIcon();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "desktopIcon", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "desktopIcon", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.frameIcon!= null);
            boolean rhsFieldIsSet = (that.frameIcon!= null);
            String lhsField;
            lhsField = this.getFrameIcon();
            String rhsField;
            rhsField = that.getFrameIcon();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "frameIcon", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "frameIcon", rhsField);
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
            boolean lhsFieldIsSet = (this.icon!= null);
            boolean rhsFieldIsSet = (that.icon!= null);
            Boolean lhsField;
            lhsField = this.isIcon();
            Boolean rhsField;
            rhsField = that.isIcon();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "icon", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "icon", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.iconifiable!= null);
            boolean rhsFieldIsSet = (that.iconifiable!= null);
            Boolean lhsField;
            lhsField = this.isIconifiable();
            Boolean rhsField;
            rhsField = that.isIconifiable();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "iconifiable", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "iconifiable", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.layer!= null);
            boolean rhsFieldIsSet = (that.layer!= null);
            String lhsField;
            lhsField = this.getLayer();
            String rhsField;
            rhsField = that.getLayer();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "layer", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "layer", rhsField);
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
            boolean lhsFieldIsSet = (this.maximizable!= null);
            boolean rhsFieldIsSet = (that.maximizable!= null);
            Boolean lhsField;
            lhsField = this.isMaximizable();
            Boolean rhsField;
            rhsField = that.isMaximizable();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "maximizable", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "maximizable", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.maximum!= null);
            boolean rhsFieldIsSet = (that.maximum!= null);
            Boolean lhsField;
            lhsField = this.isMaximum();
            Boolean rhsField;
            rhsField = that.isMaximum();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "maximum", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "maximum", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.normalBounds!= null);
            boolean rhsFieldIsSet = (that.normalBounds!= null);
            String lhsField;
            lhsField = this.getNormalBounds();
            String rhsField;
            rhsField = that.getNormalBounds();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "normalBounds", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "normalBounds", rhsField);
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
            boolean lhsFieldIsSet = (this.selected!= null);
            boolean rhsFieldIsSet = (that.selected!= null);
            Boolean lhsField;
            lhsField = this.isSelected();
            Boolean rhsField;
            rhsField = that.isSelected();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "selected", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "selected", rhsField);
            if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
                return false;
            }
        }
        {
            boolean lhsFieldIsSet = (this.title!= null);
            boolean rhsFieldIsSet = (that.title!= null);
            String lhsField;
            lhsField = this.getTitle();
            String rhsField;
            rhsField = that.getTitle();
            ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "title", lhsField);
            ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "title", rhsField);
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
            boolean theFieldIsSet = (this.ui!= null);
            String theField;
            theField = this.getUI();
            strategy.appendField(locator, this, "ui", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.closable!= null);
            Boolean theField;
            theField = this.isClosable();
            strategy.appendField(locator, this, "closable", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.closed!= null);
            Boolean theField;
            theField = this.isClosed();
            strategy.appendField(locator, this, "closed", buffer, theField, theFieldIsSet);
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
            boolean theFieldIsSet = (this.desktopIcon!= null);
            String theField;
            theField = this.getDesktopIcon();
            strategy.appendField(locator, this, "desktopIcon", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.frameIcon!= null);
            String theField;
            theField = this.getFrameIcon();
            strategy.appendField(locator, this, "frameIcon", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.glassPane!= null);
            String theField;
            theField = this.getGlassPane();
            strategy.appendField(locator, this, "glassPane", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.icon!= null);
            Boolean theField;
            theField = this.isIcon();
            strategy.appendField(locator, this, "icon", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.iconifiable!= null);
            Boolean theField;
            theField = this.isIconifiable();
            strategy.appendField(locator, this, "iconifiable", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.layer!= null);
            String theField;
            theField = this.getLayer();
            strategy.appendField(locator, this, "layer", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.layeredPane!= null);
            String theField;
            theField = this.getLayeredPane();
            strategy.appendField(locator, this, "layeredPane", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.maximizable!= null);
            Boolean theField;
            theField = this.isMaximizable();
            strategy.appendField(locator, this, "maximizable", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.maximum!= null);
            Boolean theField;
            theField = this.isMaximum();
            strategy.appendField(locator, this, "maximum", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.normalBounds!= null);
            String theField;
            theField = this.getNormalBounds();
            strategy.appendField(locator, this, "normalBounds", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.resizable!= null);
            Boolean theField;
            theField = this.isResizable();
            strategy.appendField(locator, this, "resizable", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.selected!= null);
            Boolean theField;
            theField = this.isSelected();
            strategy.appendField(locator, this, "selected", buffer, theField, theFieldIsSet);
        }
        {
            boolean theFieldIsSet = (this.title!= null);
            String theField;
            theField = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theField, theFieldIsSet);
        }
        return buffer;
    }

}
