package org.jvnet.basicjaxb.xml.bind;


/**
 * Implemented by classes which are aware of JAXB context path.
 * 
 * @author valikov
 */
public interface ContextPathAware {

  /**
   * Returns JAXB context path which could be used to
   * create a JAXBContext instance.
   * 
   * @return JAXB context path.
   */
  public String getContextPath();

}
