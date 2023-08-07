package org.jvnet.basicjaxb.xjc.model.concrete.origin;

import static java.util.Objects.requireNonNull;

import org.jvnet.basicjaxb.xjc.generator.MPackageOutlineGenerator;
import org.jvnet.basicjaxb.xjc.generator.concrete.CMPackageOutlineGenerator;
import org.jvnet.basicjaxb.xjc.generator.concrete.PackageOutlineGeneratorFactory;
import org.jvnet.basicjaxb.xml.bind.model.concrete.origin.CMPackageInfoOrigin;

import com.sun.codemodel.JPackage;
import com.sun.tools.xjc.outline.Outline;

public class XJCCMPackageInfoOrigin extends CMPackageInfoOrigin implements
		PackageInfoOrigin, PackageOutlineGeneratorFactory {

	private final JPackage source;

	public XJCCMPackageInfoOrigin(JPackage source) {
		requireNonNull(source);
		this.source = source;
	}

	@Override
	public JPackage getSource() {
		return source;
	}

	@Override
	public MPackageOutlineGenerator createGenerator(Outline outline) {
		return new CMPackageOutlineGenerator(outline, getSource());
	}

}
