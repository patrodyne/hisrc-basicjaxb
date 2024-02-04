package org.jvnet.basicjaxb.tests.one;

import static org.apache.maven.artifact.Artifact.SCOPE_RUNTIME;

import java.util.ArrayList;

import org.apache.maven.model.Dependency;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.jvnet.higherjaxb.mojo.HigherjaxbMojo;
import org.jvnet.higherjaxb.mojo.testing.AbstractMojoTest;
import org.jvnet.higherjaxb.mojo.testing.SLF4JLogger;

@Order(1)
public class RunOnePluginTest extends AbstractMojoTest
{
	@Test
	public void testExecute() throws Exception
	{
		//
		// Dependencies
		//
		
		final Dependency basicjaxb = new Dependency();
		basicjaxb.setGroupId("org.patrodyne.jvnet");
		basicjaxb.setArtifactId("hisrc-basicjaxb-plugins");
		basicjaxb.setVersion(getProjectVersion());
		basicjaxb.setScope(SCOPE_RUNTIME);
		
		//
		// MOJO Execution
		//

		HigherjaxbMojo mojo = new HigherjaxbMojo();
		mojo.setLog(new SLF4JLogger(getLogger()));

		mojo.getRemoteRepos().add(REMOTE_REPOSITORY);
		mojo.setRepoSession(REPOSITORY_SYSTEM_SESSION);
		mojo.setRepoSystem(repositorySystem);
		
		mojo.setProject(createMavenProject());
		mojo.setSchemaDirectory(fullpath("src/main/resources"));
		mojo.setGenerateDirectory(fullpath("target/generated-sources/xjc")); 
		mojo.setVerbose(true);
		mojo.setDebug(true);
		mojo.setWriteCode(true);
		mojo.setForceRegenerate(true);
		mojo.setNoFileHeader(true);
		mojo.setExtension(true);
		mojo.setArgs(new ArrayList<>());
		mojo.getArgs().add("-XtoString");
		mojo.getArgs().add("-Xequals");
		mojo.getArgs().add("-XhashCode");
		mojo.getArgs().add("-Xcopyable");
		mojo.getArgs().add("-Xmergeable");
		mojo.getArgs().add("-Xinheritance");
		mojo.getArgs().add("-Xsetters");
		mojo.getArgs().add("-Xwildcard");

		mojo.setPlugins(new Dependency[] { basicjaxb });

		mojo.execute();
	}
}
