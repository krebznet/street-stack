/**
 * 
 */
package com.dunkware.xstream.core.xclass.expressions;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.api.XObjectVar;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.core.annotations.AXObjectExpression;
import com.dunkware.xstream.xScript.XExpressionType;
import com.dunkware.xstream.xScript.XVarExpType;

/**
 * @author Duncan Krebs
 * @date Oct 26, 2015
 * @category M10-Comcast
 */
@AXObjectExpression(type = XVarExpType.class)
public class XVarExp implements XObjectExpression {

	private XVarExpType _type;
	private XObject _xObject; 
	
	@Override
	public void init(XExpressionType type, XObject object)
			 {
		_type = (XVarExpType)type;
		_xObject = object;
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
		// have to get the var value
		XObjectVar var = _xObject.getStack().getVar(_type.getExpVar().getName());
		if(var == null) { 
			throw new XStreamRuntimeException("cannot get var " + _type.getExpVar().getName() + " in stack");
		}
		return var.getValue();
	}

	
	@Override
	public void dispose() {
		
		
	}
	
	
}
