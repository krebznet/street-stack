/**
 * 
 */
package com.dunkware.xstream.core.xclass.elements;

import java.util.ArrayList;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectFunction;
import com.dunkware.xstream.core.annotations.AXObjectElement;
import com.dunkware.xstream.core.xclass.impl.XObjectElementImpl;
import com.dunkware.xstream.xScript.XFunctionCallType;

/**
 * @author Duncan Krebs
 * @date Oct 26, 2015
 * @category M10-Comcast
 */
@AXObjectElement(type = XFunctionCallType.class)
public class XFunctionCallElement extends XObjectElementImpl  {

	private XFunctionCallType _type;
	private XObject _xObject;
	private XObjectFunction _function = null;
	
	
	
	@Override
	public void init()  {
		_type = (XFunctionCallType)getType();
	
	}



	@Override
	public void dispose()  {
		
		
	}



	@Override
	public void execute()  {
		if(_function == null) {
			_function = getXObject().getFunction(_type.getFunction());
		}
		_function.invoke(new ArrayList<Object>());
		
	}
	
	

	
	
	
}
