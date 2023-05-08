package org.jvnet.basicjaxb.xml.bind.model.util;

import org.jvnet.basicjaxb.xml.bind.model.MAnyAttributePropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MAnyElementPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MAttributePropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MElementPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MElementRefPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MElementRefsPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MElementsPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MPropertyInfoVisitor;
import org.jvnet.basicjaxb.xml.bind.model.MValuePropertyInfo;

public class DefaultPropertyInfoVisitor<T, C extends T, V> implements
		MPropertyInfoVisitor<T, C, V> {

	public V visitPropertyInfo(MPropertyInfo<T, C> info) {
		return null;
	}

	@Override
	public V visitElementPropertyInfo(MElementPropertyInfo<T, C> info) {
		return visitPropertyInfo(info);
	}

	@Override
	public V visitElementsPropertyInfo(MElementsPropertyInfo<T, C> info) {
		return visitPropertyInfo(info);
	}

	@Override
	public V visitAnyElementPropertyInfo(MAnyElementPropertyInfo<T, C> info) {
		return visitPropertyInfo(info);
	}

	@Override
	public V visitAttributePropertyInfo(MAttributePropertyInfo<T, C> info) {
		return visitPropertyInfo(info);
	}

	@Override
	public V visitAnyAttributePropertyInfo(MAnyAttributePropertyInfo<T, C> info) {
		return visitPropertyInfo(info);
	}

	@Override
	public V visitValuePropertyInfo(MValuePropertyInfo<T, C> info) {
		return visitPropertyInfo(info);
	}

	@Override
	public V visitElementRefPropertyInfo(MElementRefPropertyInfo<T, C> info) {
		return visitPropertyInfo(info);
	}

	@Override
	public V visitElementRefsPropertyInfo(MElementRefsPropertyInfo<T, C> info) {
		return visitPropertyInfo(info);
	}

}
