/**
 * 
 */
package com.dunkware.xstream.api;


/**
 * @author Duncan Krebs
 * @date Oct 23, 2015
 * @category M10-Comcast
 */
public interface XObjectStack {

	public void pushFrame();
	
	public void popFrame();
	
	public XObjectVar getVar(String name);
	
	public void setVar(XObjectVar var);
	
	public void setVarValue(String name, Object value);
}
