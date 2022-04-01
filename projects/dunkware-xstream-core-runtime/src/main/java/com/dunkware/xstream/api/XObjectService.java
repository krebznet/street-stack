/**
 * 
 */
package com.dunkware.xstream.api;

import java.util.List;

import com.dunkware.xstream.xScript.XClassElementType;
import com.dunkware.xstream.xScript.XClassType;
import com.dunkware.xstream.xScript.XExpressionType;

/**
 * @author Duncan Krebs
 * @date Oct 26, 2015
 * @category M10-Comcast
 */
public interface XObjectService {
	
	public List<XClassType> getClassTypes();
	
	public XClassType getClassType(String name);

	/**
	 * returns an XObject with a reference to its XObject context, the 
	 * init() method is automatically called before returning the xobject
	 * @param clazz
	 * @param row
	 * @return
	 * @throws XClassException
	 */
	public XObject createXObject(XClassType clazz, XStreamRow row) ;

	public XObjectExpression createExpression(XExpressionType type) ;
	
	public XObjectElement createElement(XClassElementType type) ;
}
