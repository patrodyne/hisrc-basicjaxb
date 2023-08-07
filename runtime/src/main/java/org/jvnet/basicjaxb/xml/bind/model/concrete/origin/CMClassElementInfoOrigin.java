package org.jvnet.basicjaxb.xml.bind.model.concrete.origin;

import static java.util.Objects.requireNonNull;

import org.glassfish.jaxb.core.v2.model.core.ClassInfo;
import org.jvnet.basicjaxb.xml.bind.model.origin.MElementInfoOrigin;

public class CMClassElementInfoOrigin<T, C, CI extends ClassInfo<T, C>>
		implements MElementInfoOrigin, ClassInfoOrigin<T, C, CI> {

	private final CI source;

	public CMClassElementInfoOrigin(CI source) {
		requireNonNull(source);
		this.source = source;
	}

	@Override
	public CI getSource() {
		return source;
	}

}
