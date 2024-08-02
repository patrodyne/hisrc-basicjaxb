package org.swixml.el;

import static org.swixml.el.ELUtility.invokeFunctionSafe;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.swixml.LogAware;
import org.swixml.SwingEngine;

/**
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 */
public class ELAction extends AbstractAction implements LogAware
{
	private static final long serialVersionUID = 20240701L;
	private SwingEngine<?> swingEngine;
	public SwingEngine<?> getSwingEngine() { return swingEngine; }
	public void setSwingEngine(SwingEngine<?> swingEngine) { this.swingEngine = swingEngine; }

	private String methodName;
	public String getMethodName() { return methodName; }
	public void setMethodName(String methodName) { this.methodName = methodName; }

	public ELAction(SwingEngine<?> engine, String methodName)
		throws NoSuchMethodException
	{
		setSwingEngine(engine);
		setMethodName(methodName);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		invokeFunctionSafe(getSwingEngine().getELProcessor(), getMethodName(), ae);
	}
}
