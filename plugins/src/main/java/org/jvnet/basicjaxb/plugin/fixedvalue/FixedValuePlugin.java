package org.jvnet.basicjaxb.plugin.fixedvalue;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.fixedvalue.Customizations.IGNORED_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.util.OutlineUtils.filter;
import static org.jvnet.basicjaxb.util.LocatorUtils.toLocation;
import static org.jvnet.basicjaxb.xmlschema.XmlSchemaConstants.ANYSIMPLETYPE;
import static org.jvnet.basicjaxb.xmlschema.XmlSchemaConstants.BASE64BINARY;
import static org.jvnet.basicjaxb.xmlschema.XmlSchemaConstants.HEXBINARY;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.glassfish.jaxb.core.api.impl.NameConverter;
import org.jvnet.basicjaxb.dom.DOMUtils;
import org.jvnet.basicjaxb.lang.ValueUtils;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.Customizations;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.jvnet.basicjaxb.xml.namespace.util.QNameUtils;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.ClassType;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;	// Post process Model to convert any fixed property element names to
// 'constant' names

import com.sun.codemodel.JEnumConstant;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.tools.xjc.model.CAttributePropertyInfo;
import com.sun.tools.xjc.model.CBuiltinLeafInfo;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CNonElement;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.EnumConstantOutline;
import com.sun.tools.xjc.outline.EnumOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.xml.xsom.XSAttributeUse;
import com.sun.xml.xsom.XSComponent;
import com.sun.xml.xsom.XSElementDecl;
import com.sun.xml.xsom.XSParticle;
import com.sun.xml.xsom.XSTerm;
import com.sun.xml.xsom.XSType;
import com.sun.xml.xsom.XmlString;
import com.sun.xml.xsom.impl.ParticleImpl;

/**
 * <p>
 * Modifies the JAXB code model to set fixed values to the schema "fixed" attribute.
 * Currently, the following field types can be initialized:
 * </p>
 * 
 * <ul>
 *   <li>Enumerations (see {@link Enum})</li>
 *   <li>{@link String}</li>
 *   <li>Descendants of {@link java.lang.Number}
 *     <ul>
 *     <li>{@link BigDecimal}</li>
 *     <li>{@link BigInteger}</li>
 *     <li>{@link Byte}</li>
 *     <li>{@link Double}</li>
 *     <li>{@link Float}</li>
 *     <li>{@link Integer}</li>
 *     <li>{@link Long}</li>
 *     <li>{@link Short}</li>
 *     </ul>
 *   </li>
 *   <li>{@link Boolean}</li>
 *   <li>{@link Duration}</li>
 *   <li>{@link XMLGregorianCalendar}</li>
 * </ul>
 *
 * <p><b>Note:</b> The fixed value is managed by JAXB using <code>@XmlElement</code> <em>and</em> by
 * this POJO. For <em>nullable</em> fields, the fixed value is managed within the BOUND; otherwise,
 * the fixed is assigned to its <em>non-nullable</em> field, once. When the BOUND is used, the
 * fixed value can be recovered by setting the field to null; subsequently, the BOUND will return
 * the original fixed value.
 * </p>
 */
public class FixedValuePlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "XfixedValue";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "enable rewriting of classes to set fixed values as specified in XML schema";

	/** Represents the arguments of a method without parameters. */
	private static final JType[] NO_ARGS = new JType[0];
	
	/** Represents the modifiers for a fixed constant field. */
	private static final int CONSTANT_MODS = (JMod.PUBLIC | JMod.STATIC | JMod.FINAL);

	/** Creates a new <code>FixedValuePlugin</code> instance. */
	public FixedValuePlugin()
	{
	}

	/** FixedValuePlugin uses "-" + OPTION_NAME as the XJC argument */
	@Override
	public String getOptionName()
	{
		return OPTION_NAME;
	}

	/** Return usage information for plugin */
	@Override
	public String getUsage()
	{
		return format(USAGE_FORMAT, OPTION_NAME, OPTION_DESC);
	}

	private Ignoring ignoring = new CustomizedIgnoring
	(
		IGNORED_ELEMENT_NAME,
		Customizations.IGNORED_ELEMENT_NAME,
		Customizations.GENERATED_ELEMENT_NAME
	);
	public Ignoring getIgnoring()
	{
		return ignoring;
	}
	public void setIgnoring(Ignoring ignoring)
	{
		this.ignoring = ignoring;
	}

	@Override
	public Collection<QName> getCustomizationElementNames()
	{
		return Arrays.asList
		(
			IGNORED_ELEMENT_NAME,
			Customizations.IGNORED_ELEMENT_NAME,
			Customizations.GENERATED_ELEMENT_NAME
		);
	}
	
	// Plugin Processing
	
//	protected final BGMBuilder builder = Ring.get(BGMBuilder.class);

	@Override
	protected void beforePostProcessModel(Model model)
	{

		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  Verbose...: " + isVerbose());
			sb.append("\n  Debug.....: " + isDebug());
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
	protected void postProcessModel(Model model)
	{
		for (final CClassInfo classInfo : model.beans().values())
		{
			if ( !getIgnoring().isIgnored(classInfo) )
				postProcessClassInfo(model, classInfo);
		}
	}

	private void postProcessClassInfo(Model model, CClassInfo classInfo)
	{
		for (CPropertyInfo propertyInfo : classInfo.getProperties())
		{
			if ( !getIgnoring().isIgnored(propertyInfo) )
				postProcessPropertyInfo(model, classInfo, propertyInfo);
		}
	}

	// Post process Model to convert any fixed property element names to
	// 'constant' names
	private void postProcessPropertyInfo(Model model, CClassInfo classInfo, CPropertyInfo propertyInfo)
	{
		XSComponent sc = propertyInfo.getSchemaComponent();
		if ( sc instanceof ParticleImpl )
		{
			ParticleImpl xpi = (ParticleImpl) sc;
			if ( xpi.getTerm() instanceof XSElementDecl )
			{
				XSElementDecl xse = (XSElementDecl) xpi.getTerm();
				// Convert fixed property element name to constant name.
				if ( xse.getFixedValue() != null )
				{
					String privateName = propertyInfo.getName(false);
					NameConverter nc = model.getNameConverter();
					String constantName = nc.toConstantName(privateName);
					propertyInfo.setName(false, constantName);
				}
			}
		}
	}
	
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
	 * Run the plugin. We perform the following steps:
	 * </p>
	 *
	 * <ul>
	 *   <li>Look for qualifying fields, fields qualify that:
	 *     <ul>
	 *       <li>Are generated from XSD description</li>
	 *       <li>The XSD description is of type <code>xsd:element</code>
	 *           (code level fixed values are not necessary for fields 
	 *           generated from attributes)</li>
	 *       <li>A fixed value is specified</li>
	 *       <li>Map to one of the supported types</li>
	 *     </ul>
	 *   </li>
	 *   <li>Add a new initialization expression to every qualifying BOUND or field:
	 *     <ul>
	 *       <li>An element BOUND qualifies when the field is nullable;</li>
	 *       <li>Otherwise, the field qualifies to receive the initialization expression</li>
	 *     </ul>
	 *   </li>
	 *   
	 * </ul>
	 * 
     * <p>
     * Note that this method is invoked only when a plugin is activated.
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
     *      After an error is reported to {@link ErrorHandler}, a
     *      {@link SAXException} may be thrown to indicate a fatal unrecoverable
     *      error. {@link ErrorHandler} itself may throw it, if it chooses
     *      not to recover from the error.
	 */
	@Override
	public boolean run(Outline outline) throws Exception
	{
		// Filter ignored class outlines
		for (final ClassOutline classOutline : filter(outline, getIgnoring()))
			processClassOutline(outline, classOutline);
		
		return !hadError(outline.getErrorReceiver());
	}

	/**
	 * Process the XJC {@link Outline} instance. The goal is to add a fixed value to
	 * initialize all non-ignored fields from the given {@link Outline} instance.
	 * 
	 * @param outline An outline from the XJC framework.
	 * @param classOutline A class outline from the XJC framework.
     * 
	 */
	protected void processClassOutline(Outline outline, ClassOutline classOutline)
	{
		// Filter an array of {@link FieldOutline} to omit ignored or constant fields.
		FieldOutline[] declaredFilteredFields = filter(classOutline.getDeclaredFields(), getIgnoring());
		
		// check all Fields in Class
		for (FieldOutline fieldOutline : declaredFilteredFields)
		{
			// Handle primitive types via boxed representation (treat boolean as java.lang.Boolean)
			JType fieldRawType = fieldOutline.getRawType();
			boolean fieldIsPrimitive = fieldRawType.isPrimitive();
			JType fieldType = (fieldIsPrimitive) ? fieldRawType.boxify() : fieldRawType;
			
			// Get the field type's full name.
			String typeFullName = fieldType.fullName();

			// Represent the XML schema element's or attribute's fixed value.
			XmlString fixedValue = null;
			
			// Do nothing if Field is not created from an or XSAttributeUse an XSParticle
			// Set the fixedValue ONLY when this plugin needs to provide the initialization.
			CPropertyInfo fieldInfo = fieldOutline.getPropertyInfo();
			QName schemaType = fieldInfo.getSchemaType();
			if ( fieldInfo.getSchemaComponent() instanceof XSAttributeUse )
			{
				// An XSAttributeUse provides isRequired, defaultValue and fixedValue for an XSAttributeDecl.
				XSAttributeUse attribute = (XSAttributeUse) fieldInfo.getSchemaComponent();
				if (attribute.getFixedValue() != null)
				{
					// Get the fixed value from the XSD attribute as a String.
					fixedValue = attribute.getFixedValue();
					if ( schemaType == null )
					{
						if ( fieldInfo instanceof CAttributePropertyInfo)
						{
							CAttributePropertyInfo attrInfo = (CAttributePropertyInfo) fieldInfo;
							CNonElement target = attrInfo.getTarget();
							schemaType = target.getTypeName();
						}
					}
				}
			}
			else if ( fieldInfo.getSchemaComponent() instanceof XSParticle )
			{
				// An XSParticle provides min/max occurs cardinality for an XSTerm.
				XSParticle particle = (XSParticle) fieldInfo.getSchemaComponent();
				
				// Fixed values only necessary for fields derived from an xsd:element
				XSTerm term = particle.getTerm();
				if (term.isElementDecl())
				{
					// Do nothing if no fixed value.
					// Continue loop to next FieldOutline instance.
					XSElementDecl element = term.asElementDecl();
					if (element.getFixedValue() != null)
					{
						// Get the fixed value from the XSD element as a String.
						fixedValue = element.getFixedValue();
						if ( schemaType == null )
						{
							XSType elementType = element.getType();
							if ( (elementType != null) && (elementType.getName() != null) )
								schemaType = new QName(elementType.getTargetNamespace(), elementType.getName());
						}
					}
				}
			}
			
			// Provide initialization for the fixed value, when non-null.
			if ( fixedValue != null )
				processFixedValue(outline, classOutline, fieldOutline, fieldType, fieldIsPrimitive, typeFullName, fixedValue, schemaType);
		} // for FieldOutline
	}

	private void processFixedValue(Outline outline, ClassOutline classOutline,
		FieldOutline fieldOutline, JType fieldType, boolean fieldIsPrimitive,
		String typeFullName, XmlString fixedValue, QName schemaType)
	{
		// Get handle to implementation class containing the field
		JDefinedClass theClass = classOutline.implClass;
		
		// Get the property info from the outline.
		CPropertyInfo fieldInfo = fieldOutline.getPropertyInfo();
		
		// Get the field variable for the given fieldInfo private name
		// from the map of fields for theClass. .
		Map<String, JFieldVar> fields = theClass.fields();
		JFieldVar fieldVar = fields.get(fieldInfo.getName(false));
		
		// Get the BOUND for the given fieldInfo public name
		// from the list of methods for theClass.
		String publicName = fieldInfo.getName(true);
		
		// Convert the public name to a constant name.
		NameConverter nc = outline.getModel().getNameConverter();
		String constantName = nc.toConstantName(publicName);
		
		// Collect the property methods bound to the field variable.
		List<JMethod> propMethods = collectPropertyMethods(theClass, publicName, fieldOutline);
		
		// Get handle to JCodeModel owning this class.
		JCodeModel theCodeModel = theClass.owner();
		
		// Get reference to ValueUtils to invoke its static methods.
		JClass refValueUtils = theCodeModel.ref(ValueUtils.class);
		
		String fieldLoc = toLocation(fieldInfo.getLocator());
		String fieldName = fieldInfo.displayName();
		
//		if ( fieldInfo.defaultValue != null )
//		{
//			JExpression lv = fieldInfo.defaultValue.compute(classOutline.parent());
//			debug("{}, processFixedValue; {} = \"{}\" (String); LV={}", fieldLoc, fieldName, fixedValue, lv);
//		}
		
		// PROCESS: Create an appropriate fixed expression depending on type
		if ((fieldType instanceof JDefinedClass) && (((JDefinedClass) fieldType).getClassType() == ClassType.ENUM))
		{
			// Find Enumeration constant
			JEnumConstant literalValue = findEnumConstant(fieldType, fixedValue.value, outline);
			if (literalValue != null)
			{
				initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
				debug("{}, processFixedValue; {} = {} (Enum)", fieldLoc, fieldName, literalValue.getName());
			}
		}
		else if (String.class.getName().equals(typeFullName))
		{
			JExpression literalValue = CBuiltinLeafInfo.STRING.createConstant( outline, fixedValue);
//			JExpression literalValue = refValueUtils.staticInvoke("toString").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = \"{}\" (String)", fieldLoc, fieldName, fixedValue);
		}
		else if (Boolean.class.getName().equals(typeFullName))
		{
			JExpression literalValue = CBuiltinLeafInfo.BOOLEAN.createConstant( outline, fixedValue);
//			JExpression literalValue = refValueUtils.staticInvoke("toBoolean").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {} (Boolean)", fieldLoc, fieldName, fixedValue);
		}
		else if (Byte.class.getName().equals(typeFullName))
		{
			JExpression literalValue = CBuiltinLeafInfo.BYTE.createConstant( outline, fixedValue);
//			JExpression literalValue = refValueUtils.staticInvoke("toByte").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {} (Byte)", fieldLoc, fieldName, fixedValue);
		}
		else if (Short.class.getName().equals(typeFullName))
		{
			JExpression literalValue = CBuiltinLeafInfo.SHORT.createConstant( outline, fixedValue);
//			JExpression literalValue = refValueUtils.staticInvoke("toShort").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {} (Short)", fieldLoc, fieldName, fixedValue);
		}
		else if (Integer.class.getName().equals(typeFullName))
		{
			JExpression literalValue = CBuiltinLeafInfo.INT.createConstant( outline, fixedValue);
//			JExpression literalValue = refValueUtils.staticInvoke("toInteger").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {} (Integer)", fieldLoc, fieldName, fixedValue);
		}
		else if (Long.class.getName().equals(typeFullName))
		{
			JExpression literalValue = CBuiltinLeafInfo.LONG.createConstant( outline, fixedValue);
//			JExpression literalValue = refValueUtils.staticInvoke("toLong").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {} (Long)", fieldLoc, fieldName, fixedValue);
		}
		else if (Float.class.getName().equals(typeFullName))
		{
			JExpression literalValue = CBuiltinLeafInfo.FLOAT.createConstant( outline, fixedValue);
//			JExpression literalValue = refValueUtils.staticInvoke("toFloat").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {} (Float)", fieldLoc, fieldName, fixedValue);
		}
		else if (Double.class.getName().equals(typeFullName))
		{
			JExpression literalValue = CBuiltinLeafInfo.DOUBLE.createConstant( outline, fixedValue);
//			JExpression literalValue = refValueUtils.staticInvoke("toDouble").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {} (Double)", fieldLoc, fieldName, fixedValue);
		}
		else if (BigDecimal.class.getName().equals(typeFullName))
		{
			JExpression literalValue = CBuiltinLeafInfo.BIG_DECIMAL.createConstant( outline, fixedValue);
//			JExpression literalValue = refValueUtils.staticInvoke("toBigDecimal").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {} (BigDecimal)", fieldLoc, fieldName, fixedValue);
		}
		else if (BigInteger.class.getName().equals(typeFullName))
		{
			JExpression literalValue = CBuiltinLeafInfo.BIG_INTEGER.createConstant( outline, fixedValue);
//			JExpression literalValue = refValueUtils.staticInvoke("toBigInteger").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {} (BigInteger)", fieldLoc, fieldName, fixedValue);
		}
		else if
		(
			byte[].class.getCanonicalName().equals(typeFullName) && (schemaType == null) ||
			byte[].class.getCanonicalName().equals(typeFullName) && BASE64BINARY.equals(schemaType) 
		)
		{
			JExpression literalValue = CBuiltinLeafInfo.BASE64_BYTE_ARRAY.createConstant( outline, fixedValue);
//			JExpression literalValue = refValueUtils.staticInvoke("parseBase64Binary").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {} (Base64Binary)", fieldLoc, fieldName, fixedValue);
		}
		else if ( byte[].class.getCanonicalName().equals(typeFullName) && HEXBINARY.equals(schemaType) )
		{
			JExpression literalValue = CBuiltinLeafInfo.HEXBIN_BYTE_ARRAY.createConstant( outline, fixedValue);
//			JExpression literalValue = refValueUtils.staticInvoke("parseHexBinary").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {} (HexBinary)", fieldLoc, fieldName, fixedValue);
		}
		else if (XMLGregorianCalendar.class.getName().equals(typeFullName))
		{
			JExpression literalValue = refValueUtils.staticInvoke("toXMLGregorianCalendar").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {} (XMLGregorianCalendar)", fieldLoc, fieldName, fixedValue);
		}
		else if (Duration.class.getName().equals(typeFullName))
		{
//			JExpression literalValue = CBuiltinLeafInfo.DURATION.createConstant( outline, fixedValue);
			JExpression literalValue = refValueUtils.staticInvoke("toDuration").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {} (Duration)", fieldLoc, fieldName, fixedValue);
		}
		else if (QName.class.getName().equals(typeFullName))
		{
			JExpression literalValue = null;
			if ( fieldInfo.defaultValue != null )
				literalValue = fieldInfo.defaultValue.compute(classOutline.parent());
			else
			{
				// ALT?: javax.xml.namespace.QName parseQName(String lexicalXSDQName, javax.xml.namespace.NamespaceContext nsc);
				JFieldVar ofField = theClass.fields().get("OBJECT_FACTORY");
				if (ofField == null)
					ofField = installObjectFactory(theClass);
				JClass refQNameUtils = theCodeModel.ref(QNameUtils.class);
				JInvocation jaxbElement = JExpr.invoke(ofField, "create" + publicName).arg(JExpr._null());
				literalValue = refQNameUtils.staticInvoke("toName").arg(jaxbElement).arg(fixedValue.value);
			}
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {} (QName)", fieldLoc, fieldName, fixedValue);
		}
		else if (Object.class.getName().equals(typeFullName) && ANYSIMPLETYPE.equals(schemaType))
		{
			// ALT?: jakarta.xml.bind.DatatypeConverter.parseAnySimpleType(String)
			JFieldVar ofField = theClass.fields().get("OBJECT_FACTORY");
			if (ofField == null)
				ofField = installObjectFactory(theClass);
			JClass refDOMUtils = theCodeModel.ref(DOMUtils.class);
			JInvocation jaxbElement = JExpr.invoke(ofField, "create" + publicName).arg(fixedValue.value);
			JExpression literalValue = refDOMUtils.staticInvoke("toNode").arg(jaxbElement);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = \"{}\" (Object)", fieldLoc, fieldName, fixedValue);
		}
		else if ((List.class.getName()+"<"+BigDecimal.class.getName()+">").equals(typeFullName))
		{
			JExpression literalValue = refValueUtils.staticInvoke("toBigDecimalList").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {{}} (List<BigDecimal>)", fieldLoc, fieldName, fixedValue);
		}
		else if ((List.class.getName()+"<"+BigInteger.class.getName()+">").equals(typeFullName))
		{
			JExpression literalValue = refValueUtils.staticInvoke("toBigIntegerList").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {{}} (List<BigInteger>)", fieldLoc, fieldName, fixedValue);
		}
		else if ((List.class.getName()+"<"+Boolean.class.getName()+">").equals(typeFullName))
		{
			JExpression literalValue = refValueUtils.staticInvoke("toBooleanList").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {{}} (List<Boolean>)", fieldLoc, fieldName, fixedValue);
		}
		else if ((List.class.getName()+"<"+Byte.class.getName()+">").equals(typeFullName))
		{
			JExpression literalValue = refValueUtils.staticInvoke("toByteList").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {{}} (List<Byte>)", fieldLoc, fieldName, fixedValue);
		}
		else if ((List.class.getName()+"<"+Double.class.getName()+">").equals(typeFullName))
		{
			JExpression literalValue = refValueUtils.staticInvoke("toDoubleList").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {{}} (List<Double>)", fieldLoc, fieldName, fixedValue);
		}
		else if ((List.class.getName()+"<"+Duration.class.getName()+">").equals(typeFullName))
		{
			JExpression literalValue = refValueUtils.staticInvoke("toDurationList").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {{}} (List<Duration>)", fieldLoc, fieldName, fixedValue);
		}
		else if ((List.class.getName()+"<"+Float.class.getName()+">").equals(typeFullName))
		{
			JExpression literalValue = refValueUtils.staticInvoke("toFloatList").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {{}} (List<Float>)", fieldLoc, fieldName, fixedValue);
		}
		else if ((List.class.getName()+"<"+Integer.class.getName()+">").equals(typeFullName))
		{
			JExpression literalValue = refValueUtils.staticInvoke("toIntegerList").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {{}} (List<Integer>)", fieldLoc, fieldName, fixedValue);
		}
		else if ((List.class.getName()+"<"+Long.class.getName()+">").equals(typeFullName))
		{
			JExpression literalValue = refValueUtils.staticInvoke("toLongList").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {{}} (List<Long>)", fieldLoc, fieldName, fixedValue);
		}
		else if ((List.class.getName()+"<"+Short.class.getName()+">").equals(typeFullName))
		{
			JExpression literalValue = refValueUtils.staticInvoke("toShortList").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {{}} (List<Short>)", fieldLoc, fieldName, fixedValue);
		}
		else if ((List.class.getName()+"<"+String.class.getName()+">").equals(typeFullName))
		{
			JExpression literalValue = refValueUtils.staticInvoke("toStringList").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {{}} (List<String>)", fieldLoc, fieldName, fixedValue);
		}
		else if ((List.class.getName()+"<"+XMLGregorianCalendar.class.getName()+">").equals(typeFullName))
		{
			JExpression literalValue = refValueUtils.staticInvoke("toXMLGregorianCalendarList").arg(fixedValue.value);
			initializer(theClass, fieldInfo, fieldVar, propMethods, constantName, literalValue);
			debug("{}, processFixedValue; {} = {{}} (List<XMLGregorianCalendar>)", fieldLoc, fieldName, fixedValue);
		}
		// Don't know how to create fixed value for this type
		else
		{
			warn("{}, processFixedValue; Did not create fixed value for field {}. "
				+ "Don't know how to create fixed value expression for fields of type {} with schema type {}. "
				+ "Fixed value of \"{}\" specified in schema.", fieldLoc, fieldName, typeFullName, schemaType, fixedValue);
		}
	}

	private List<JMethod> collectPropertyMethods(JDefinedClass theClass, String publicName, FieldOutline fieldOutline)
	{
		List<JMethod> propMethods = new ArrayList<>();
		
		JMethod accessor = theClass.getMethod("get" + publicName, NO_ARGS);
		if ( accessor == null )
			accessor = theClass.getMethod("is" + publicName, NO_ARGS);
		if ( accessor != null )
			propMethods.add(accessor);
		
		String mutatorName = "set" + publicName;
		JType mutatorType = fieldOutline.getRawType();
        JMethod boxifiedMutator = theClass.getMethod(mutatorName, new JType[] { mutatorType.boxify() });
        JMethod unboxifiedMutator = theClass.getMethod(mutatorName, new JType[] { mutatorType.unboxify() });
        JMethod mutator = boxifiedMutator != null ? boxifiedMutator : unboxifiedMutator;
		if ( mutator != null )
			propMethods.add(mutator);        

		JMethod detector = theClass.getMethod("isSet" + publicName, NO_ARGS);
		if ( detector != null )
			propMethods.add(detector);
		
		JMethod reverter = theClass.getMethod("unset" + publicName, NO_ARGS);
		if ( reverter != null )
			propMethods.add(reverter);
		
		return propMethods;
	}

	private void initializer(JDefinedClass theClass, CPropertyInfo fieldInfo, JFieldVar fieldVar,
		List<JMethod> propMethods, String constantName, JExpression literalValue)
	{
		theClass.removeField(fieldVar);
		JFieldVar constantField = theClass.field(CONSTANT_MODS, fieldVar.type(), constantName, literalValue);
		if ( !fieldVar.javadoc().isEmpty() )
			constantField.javadoc().addAll(fieldVar.javadoc());
		for ( JAnnotationUse annotation : fieldVar.annotations() )
			constantField.annotate(annotation);
		theClass.methods().removeAll(propMethods);
		fieldInfo.setName(true, constantName);
	}
	
	/**
	 * Retrieve the enum constant that correlates to the string value.
	 * 
	 * @param enumType Type identifying an Enum in the code model
	 * @param enumStringValue Lexical value of the constant to search
	 * @param outline Outline of the code model
	 * 
	 * @return The matching Constant from the enum type or NULL if not found
	 */
	private JEnumConstant findEnumConstant(JType enumType, String enumStringValue, Outline outline)
	{
		JEnumConstant ec = null;
		
		// Search all Enums generated
		for (EnumOutline eo : outline.getEnums())
		{
			// Is it the type of my variable?
			if (eo.getImplClass() == enumType)
			{
				// Search all Constants of that enum
				for (EnumConstantOutline eco : eo.constants)
				{
					// Is the enum generated from the XML fixed value string?
					if (eco.target.getLexicalValue().equals(enumStringValue))
					{
						ec = eco.constRef;
						break;
					}
				} // for Constants
				
				if ( ec == null )
				{
					warn("{}, findEnumConstant; Could not find EnumConstant for value: \"{}\"",
						toLocation(eo.getImplClass().metadata), enumStringValue);
					break;
				}
			}
		}
		
		if ( ec == null )
			warn("findEnumConstant; Could not find Enum class for type: " + enumType.fullName());
		
		return ec;
	}

	/**
	 * On the defined class, install a field for the relative ObjectFactory.
	 * 
	 * @param theClass The target class.
	 * 
	 * @return A field representing the ObjectFactory instance.
	 */
	private JFieldVar installObjectFactory(JDefinedClass theClass)
	{
		JClass ofClass = theClass.owner().ref(theClass.getPackage().name() + ".ObjectFactory");
		return theClass.field(JMod.STATIC | JMod.FINAL | JMod.PRIVATE, ofClass, "OBJECT_FACTORY", JExpr.direct("new ObjectFactory()"));
	}
}
