package org.jvnet.basicjaxb.xjc.outline.concrete;

import static java.util.Objects.requireNonNull;

import org.jvnet.basicjaxb.util.FieldAccessorFactory;
import org.jvnet.basicjaxb.xjc.outline.FieldAccessorEx;
import org.jvnet.basicjaxb.xjc.outline.MPropertyAccessor;
import org.jvnet.basicjaxb.xjc.outline.MPropertyAccessorFactory;

import com.sun.codemodel.JExpression;
import com.sun.tools.xjc.outline.FieldOutline;

public class CMPropertyAccessorFactory implements MPropertyAccessorFactory {

	private final FieldAccessorFactory fieldAccessorFactory;
	private final FieldOutline fieldOutline;

	public CMPropertyAccessorFactory(FieldAccessorFactory fieldAccessorFactory,
			FieldOutline fieldOutline) {
		requireNonNull(fieldAccessorFactory);
		requireNonNull(fieldOutline);
		this.fieldAccessorFactory = fieldAccessorFactory;
		this.fieldOutline = fieldOutline;
	}

	@Override
	public MPropertyAccessor createPropertyAccessor(JExpression target) {
		FieldAccessorEx fieldAccessor = fieldAccessorFactory
				.createFieldAccessor(fieldOutline, target);
		return new CMPropertyAccessor(fieldAccessor);
	}

}
