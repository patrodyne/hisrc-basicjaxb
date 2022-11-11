package org.jvnet.basicjaxb.tests.issues;

import java.util.ArrayList;
import java.util.List;

import org.jvnet.higherjaxb.mojo.AbstractHigherjaxbParmMojo;
import org.jvnet.higherjaxb.mojo.test.RunHigherjaxbMojo;

public class RunIssuesPlugin extends RunHigherjaxbMojo {

	@Override
	protected void configureMojo(AbstractHigherjaxbParmMojo mojo) {
		super.configureMojo(mojo);
		mojo.setExtension(true);
		mojo.setForceRegenerate(true);
	}

	@Override
	public List<String> getArgs() {
		final List<String> args = new ArrayList<String>(super.getArgs());
//		args.add("-XelementWrapper");
		args.add("-XenumValue");
		args.add("-XtoString");
		args.add("-Xequals");
		args.add("-XhashCode");
		args.add("-Xcopyable");
		args.add("-Xmergeable");
		args.add("-Xinheritance");
		args.add("-Xsetters");
		args.add("-Xsetters-mode=direct");
		args.add("-Xwildcard");
		args.add("-XautoInheritance");
		args.add("-XautoInheritance-xmlRootElementsExtend=org.jvnet.basicjaxb.tests.issues.IssueJIIB14BaseClass");
		args.add("-XautoInheritance-xmlRootElementsImplement=org.jvnet.basicjaxb.tests.issues.IssueJIIB14BaseInterfaceOne");
		args.add("-XautoInheritance-xmlRootElementsImplement=org.jvnet.basicjaxb.tests.issues.IssueJIIB14BaseInterfaceTwo");
		args.add("-XautoInheritance-jaxbElementsImplement=org.jvnet.basicjaxb.tests.issues.IssueJIIB14BaseInterfaceThree");
		args.add("-XautoInheritance-jaxbElementsImplement=org.jvnet.basicjaxb.tests.issues.IssueJIIB14BaseInterfaceFour");
		args.add("-Xannotate");
		return args;
	}

}
