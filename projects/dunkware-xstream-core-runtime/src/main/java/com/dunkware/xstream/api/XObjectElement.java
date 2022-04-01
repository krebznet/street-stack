/**
 * 
 */
package com.dunkware.xstream.api;

import java.util.List;

import com.dunkware.xstream.xScript.XClassElementType;

/**
 * @author Duncan Krebs
 * @date Oct 23, 2015
 * @category M10-Comcast
 */
public interface XObjectElement {
	
	public void setType(XClassElementType type);
	
	public void init() ;
	
	public void dispose() ;
	
	public void setParent(XObjectElement element);
	
	public XObjectElement getParent();
	
	public void execute() ; 
	
	public List<XObjectElement> getChildren();
	
	public void setXObject(XObject object);
	
	public XObject getXObject();
	
	public Object getType();

}
