package org.jvnet.basicjaxb.locator;

/**
 * Models a property locator.
 * 
 * @author Aleksei Valikov
 * 
 */
public interface PropertyObjectLocator extends ObjectLocator {

	/**
	 * @return Name of the property.
	 */
	public String getPropertyName();

	/**
	 * @return Returns value of the property.
	 */
	public Object getObject();

}
