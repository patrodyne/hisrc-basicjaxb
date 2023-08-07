package org.jvnet.basicjaxb.xjc.outline.concrete;

import static java.util.Objects.requireNonNull;

import org.jvnet.basicjaxb.xjc.outline.MClassOutline;
import org.jvnet.basicjaxb.xjc.outline.MPropertyAccessor;
import org.jvnet.basicjaxb.xjc.outline.MPropertyAccessorFactory;
import org.jvnet.basicjaxb.xjc.outline.MPropertyOutline;
import org.jvnet.basicjaxb.xml.bind.model.MPropertyInfo;

import com.sun.codemodel.JExpression;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;

public class CMPropertyOutline implements MPropertyOutline {

	private final MClassOutline classOutline;

	private final MPropertyInfo<NType, NClass> target;

	private final MPropertyAccessorFactory propertyAccessorFactory;

	public CMPropertyOutline(MClassOutline classOutline,
			MPropertyInfo<NType, NClass> target,
			MPropertyAccessorFactory propertyAccessorFactory) {
		requireNonNull(classOutline);
		requireNonNull(target);
		requireNonNull(propertyAccessorFactory);
		this.classOutline = classOutline;
		this.target = target;
		this.propertyAccessorFactory = propertyAccessorFactory;
	}

	@Override
	public MClassOutline getClassOutline() {
		return classOutline;
	}

	@Override
	public MPropertyInfo<NType, NClass> getTarget() {
		return target;
	}

	@Override
	public MPropertyAccessor createPropertyAccessor(JExpression target) {
		return this.propertyAccessorFactory.createPropertyAccessor(target);
	}

}
