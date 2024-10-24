
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
 * <p>Java class for JTableHeader complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="JTableHeader">
 *	 <complexContent>
 *	   <extension base="{http://www.swixml.org/2007/Swixml}JComponent">
 *		 <sequence>
 *		 </sequence>
 *		 <attribute name="UI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="columnModel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="defaultRenderer" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="draggedColumn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="draggedDistance" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="reorderingAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="resizingAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *		 <attribute name="resizingColumn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="table" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 <attribute name="updateTableInRealTime" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *	   </extension>
 *	 </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JTableHeader")
public class JTableHeader
	extends JComponent
	implements Serializable
{

	private static final long serialVersionUID = 20240701L;
	@XmlAttribute(name = "UI")
	protected String ui;
	@XmlAttribute(name = "columnModel")
	protected String columnModel;
	@XmlAttribute(name = "defaultRenderer")
	protected String defaultRenderer;
	@XmlAttribute(name = "draggedColumn")
	protected String draggedColumn;
	@XmlAttribute(name = "draggedDistance")
	protected String draggedDistance;
	@XmlAttribute(name = "reorderingAllowed")
	protected Boolean reorderingAllowed;
	@XmlAttribute(name = "resizingAllowed")
	protected Boolean resizingAllowed;
	@XmlAttribute(name = "resizingColumn")
	protected String resizingColumn;
	@XmlAttribute(name = "table")
	protected String table;
	@XmlAttribute(name = "updateTableInRealTime")
	protected Boolean updateTableInRealTime;

	/**
	 * Gets the value of the ui property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getUI() {
		return ui;
	}

	/**
	 * Sets the value of the ui property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setUI(String value) {
		this.ui = value;
	}

	/**
	 * Gets the value of the columnModel property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getColumnModel() {
		return columnModel;
	}

	/**
	 * Sets the value of the columnModel property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setColumnModel(String value) {
		this.columnModel = value;
	}

	/**
	 * Gets the value of the defaultRenderer property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getDefaultRenderer() {
		return defaultRenderer;
	}

	/**
	 * Sets the value of the defaultRenderer property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setDefaultRenderer(String value) {
		this.defaultRenderer = value;
	}

	/**
	 * Gets the value of the draggedColumn property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getDraggedColumn() {
		return draggedColumn;
	}

	/**
	 * Sets the value of the draggedColumn property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setDraggedColumn(String value) {
		this.draggedColumn = value;
	}

	/**
	 * Gets the value of the draggedDistance property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getDraggedDistance() {
		return draggedDistance;
	}

	/**
	 * Sets the value of the draggedDistance property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setDraggedDistance(String value) {
		this.draggedDistance = value;
	}

	/**
	 * Gets the value of the reorderingAllowed property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isReorderingAllowed() {
		return reorderingAllowed;
	}

	/**
	 * Sets the value of the reorderingAllowed property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setReorderingAllowed(Boolean value) {
		this.reorderingAllowed = value;
	}

	/**
	 * Gets the value of the resizingAllowed property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isResizingAllowed() {
		return resizingAllowed;
	}

	/**
	 * Sets the value of the resizingAllowed property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setResizingAllowed(Boolean value) {
		this.resizingAllowed = value;
	}

	/**
	 * Gets the value of the resizingColumn property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getResizingColumn() {
		return resizingColumn;
	}

	/**
	 * Sets the value of the resizingColumn property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setResizingColumn(String value) {
		this.resizingColumn = value;
	}

	/**
	 * Gets the value of the table property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link String }
	 *	   
	 */
	public String getTable() {
		return table;
	}

	/**
	 * Sets the value of the table property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link String }
	 *	   
	 */
	public void setTable(String value) {
		this.table = value;
	}

	/**
	 * Gets the value of the updateTableInRealTime property.
	 * 
	 * @return
	 *	   possible object is
	 *	   {@link Boolean }
	 *	   
	 */
	public Boolean isUpdateTableInRealTime() {
		return updateTableInRealTime;
	}

	/**
	 * Sets the value of the updateTableInRealTime property.
	 * 
	 * @param value
	 *	   allowed object is
	 *	   {@link Boolean }
	 *	   
	 */
	public void setUpdateTableInRealTime(Boolean value) {
		this.updateTableInRealTime = value;
	}

	@Override
	public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
		int currentHashCode = super.hashCode(locator, strategy);
		{
			boolean theFieldIsSet = (this.ui!= null);
			String theField;
			theField = this.getUI();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "ui", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.columnModel!= null);
			String theField;
			theField = this.getColumnModel();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "columnModel", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.defaultRenderer!= null);
			String theField;
			theField = this.getDefaultRenderer();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "defaultRenderer", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.draggedColumn!= null);
			String theField;
			theField = this.getDraggedColumn();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "draggedColumn", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.draggedDistance!= null);
			String theField;
			theField = this.getDraggedDistance();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "draggedDistance", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.reorderingAllowed!= null);
			Boolean theField;
			theField = this.isReorderingAllowed();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "reorderingAllowed", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.resizingAllowed!= null);
			Boolean theField;
			theField = this.isResizingAllowed();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "resizingAllowed", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.resizingColumn!= null);
			String theField;
			theField = this.getResizingColumn();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "resizingColumn", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.table!= null);
			String theField;
			theField = this.getTable();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "table", theField);
			currentHashCode = strategy.hashCode(theFieldLocator, currentHashCode, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.updateTableInRealTime!= null);
			Boolean theField;
			theField = this.isUpdateTableInRealTime();
			ObjectLocator theFieldLocator = LocatorUtils.property(locator, "updateTableInRealTime", theField);
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
		final JTableHeader that = ((JTableHeader) object);
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
			boolean lhsFieldIsSet = (this.columnModel!= null);
			boolean rhsFieldIsSet = (that.columnModel!= null);
			String lhsField;
			lhsField = this.getColumnModel();
			String rhsField;
			rhsField = that.getColumnModel();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "columnModel", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "columnModel", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.defaultRenderer!= null);
			boolean rhsFieldIsSet = (that.defaultRenderer!= null);
			String lhsField;
			lhsField = this.getDefaultRenderer();
			String rhsField;
			rhsField = that.getDefaultRenderer();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "defaultRenderer", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "defaultRenderer", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.draggedColumn!= null);
			boolean rhsFieldIsSet = (that.draggedColumn!= null);
			String lhsField;
			lhsField = this.getDraggedColumn();
			String rhsField;
			rhsField = that.getDraggedColumn();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "draggedColumn", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "draggedColumn", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.draggedDistance!= null);
			boolean rhsFieldIsSet = (that.draggedDistance!= null);
			String lhsField;
			lhsField = this.getDraggedDistance();
			String rhsField;
			rhsField = that.getDraggedDistance();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "draggedDistance", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "draggedDistance", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.reorderingAllowed!= null);
			boolean rhsFieldIsSet = (that.reorderingAllowed!= null);
			Boolean lhsField;
			lhsField = this.isReorderingAllowed();
			Boolean rhsField;
			rhsField = that.isReorderingAllowed();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "reorderingAllowed", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "reorderingAllowed", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.resizingAllowed!= null);
			boolean rhsFieldIsSet = (that.resizingAllowed!= null);
			Boolean lhsField;
			lhsField = this.isResizingAllowed();
			Boolean rhsField;
			rhsField = that.isResizingAllowed();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "resizingAllowed", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "resizingAllowed", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.resizingColumn!= null);
			boolean rhsFieldIsSet = (that.resizingColumn!= null);
			String lhsField;
			lhsField = this.getResizingColumn();
			String rhsField;
			rhsField = that.getResizingColumn();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "resizingColumn", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "resizingColumn", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.table!= null);
			boolean rhsFieldIsSet = (that.table!= null);
			String lhsField;
			lhsField = this.getTable();
			String rhsField;
			rhsField = that.getTable();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "table", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "table", rhsField);
			if (!strategy.equals(lhsFieldLocator, rhsFieldLocator, lhsField, rhsField, lhsFieldIsSet, rhsFieldIsSet)) {
				return false;
			}
		}
		{
			boolean lhsFieldIsSet = (this.updateTableInRealTime!= null);
			boolean rhsFieldIsSet = (that.updateTableInRealTime!= null);
			Boolean lhsField;
			lhsField = this.isUpdateTableInRealTime();
			Boolean rhsField;
			rhsField = that.isUpdateTableInRealTime();
			ObjectLocator lhsFieldLocator = LocatorUtils.property(thisLocator, "updateTableInRealTime", lhsField);
			ObjectLocator rhsFieldLocator = LocatorUtils.property(thatLocator, "updateTableInRealTime", rhsField);
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
			boolean theFieldIsSet = (this.ui!= null);
			String theField;
			theField = this.getUI();
			strategy.appendField(locator, this, "ui", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.columnModel!= null);
			String theField;
			theField = this.getColumnModel();
			strategy.appendField(locator, this, "columnModel", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.defaultRenderer!= null);
			String theField;
			theField = this.getDefaultRenderer();
			strategy.appendField(locator, this, "defaultRenderer", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.draggedColumn!= null);
			String theField;
			theField = this.getDraggedColumn();
			strategy.appendField(locator, this, "draggedColumn", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.draggedDistance!= null);
			String theField;
			theField = this.getDraggedDistance();
			strategy.appendField(locator, this, "draggedDistance", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.reorderingAllowed!= null);
			Boolean theField;
			theField = this.isReorderingAllowed();
			strategy.appendField(locator, this, "reorderingAllowed", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.resizingAllowed!= null);
			Boolean theField;
			theField = this.isResizingAllowed();
			strategy.appendField(locator, this, "resizingAllowed", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.resizingColumn!= null);
			String theField;
			theField = this.getResizingColumn();
			strategy.appendField(locator, this, "resizingColumn", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.table!= null);
			String theField;
			theField = this.getTable();
			strategy.appendField(locator, this, "table", buffer, theField, theFieldIsSet);
		}
		{
			boolean theFieldIsSet = (this.updateTableInRealTime!= null);
			Boolean theField;
			theField = this.isUpdateTableInRealTime();
			strategy.appendField(locator, this, "updateTableInRealTime", buffer, theField, theFieldIsSet);
		}
		return buffer;
	}

}
