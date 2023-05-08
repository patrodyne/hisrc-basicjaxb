package org.jvnet.basicjaxb.xjc.generator.artificial;

import org.jvnet.basicjaxb.xjc.generator.MPropertyOutlineGenerator;
import org.jvnet.basicjaxb.xjc.outline.MClassOutline;
import org.jvnet.basicjaxb.xjc.outline.MPropertyAccessor;
import org.jvnet.basicjaxb.xjc.outline.MPropertyAccessorFactory;
import org.jvnet.basicjaxb.xjc.outline.MPropertyOutline;
import org.jvnet.basicjaxb.xjc.outline.concrete.CMPropertyOutline;
import org.jvnet.basicjaxb.xml.bind.model.MModelInfo;
import org.jvnet.basicjaxb.xml.bind.model.MPropertyInfo;

import com.sun.codemodel.JExpression;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;

public class WrapperPropertyOutlineGenerator implements
		MPropertyOutlineGenerator {

	@Override
	public MPropertyOutline generate(MClassOutline classOutline,
			MModelInfo<NType, NClass> modelInfo,
			MPropertyInfo<NType, NClass> propertyInfo) {
		return new CMPropertyOutline(classOutline, propertyInfo,
				new MPropertyAccessorFactory() {

					@Override
					public MPropertyAccessor createPropertyAccessor(
							JExpression target) {
						// TODO
						throw new UnsupportedOperationException();
					}
				});
	}

}
