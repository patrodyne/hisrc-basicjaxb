package org.jvnet.basicjaxb.plugin.tostring.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jvnet.higherjaxb.mojo.AbstractHigherjaxbParmMojo;
import org.jvnet.higherjaxb.mojo.test.RunHigherjaxbMojo;
import org.jvnet.basicjaxb.lang.JAXBToStringStrategy;

public class RunToStringPlugin extends RunHigherjaxbMojo {

	@Override
	public File getSchemaDirectory() {
		return new File(getBaseDir(), "src/test/resources");
	}

	@Override
	protected void configureMojo(AbstractHigherjaxbParmMojo<?> mojo) {
		super.configureMojo(mojo);
		mojo.setForceRegenerate(true);
	}

	@Override
	public List<String> getArgs() {
		final List<String> args = new ArrayList<String>(super.getArgs());
		args.add("-XtoString");
		args.add("-XtoString-toStringStrategy="
				+ JAXBToStringStrategy.class.getName());
		return args;
	}

}
