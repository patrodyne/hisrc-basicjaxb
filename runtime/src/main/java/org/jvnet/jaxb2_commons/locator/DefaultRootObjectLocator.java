package org.jvnet.jaxb2_commons.locator;

import static java.lang.Integer.toHexString;
import static java.lang.System.identityHashCode;

import java.text.MessageFormat;

public final class DefaultRootObjectLocator extends AbstractObjectLocator
		implements RootObjectLocator {

	public DefaultRootObjectLocator(Object rootObject) {
		super(null, rootObject);
	}

	public Object[] getMessageParameters() {
		return new Object[] { getObject() };
	}

	@Override
	protected String getDefaultMessage() {
		return MessageFormat
				.format("Root object: {0}.", getMessageParameters());
	}

	@Override
	protected String getStepAsString() {
		return "("+ getObject().getClass().getSimpleName() + "@" +
			toHexString(identityHashCode(getObject())) + ")";
	}
}
