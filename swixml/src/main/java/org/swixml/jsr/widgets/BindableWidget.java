package org.swixml.jsr.widgets;

import org.jdesktop.beansbinding.Converter;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public interface BindableWidget
{
    String CONVERTER_PROPERTY = "org.swixml.jsr.widgets.Converter";
    
    Converter<?,?> getConverter();
    void setConverter( Converter<?,?> converter );
}
