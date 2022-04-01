/**
 * 
 */
package com.dunkware.xstream.core.xclass.expressions;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.core.annotations.AXObjectExpression;
import com.dunkware.xstream.xScript.XBoolConstantType;
import com.dunkware.xstream.xScript.XExpressionType;

/**
 * @author Duncan Krebs
 * @date Oct 28, 2015
 * @category M10-Comcast
 */
@AXObjectExpression(type = XBoolConstantType.class)
public class XBooleanExpression implements XObjectExpression  {

	private XBoolConstantType _type;
	private XObject _object; 
	
	@Override
	public void init(XExpressionType type, XObject object) {
		_type = (XBoolConstantType)type;
		_object = object; 
	}

	
	@Override
	public boolean canExecute()  {
		return true; 
	}

	@Override
	public XExpressionType getType() {
		return _type;
	}


	@Override
	public Object execute()  {
		return new Boolean(_type.getValue());
	}

	
	@Override
	public void dispose() {
		// do nothing: 
	}
	
	

}
