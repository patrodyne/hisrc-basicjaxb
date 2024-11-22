package org.jvnet.basicjaxb.plugin.simplify;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.simplify.Customizations.AS_ELEMENT_PROPERTY_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.simplify.Customizations.AS_REFERENCE_PROPERTY_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.simplify.Customizations.IGNORED_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.simplify.Customizations.PROPERTY_ELEMENT_NAME;
import static org.jvnet.basicjaxb.util.LocatorUtils.toLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.xml.namespace.QName;

import org.glassfish.jaxb.core.v2.model.core.ID;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.Customizations;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.jvnet.basicjaxb.util.CustomizationUtils;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

import com.sun.codemodel.JJavaName;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.CAdapter;
import com.sun.tools.xjc.model.CAttributePropertyInfo;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CClassRef;
import com.sun.tools.xjc.model.CElement;
import com.sun.tools.xjc.model.CElementInfo;
import com.sun.tools.xjc.model.CElementPropertyInfo;
import com.sun.tools.xjc.model.CElementPropertyInfo.CollectionMode;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.CPropertyVisitor;
import com.sun.tools.xjc.model.CReferencePropertyInfo;
import com.sun.tools.xjc.model.CTypeRef;
import com.sun.tools.xjc.model.CValuePropertyInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.Outline;

/**
 * <p>
 * For XML Schema <b><code>choice</code></b> when <code>maxOccurs="unbounded"</code>, this plugin simplifies <em>complex</em> properties
 * (<code>fooOrBarOrBaz</code>) into several <em>simple</em> properties: <code>foo</code>,
 * <code>bar</code>, <code>baz</code>. For example, <code>fooOrBarOrBaz</code> generated from
 * <em>repeatable</em> (i.e. {@code maxOccurs > 1}) choices.
 * </p>
 *
 * <p>
 * Optionally, add <code>-Xsimplify-usePluralForm=true</code> if you want collection property
 * names to be pluralized: <code>foo</code> becomes <code>foos</code>).
 * </p>
 *
 * <p>
 * The Simplify Plugin allows you to simplify your complex properties. The plugin
 * will remove the complex property and insert several simpler properties instead
 * of the original (complex) property.
 * </p>
 * 
 * <p>
 * By default, XJC will complex properties modeling several references or
 * elements in one. 
 * </p>
 * 
 * <p>
 * These complex properties are required to model complex content of the XML
 * schema adequately, i.e. to maintain the order of the elements in the repeatable
 * choice. 
 * </p>
 * 
 * <p>
 * However, if the order of the elements is not significant - that is, you can
 * live with the fact that it will change after re-marshalling, the structures of
 * these properties can be simplified: complex properties can be split into
 * several simple properties.
 * </p>
 * 
 * <p>
 * The JAXB Simplify Plugin works with two kinds of properties: <b>elements</b>
 * and <b>references</b>.
 * </p>
 * 
 * <ul>
 * <li>Use {@code simplify:as-element-property} or {@code simplify:as-reference-property} customization elements to specify, which properties you want to simplify. You can configure this elements directly in schema or in the bindings file.</li>
 * <li>Optionally, if you have trouble placing these customizations on the property, you may place them inside {@code simplify:property} customization with {@code @name='myProperty'} attribute on the class.</li>
 * </ul>
 * 
 * 
 * <p><b>Type with Elements</b></p>
 * 
 * <pre>
 * &lt;jaxb:bindings node="xs:complexType[@name='typeWithElementsProperty']/xs:choice"&gt;
 *   &lt;simplify:as-element-property/&gt;
 * &lt;/jaxb:bindings&gt;
 * 
 * OR
 * 
 * &lt;jaxb:bindings node="xs:complexType[@name='typeWithElementsProperty']"&gt;
 *   &lt;simplify:property name="fooOrBar"&gt;
 *     &lt;simplify:as-element-property/&gt;
 *   &lt;/simplify:property&gt;
 * &lt;/jaxb:bindings&gt;
 * </pre>
 * 
 * <p><b>Type with References</b></p>
 * 
 * <pre>
 * &lt;jaxb:bindings node="xs:complexType [@name='typeWithReferencesProperty']/xs:choice/xs:element[@name='foo']"&gt;
 *   &lt;simplify:as-element-property/&gt;
 * &lt;/jaxb:bindings&gt;
 * 
 * OR
 * 
 * &lt;jaxb:bindings node="xs:complexType [@name='typeWithReferencesProperty']"&gt;
 *   &lt;simplify:property name="fooOrBar"&gt;
 *     &lt;simplify:as-element-property/&gt;
 *   &lt;/simplify:property&gt;
 * &lt;/jaxb:bindings&gt;
 * </pre>
 * 
 * <p>
 * You can use the <code>simplify:as-element-property</code> element to <em>remodel</em> a
 * complex property as element properties <b><code>@XmlElement</code></b> or 
 * <code>simplify:as-reference-property</code> as reference properties <b><code>@XmlElementRef</code></b>.
 * </p>
 * 
 * <b>Notes:</b>
 * 
 * <ul>
 * <li>Element properties are simpler to work with than reference properties,
 *     but you may need to retain reference properties if you have substitution groups.</li>
 * <li>In the case of a reference property, you have to customize one of the
 *     <code>xs:elements</code> and not the <code>xs:choice</code>.</li>
 * <li>
 *     A non-repeating choice model group is bound to a simple property. The SIMPLE
 *     choice content property is derived from a choice model group per specification.
 *     Setting 'choiceContentProperty' to "true" causes the elements to be mapped into
 *     a single property (the {@code List<Object>}). Setting it to "false" causes the elements
 *     to be wrapped into separate properties.</li>
 * </ul>
 * 
 */
public class SimplifyPlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "Xsimplify";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "simplifies unbounded 'choice' properties like fooOrBarOrBaz";

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

	private Ignoring ignoring = new CustomizedIgnoring(IGNORED_ELEMENT_NAME, Customizations.IGNORED_ELEMENT_NAME, Customizations.GENERATED_ELEMENT_NAME);
	public Ignoring getIgnoring() { return ignoring; }
	public void setIgnoring(Ignoring ignoring) { this.ignoring = ignoring; }

	@Override
	public Collection<QName> getCustomizationElementNames()
	{
		return Arrays.asList
		(
			PROPERTY_ELEMENT_NAME,
			AS_ELEMENT_PROPERTY_ELEMENT_NAME,
			AS_REFERENCE_PROPERTY_ELEMENT_NAME,
			IGNORED_ELEMENT_NAME,
			Customizations.IGNORED_ELEMENT_NAME,
			Customizations.GENERATED_ELEMENT_NAME);
	}
	
	private boolean usePluralForm = false;
	public boolean isUsePluralForm() { return usePluralForm; }
	public void setUsePluralForm(boolean usePluralForm) { this.usePluralForm = usePluralForm; }

	// Plugin Processing

	@Override
	protected void beforePostProcessModel(Model model)
	{
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  UsePluralForm.: " + isUsePluralForm());
			sb.append("\n  Verbose.......: " + isVerbose());
			sb.append("\n  Debug.........: " + isDebug());
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
     *      The object that represents the classes/properties to
     *      be generated.
     */
	@Override
	public void postProcessModel(final Model model)
	{
		for (final CClassInfo classInfo : model.beans().values())
			postProcessClassInfo(model, classInfo);
	}

	private void postProcessClassInfo(final Model model, final CClassInfo classInfo)
	{
		final List<CPropertyInfo> properties = new ArrayList<CPropertyInfo>(classInfo.getProperties());
		for (CPropertyInfo property : properties)
		{
			property.accept(new CPropertyVisitor<Void>()
			{
				@Override
				public Void onElement(CElementPropertyInfo elementProperty)
				{
					postProcessElementPropertyInfo(model, classInfo, elementProperty);
					return null;
				}

				@Override
				public Void onReference(CReferencePropertyInfo p)
				{
					postProcessReferencePropertyInfo(model, classInfo, p);
					return null;
				}

				@Override
				public Void onAttribute(CAttributePropertyInfo attributeProperty)
				{
					// No action
					return null;
				}

				@Override
				public Void onValue(CValuePropertyInfo valueProperty)
				{
					// No action
					return null;
				}
			});
		}
		debug("{}, postProcessClassInfo; Class={}", toLocation(classInfo.getLocator()), classInfo.shortName);
	}

	private void postProcessElementPropertyInfo(final Model model, final CClassInfo classInfo,
		CElementPropertyInfo property)
	{
		if (CustomizationUtils.containsPropertyCustomizationInPropertyOrClass(property, PROPERTY_ELEMENT_NAME, AS_ELEMENT_PROPERTY_ELEMENT_NAME))
			simplifyElementPropertyInfoAsElementPropertyInfo(model, classInfo, property);
	}

	private void postProcessReferencePropertyInfo(final Model model, final CClassInfo classInfo,
		CReferencePropertyInfo property)
	{
		if (CustomizationUtils.containsPropertyCustomizationInPropertyOrClass(property, PROPERTY_ELEMENT_NAME, AS_ELEMENT_PROPERTY_ELEMENT_NAME))
			simplifyReferencePropertyInfoAsElementPropertyInfo(model, classInfo, property);
		else if (CustomizationUtils.containsPropertyCustomizationInPropertyOrClass(property, PROPERTY_ELEMENT_NAME, AS_REFERENCE_PROPERTY_ELEMENT_NAME))
			simplifyReferencePropertyInfoAsReferencePropertyInfo(model, classInfo, property);
	}

	private void simplifyElementPropertyInfoAsElementPropertyInfo(final Model model, final CClassInfo classInfo, CElementPropertyInfo property)
	{
		if (property.getTypes().size() > 1)
		{
			int index = classInfo.getProperties().indexOf(property);
			for (CTypeRef typeRef : property.getTypes())
			{
				final CElementPropertyInfo elementPropertyInfo = createElementPropertyInfo(model, property, typeRef);
				classInfo.getProperties().add(index++, elementPropertyInfo);
			}
			classInfo.getProperties().remove(property);
			trace("{}, simplifyElementPropertyInfoAsElementPropertyInfo; Class={}, Property={}",
				toLocation(property.getLocator()), classInfo.shortName, property.getName(false));
		}
		else
		{
			warn("{}, simplifyElementPropertyInfoAsElementPropertyInfo; Class={}, Element property [{}] will not be simplified as it does not contain multiple types.",
				toLocation(property.getLocator()), classInfo.shortName, property.getName(false));
		}
	}

	private void simplifyReferencePropertyInfoAsReferencePropertyInfo(final Model model, final CClassInfo classInfo,
		CReferencePropertyInfo property)
	{
		if (property.getElements().size() <= 1 && !property.isMixed())
		{
			warn("{}, simplifyReferencePropertyInfoAsReferencePropertyInfo; Class={}, Element reference property [{}] will not be simplified as it does not contain multiple elements and is not mixed.",
				toLocation(property.getLocator()), classInfo.shortName, property.getName(false));
		}
		else
		{
			int index = classInfo.getProperties().indexOf(property);
			for (CElement element : property.getElements())
			{
				final CReferencePropertyInfo referencePropertyInfo = createReferencePropertyInfo(model, property, element);
				classInfo.getProperties().add(index++, referencePropertyInfo);
			}
			
			if (property.isMixed())
				classInfo.getProperties().add(index++, createContentReferencePropertyInfo(model, property));
			classInfo.getProperties().remove(property);

			trace("{}, simplifyReferencePropertyInfoAsReferencePropertyInfo; Class={}, Reference={}",
				toLocation(property.getLocator()), classInfo.shortName, property.getName(false));
		}
	}

	private void simplifyReferencePropertyInfoAsElementPropertyInfo(final Model model, final CClassInfo classInfo,
		CReferencePropertyInfo property)
	{
		if (property.getElements().size() <= 1 && !property.isMixed())
		{
			warn("{}, simplifyReferencePropertyInfoAsElementPropertyInfo; Class={}, Element reference property [{}] will not be simplified as it does not contain multiple elements and is not mixed.",
				toLocation(property.getLocator()), classInfo.shortName, property.getName(false));
		}
		else
		{
			boolean erpSimplified = false;
			int index = classInfo.getProperties().indexOf(property);
			for (CElement element : property.getElements())
			{
				final CElementPropertyInfo elementPropertyInfo;
				if (element instanceof CElementInfo)
					elementPropertyInfo = createElementPropertyInfo(model, property, element, (CElementInfo) element);
				else if (element instanceof CClassInfo)
					elementPropertyInfo = createElementPropertyInfo(model, property, element, (CClassInfo) element);
				else if (element instanceof CClassRef)
				{
					String msg = format("Element reference property [%s] contains a class reference type [%s] and therefore cannot be fully simplified as element property",
						property.getName(false), ((CClassRef) element).fullName());
					error("{}, simplifyReferencePropertyInfoAsElementPropertyInfo; Class={}, {}.",
						toLocation(property.getLocator()), classInfo.shortName, msg);
					handleException(getErrorHandler(), new SAXParseException(msg, property.getLocator()));
					elementPropertyInfo = null;
				}
				else
				{
					String msg = format("Unsupported CElement type [%s]", element);
					error("{}, simplifyReferencePropertyInfoAsElementPropertyInfo; Class={}, {}.",
						toLocation(property.getLocator()), classInfo.shortName, msg);
					handleException(getErrorHandler(), new SAXParseException(msg, property.getLocator()));
					elementPropertyInfo = null;
				}
				
				if (elementPropertyInfo != null)
				{
					classInfo.getProperties().add(index++, elementPropertyInfo);
					erpSimplified = true;
				}
			}
			
			if (property.isMixed())
				classInfo.getProperties().add(index++, createContentReferencePropertyInfo(model, property));
			
			classInfo.getProperties().remove(property);
			
			if ( erpSimplified )
			{
				trace("{}, simplifyReferencePropertyInfoAsElementPropertyInfo; Class={}, Reference={}",
					toLocation(property.getLocator()), classInfo.shortName, property.getName(false));
			}
		}
	}

	private CElementPropertyInfo createElementPropertyInfo(final Model model, CReferencePropertyInfo property,
		CElement element, final CElementInfo elementInfo)
	{
		final CElementPropertyInfo elementPropertyInfo;
		final String propertyName = createPropertyName(model, property, element);
		final CElementPropertyInfo originalPropertyInfo = elementInfo.getProperty();
		elementPropertyInfo = new CElementPropertyInfo(propertyName,
			property.isCollection() ? CollectionMode.REPEATED_ELEMENT : CollectionMode.NOT_REPEATED, ID.NONE, null,
			element.getSchemaComponent(), element.getCustomizations(), element.getLocator(), false);
		
		final CAdapter adapter = originalPropertyInfo.getAdapter();
		if (adapter != null)
			elementPropertyInfo.setAdapter(adapter);
		
		elementPropertyInfo.getTypes().add(new CTypeRef(elementInfo.getContentType(), element.getElementName(),
			elementInfo.getContentType().getTypeName(), false, null));
		return elementPropertyInfo;
	}

	private CElementPropertyInfo createElementPropertyInfo(final Model model, CReferencePropertyInfo property,
		CElement element, final CClassInfo classInfo)
	{
		final CElementPropertyInfo elementPropertyInfo;
		final String propertyName = createPropertyName(model, property, element);
		elementPropertyInfo = new CElementPropertyInfo
		(
			propertyName,
			property.isCollection() ? CollectionMode.REPEATED_ELEMENT : CollectionMode.NOT_REPEATED,
			ID.NONE,
			/* expectedMimeType */ null,
			element.getSchemaComponent(),
			element.getCustomizations(),
			element.getLocator(),
			/* required */ false
		);
		elementPropertyInfo.getTypes()
			.add(new CTypeRef(classInfo, element.getElementName(), classInfo.getTypeName(), false, null));
		return elementPropertyInfo;
	}

//	private CElementPropertyInfo createElementPropertyInfo(final Model model, CReferencePropertyInfo property,
//		CElement element, final CClassRef classInfo)
//	{
//		final CElementPropertyInfo elementPropertyInfo;
//		final String propertyName = createPropertyName(model, element);
//		elementPropertyInfo = new CElementPropertyInfo
//		(
//			propertyName,
//			property.isCollection() ? CollectionMode.REPEATED_ELEMENT : CollectionMode.NOT_REPEATED,
//			ID.NONE,
//			/* expectedMimeType */ null,
//			element.getSchemaComponent(),
//			element.getCustomizations(),
//			element.getLocator(),
//			/* required */ false
//		);
//		elementPropertyInfo.getTypes()
//			.add(new CTypeRef(classInfo, element.getElementName(), classInfo.getTypeName(), false, null));
//		return elementPropertyInfo;
//	}
	
	private CReferencePropertyInfo createReferencePropertyInfo(final Model model, CReferencePropertyInfo property,
		CElement element)
	{
		final String propertyName = createPropertyName(model, property, element);
		final CReferencePropertyInfo referencePropertyInfo = new CReferencePropertyInfo
		(
			propertyName,
			property.isCollection(),
			/* required */ false,
			/* mixed */ false,
			element.getSchemaComponent(),
			element.getCustomizations(),
			element.getLocator(),
			property.isDummy(),
			property.isContent(),
			property.isMixedExtendedCust()
		);
		referencePropertyInfo.getElements().add(element);
		return referencePropertyInfo;
	}

	private CReferencePropertyInfo createContentReferencePropertyInfo(final Model model,
		CReferencePropertyInfo property)
	{
		final String propertyName = "Mixed" + property.getName(true);
		final CReferencePropertyInfo referencePropertyInfo = new CReferencePropertyInfo
		(
			propertyName,
			/* collection */ true,
			/* required */ false,
			/* mixed */ true, 
			property.getSchemaComponent(), 
			property.getCustomizations(), 
			property.getLocator(),
			/* dummy */ false,
			/* content */ true,
			property.isMixedExtendedCust()
		);
		return referencePropertyInfo;
	}

	private CElementPropertyInfo createElementPropertyInfo(final Model model, CElementPropertyInfo property,
		CTypeRef typeRef)
	{
		final String propertyName = createPropertyName(model, property, typeRef);
		boolean required = false;
		final CElementPropertyInfo elementPropertyInfo = new CElementPropertyInfo
		(
			propertyName,
			property.isCollection() ? CollectionMode.REPEATED_ELEMENT : CollectionMode.NOT_REPEATED,
			typeRef.getTarget().idUse(),
			typeRef.getTarget().getExpectedMimeType(),
			property.getSchemaComponent(),
			property.getCustomizations(),
			property.getLocator(),
			required
		);
		final CAdapter adapter = property.getAdapter();
		
		if (adapter != null)
			elementPropertyInfo.setAdapter(adapter);
		
		elementPropertyInfo.getTypes().add(typeRef);
		return elementPropertyInfo;
	}

	private String createPropertyName(final Model model, CPropertyInfo propertyInfo, CElement element)
	{
		final String localPart;
		if (element instanceof CClassRef)
		{
			final CClassRef classRef = (CClassRef) element;
			final String fullName = classRef.fullName();
			localPart = fullName.substring(fullName.lastIndexOf('.') + 1);
		}
		else
			localPart = element.getElementName().getLocalPart();
		
		final String propertyName = model.getNameConverter()
			.toPropertyName(pluralizeIfNecessary(propertyInfo, localPart));
		
		return propertyName;
	}

	private String createPropertyName(final Model model, CPropertyInfo propertyInfo, CTypeRef element)
	{
		final String propertyName = model.getNameConverter()
			.toPropertyName(pluralizeIfNecessary(propertyInfo, element.getTagName().getLocalPart()));
		return propertyName;
	}

	private String pluralizeIfNecessary(CPropertyInfo propertyInfo, final String propertyName)
	{
		return (propertyInfo.isCollection() && isUsePluralForm())
			? JJavaName.getPluralForm(propertyName) : propertyName;
	}
}
