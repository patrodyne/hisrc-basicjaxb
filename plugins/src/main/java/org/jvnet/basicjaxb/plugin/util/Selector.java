package org.jvnet.basicjaxb.plugin.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.sun.codemodel.JAnnotationArrayMember;
import com.sun.codemodel.JAnnotationClassValue;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JAnnotationValue;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.xml.xsom.XSIdentityConstraint;
import com.sun.xml.xsom.XSXPath;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlElements;

/**
 * Select {@link JFieldVar}s for the given {@link ClassOutline}
 * and {@link XSIdentityConstraint}.
 */
public class Selector
{
	public static final String XML_ATTRIBUTE_NAME = XmlAttribute.class.getName();
	public static final String XML_ELEMENT_NAME = XmlElement.class.getName();
	public static final String XML_ELEMENTS_NAME = XmlElements.class.getName();
	public static final String XML_ELEMENT_REF_NAME = XmlElementRef.class.getName();
	public static final String XML_ELEMENT_REFS_NAME = XmlElementRefs.class.getName();
	
	private ClassOutline classOutline;
	public ClassOutline getClassOutline()
	{
		return classOutline;
	}
	protected void setClassOutline(ClassOutline classOutline)
	{
		this.classOutline = classOutline;
	}

	private XSIdentityConstraint identityConstraint;
	public XSIdentityConstraint getIdentityConstraint()
	{
		return identityConstraint;
	}
	protected void setIdentityConstraint(XSIdentityConstraint identityConstraint)
	{
		this.identityConstraint = identityConstraint;
	}

	private Map<JDefinedClass, List<JFieldVar>> selectedFieldMap;
	public Map<JDefinedClass, List<JFieldVar>> getSelectedFieldMap()
	{
		if ( selectedFieldMap == null )
			setSelectedFieldMap(new LinkedHashMap<>());
		return selectedFieldMap;
	}
	protected void setSelectedFieldMap(Map<JDefinedClass, List<JFieldVar>> selectedFieldMap)
	{
		this.selectedFieldMap = selectedFieldMap;
	}
	
	private List<List<String>> selectedPaths;
	public List<List<String>> getSelectedPaths()
	{
		if ( selectedPaths == null )
			setSelectedPaths(new ArrayList<>());
		return selectedPaths;
	}
	protected void setSelectedPaths(List<List<String>> selectedPaths)
	{
		this.selectedPaths = selectedPaths;
	}
	
	private List<List<String>> selectedFieldPaths;
	public List<List<String>> getSelectedFieldPaths()
	{
		if ( selectedFieldPaths == null )
			setSelectedFieldPaths(new ArrayList<>());
		return selectedFieldPaths;
	}
	protected void setSelectedFieldPaths(List<List<String>> selectedFieldPaths)
	{
		this.selectedFieldPaths = selectedFieldPaths;
	}
	
	public Selector(ClassOutline classOutline, XSIdentityConstraint identityConstraint)
	{
		setClassOutline(classOutline);
		setIdentityConstraint(identityConstraint);
		initialize();
	}
	
	private void initialize()
	{
		if ( getSelectedFieldMap().isEmpty() )
		{
			parseSelector(getIdentityConstraint().getSelector());
			for ( XSXPath field : getIdentityConstraint().getFields() )
			{
				List<String> selectedFieldPath = parseField(field);
				if ( !selectedFieldPath.isEmpty() )
					getSelectedFieldPaths().add(selectedFieldPath);
			}

			JDefinedClass rootClass = getClassOutline().getImplClass();
			Set<JDefinedClass> selectedClassSet = new LinkedHashSet<>();
			for ( List<String> selectedPath : getSelectedPaths() )
			{
				Set<JDefinedClass> selectClassSet = new LinkedHashSet<>();
				selectClassSet.add(rootClass);
				
				Iterator<String> selectedPathIter = selectedPath.iterator();
				while ( selectedPathIter.hasNext() )
				{
					String selectedPathPart = selectedPathIter.next();
					if ( selectedPathPart.startsWith(".") )
					{
						if ( selectedPath.size() == 1 )
							selectedClassSet.add(rootClass);
					}
					else
					{
						Set<JDefinedClass> nextSelectClassSet = new LinkedHashSet<>();
						for ( JDefinedClass selectClass : selectClassSet )
						{
							Set<JDefinedClass> walkSelectedPath = walkSelectedPath(selectedPathPart, selectClass);
							if ( selectedPathIter.hasNext() )
								nextSelectClassSet.addAll(walkSelectedPath);
							else
								selectedClassSet.addAll(walkSelectedPath);
						}
						selectClassSet = nextSelectClassSet;
					}
				}
			}

			for ( JDefinedClass selectedClass : selectedClassSet )
			{
				for ( Entry<String, JFieldVar> selectedClassField : selectedClass.fields().entrySet() )
				{
					boolean addedField = false;
					for ( JAnnotationUse selectedClassFieldAnno : selectedClassField.getValue().annotations() )
					{
						JClass selectedClassFieldAnnoClass = selectedClassFieldAnno.getAnnotationClass();
						if ( XML_ELEMENTS_NAME.equals(selectedClassFieldAnnoClass.fullName()) )
						{
							JAnnotationValue scFieldAnnoMbrValue = selectedClassFieldAnno.getAnnotationMembers().get("value");
							if ( scFieldAnnoMbrValue instanceof JAnnotationArrayMember )
							{
								JAnnotationArrayMember scFieldAnnoMbrValues = (JAnnotationArrayMember) scFieldAnnoMbrValue;
								for ( JAnnotationUse scFieldAnnoMbr : scFieldAnnoMbrValues.annotations() )
								{
									JClass scFieldAnnoMbrClass = scFieldAnnoMbr.getAnnotationClass();
									addedField = walkSelectedField(selectedClass, selectedClassField, scFieldAnnoMbr, scFieldAnnoMbrClass);
								}
							}
						}
						else
							addedField = walkSelectedField(selectedClass, selectedClassField, selectedClassFieldAnno, selectedClassFieldAnnoClass);
					}
					if ( !addedField )
						walkSelectedField(selectedClass, selectedClassField);
				}
			}
		}
	}

	private void walkSelectedField(JDefinedClass selectedClass, Entry<String, JFieldVar> selectedClassField)
	{
		JFieldVar selectedClassFieldValue = selectedClassField.getValue();
		for ( List<String> selectedFieldPath : getSelectedFieldPaths() )
		{
			String selectedFieldPathPart = selectedFieldPath.get(0);
			if ( selectedFieldPathPart.startsWith("@") )
				selectedFieldPathPart = selectedFieldPathPart.substring(1);
			String fieldElementName = selectedClassFieldValue.name();
			if ( selectedFieldPathPart.equals(fieldElementName) )
			{
				if ( !getSelectedFieldMap().containsKey(selectedClass) )
					getSelectedFieldMap().put(selectedClass, new ArrayList<>());
				getSelectedFieldMap().get(selectedClass).add(selectedClassFieldValue);
			}
		}
	}
	
	private boolean walkSelectedField(JDefinedClass selectedClass, Entry<String, JFieldVar> selectedClassField,
		JAnnotationUse selectedClassFieldAnno, JClass selectedClassFieldAnnoClass)
	{
		boolean addedField = false;
		JFieldVar selectedClassFieldValue = selectedClassField.getValue();
		for ( List<String> selectedFieldPath : getSelectedFieldPaths() )
		{
			String targetAnnotationName = XML_ELEMENT_NAME;
			String selectedFieldPathPart = selectedFieldPath.get(0);
			if ( selectedFieldPathPart.startsWith("@") )
			{
				targetAnnotationName = XML_ATTRIBUTE_NAME;
				selectedFieldPathPart = selectedFieldPathPart.substring(1);
			}
			if ( targetAnnotationName.equals(selectedClassFieldAnnoClass.fullName()))
			{
				String fieldElementName = null;
				JAnnotationValue selectedClassFieldAnnoMbrName = selectedClassFieldAnno.getAnnotationMembers().get("name");
				if ( selectedClassFieldAnnoMbrName != null )
					fieldElementName = selectedClassFieldAnnoMbrName.toString();
				else
					fieldElementName = selectedClassFieldValue.name();

				if ( selectedFieldPathPart.equals(fieldElementName) )
				{
					if ( !getSelectedFieldMap().containsKey(selectedClass) )
						getSelectedFieldMap().put(selectedClass, new ArrayList<>());
					getSelectedFieldMap().get(selectedClass).add(selectedClassFieldValue);
					addedField = true;
				}
			}
		}
		return addedField;
	}

	private Set<JDefinedClass> walkSelectedPath(String selectedPathPart, JDefinedClass selectClass)
	{
		Set<JDefinedClass> walkSelectedPath = new LinkedHashSet<>(); 
		for ( Entry<String, JFieldVar> scField : selectClass.fields().entrySet() )
		{
			boolean addedPath = false;
			for ( JAnnotationUse scFieldAnno : scField.getValue().annotations() )
			{
				JClass scFieldAnnoClass = scFieldAnno.getAnnotationClass();
				String scFACName = scFieldAnnoClass.fullName();
				if ( XML_ELEMENT_NAME.equals(scFACName) || XML_ELEMENT_REF_NAME.equals(scFACName) )
					addedPath = walkSelectedPath(walkSelectedPath, selectedPathPart, scField, scFieldAnno);
				else if ( XML_ELEMENTS_NAME.equals(scFACName) || XML_ELEMENT_REFS_NAME.equals(scFACName) )
				{
					JAnnotationValue scFieldAnnoMbrValue = scFieldAnno.getAnnotationMembers().get("value");
					if ( scFieldAnnoMbrValue instanceof JAnnotationArrayMember )
					{
						JAnnotationArrayMember scFieldAnnoMbrValues = (JAnnotationArrayMember) scFieldAnnoMbrValue;
						for ( JAnnotationUse scFieldAnnoMbr : scFieldAnnoMbrValues.annotations() )
							addedPath = walkSelectedPath(walkSelectedPath, selectedPathPart, scField, scFieldAnnoMbr);
					}
				}
			}
			if ( !addedPath )
				walkSelectedPath(walkSelectedPath, selectedPathPart, scField);
		}
		return walkSelectedPath;
	}

	private void walkSelectedPath(Set<JDefinedClass> walkSelectedPath, String selectedPathPart,
		Entry<String, JFieldVar> scField)
	{
		JFieldVar scFieldValue = scField.getValue();
		String fieldElementName = scFieldValue.name();
		
		if ( selectedPathPart.equals(fieldElementName) || selectedPathPart.equals("*") )
		{
			JDefinedClass nextSelectClass = null;
			if ( scFieldValue.type() instanceof JClass )
			{
				JClass scFieldValueType = (JClass) scFieldValue.type();
				if ( scFieldValueType.isParameterized() )
				{
					for ( JClass scFieldValueTypeParam : scFieldValueType.getTypeParameters() )
					{
						if ( scFieldValueTypeParam instanceof JDefinedClass )
						{
							nextSelectClass = (JDefinedClass) scFieldValueTypeParam;
							addExtendedSelectClass(walkSelectedPath, nextSelectClass);
						}
					}
				}
				else
				{
					if ( scFieldValueType instanceof JDefinedClass )
					{
						nextSelectClass = (JDefinedClass) scFieldValueType;
						addExtendedSelectClass(walkSelectedPath, nextSelectClass);
					}
				}
			}
		}
	}
	
	private boolean walkSelectedPath(Set<JDefinedClass> walkSelectedPath, String selectedPathPart,
		Entry<String, JFieldVar> scField, JAnnotationUse scFieldAnno)
	{
		boolean addedPath = false;
		JFieldVar scFieldValue = scField.getValue();
		
		String fieldElementName = null;
		JAnnotationValue scFieldAnnoMbrName = scFieldAnno.getAnnotationMembers().get("name");
		if ( scFieldAnnoMbrName != null )
			fieldElementName = scFieldAnnoMbrName.toString();
		else
			fieldElementName = scFieldValue.name();
		
		if ( selectedPathPart.equals(fieldElementName) || selectedPathPart.equals("*") )
		{
			JAnnotationValue scFieldAnnoMbrType = scFieldAnno.getAnnotationMembers().get("type");
			
			if ( scFieldAnnoMbrType instanceof JAnnotationClassValue )
			{
				JAnnotationClassValue scFieldAnnoMbrClass = (JAnnotationClassValue) scFieldAnnoMbrType;
				JClass scFieldAnnoMbrClassType = scFieldAnnoMbrClass.type();
				addedPath = nextSelectClass(walkSelectedPath, scFieldAnnoMbrClassType);
			}
			
			if ( !addedPath )
			{
				if ( scFieldValue.type() instanceof JClass )
				{
					JClass scFieldValueType = (JClass) scFieldValue.type();
					addedPath = nextSelectClass(walkSelectedPath, scFieldValueType);
				}
			}
		}
		return addedPath;
	}
	
	private boolean nextSelectClass(Set<JDefinedClass> walkSelectedPath, JClass valueType)
	{
		boolean addedPath = false;
		JDefinedClass nextSelectClass;
		if ( valueType.isParameterized() )
		{
			for ( JClass scFieldAnnoMbrClassTypeParam : valueType.getTypeParameters() )
			{
				if ( scFieldAnnoMbrClassTypeParam instanceof JDefinedClass )
				{
					nextSelectClass = (JDefinedClass) scFieldAnnoMbrClassTypeParam;
					addExtendedSelectClass(walkSelectedPath, nextSelectClass);
					addedPath = true;
				}
			}
		}
		else
		{
			if ( valueType instanceof JDefinedClass )
			{
				nextSelectClass = (JDefinedClass) valueType;
				addExtendedSelectClass(walkSelectedPath, nextSelectClass);
				addedPath = true;
			}
		}
		return addedPath;
	}
	
	private void addExtendedSelectClass(Set<JDefinedClass> selectedSet, JDefinedClass selectClass)
	{
		selectedSet.add(selectClass);
		if ( selectClass._extends() instanceof JDefinedClass )
		{
			JDefinedClass extendsSelectClass = (JDefinedClass) selectClass._extends();
			addExtendedSelectClass(selectedSet, extendsSelectClass);
		}
	}
	
	private void parseSelector(XSXPath selector)
	{
		String xpathValue = selector.getXPath().value;
		for ( String xpath : xpathValue.split("\\|") )
		{
			List<String> selectedPath = new ArrayList<>();
			for ( String xpathPart : xpath.trim().split("/+") )
			{
				int prefixIndex = xpathPart.indexOf(':');
				if ( prefixIndex >= 0 )
					xpathPart = xpathPart.substring(prefixIndex+1);
				if ( selectedPath.isEmpty() )
					selectedPath.add(xpathPart.trim());
				else if ( !".".equals(xpathPart) )
					selectedPath.add(xpathPart.trim());
			}
			if ( !selectedPath.isEmpty() )
				getSelectedPaths().add(selectedPath);
		}
	}

	private List<String> parseField(XSXPath field)
	{
		String xpathValue = field.getXPath().value;
		List<String> fieldPath = new ArrayList<>();
		for ( String xpathPart : xpathValue.trim().split("/+") )
		{
			int prefixIndex = xpathPart.indexOf(':');
			if ( prefixIndex >= 0 )
				xpathPart = xpathPart.substring(prefixIndex+1);
			if ( !(".".equals(xpathPart) || "*".equals(xpathPart)) )
				fieldPath.add(xpathPart.trim());
		}
		return fieldPath;
	}
}
