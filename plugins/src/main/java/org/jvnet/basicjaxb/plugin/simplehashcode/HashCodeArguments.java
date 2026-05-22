package org.jvnet.basicjaxb.plugin.simplehashcode;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.ListIterator;
import java.util.Map;

import org.jvnet.basicjaxb.plugin.codegenerator.Arguments;
import org.jvnet.basicjaxb.util.FieldUtils;
import org.jvnet.basicjaxb.util.FieldUtils.ValueArguments;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;

public class HashCodeArguments implements Arguments<HashCodeArguments> {

	private final JCodeModel codeModel;
	private final JVar currentHashCode;
	private final int multiplier;
	private final JVar value;
	private final JExpression hasSetValue;
	private final String idGetterName;

	public HashCodeArguments(JCodeModel codeModel, JVar currentHashCode,
			int multiplier, JVar value, JExpression hasSetValue,
			String idGetter, Boolean valueIsCollection, JType valueCollectionType)
	{
		this.codeModel = requireNonNull(codeModel);
		this.currentHashCode = requireNonNull(currentHashCode);
		this.multiplier = multiplier;
		this.value = requireNonNull(value);
		this.hasSetValue = requireNonNull(hasSetValue);
		this.idGetterName = idGetter;
		this.valueIsCollection = valueIsCollection;
		this.valueCollectionType = valueCollectionType;
	}

	private JCodeModel getCodeModel() {
		return codeModel;
	}

	public JVar currentHashCode() {
		return currentHashCode;
	}

	public int multiplier() {
		return multiplier;
	}

	public JVar value() {
		return value;
	}

	public JExpression hasSetValue() {
		return hasSetValue;
	}

	public String getIdGetterName()
	{
		return idGetterName;
	}

	private HashCodeArguments spawn(JVar value, JExpression hasSetValue)
	{
		ValueArguments valueArguments = FieldUtils.getValueArguments(getCodeModel(), value);
		return new HashCodeArguments(
			getCodeModel(),
			currentHashCode(),
			multiplier(),
			value,
			hasSetValue,
			valueArguments.idGetterName,
			valueArguments.valueIsCollection,
			valueArguments.valueCollectionType);
	}

	@Override
	public HashCodeArguments property(JBlock block, String propertyName,
			String propertyMethod, JType declarablePropertyType,
			JType propertyType, Collection<JType> possiblePropertyTypes) {
		block.assign(currentHashCode(),
				currentHashCode().mul(JExpr.lit(multiplier())));
		final JVar propertyValue = block.decl(JMod.FINAL,
				declarablePropertyType, value().name() + propertyName, value()
						.invoke(propertyMethod));
		// We assume that primitive properties are always set
		boolean isAlwaysSet = propertyType.isPrimitive();
		final JExpression propertyHasSetValue = isAlwaysSet ? JExpr.TRUE
				: propertyValue.ne(JExpr._null());
		return spawn(propertyValue, propertyHasSetValue);
	}

	@Override
	public HashCodeArguments iterator(JBlock block, JType elementType) {
		final JVar listIterator = block
				.decl(JMod.FINAL, getCodeModel().ref(ListIterator.class)
						.narrow(elementType), value().name() + "ListIterator",
						value().invoke("listIterator"));

		return spawn(listIterator, JExpr.TRUE);
	}

	@Override
	public HashCodeArguments element(JBlock subBlock, JType elementType) {
		final JVar elementValue = subBlock.decl(JMod.FINAL, elementType,
				value().name() + "Element", value().invoke("next"));
		final boolean isElementAlwaysSet = elementType.isPrimitive();
		final JExpression elementHasSetValue = isElementAlwaysSet ? JExpr.TRUE
				: elementValue.ne(JExpr._null());
		return spawn(elementValue, elementHasSetValue);

	}

	@Override
	public JExpression _instanceof(JType type) {
		return value()._instanceof(type);
	}

	@Override
	public HashCodeArguments cast(String suffix, JBlock block,
			JType jaxbElementType, boolean suppressWarnings) {
		final JVar castedValue = block.decl(JMod.FINAL, jaxbElementType,
				value().name() + suffix, JExpr.cast(jaxbElementType, value()));
		if (suppressWarnings) {
			castedValue.annotate(SuppressWarnings.class).param("value",
					"unchecked");
		}
		return spawn(castedValue, JExpr.TRUE);
	}

	@Override
	public JBlock ifHasSetValue(JBlock block, boolean isAlwaysSet,
			boolean checkForNullRequired) {

		if (isAlwaysSet || !checkForNullRequired) {
			return block;
		} else {
			return block._if(hasSetValue())._then();
		}
	}

	@Override
	public JBlock _while(JBlock block) {
		final JBlock subBlock = block._while(value().invoke("hasNext")).body();
		subBlock.assign(currentHashCode(),
				currentHashCode().mul(JExpr.lit(multiplier())));
		return subBlock;
	}

	private Boolean valueIsCollection = null;
	public boolean valueIsCollection()
	{
		if ( valueIsCollection == null )
		{
			boolean isCollection = false;
			if ( (value() != null) && value().type() instanceof JClass )
			{
				JClass jclass = (JClass) value().type().erasure();
				isCollection = getCodeModel().ref(Collection.class).isAssignableFrom(jclass) ||
					getCodeModel().ref(Map.class).isAssignableFrom(jclass);
			}
			valueIsCollection = isCollection;
		}
		return valueIsCollection;
	}

	private JType valueCollectionType;
	public JType valueCollectionType()
	{
		return valueCollectionType;
	}
}
