package org.jvnet.basicjaxb.xjc.generator.concrete;

import org.apache.commons.lang3.Validate;
import org.jvnet.basicjaxb.xjc.generator.MPackageOutlineGenerator;
import org.jvnet.basicjaxb.xjc.outline.MModelOutline;
import org.jvnet.basicjaxb.xjc.outline.MPackageOutline;
import org.jvnet.basicjaxb.xjc.outline.concrete.CMPackageOutline;
import org.jvnet.basicjaxb.xml.bind.model.MModelInfo;
import org.jvnet.basicjaxb.xml.bind.model.MPackageInfo;

import com.sun.codemodel.JPackage;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;
import com.sun.tools.xjc.outline.Outline;
import com.sun.tools.xjc.outline.PackageOutline;

public class CMPackageOutlineGenerator implements MPackageOutlineGenerator {

	private final Outline outline;

	private final JPackage packageInfo;

	public CMPackageOutlineGenerator(Outline outline, JPackage packageInfo) {
		Validate.notNull(outline);
		Validate.notNull(packageInfo);
		this.outline = outline;
		this.packageInfo = packageInfo;
	}

	@Override
	public MPackageOutline generate(MModelOutline parent,
			MModelInfo<NType, NClass> modelInfo, MPackageInfo packageInfo) {
		final PackageOutline packageOutline = outline
				.getPackageContext(this.packageInfo);
		Validate.notNull(packageOutline);
		return new CMPackageOutline(parent, packageInfo, packageOutline);
	}

}
