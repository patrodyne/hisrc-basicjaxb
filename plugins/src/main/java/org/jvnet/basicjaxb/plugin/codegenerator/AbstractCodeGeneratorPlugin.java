package org.jvnet.basicjaxb.plugin.codegenerator;

import static org.jvnet.basicjaxb.plugin.util.OutlineUtils.filter;

import java.util.Arrays;
import java.util.Collection;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.Customizations;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.jvnet.basicjaxb.util.FieldAccessorFactory;
import org.jvnet.basicjaxb.util.PropertyFieldAccessorFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.Outline;

public abstract class AbstractCodeGeneratorPlugin<A extends Arguments<A>>
	extends AbstractParameterizablePlugin
{
	private FieldAccessorFactory fieldAccessorFactory = PropertyFieldAccessorFactory.INSTANCE;
	public FieldAccessorFactory getFieldAccessorFactory()
	{
		return fieldAccessorFactory;
	}
	public void setFieldAccessorFactory(FieldAccessorFactory fieldAccessorFactory)
	{
		this.fieldAccessorFactory = fieldAccessorFactory;
	}

	protected abstract QName getSpecialIgnoredElementName();
	public Ignoring getIgnoring() { return ignoring; }
	public void setIgnoring(Ignoring ignoring) { this.ignoring = ignoring; }

	private Ignoring ignoring = new CustomizedIgnoring
	(
		getSpecialIgnoredElementName(),
		Customizations.IGNORED_ELEMENT_NAME,
		Customizations.GENERATED_ELEMENT_NAME
	);

	@Override
	public Collection<QName> getCustomizationElementNames()
	{
		return Arrays.asList
		(
			getSpecialIgnoredElementName(),
			Customizations.IGNORED_ELEMENT_NAME,
			Customizations.GENERATED_ELEMENT_NAME
		);
	}

	private CodeGenerator<A> codeGenerator;
	protected CodeGenerator<A> getCodeGenerator()
	{
		if (codeGenerator == null)
			throw new IllegalStateException("Code generator was not set yet.");
		return codeGenerator;
	}
	public void setCodeGenerator(CodeGenerator<A> codeGenerator)
	{
		this.codeGenerator = codeGenerator;
	}
	
	/**
	 * <p>
	 * Run the plugin with and XJC {@link Outline}.
	 * </p>
	 * 
     * <p>
     * <b>Note:</b> This method is invoked only when a plugin is activated.
     * </p>
	 *
     * @param outline
     *      This object allows access to various generated code.
     * 
     * @return
     *      If the add-on executes successfully, return true.
     *      If it detects some errors but those are reported and
     *      recovered gracefully, return false.
     *
     * @throws Exception
     *      This 'run' method is a call-back method from {@link AbstractPlugin}
     *      and that method is responsible for handling all exceptions. It reports
     *      any exception to {@link ErrorHandler} and converts the exception to
     *      a {@link SAXException} for processing by {@link com.sun.tools.xjc.Plugin}.
	 */
	@Override
	public boolean run(Outline outline) throws Exception
	{
		setCodeGenerator(createCodeGenerator(outline.getCodeModel()));
		
		// Filter ignored class outlines
		for (final ClassOutline classOutline : filter(outline, getIgnoring()))
			processClassOutline(classOutline);
		
		return !hadError(outline.getErrorReceiver());
	}

	protected abstract CodeGenerator<A> createCodeGenerator(JCodeModel codeModel);
	protected abstract void generate(final ClassOutline classOutline, final JDefinedClass theClass);

	protected void processClassOutline(ClassOutline classOutline)
	{
		final JDefinedClass theClass = classOutline.implClass;
		generate(classOutline, theClass);
	}
}
