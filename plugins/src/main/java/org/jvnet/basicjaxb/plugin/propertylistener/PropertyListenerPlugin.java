package org.jvnet.basicjaxb.plugin.propertylistener;

import static com.sun.codemodel.JExpr._new;
import static com.sun.codemodel.JExpr._this;
import static com.sun.codemodel.JMod.PRIVATE;
import static com.sun.codemodel.JMod.PUBLIC;
import static java.lang.Character.toLowerCase;
import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.propertylistener.Customizations.IGNORED_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.util.OutlineUtils.filter;
import static org.jvnet.basicjaxb.plugin.util.StrategyClassUtils.createStrategyInstanceExpression;
import static org.jvnet.basicjaxb.plugin.util.StrategyClassUtils.superClassImplements;
import static org.jvnet.basicjaxb.util.CodeModelUtils.groupMethods;
import static org.jvnet.basicjaxb.util.LocatorUtils.toLocation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.beans.VetoableChangeSupport;
import java.util.Arrays;
import java.util.Collection;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.lang.Bound;
import org.jvnet.basicjaxb.lang.Constrained;
import org.jvnet.basicjaxb.lang.DefaultVetoStrategy;
import org.jvnet.basicjaxb.lang.VetoStrategy;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.Customizations;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.jvnet.basicjaxb.util.ClassUtils;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JConditional;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldRef;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

import jakarta.xml.bind.annotation.XmlTransient;

/**
 * <p>This plugin generates bound and/or constrained {@link PropertyChangeEvent}s
 * on non-ignored properties.</p>
 * 
 * <p>Supported <code>-XpropertyListener-PARM</code> values are:</p>
 * <ul>
 *   <li><code>bound</code>: Generate change events (default: true)</li>
 *   <li><code>constrained</code>: Generate veto events (default: true)</li>
 *   <li><code>vetoStrategyClass</code>: Custom veto strategy full class name</li>
 * </ul>
 * 
 * <p>Java Beans provides support for <em>bound</em> and <em>constrained</em> properties.</p>
 * 
 * <p><b>Bound Property</b></p>
 * <p>A bean can choose to provide a change notification service for some or all of its
 * properties. Such properties are commonly known as <em>bound</em> properties, as they
 * allow other beans to bind special behavior to property changes.</p>
 * 
 * <p><b>Constrained Property</b></p>
 * <p>Sometimes when a property change occurs some other bean may wish to validate the
 * change and reject it if it is inappropriate. We refer to properties that undergo this
 * kind of checking as <em>constrained</em> properties.</p>
 * 
 * <p>When <code>-XpropertyListener-bound=true</code>, generated classes will implement
 * {@link org.jvnet.basicjaxb.lang.Bound} to provide this support method:</p>
 * 
 * <pre>
 * public PropertyChangeSupport getPropertyChangeSupport()
 * </pre>
 * 
 * <p>Applications add/remove listeners, at runtime, to receive and process property changes.</p>
 * 
 * <p>When <code>-XpropertyListener-constrained=true</code>, generated classes will implement
 * {@link org.jvnet.basicjaxb.lang.Constrained} to provide this support method:</p>
 * 
 * <pre>
 * public VetoableChangeSupport getVetoableChangeSupport()
 * </pre>
 * 
 * <p>Applications add/remove listeners, at runtime, to receive and process property vetoes.</p>
 * 
 * <p><b>Veto Strategy</b></p>
 * <p>The {@link org.jvnet.basicjaxb.lang.DefaultVetoStrategy} implements 
 * {@link org.jvnet.basicjaxb.lang.VetoStrategy} to handle {@link java.beans.PropertyVetoException}
 * when it is thrown by your listener. The default action is to log a WARN message and continue.
 * This can be elevated to an ERROR message by setting the log level to
 * <code>org.jvnet.basicjaxb.lang.DefaultVetoStrategy=ERROR</code>. You can configure your own
 * implementation to implement a handler with other behavior.</p>
 * 
 * @see <a href="https://download.oracle.com/otn-pub/jcp/7224-javabeans-1.01-fr-spec-oth-JSpec/beans.101.pdf">JavaBeans 1.01 Specification</a>
 */
public class PropertyListenerPlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "XpropertyListener";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "generate bound and/or constrained properties";

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
	
	private boolean bound = true;
	public boolean isBound() { return bound; }
	public void setBound(boolean bound) { this.bound = bound; }

	private boolean constrained = true;
	public boolean isConstrained() { return constrained; }
	public void setConstrained(boolean constrained) { this.constrained = constrained; }

	private String vetoStrategyClass = DefaultVetoStrategy.class.getName();
	public String getVetoStrategyClass()
	{
		return vetoStrategyClass;
	}
	public void setVetoStrategyClass(String vetoStrategy)
	{
		this.vetoStrategyClass = vetoStrategy;
	}
	
	public JExpression createVetoStrategy(JCodeModel codeModel)
	{
		return createStrategyInstanceExpression(codeModel, VetoStrategy.class, getVetoStrategyClass());
	}

	// Plugin Processing
	
	@Override
	protected void beforeRun(Outline outline) throws Exception
	{
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  Bound.............: " + isBound());
			sb.append("\n  Constrained.......: " + isConstrained());
			sb.append("\n  VetoStrategyClass.: " + getVetoStrategyClass());
			sb.append("\n  Verbose...........: " + isVerbose());
			sb.append("\n  Debug.............: " + isDebug());
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
		JCodeModel codeModel = outline.getCodeModel();
		JExpression vetoStrategy = createVetoStrategy(codeModel);
		// Filter ignored class outlines
		for (final ClassOutline classOutline : filter(outline, getIgnoring()))
			processClassOutline(classOutline, codeModel, vetoStrategy);
		return !hadError(outline.getErrorReceiver());
	}

	protected void processClassOutline(ClassOutline classOutline,
		JCodeModel codeModel, JExpression vetoStrategy)
	{
		final JDefinedClass theClass = classOutline.implClass;
		
		if ( isBound() || isConstrained() )
		{
			// Position of Bound and/or Constrained getters.
			int index = -1;
			
			if ( isBound() )
			{
				if ( !superClassImplements(classOutline, getIgnoring(), Bound.class, false) )
				{
					ClassUtils._implements(theClass, theClass.owner().ref(Bound.class));
					changeSupport(PropertyChangeSupport.class, theClass, codeModel, index++);
				}
			}
			
			if ( isConstrained() )
			{
				if ( !superClassImplements(classOutline, getIgnoring(), Constrained.class, false) )
				{
					ClassUtils._implements(theClass, theClass.owner().ref(Constrained.class));
					changeSupport(VetoableChangeSupport.class, theClass, codeModel, index++);
				}
			}

			// Filter ignored field outlines
			for (final FieldOutline fieldOutline : filter(classOutline, getIgnoring()))
				processFieldOutline(fieldOutline, theClass, codeModel, vetoStrategy);
		}
		
		debug("{}, processClassOutline; Class={}", toLocation(theClass.metadata), theClass.name());
	}
	
	private void changeSupport(Class<?> clazz, JDefinedClass theClass, JCodeModel codeModel, int index)
	{
		JClass type = codeModel.ref(clazz);
		String pubName = clazz.getSimpleName();
		String name = toLowerCase(pubName.charAt(0)) + pubName.substring(1);
		JFieldVar field = theClass.field(PRIVATE, type, name, _new(type).arg(_this()));
		field.annotate(XmlTransient.class);
		JMethod getter = theClass.method(PUBLIC, type, "get" + pubName);
		getter.body()._return(field);
		getter.annotate(Override.class);
		groupMethods(theClass, index, getter);
	}
	
	private void processFieldOutline(FieldOutline fieldOutline, JDefinedClass theClass,
		JCodeModel codeModel, JExpression vetoStrategy)
	{
		final CPropertyInfo fieldInfo = fieldOutline.getPropertyInfo();
		
		// Get the setter method from the field.
		final JType setterType = fieldOutline.getRawType();
		final String publicName = fieldInfo.getName(true);
		final String setterName = "set" + publicName;
		final JMethod boxifiedSetter = theClass.getMethod(setterName, new JType[] { setterType.boxify() });
		final JMethod unboxifiedSetter = theClass.getMethod(setterName, new JType[] { setterType.unboxify() });
		final JMethod setter = boxifiedSetter != null ? boxifiedSetter : unboxifiedSetter;
		if (setter != null)
		{
			// Declare PropertyChangeEvent
			String fieldName = fieldInfo.getName(false);
			JFieldRef oldValue = _this().ref(fieldName);
			JVar newValue = setter.listParams()[0];
			JClass eventType = codeModel.ref(PropertyChangeEvent.class);
			JInvocation newEvent = _new(eventType)
				.arg(JExpr._this())
				.arg(fieldName)
				.arg(oldValue)
				.arg(newValue);
			
			// Position next declaration at start of setter body.
			setter.body().pos(0);
			
			// Declare PropertyChangeEvent
			JVar event = setter.body().decl(eventType, "event", newEvent);
			
			// Conditional Constrained#vetoPropertyChange
			if ( isConstrained() )
			{
				JConditional ifVetoPropertyChange = setter.body()
					._if(_this().invoke("vetoPropertyChange")
						.arg(event)
						.arg(vetoStrategy));
				ifVetoPropertyChange._then()
					._return();
			}
			
			// Position next invocation at end of setter body.
			setter.body().pos(setter.body().getContents().size());
			
			// Bound#firePropertyChange
			if ( isBound() )
				setter.body().invoke("firePropertyChange").arg(event);
			
			trace("{}, processFieldOutline; Class={}, Field={}",
				toLocation(fieldInfo.getLocator()), theClass.name(), fieldName);
		}
	}
}
