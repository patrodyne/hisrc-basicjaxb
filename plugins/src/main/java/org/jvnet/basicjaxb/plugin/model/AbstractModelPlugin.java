package org.jvnet.basicjaxb.plugin.model;

import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.elementwrapper.ElementWrapperPlugin;
import org.jvnet.basicjaxb.xjc.generator.MModelOutlineGenerator;
import org.jvnet.basicjaxb.xjc.generator.concrete.ModelOutlineGeneratorFactory;
import org.jvnet.basicjaxb.xjc.model.concrete.XJCCMInfoFactory;
import org.jvnet.basicjaxb.xjc.outline.MModelOutline;
import org.jvnet.basicjaxb.xml.bind.model.MModelInfo;
import org.xml.sax.ErrorHandler;

import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;
import com.sun.tools.xjc.outline.Outline;
import com.sun.tools.xjc.reader.Ring;

/**
 * An extension of {@link AbstractParameterizablePlugin} that is used by {@link ElementWrapperPlugin}.
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

	@Override
	public void postProcessModel(Model model, ErrorHandler errorHandler)
	{
		setModelInfo(getModel(model));
		postProcessModel(model, getModelInfo(), errorHandler);
	}

	protected void postProcessModel(Model model, MModelInfo<NType, NClass> modelInfo, ErrorHandler errorHandler)
	{
		// Template method to be overridden by classes
	}

	@Override
	protected boolean run(Outline outline, Options options)
		throws Exception
	{
		if (getModelInfo().getOrigin() instanceof ModelOutlineGeneratorFactory)
		{
			MModelOutlineGenerator generator =
				((ModelOutlineGeneratorFactory) getModelInfo().getOrigin()).createGenerator(outline);
			MModelOutline modelOutline = generator.generate(getModelInfo());
			Ring.add(MModelOutline.class, modelOutline);
		}
		else
			throw new AssertionError("Model is expected to be generateable");
		return true;
	}
}
