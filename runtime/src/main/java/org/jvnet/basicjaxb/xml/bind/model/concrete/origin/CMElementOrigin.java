package org.jvnet.basicjaxb.xml.bind.model.concrete.origin;

import static java.util.Objects.requireNonNull;

import org.glassfish.jaxb.core.v2.model.core.Element;
import org.jvnet.basicjaxb.xml.bind.model.origin.MElementOrigin;

public class CMElementOrigin<T, C, E extends Element<T, C>>
		implements MElementOrigin, ElementOrigin<T, C, E> {

	private final E source;

	public CMElementOrigin(E source) {
		requireNonNull(source);
		this.source = source;
	}

	@Override
	public E getSource() {
		return source;
	}

}
