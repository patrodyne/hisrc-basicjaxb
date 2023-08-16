package org.jvnet.basicjaxb.tests.one;

import java.util.ArrayList;
import java.util.List;

import org.jvnet.higherjaxb.mojo.AbstractHigherjaxbParmMojo;
import org.jvnet.higherjaxb.mojo.test.RunHigherJaxbMojo;

public class RunOnePlugin extends RunHigherJaxbMojo {
	
	@Override
	protected void configureMojo(AbstractHigherjaxbParmMojo<?> mojo) {
		super.configureMojo(mojo);
		mojo.setExtension(true);
		mojo.setForceRegenerate(true);
		mojo.setDebug(false);
	}

	@Override
	public List<String> getArgs() {
		final List<String> args = new ArrayList<String>(super.getArgs());
		args.add("-XtoString");
		args.add("-Xequals");
		args.add("-XhashCode");
		args.add("-Xcopyable");
		args.add("-Xmergeable");
		args.add("-Xinheritance");
		args.add("-Xsetters");
		args.add("-Xwildcard");
		return args;
	}

}
