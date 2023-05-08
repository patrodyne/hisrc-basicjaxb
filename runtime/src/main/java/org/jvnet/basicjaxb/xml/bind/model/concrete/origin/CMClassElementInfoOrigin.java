package org.jvnet.basicjaxb.xml.bind.model.concrete.origin;

import org.jvnet.basicjaxb.lang.Validate;
import org.jvnet.basicjaxb.xml.bind.model.origin.MElementInfoOrigin;

import org.glassfish.jaxb.core.v2.model.core.ClassInfo;

public class CMClassElementInfoOrigin<T, C, CI extends ClassInfo<T, C>>
		implements MElementInfoOrigin, ClassInfoOrigin<T, C, CI> {

	private final CI source;

	public CMClassElementInfoOrigin(CI source) {
		Validate.notNull(source);
		this.source = source;
	}

	@Override
	public CI getSource() {
		return source;
	}

}
