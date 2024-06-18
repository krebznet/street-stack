/**
 * 
 */
package com.dunkware.xstream.core.xclass.expressions;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.core.annotations.AXObjectExpression;
import com.dunkware.xstream.xScript.XExpressionType;
import com.dunkware.xstream.xScript.XStringConstantType;

/**
 * @author Duncan Krebs
 * @date Oct 28, 2015
 * @category M10-Comcast
 */
@AXObjectExpression(type = XStringConstantType.class)
public class XStringExpression implements XObjectExpression  {

	private XStringConstantType _type;
	private XObject _object; 
	
	@Override
	public void init(XExpressionType type, XObject object) {
		_type = (XStringConstantType)type;
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
		return _type.getValue();
	}

	
	@Override
	public void dispose() {
		// do nothing: 
	}
	
	

}
