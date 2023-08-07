package org.jvnet.basicjaxb.xjc.generator.concrete;

import static java.util.Objects.requireNonNull;

import org.jvnet.basicjaxb.util.FieldAccessorFactory;
import org.jvnet.basicjaxb.util.PropertyFieldAccessorFactory;
import org.jvnet.basicjaxb.xjc.generator.MPropertyOutlineGenerator;
import org.jvnet.basicjaxb.xjc.outline.MClassOutline;
import org.jvnet.basicjaxb.xjc.outline.MPropertyAccessorFactory;
import org.jvnet.basicjaxb.xjc.outline.MPropertyOutline;
import org.jvnet.basicjaxb.xjc.outline.concrete.CMPropertyAccessorFactory;
import org.jvnet.basicjaxb.xjc.outline.concrete.CMPropertyOutline;
import org.jvnet.basicjaxb.xml.bind.model.MModelInfo;
import org.jvnet.basicjaxb.xml.bind.model.MPropertyInfo;

import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

public class CMPropertyOutlineGenerator implements MPropertyOutlineGenerator {

	private final Outline outline;

	private final CPropertyInfo propertyInfo;

	private final FieldAccessorFactory fieldAccessorFactory = PropertyFieldAccessorFactory.INSTANCE;

	public CMPropertyOutlineGenerator(Outline outline,
			CPropertyInfo propertyInfo) {
		requireNonNull(outline);
		requireNonNull(propertyInfo);
		this.outline = outline;
		this.propertyInfo = propertyInfo;
	}

	@Override
	public MPropertyOutline generate(MClassOutline classOutline,
			MModelInfo<NType, NClass> modelInfo,
			MPropertyInfo<NType, NClass> propertyInfo) {

		final FieldOutline fieldOutline = outline.getField(this.propertyInfo);

		final MPropertyAccessorFactory propertyAccessorFactory = new CMPropertyAccessorFactory(
				this.fieldAccessorFactory, fieldOutline);
		return new CMPropertyOutline(classOutline, propertyInfo,
				propertyAccessorFactory);
	}

}
