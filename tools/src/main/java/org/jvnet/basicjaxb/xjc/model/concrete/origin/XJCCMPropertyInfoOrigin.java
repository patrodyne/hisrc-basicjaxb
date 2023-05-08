package org.jvnet.basicjaxb.xjc.model.concrete.origin;

import org.hisrc.xml.xsom.SchemaComponentAware;
import org.jvnet.basicjaxb.xjc.generator.MPropertyOutlineGenerator;
import org.jvnet.basicjaxb.xjc.generator.concrete.CMPropertyOutlineGenerator;
import org.jvnet.basicjaxb.xjc.generator.concrete.PropertyOutlineGeneratorFactory;
import org.jvnet.basicjaxb.xml.bind.model.concrete.origin.CMPropertyInfoOrigin;

import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;
import com.sun.tools.xjc.outline.Outline;
import com.sun.xml.xsom.XSComponent;

public class XJCCMPropertyInfoOrigin extends
		CMPropertyInfoOrigin<NType, NClass, CPropertyInfo> implements
		PropertyOutlineGeneratorFactory, SchemaComponentAware {

	public XJCCMPropertyInfoOrigin(CPropertyInfo source) {
		super(source);
	}

	@Override
	public MPropertyOutlineGenerator createGenerator(Outline outline) {
		return new CMPropertyOutlineGenerator(outline, getSource());
	}

	@Override
	public XSComponent getSchemaComponent() {
		return getSource().getSchemaComponent();
	}

}
