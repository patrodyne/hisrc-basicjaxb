package org.jvnet.basicjaxb.plugin.elementwrapper;

import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.xjc.generator.MModelOutlineGenerator;
import org.jvnet.basicjaxb.xjc.generator.concrete.ModelOutlineGeneratorFactory;
import org.jvnet.basicjaxb.xjc.model.concrete.XJCCMInfoFactory;
import org.jvnet.basicjaxb.xjc.outline.MModelOutline;
import org.jvnet.basicjaxb.xml.bind.model.MModelInfo;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;
import com.sun.tools.xjc.outline.Outline;
import com.sun.tools.xjc.reader.Ring;

/**
 * An extension of {@link AbstractParameterizablePlugin} that is used by 
 * {@link ElementWrapperPlugin}.
 */
public abstract class AbstractModelPlugin extends AbstractParameterizablePlugin
{
	// Represents a MModelInfo<NType, NClass> interface.
	private MModelInfo<NType, NClass> modelInfo;
	protected MModelInfo<NType, NClass> getModelInfo() { return modelInfo; }
	protected void setModelInfo(MModelInfo<NType, NClass> modelInfo) { this.modelInfo = modelInfo; }

	/**
	 * Get an {@link MModelInfo} from a {@link Ring} or create from the given {@link Model} instance.
	 * 
	 * @param model A schema language neutral representation of the result of a schema parsing.
	 * 
	 * @return An {@link MModelInfo} interface for the given {@link Model} instance.
	 */
	protected MModelInfo<NType, NClass> getModel(Model model)
	{
		try
		{
			@SuppressWarnings("unchecked")
			final MModelInfo<NType, NClass> modelInfo = (MModelInfo<NType, NClass>) Ring.get(MModelInfo.class);
			return modelInfo;
		}
		catch (Throwable t)
		{
			final MModelInfo<NType, NClass> mmodel = new XJCCMInfoFactory(model).createModel();
			Ring.add(MModelInfo.class, mmodel);
			return mmodel;
		}
	}

	protected MModelOutline getModelOutline(MModelInfo<NType, NClass> modelInfo, Outline outline, Options options)
	{
		try
		{
			final MModelOutline modelOutline = (MModelOutline) Ring.get(MModelOutline.class);
			return modelOutline;
		}
		catch (Throwable t)
		{
			if (modelInfo.getOrigin() instanceof ModelOutlineGeneratorFactory)
			{
				MModelOutlineGenerator generator =
					((ModelOutlineGeneratorFactory) modelInfo.getOrigin()).createGenerator(outline);
				MModelOutline modelOutline = generator.generate(modelInfo);
				Ring.add(MModelOutline.class, modelOutline);
				return modelOutline;
			}
			else
			{
				throw new AssertionError("Model is expected to be generateable");
			}
		}
	}

    /**
     * Performs the post-processing of the {@link Model}.
     *
     * <p>
     * This method is invoked after XJC has internally finished
     * the model construction. This is a chance for a plugin to
     * affect the way code generation is performed.
     * </p>
     *
     * <p>
     * Compared to the {@link #run(Outline, Options, ErrorHandler)}
     * method, this method allows a plugin to work at the higher level
     * conceptually closer to the abstract JAXB model, as opposed to
     * Java syntax level.
     * </p>
     *
     * <p>
     * This 'postProcessModel' method is a call-back method from
     * {@link AbstractPlugin} and that method is responsible for handling
     * all exceptions. It reports any exception to {@link ErrorHandler}
     * for processing by {@link com.sun.tools.xjc.Plugin}.
     * </p>
     *
     * <p>
     * <b>Note:</b> This method is invoked only when a plugin is activated.
     * </p>
     *
     * @param model
     *      The object that represents the classes/properties to
     *      be generated.
     */
	@Override
	public void postProcessModel(Model model)
	{
		setModelInfo(getModel(model));
		postProcessModel(model, getModelInfo());
	}

	protected void postProcessModel(Model model, MModelInfo<NType, NClass> modelInfo)
	{
		// Template method to be overridden by classes
	}

	// Plugin Processing
	
	@Override
	protected void beforeRun(Outline outline) throws Exception
	{
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START + " Run");
			sb.append("\nParameters");
			sb.append("\n  Verbose.: " + isVerbose());
			sb.append("\n  Debug...: " + isDebug());
			info(sb.toString());
		}
	}
	
	@Override
	protected void afterRun(Outline outline) throws Exception
	{
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_FINISH + " Run");
			sb.append("\nResults");
			sb.append("\n  HadError.: " + hadError(outline.getErrorReceiver()));
			info(sb.toString());
		}
	}
	
	/**
	 * <p>
	 * Run the plugin with and XJC {@link Outline}.
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
	protected boolean run(Outline outline) throws Exception
	{
		if (getModelInfo().getOrigin() instanceof ModelOutlineGeneratorFactory)
		{
			MModelOutlineGenerator generator =
				((ModelOutlineGeneratorFactory) getModelInfo().getOrigin()).createGenerator(outline);
			MModelOutline modelOutline = generator.generate(getModelInfo());
			
			// FIXME: NPE "com.sun.tools.xjc.reader.Ring.get()" is null
			Ring.add(MModelOutline.class, modelOutline);
		}
		else
			throw new AssertionError("Model is expected to be generateable");
		return !hadError(outline.getErrorReceiver());
	}
}
