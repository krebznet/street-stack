/**
 * 
 */
package com.dunkware.xstream.api;

import com.dunkware.xstream.xScript.XClassElementType;
import com.dunkware.xstream.xScript.XClassType;
import com.dunkware.xstream.xScript.XExpressionType;

/**
 * @author Duncan Krebs
 * @date Oct 26, 2015
 * @category M10-Comcast
 */
public interface XObjectContext {

	public XObject getXObject();
	
	public void startWorker(XObjectWorker worker) ;
	
	public void disposeWorker(XObjectWorker worker) ;
	public Object getAttribute(String attribute);
	
	public void setAttribute(String name, Object value);
	
	public void startContext() ;
	
	public void disposeContext() ;
	
	public XClassType getXClassType();
	
	public boolean isDisposed();
	
	public XObjectExpression createXExpression(XExpressionType type) ;
	
	public XObjectElement createElement(XClassElementType type) ;

}
