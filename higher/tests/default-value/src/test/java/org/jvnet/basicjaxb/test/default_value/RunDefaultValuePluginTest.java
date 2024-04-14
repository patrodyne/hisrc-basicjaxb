package org.jvnet.basicjaxb.test.default_value;

import static org.apache.maven.artifact.Artifact.SCOPE_RUNTIME;

import java.util.ArrayList;

import org.apache.maven.model.Dependency;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.jvnet.higherjaxb.mojo.HigherjaxbMojo;
import org.jvnet.higherjaxb.mojo.testing.AbstractMojoTest;
import org.jvnet.higherjaxb.mojo.testing.SLF4JLogger;

@Order(1)
public class RunDefaultValuePluginTest extends AbstractMojoTest
{
	@Test
	public void testExecute() throws Exception
	{
		//
		// Dependencies
		//

		final Dependency hyperannox = new Dependency();
		hyperannox.setGroupId("org.patrodyne.jvnet");
		hyperannox.setArtifactId("hisrc-hyperjaxb-annox-plugin");
		hyperannox.setVersion(getProjectVersion());
		hyperannox.setScope(SCOPE_RUNTIME);

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
		
		mojo.getArgs().add("-Xannotate");
		mojo.getArgs().add("-Xcustomizations");
		mojo.getArgs().add("-Xcustomizations-directory=src/main/resources");
		mojo.getArgs().add("-Xcustomizations-verbose=true");
		mojo.getArgs().add("-Xcustomizations-debug=true");
		mojo.getArgs().add("-XhashCode");
		mojo.getArgs().add("-Xequals");
		mojo.getArgs().add("-XtoString");
		mojo.getArgs().add("-Xinheritance");
		mojo.getArgs().add("-XdefaultValue");
		mojo.getArgs().add("-XdefaultValue-mapClass=java.util.TreeMap");

		mojo.setPlugins(new Dependency[] { hyperannox, basicjaxb });
		
		mojo.execute();
	}
}
