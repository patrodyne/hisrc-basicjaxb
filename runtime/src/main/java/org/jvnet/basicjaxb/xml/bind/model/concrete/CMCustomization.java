package org.jvnet.basicjaxb.xml.bind.model.concrete;

import org.jvnet.basicjaxb.xml.bind.model.MCustomization;
import org.w3c.dom.Element;

public class CMCustomization implements MCustomization {

	private final Element element;

	public CMCustomization(Element element) {
		this.element = element;
	}

	public Element getElement() {
		return element;
	}
}
