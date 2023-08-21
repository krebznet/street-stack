/**
 * 
 */
package com.dunkware.xstream.api;

import com.dunkware.xstream.xScript.XClassType;
import com.dunkware.xstream.xScript.XFunctionType;

/**
 * @author Duncan Krebs
 * @date Oct 23, 2015
 * @category M10-Comcast
 */
public interface XObject {

	public XObjectFunction[] getFunctions();

	public XObjectFunction getFunction(XFunctionType type);
	
	public XObjectFunction getFunction(String functionName);
	
	public boolean functionExists(String functionName);
	
	public XObjectStack getStack();
	
	public XClassType getXClassType();
	
	public XStreamEntity getRow();
		
	public XObjectContext getContext();
	
	public XStream getStream();
	
	public void start(XObjectContext context);
	
	public void dispose();

}
