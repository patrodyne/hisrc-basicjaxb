/*
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jvnet.basicjaxb.plugin.valueconstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.Outline;

import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

/**
 * Adds a default constructor and a constructor for all instance (non static) fields.
 * <p>
 * Note: this plugin was copied from the original java EE jaxb2-commons project at:
 * https://github.com/javaee/jaxb2-commons
 * 
 */
public class ValueConstructorPlugin extends AbstractParameterizablePlugin {

  @Override
  public String getOptionName() 
  {
    return "Xvalue-constructor";
  }

  @Override
  public String getUsage() 
  {
    return "  -Xvalue-constructor        :  enable generation of value constructors";
  }

  @Override
  public boolean run(Outline outline, Options options, ErrorHandler errorHandler) throws SAXException 
  {
    // For each defined class
    for (final ClassOutline classOutline : outline.getClasses()) {
      processDefinedClass(classOutline.implClass);
    }

    return true;
  }

  protected void processDefinedClass(final JDefinedClass implClass) 
  {
    // Create the default, no-arg constructor
    @SuppressWarnings("unused")
    final JMethod defaultConstructor = implClass.constructor(JMod.PUBLIC);
    defaultConstructor.javadoc().add("Default no-arg constructor");
    defaultConstructor.body().invoke("super");

    final Collection<JFieldVar> superClassInstanceFields = getInstanceFields(getSuperclassFields(implClass));
    final Collection<JFieldVar> thisClassInstanceFields = getInstanceFields(implClass.fields().values());

    final boolean doGenerateValueConstructor = !superClassInstanceFields.isEmpty()
        || !thisClassInstanceFields.isEmpty();

    // If the class or its (generated) superclass has fields, then generate a value
    // constructor
    if (doGenerateValueConstructor) {

      // Create the skeleton of the value constructor
      final JMethod valueConstructor = implClass.constructor(JMod.PUBLIC);
      valueConstructor.javadoc().add("Fully-initialising value constructor");

      // If our superclass is also being generated, then we can assume it will also
      // have
      // its own value constructor, so we add an invocation of that constructor.
      if (implClass._extends() instanceof JDefinedClass) {

        final JInvocation superInvocation = valueConstructor.body().invoke("super");

        // Add each argument to the super constructor.
        for (JFieldVar superClassField : superClassInstanceFields) {
          if (generateConstructorParameter(superClassField)) {
            final JVar arg = valueConstructor.param(JMod.FINAL, superClassField.type(), superClassField.name());
            superInvocation.arg(arg);
          }
        }
      }

      // Now add constructor parameters for each field in "this" class, and assign
      // them to
      // our fields.
      for (final JFieldVar field : thisClassInstanceFields) {
        if (generateConstructorParameter(field)) {
          final JVar arg = valueConstructor.param(JMod.FINAL, field.type(), field.name());
          valueConstructor.body().assign(JExpr.refthis(field.name()), arg);
        }
      }
    }
  }
      

  /**
   * Takes a collection of fields, and returns a new collection containing only
   * the instance
   * (i.e. non-static) fields.
   */
  private Collection<JFieldVar> getInstanceFields(final Collection<JFieldVar> fields) 
  {
    final List<JFieldVar> instanceFields = new ArrayList<JFieldVar>();
    for (final JFieldVar fieldVar : fields) {
      final boolean isStaticField = (fieldVar.mods().getValue() & JMod.STATIC) != 0;
      if (!isStaticField) {
        instanceFields.add(fieldVar);
      }
    }
    return instanceFields;
  }

  /**
   * Whether or not to generate a constructor parameter for the given field.
   */
  private boolean generateConstructorParameter(final JFieldVar field) 
  {
    final boolean isStaticField = (field.mods().getValue() & JMod.STATIC) > 0;
    return !isStaticField;
  }

  /**
   * Retrieve a List of the fields of each ancestor class. I walk up the class
   * hierarchy
   * until I reach a class that isn't being generated by JAXB.
   */
  protected List<JFieldVar> getSuperclassFields(final JDefinedClass implClass) 
  {
    final List<JFieldVar> fieldList = new LinkedList<JFieldVar>();

    JClass superclass = implClass._extends();
    while (superclass instanceof JDefinedClass) {
      fieldList.addAll(0, ((JDefinedClass) superclass).fields().values());
      superclass = superclass._extends();
    }

    return fieldList;
  }
}
