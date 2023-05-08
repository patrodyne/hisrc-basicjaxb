package org.jvnet.basicjaxb.xjc.outline.artificial;

import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.commons.lang3.Validate;
import org.jvnet.basicjaxb.xjc.outline.MModelOutline;
import org.jvnet.basicjaxb.xml.bind.model.MAnyAttributePropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MAnyElementPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MAttributePropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MElementPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MElementRefPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MElementRefsPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MElementsPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MPropertyInfoVisitor;
import org.jvnet.basicjaxb.xml.bind.model.MValuePropertyInfo;

import com.sun.codemodel.JType;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;

public class PropertyTypeVisitor implements
		MPropertyInfoVisitor<NType, NClass, JType> {

	private final MModelOutline modelOutline;

	public PropertyTypeVisitor(MModelOutline modelOutline) {
		Validate.notNull(modelOutline);
		this.modelOutline = modelOutline;
	}

	@Override
	public JType visitAnyAttributePropertyInfo(
			MAnyAttributePropertyInfo<NType, NClass> info) {

		return modelOutline.getCode().ref(Map.class).narrow(QName.class)
				.narrow(Object.class);
	}

	@Override
	public JType visitElementPropertyInfo(
			MElementPropertyInfo<NType, NClass> info) {
		throw new UnsupportedOperationException();
	}

	@Override
	public JType visitElementsPropertyInfo(
			MElementsPropertyInfo<NType, NClass> info) {
		throw new UnsupportedOperationException();
	}

	@Override
	public JType visitAnyElementPropertyInfo(
			MAnyElementPropertyInfo<NType, NClass> info) {
		throw new UnsupportedOperationException();
	}

	@Override
	public JType visitAttributePropertyInfo(
			MAttributePropertyInfo<NType, NClass> info) {
		throw new UnsupportedOperationException();
	}

	@Override
	public JType visitValuePropertyInfo(MValuePropertyInfo<NType, NClass> info) {
		throw new UnsupportedOperationException();
	}

	@Override
	public JType visitElementRefPropertyInfo(
			MElementRefPropertyInfo<NType, NClass> info) {
		throw new UnsupportedOperationException();
	}

	@Override
	public JType visitElementRefsPropertyInfo(
			MElementRefsPropertyInfo<NType, NClass> info) {
		throw new UnsupportedOperationException();
	}

}
