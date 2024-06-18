/**
 * 
 */
package com.dunkware.xstream.core.xclass.expressions;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.core.annotations.AXObjectExpression;
import com.dunkware.xstream.xScript.XAndType;
import com.dunkware.xstream.xScript.XExpressionType;

/**
 * @author Duncan Krebs
 * @date Oct 28, 2015
 * @category M10-Comcast
 */
@AXObjectExpression(type =  XAndType.class)
public class XAndExpression implements XObjectExpression  {

	private XAndType _type;
	
	private XObjectExpression _leftExp; 
	private XObjectExpression _rightExp;
	
	private XObject _xObject; 
	

	@Override
	public void init(XExpressionType type, XObject object)
			 {
		_type = (XAndType)type;
		_xObject = object;
		_leftExp = _xObject.getContext().createXExpression(_type.getLeft());
		_rightExp = _xObject.getContext().createXExpression(_type.getRight());
	}


	@Override
	public boolean canExecute()  {
		if(_leftExp.canExecute() == false || _rightExp.canExecute() == false) {
			return false;
		}
		return true;
	}

		@Override
	public XExpressionType getType() {
		return _type;
	}

	
	@Override
	public Object execute()  {
		Object leftValue = _leftExp.execute();
		Object rightValue = _rightExp.execute();
		if (leftValue instanceof Boolean == false || rightValue instanceof Boolean == false) {
			return new Boolean(false);
		}
		Boolean leftBool = (Boolean)leftValue;
		Boolean rightBool = (Boolean)rightValue;
		if(leftBool == true && rightBool == true) { 
			return new Boolean(true);
		}
		return new Boolean(false);
	}

	
	@Override
	public void dispose() {
		
		
	}
	
	

}
