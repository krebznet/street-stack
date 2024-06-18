/**
 * 
 */
package com.dunkware.xstream.api;

import com.dunkware.xstream.xScript.XExpressionType;

/**
 * @author Duncan Krebs
 * @date Oct 23, 2015
 * @category M10-Comcast
 */
public interface XObjectExpression {
	
	public void init(XExpressionType type, XObject object) ;
	
	public boolean canExecute() ;
	
	public XExpressionType getType();
	
	public Object execute() ;
	
	public void dispose();

}
