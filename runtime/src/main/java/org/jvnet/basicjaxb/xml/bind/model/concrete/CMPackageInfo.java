package org.jvnet.basicjaxb.xml.bind.model.concrete;

import org.jvnet.basicjaxb.lang.StringUtils;
import org.jvnet.basicjaxb.lang.Validate;
import org.jvnet.basicjaxb.xml.bind.model.MPackageInfo;
import org.jvnet.basicjaxb.xml.bind.model.origin.MPackageInfoOrigin;

public class CMPackageInfo implements MPackageInfo {

	private final MPackageInfoOrigin origin;
	private final String packageName;

	public CMPackageInfo(MPackageInfoOrigin origin, String packageName) {
		Validate.notNull(origin);
		Validate.notNull(packageName);
		this.origin = origin;
		this.packageName = packageName;
	}

	@Override
	public MPackageInfoOrigin getOrigin() {
		return origin;
	}

	@Override
	public String getPackageName() {
		return packageName;
	}

	@Override
	public String getPackagedName(String localName) {
		if (StringUtils.isEmpty(packageName)) {
			return localName;
		} else {
			return packageName + "." + localName;
		}
	}

	@Override
	public String getLocalName() {
		return null;
	}

	@Override
	public String getContainerLocalName(String delimiter) {
		return null;
	}

	@Override
	public MPackageInfo getPackageInfo() {
		return this;
	}

}
