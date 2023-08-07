package org.jvnet.basicjaxb.xml.bind.model.concrete.origin;

import static java.util.Objects.requireNonNull;

import org.glassfish.jaxb.core.v2.model.core.ElementInfo;
import org.jvnet.basicjaxb.xml.bind.model.origin.MElementInfoOrigin;

public class CMElementInfoOrigin<T, C, EI extends ElementInfo<T, C>> implements
		MElementInfoOrigin, ElementInfoOrigin<T, C, EI> {

	private final EI source;

	public CMElementInfoOrigin(EI source) {
		requireNonNull(source);
		this.source = source;
	}

	@Override
	public EI getSource() {
		return source;
	}

}
