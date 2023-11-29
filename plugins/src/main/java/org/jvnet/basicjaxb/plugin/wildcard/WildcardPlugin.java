package org.jvnet.basicjaxb.plugin.wildcard;

import static com.sun.tools.xjc.outline.Aspect.IMPLEMENTATION;
import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.wildcard.Customizations.HANDLER_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.wildcard.Customizations.LAX_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.wildcard.Customizations.SKIP_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.wildcard.Customizations.STRICT_ELEMENT_NAME;
import static org.jvnet.basicjaxb.util.CustomizationUtils.containsCustomization;
import static org.jvnet.basicjaxb.util.CustomizationUtils.findCustomization;
import static org.jvnet.basicjaxb.util.CustomizationUtils.unmarshall;
import static org.jvnet.basicjaxb.util.FieldAccessorUtils.field;
import static org.jvnet.basicjaxb.util.LocatorUtils.toLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.xml.namespace.QName;

import org.glassfish.jaxb.core.v2.TODO;
import org.glassfish.jaxb.core.v2.model.core.WildcardMode;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.wildcard.model.HandlerClass;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JAnnotatable;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.generator.annotation.spec.XmlAnyElementWriter;
import com.sun.tools.xjc.generator.annotation.spec.XmlElementRefWriter;
import com.sun.tools.xjc.generator.annotation.spec.XmlElementRefsWriter;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CElement;
import com.sun.tools.xjc.model.CPluginCustomization;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.CReferencePropertyInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

import jakarta.xml.bind.annotation.DomHandler;
import jakarta.xml.bind.annotation.W3CDomHandler;
import jakarta.xml.bind.annotation.XmlMixed;

/**
 * This plugin specifies wildcard mode and/or {@link DomHandler}
 * for the wildcard property.
 * 
 * @author Alexey Valikov
 */
public class WildcardPlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "Xwildcard";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "specify the wildcard mode and/or DomHandler";

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
	public Collection<QName> getCustomizationElementNames()
	{
		return Arrays.asList
		(
			HANDLER_ELEMENT_NAME,
			LAX_ELEMENT_NAME,
			SKIP_ELEMENT_NAME,
			STRICT_ELEMENT_NAME
		);
	}

	// Plugin Processing: Model

	@Override
	protected void beforePostProcessModel(Model model)
	{
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  Verbose.: " + isVerbose());
			sb.append("\n  Debug...: " + isDebug());
			info(sb.toString());
		}
	}
	
	@Override
	protected void afterPostProcessModel(Model model, ErrorHandler errorHandler)
	{
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_FINISH);
			sb.append("\nResults");
			sb.append("\n  HadError.: " + hadError(errorHandler));
			info(sb.toString());
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
	 *		The object that represents the classes/properties to
	 *		be generated.
	 *
	 * @throws ClassNotFoundException When the DOM handler class cannot be found.
	 */
	@Override
	public void postProcessModel(Model model) throws ClassNotFoundException
	{
		HandlerClass handlerModel = toHandler(findCustomization(model, HANDLER_ELEMENT_NAME));
		boolean laxModel = containsCustomization(model, LAX_ELEMENT_NAME);
		boolean skipModel = containsCustomization(model, SKIP_ELEMENT_NAME);
		boolean strictModel = containsCustomization(model, STRICT_ELEMENT_NAME);
		
		for (CClassInfo classInfo : model.beans().values())
		{
			HandlerClass handlerClassInfo = toHandler(findCustomization(classInfo, HANDLER_ELEMENT_NAME));
			boolean laxClassInfo = containsCustomization(classInfo, LAX_ELEMENT_NAME);
			boolean skipClassInfo = containsCustomization(classInfo, SKIP_ELEMENT_NAME);
			boolean strictClassInfo = containsCustomization(classInfo, STRICT_ELEMENT_NAME);
			
			List<CPropertyInfo> propertInfoList = new ArrayList<>();
			for ( CPropertyInfo propertyInfo : classInfo.getProperties() )
			{
				if ( propertyInfo instanceof CReferencePropertyInfo )
				{
					final CReferencePropertyInfo referencePropertyInfo = (CReferencePropertyInfo) propertyInfo;
					CReferencePropertyInfoPlus referencePropertyInfoPlus = new CReferencePropertyInfoPlus(referencePropertyInfo);
					propertInfoList.add(referencePropertyInfoPlus);
				}
				else
					propertInfoList.add(propertyInfo);
			}
			classInfo.getProperties().clear();
			classInfo.getProperties().addAll(propertInfoList);

			for ( CPropertyInfo propertyInfo : classInfo.getProperties() )
			{
				if ( propertyInfo instanceof CReferencePropertyInfoPlus )
				{
					final CReferencePropertyInfoPlus referencePropertyInfoPlus = (CReferencePropertyInfoPlus) propertyInfo;

					HandlerClass handlerPropertyInfo = toHandler(findCustomization(referencePropertyInfoPlus, HANDLER_ELEMENT_NAME));
					boolean laxPropertyInfo = containsCustomization(referencePropertyInfoPlus, LAX_ELEMENT_NAME);
					boolean skipPropertyInfo = containsCustomization(referencePropertyInfoPlus, SKIP_ELEMENT_NAME);
					boolean strictPropertyInfo = containsCustomization(referencePropertyInfoPlus, STRICT_ELEMENT_NAME);
					
					// Model
					if ( handlerModel != null )
					{
						referencePropertyInfoPlus.setDOMHandler(handlerModel.getValue());
						if ( handlerModel.isLax() != null )
							laxModel = handlerModel.isLax();
					}
					if ( laxModel )
						referencePropertyInfoPlus.setWildcard(WildcardMode.LAX);
					else if ( skipModel )
						referencePropertyInfoPlus.setWildcard(WildcardMode.SKIP);
					else if ( strictModel )
						referencePropertyInfoPlus.setWildcard(WildcardMode.STRICT);

					// ClassInfo
					if ( handlerClassInfo != null )
					{
						referencePropertyInfoPlus.setDOMHandler(handlerClassInfo.getValue());
						if ( handlerClassInfo.isLax() != null )
							laxClassInfo = handlerClassInfo.isLax();
					}
					if ( laxClassInfo )
						referencePropertyInfoPlus.setWildcard(WildcardMode.LAX);
					else if ( skipClassInfo )
						referencePropertyInfoPlus.setWildcard(WildcardMode.SKIP);
					else if ( strictClassInfo )
						referencePropertyInfoPlus.setWildcard(WildcardMode.STRICT);

					// PropertyInfo
					if ( handlerPropertyInfo != null )
					{
						referencePropertyInfoPlus.setDOMHandler(handlerPropertyInfo.getValue());
						if ( handlerPropertyInfo.isLax() != null )
							laxPropertyInfo = handlerPropertyInfo.isLax();
					}
					if ( laxPropertyInfo )
						referencePropertyInfoPlus.setWildcard(WildcardMode.LAX);
					else if ( skipPropertyInfo )
						referencePropertyInfoPlus.setWildcard(WildcardMode.SKIP);
					else if ( strictPropertyInfo )
						referencePropertyInfoPlus.setWildcard(WildcardMode.STRICT);
					
					trace("{}, postProcessModel; Class={}, Property={}, Wildcard={}",
						toLocation(propertyInfo.getLocator()), classInfo.shortName,
						propertyInfo.getName(false), referencePropertyInfoPlus.getWildcard());
				}
				else if ( propertyInfo instanceof CReferencePropertyInfo )
				{
					final CReferencePropertyInfo referencePropertyInfo = (CReferencePropertyInfo) propertyInfo;
					
					HandlerClass handlerPropertyInfo = toHandler(findCustomization(referencePropertyInfo, HANDLER_ELEMENT_NAME));
					boolean laxPropertyInfo = containsCustomization(referencePropertyInfo, LAX_ELEMENT_NAME);
					boolean skipPropertyInfo = containsCustomization(referencePropertyInfo, SKIP_ELEMENT_NAME);
					boolean strictPropertyInfo = containsCustomization(referencePropertyInfo, STRICT_ELEMENT_NAME);

					// Model
					if ( handlerModel != null )
					{
						if ( handlerModel.isLax() != null )
							laxModel = handlerModel.isLax();
					}
					if ( laxModel )
						referencePropertyInfo.setWildcard(WildcardMode.LAX);
					else if ( skipModel )
						referencePropertyInfo.setWildcard(WildcardMode.SKIP);
					else if ( strictModel )
						referencePropertyInfo.setWildcard(WildcardMode.STRICT);

					// ClassInfo
					if ( handlerClassInfo != null )
					{
						if ( handlerClassInfo.isLax() != null )
							laxClassInfo = handlerClassInfo.isLax();
					}
					if ( laxClassInfo )
						referencePropertyInfo.setWildcard(WildcardMode.LAX);
					else if ( skipClassInfo )
						referencePropertyInfo.setWildcard(WildcardMode.SKIP);
					else if ( strictClassInfo )
						referencePropertyInfo.setWildcard(WildcardMode.STRICT);

					// PropertyInfo
					if ( handlerPropertyInfo != null )
					{
						if ( handlerPropertyInfo.isLax() != null )
							laxPropertyInfo = handlerPropertyInfo.isLax();
					}
					if ( laxPropertyInfo )
						referencePropertyInfo.setWildcard(WildcardMode.LAX);
					else if ( skipPropertyInfo )
						referencePropertyInfo.setWildcard(WildcardMode.SKIP);
					else if ( strictPropertyInfo )
						referencePropertyInfo.setWildcard(WildcardMode.STRICT);
					
					trace("{}, postProcessModel; Class={}, Property={}, Wildcard={}",
						toLocation(propertyInfo.getLocator()), classInfo.shortName,
						propertyInfo.getName(false), referencePropertyInfo.getWildcard());
				}
			}
			
			debug("{}, postProcessModel; Class={}", toLocation(classInfo.getLocator()), classInfo.shortName);
		}
	}

	private HandlerClass toHandler(CPluginCustomization handlerCustomization)
	{
		HandlerClass handlerClass = null;
		if ( handlerCustomization != null )
			handlerClass = (HandlerClass) unmarshall(Customizations.getContext(), handlerCustomization);
		return handlerClass;
	}
	
	// Plugin Processing: Outline
	
	@Override
	protected void beforeRun(Outline outline) throws Exception
	{
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
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
			sb.append(LOGGING_FINISH);
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
	 * <p>
	 * <b>Note:</b> This method is invoked only when a plugin is activated.
	 * </p>
	 *
	 * @param outline
	 *		This object allows access to various generated code.
	 * 
	 * @return
	 *		If the add-on executes successfully, return true.
	 *		If it detects some errors but those are reported and
	 *		recovered gracefully, return false.
	 *
	 * @throws Exception
	 *		This 'run' method is a call-back method from {@link AbstractPlugin}
	 *		and that method is responsible for handling all exceptions. It reports
	 *		any exception to {@link ErrorHandler} and converts the exception to
	 *		a {@link SAXException} for processing by {@link com.sun.tools.xjc.Plugin}.
	 */
	@Override
	public boolean run(Outline outline) throws Exception
	{
		for (ClassOutline classOutline : outline.getClasses())
			processClassOutline(outline, classOutline);
		
		return !hadError(outline.getErrorReceiver());
	}

	private void processClassOutline(Outline outline, ClassOutline classOutline) throws ClassNotFoundException
	{
		final JDefinedClass theClass = classOutline.implClass;
//		FieldOutline[] declaredFilteredFields = filter(classOutline.getDeclaredFields(), getIgnoring());
		FieldOutline[] declaredFilteredFields = classOutline.getDeclaredFields();
		for (FieldOutline fieldOutline : declaredFilteredFields)
		{
			CPropertyInfo fieldInfo = fieldOutline.getPropertyInfo();
			if ( fieldInfo instanceof CReferencePropertyInfoPlus )
			{
				CReferencePropertyInfoPlus refPropInfoPlus = (CReferencePropertyInfoPlus) fieldInfo;
				NClass domHandler = refPropInfoPlus.getDOMHandler();
				annotateReference(classOutline, field(fieldOutline), refPropInfoPlus);
				trace("{}, processClassOutline; Class={}; Field={}; Handler={}", toLocation(theClass.metadata), theClass.name(),
					fieldInfo.getName(false), (domHandler != null) ? domHandler.fullName() : "");
			}
		}
		debug("{}, processClassOutline; Class={}", toLocation(theClass.metadata), theClass.name());
	}
	
	private void annotateReference(ClassOutline classOutline, JAnnotatable field, CReferencePropertyInfoPlus refPropInfoPlus)
	{
		TODO.prototype();

		Collection<CElement> elements = refPropInfoPlus.getElements();

		XmlElementRefWriter refw;
		if ( elements.size() == 1 )
		{
			refw = field.annotate2(XmlElementRefWriter.class);
			CElement element = elements.iterator().next();
			refw.name(element.getElementName().getLocalPart())
				.namespace(element.getElementName().getNamespaceURI())
				.type(element.getType().toType(classOutline.parent(), IMPLEMENTATION));
				refw.required(refPropInfoPlus.isRequired());
		}
		else if ( elements.size() > 1 )
		{
			XmlElementRefsWriter refsw = field.annotate2(XmlElementRefsWriter.class);
			for( CElement element : elements )
			{
				refw = refsw.value();
				refw.name(element.getElementName().getLocalPart())
					.namespace(element.getElementName().getNamespaceURI())
					.type(element.getType().toType(classOutline.parent(), IMPLEMENTATION));
					refw.required(refPropInfoPlus.isRequired());
			}
		}

		if ( refPropInfoPlus.isMixed() )
			field.annotate(XmlMixed.class);

		NClass dh = refPropInfoPlus.getDOMHandler();
		if ( dh != null )
		{
			XmlAnyElementWriter xaew = field.annotate2(XmlAnyElementWriter.class);
			xaew.lax(refPropInfoPlus.getWildcard().allowTypedObject);

			final JCodeModel codeModel = classOutline.parent().getCodeModel();
			final JClass value = dh.toType(classOutline.parent(), IMPLEMENTATION);
			if( !value.equals(codeModel.ref(W3CDomHandler.class)) )
				xaew.value(value);
		}
	}
}
