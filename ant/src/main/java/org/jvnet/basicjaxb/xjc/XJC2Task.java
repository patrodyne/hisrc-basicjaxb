package org.jvnet.basicjaxb.xjc;

import org.apache.tools.ant.BuildException;

public class XJC2Task extends com.sun.tools.xjc.XJC2Task
{
	private boolean disableXmlSecurity = true;
	public void setDisableXmlSecurity(boolean disableXmlSecurity)
	{
		this.disableXmlSecurity = disableXmlSecurity;
	}

	private String accessExternalSchema = "all";
	public void setAccessExternalSchema(String accessExternalSchema)
	{
		this.accessExternalSchema = accessExternalSchema;
	}

	private String accessExternalDTD = "all";
	public void setAccessExternalDTD(String accessExternalDTD)
	{
		this.accessExternalDTD = accessExternalDTD;
	}

    /**
     * Called by the project to let the task do its work. This method may be
     * called more than once, if the task is invoked more than once.
     * 
     * For example, if target1 and target2 both depend on target3, then running
     * "ant target1 target2" will run all tasks in target3 twice.
     *
     * @exception BuildException if something goes wrong with the build.
     * 
	 * @see javax.xml.XMLConstants
     */
	@Override
	public void execute()
		throws BuildException
	{
		this.options.disableXmlSecurity = this.disableXmlSecurity;

		// See javax.xml.XMLConstants
		
		if (accessExternalSchema != null)
			System.setProperty("javax.xml.accessExternalSchema", accessExternalSchema);

		if (accessExternalDTD != null)
			System.setProperty("javax.xml.accessExternalDTD", accessExternalDTD);

		super.execute();
	}
}
