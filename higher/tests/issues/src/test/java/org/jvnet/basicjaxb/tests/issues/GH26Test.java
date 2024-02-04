package org.jvnet.basicjaxb.tests.issues;

import static org.apache.maven.artifact.Artifact.SCOPE_RUNTIME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.apache.maven.model.Dependency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.xjc.model.concrete.XJCCMInfoFactory;
import org.jvnet.basicjaxb.xml.bind.model.MAttributePropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MClassInfo;
import org.jvnet.basicjaxb.xml.bind.model.MElementPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MElementRefsPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MElementsPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MModelInfo;
import org.jvnet.higherjaxb.mojo.HigherjaxbMojo;
import org.jvnet.higherjaxb.mojo.testing.AbstractMojoTest;
import org.jvnet.higherjaxb.mojo.testing.SLF4JLogger;

import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;
import com.sun.tools.xjc.outline.Outline;

@Order(2)
public class GH26Test extends AbstractMojoTest
{
	private Outline outline;
	public Outline getOutline() { return outline; }
	public void setOutline(Outline outline) { this.outline = outline; }

	@Override
	@BeforeEach
	public void beforeEach() throws Exception
	{
		super.beforeEach();
		
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
		mojo.setForceRegenerate(true);
		mojo.setNoFileHeader(true);
		mojo.setExtension(true);
		mojo.setArgs(new ArrayList<>());
		mojo.getArgs().add("-XhashCode");
		mojo.getArgs().add("-Xequals");
		mojo.getArgs().add("-XtoString");
		mojo.getArgs().add("-Xcopyable");
		mojo.getArgs().add("-Xmergeable");
		mojo.getArgs().add("-Xinheritance");
		mojo.getArgs().add("-Xsetters");
		mojo.getArgs().add("-Xsetters-mode=direct");
		mojo.getArgs().add("-Xwildcard");
		mojo.getArgs().add("-Xwildcard-debug=true");
		mojo.getArgs().add("-Xwildcard-verbose=true");
		mojo.getArgs().add("-XenumValue");
		mojo.setWriteCode(false);
		
		mojo.setPlugins(new Dependency[] { basicjaxb });
		
		mojo.execute();
		
		if ( mojo.getOutline() instanceof Outline )
			setOutline( (Outline) mojo.getOutline() );
		else
			fail("Unknown outline: " + mojo.getOutline());
	}
	
	@Test
	public void testAddDefaultValue() throws Exception
	{
		// IssueGH26Type
		Model model = getOutline().getModel();
		
		final XJCCMInfoFactory factory = new XJCCMInfoFactory(model);
		final MModelInfo<NType, NClass> mmodel = factory.createModel();

		final MClassInfo<NType, NClass> classInfo =
			mmodel.getClassInfo("org.jvnet.basicjaxb.tests.issues.IssueGH26Type");
		assertNotNull(classInfo);
		
		final MElementPropertyInfo<NType, NClass> a =
			(MElementPropertyInfo<NType, NClass>) classInfo.getProperty("a");
		
		assertEquals("a", a.getDefaultValue());
		assertNotNull(a.getDefaultValueNamespaceContext());
		
		final MElementsPropertyInfo<NType, NClass> bOrC =
			(MElementsPropertyInfo<NType, NClass>) classInfo.getProperty("bOrC");
//		assertEquals("b", bOrC.getElementTypeInfos().get(0).getDefaultValue());
		assertNotNull(bOrC.getElementTypeInfos().get(0).getDefaultValueNamespaceContext());
//		assertEquals("3", bOrC.getElementTypeInfos().get(1).getDefaultValue());
		assertNotNull(bOrC.getElementTypeInfos().get(1).getDefaultValueNamespaceContext());

		final MElementRefsPropertyInfo<NType, NClass> dOrE =
			(MElementRefsPropertyInfo<NType, NClass>) classInfo.getProperty("dOrE");
//		assertEquals("e", dOrE.getElementTypeInfos().get(0).getDefaultValue());
		assertNotNull(dOrE.getElementTypeInfos().get(0).getDefaultValueNamespaceContext());
//		assertEquals("d", dOrE.getElementTypeInfos().get(1).getDefaultValue());
		assertNotNull(dOrE.getElementTypeInfos().get(1).getDefaultValueNamespaceContext());

		final MAttributePropertyInfo<NType, NClass> z =
			(MAttributePropertyInfo<NType, NClass>) classInfo.getProperty("z");
		assertEquals("z", z.getDefaultValue());
		assertNotNull(z.getDefaultValueNamespaceContext());
	}
}
