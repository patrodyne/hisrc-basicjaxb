package org.jvnet.basicjaxb.plugin.valueconstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JVar;

import org.junit.jupiter.api.Test;

public class ValueConstructorPluginTest {
  private final ValueConstructorPlugin plugin = new ValueConstructorPlugin();
  private final JDefinedClass aClass;

  public ValueConstructorPluginTest() throws Exception {
      JCodeModel aModel = new JCodeModel();
      JPackage aPackage = aModel._package("test");
      aClass = aPackage._class("AClass");

      JMethod aSetter = aClass.method(JMod.PUBLIC, aModel.VOID, "setField");

      JFieldVar aField = aClass.field(JMod.PRIVATE, aModel.INT, "field");
      aClass.field(JMod.PRIVATE, aModel.BOOLEAN, "anotherField");
      aClass.field(JMod.STATIC | JMod.PUBLIC, aModel.SHORT, "staticField");
      JMethod aGetter = aClass.method(JMod.PUBLIC, aModel.INT, "getField");
      aGetter.body()._return(aField);
      final JVar setterParam = aSetter.param(aModel.INT, "field");
      aSetter.body().assign(aField, setterParam);

      JDefinedClass aSuperClass = aPackage._class("ASuperClass");
      aClass._extends(aSuperClass);
      aSuperClass.field(JMod.PRIVATE, aModel.DOUBLE, "superClassField");
  }

  @Test
  public void testGenerateConstructors() {
    int constructorCount = sizeOf(aClass.constructors());
    assertEquals(0, constructorCount);

    plugin.processDefinedClass(aClass);
    
    constructorCount = sizeOf(aClass.constructors());
    assertEquals(2, constructorCount);
  }
  
  private int sizeOf(Iterator<JMethod> iterator)
  {
    int size = 0;
    for ( ; iterator.hasNext() ; ++size )
    {
      iterator.next();
    }
    return size;
  }
}
