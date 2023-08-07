package org.jvnet.basicjaxb.xjc.model.concrete.origin;

import static java.util.Objects.requireNonNull;

import org.jvnet.basicjaxb.xml.bind.model.origin.MClassRefOrigin;

import com.sun.tools.xjc.model.CClassRef;

public class XJCCMClassRefOrigin implements MClassRefOrigin {

	private final CClassRef source;

	public XJCCMClassRefOrigin(CClassRef source) {
		requireNonNull(source);
		this.source = source;
	}

	public CClassRef getSource() {
		return source;
	}

}
