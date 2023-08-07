package org.jvnet.basicjaxb.xjc.outline.concrete;

import static java.util.Objects.requireNonNull;

import org.jvnet.basicjaxb.xjc.outline.MModelOutline;
import org.jvnet.basicjaxb.xjc.outline.MObjectFactoryOutline;
import org.jvnet.basicjaxb.xjc.outline.MPackageOutline;

import com.sun.codemodel.JDefinedClass;

public class CMObjectFactoryOutline implements MObjectFactoryOutline {

	private final MModelOutline parent;

	private final MPackageOutline packageOutline;

	private final JDefinedClass code;

	public CMObjectFactoryOutline(MModelOutline parent,
			MPackageOutline packageOutline, JDefinedClass code) {
		requireNonNull(parent);
		requireNonNull(packageOutline);
		requireNonNull(code);
		this.parent = parent;
		this.packageOutline = packageOutline;
		this.code = code;
	}

	@Override
	public MModelOutline getParent() {
		return parent;
	}

	@Override
	public MPackageOutline getPackageOutline() {
		return packageOutline;
	}

	@Override
	public JDefinedClass getCode() {
		return code;
	}

}
