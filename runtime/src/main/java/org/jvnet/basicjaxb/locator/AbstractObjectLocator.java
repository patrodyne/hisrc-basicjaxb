package org.jvnet.basicjaxb.locator;

import java.net.URL;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.w3c.dom.Node;

/**
 * Abstract base class for event locators.
 * 
 * @author Aleksei Valikov
 */
public abstract class AbstractObjectLocator implements ObjectLocator {
	/**
	 * Parent locator.
	 */
	protected final ObjectLocator parentLocator;
	/**
	 * Object.
	 */
	protected final Object object;

	/**
	 * Constructs a new validation event locator.
	 * 
	 * @param parentLocator
	 *            parent location (may be <code>null</code>).
	 * @param object
	 *            object.
	 */
	protected AbstractObjectLocator(final ObjectLocator parentLocator,
			final Object object) {
		this.object = object;
		this.parentLocator = parentLocator;
	}

	/**
	 * Returns parent locator.
	 * 
	 * @return Parent locator.
	 */
	@Override
	public ObjectLocator getParentLocator() {
		return parentLocator;
	}

	@Override
	public ObjectLocator[] getPath() {
		final ObjectLocator[] path = new ObjectLocator[getAncestorCount(this) + 1];
		fillPath(this, path, path.length - 1);
		return path;
	}

	@Override
	public String getPathAsString() {
		final String stepAsString = getStepAsString();
		final ObjectLocator parentLocator = getParentLocator();
		return parentLocator == null ? stepAsString : parentLocator
				.getPathAsString() + stepAsString;
	}

	protected abstract String getStepAsString();

	private void fillPath(ObjectLocator locator, ObjectLocator[] path, int index) {
		path[index] = locator;
		final ObjectLocator parent = locator.getParentLocator();
		if (parent != null)
			fillPath(parent, path, index - 1);
	}

	private int getAncestorCount(ObjectLocator locator) {
		final ObjectLocator parent = locator.getParentLocator();
		if (parent == null)
			return 0;
		else
			return 1 + getAncestorCount(parent);
	}

	@Override
	public Object getObject() {
		return object;
	}

	@Override
	public int getColumnNumber() {
		return 0;
	}

	@Override
	public int getLineNumber() {
		return 0;
	}

	@Override
	public int getOffset() {
		return 0;
	}

	@Override
	public URL getURL() {
		return null;
	}

	@Override
	public Node getNode() {
		return null;
	}

	// /**
	// * Returns expression step (for EL and JXPath expressions).
	// * @return Expression step.
	// */
	// public abstract String getStep();

	@Override
	public String toString() {
		return getMessage();
	}

	/**
	 * Returns message code.
	 * 
	 * @return Message code.
	 */
	@Override
	public String getMessageCode() {
		return getClass().getName();
	}

	protected abstract String getDefaultMessage();

	// public Object[] getMessageParameters() {
	// return new Object[] { getObject() };
	// }
	//
	@Override
	public String getMessage(ResourceBundle bundle) {
		try {
			final String messageTemplate = bundle.getString(getMessageCode());
			return MessageFormat
					.format(messageTemplate, getMessageParameters());
		} catch (MissingResourceException mrex) {
			return getDefaultMessage();
		}
	}

	/**
	 * Returns location message.
	 * 
	 * @return Location message.
	 */
	@Override
	public String getMessage() {
		return getMessage(ResourceBundle.getBundle(getClass().getPackage()
				.getName() + ".messages"));
	}

	// public int hashCode() {
	// int hashCode = getObject().hashCode();
	// return hashCode;
	// }

	@Override
	public ItemObjectLocator item(int index, Object value) {
		return new DefaultItemObjectLocator(this, index, value);
	}

	@Override
	public PropertyObjectLocator property(String name, Object value) {
		return new DefaultPropertyObjectLocator(this, name, value);
	}

}
