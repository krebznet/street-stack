/**
 * 
 */
package com.dunkware.xstream.api;

import com.dunkware.xstream.xScript.XVarType;

/**
 * @author Duncan Krebs
 * @date Oct 23, 2015
 * @category M10-Comcast
 */
public interface XObjectVar {

	public Object getValue();
	
	public String getName();
	
	public XVarType getType();
	
	public boolean isNull();
	
}
