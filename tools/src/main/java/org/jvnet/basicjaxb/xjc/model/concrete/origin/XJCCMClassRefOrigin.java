package org.jvnet.basicjaxb.xjc.model.concrete.origin;

import org.jvnet.basicjaxb.lang.Validate;
import org.jvnet.basicjaxb.xml.bind.model.origin.MClassRefOrigin;

import com.sun.tools.xjc.model.CClassRef;

public class XJCCMClassRefOrigin implements MClassRefOrigin {

	private final CClassRef source;

	public XJCCMClassRefOrigin(CClassRef source) {
		Validate.notNull(source);
		this.source = source;
	}

	public CClassRef getSource() {
		return source;
	}

}
