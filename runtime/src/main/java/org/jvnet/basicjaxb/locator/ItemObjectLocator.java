package org.jvnet.basicjaxb.locator;

/**
 * Models item locator.
 * 
 * @author Aleksei Valikov
 * 
 */
public interface ItemObjectLocator extends ObjectLocator {

	/**
	 * @return Item index.
	 */
	public int getIndex();

	/**
	 * @return Item value.
	 */
	@Override
	public Object getObject();

}
