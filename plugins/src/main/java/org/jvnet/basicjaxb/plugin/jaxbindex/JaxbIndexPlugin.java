package org.jvnet.basicjaxb.plugin.jaxbindex;

import static org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin.USAGE_FORMAT;
import static java.lang.String.format;

import org.jvnet.basicjaxb.util.CodeModelUtils;
import org.xml.sax.ErrorHandler;

import com.sun.codemodel.fmt.JTextFile;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.tools.xjc.outline.PackageOutline;

import jakarta.xml.bind.JAXBContext;

/**
 * <p>
 * Programmer annotated Jakarta XML Binding mapped classes can be listed in a
 * <code>jaxb.index</code> resource file. The <code>jaxb.index</code> file is a
 * listing of the classes in the containing package that have JAXB annotations.
 * Each line in the file is a class's simple (non-fully-qualified) name.
 * </p>
 * 
 * <p>
 * Every package listed on the context path must meet one or both of the
 * following conditions; otherwise, a {@link jakarta.xml.bind.JAXBException}
 * will be thrown:
 * </p>
 * 
 * <ul>
 * <li>it must contain ObjectFactory.class</li>
 * <li>it must contain jaxb.index</li>
 * </ul>
 * 
 * <p>
 * The JavaDoc for {@link JAXBContext#newInstance(String, ClassLoader)} provides
 * more detail.
 * </p>
 */
public class JaxbIndexPlugin extends com.sun.tools.xjc.Plugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "Xjaxbindex";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "generate per-package 'jaxb.index' file";

	@Override
	public String getOptionName()
	{
		return OPTION_NAME;
	}

	@Override
	public String getUsage()
	{
		return format(USAGE_FORMAT, OPTION_NAME, OPTION_DESC);
	}

	@Override
	public boolean run(Outline outline, Options opt, ErrorHandler errorHandler)
	{
		for (final PackageOutline packageOutline : outline.getAllPackageContexts())
		{
			final StringBuilder sb = new StringBuilder();
			
			for (final ClassOutline classOutline : packageOutline.getClasses())
			{
				sb.append(CodeModelUtils.getLocalClassName(classOutline.ref));
				sb.append("\n");
			}
			
			final JTextFile indexFile = new JTextFile("jaxb.index");
			indexFile.setContents(sb.toString());
			packageOutline._package().addResourceFile(indexFile);
		}
		return true;
	}
}
