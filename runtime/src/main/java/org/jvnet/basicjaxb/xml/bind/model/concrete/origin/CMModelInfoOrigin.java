package org.jvnet.basicjaxb.xml.bind.model.concrete.origin;

import static java.util.Objects.requireNonNull;

import org.glassfish.jaxb.core.v2.model.core.TypeInfoSet;
import org.jvnet.basicjaxb.xml.bind.model.origin.MModelInfoOrigin;

public class CMModelInfoOrigin<T, C, TIS extends TypeInfoSet<T, C, ?, ?>>
		implements MModelInfoOrigin, TypeInfoSetOrigin<T, C, TIS> {

	private final TIS source;

	public CMModelInfoOrigin(TIS source) {
		requireNonNull(source);
		this.source = source;
	}

	@Override
	public TIS getSource() {
		return source;
	}

}
