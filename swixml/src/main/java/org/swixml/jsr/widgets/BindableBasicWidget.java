package org.swixml.jsr.widgets;

import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Converter;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public interface BindableBasicWidget extends BindableWidget
{

    String CONVERTER_PROPERTY = "org.swixml.jsr.widgets.Converter";
    String BINDING_PROPERTY = "org.swixml.jsr.widgets.Binding";
    String BINDING_GROUP_PROPERTY = "org.swixml.jsr.widgets.BindingGroup";
    
    Converter<?,?> getConverter();
    void setConverter( Converter<?,?> converter );
    
    Binding<?, ?, ?, ?> getBinding();
    void setBinding(Binding<?, ?, ?, ?> binding);

    BindingGroup getBindingGroup();
    void setBindingGroup(BindingGroup bindingGroup);
}
