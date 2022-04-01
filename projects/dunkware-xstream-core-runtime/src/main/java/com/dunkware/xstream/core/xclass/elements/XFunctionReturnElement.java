/**
 * 
 */
package com.dunkware.xstream.core.xclass.elements;

import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.core.annotations.AXObjectElement;
import com.dunkware.xstream.core.xclass.impl.XObjectElementImpl;
import com.dunkware.xstream.xScript.XFunctionReturnType;

/**
 * @author Duncan Krebs
 * @date Oct 24, 2015
 * @category M10-Comcast
 */
@AXObjectElement(type = XFunctionReturnType.class)
public class XFunctionReturnElement extends XObjectElementImpl {

	XFunctionReturnType _type;
	private XObjectExpression _returnExpression = null;
	
	@Override
	public void init()  {
		_type = (XFunctionReturnType)getType();
		if(_type.getReturnValue() != null) {
			_returnExpression = getXObject().getContext().createXExpression(_type.getReturnValue());
		}
	}

	
	@Override
	public void dispose() {
		
		
	}

	@Override
	public void execute()   {
		if(_returnExpression != null) { 
			Object retValue = _returnExpression.execute();
			throw new XFunctionReturnException(retValue);
		}
		throw new XFunctionReturnException(null);
	}
	
	
	

}
