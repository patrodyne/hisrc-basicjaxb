package org.jvnet.basicjaxb.xml.bind.model.concrete.origin;

import static java.util.Objects.requireNonNull;

import org.glassfish.jaxb.core.v2.model.core.ClassInfo;
import org.jvnet.basicjaxb.xml.bind.model.origin.MClassInfoOrigin;
import org.jvnet.basicjaxb.xml.bind.model.origin.MElementInfoOrigin;

public class CMClassInfoOrigin<T, C, CI extends ClassInfo<T, C>> implements
		MClassInfoOrigin, ClassInfoOrigin<T, C, CI> {

	private final CI source;

	public CMClassInfoOrigin(CI source) {
		requireNonNull(source);
		this.source = source;
	}

	@Override
	public CI getSource() {
		return source;
	}

	@Override
	public MElementInfoOrigin createElementInfoOrigin() {
		return new CMClassElementInfoOrigin<T, C, CI>(getSource());
	}

}
