/**
 * 
 */
package com.dunkware.xstream.core.xclass.expressions;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.core.annotations.AXObjectExpression;
import com.dunkware.xstream.xScript.XEqualityType;
import com.dunkware.xstream.xScript.XExpressionType;

/**
 * @author Duncan Krebs
 * @date Oct 28, 2015
 * @category M10-Comcast
 */
@AXObjectExpression(type = XEqualityType.class)
public class XEqualityExpression implements XObjectExpression {

	private XEqualityType _type;
	private XObject _xObject; 
	
	private XObjectExpression _leftExpression;
	private XObjectExpression _rightExpression; 
	
	@Override
	public void init(XExpressionType type, XObject object)  {
		_type = (XEqualityType)type;
		_xObject = object;
		_leftExpression = _xObject.getContext().createXExpression(_type.getLeft());
		_rightExpression = _xObject.getContext().createXExpression(_type.getRight());
	}

	
	@Override
	public boolean canExecute()  {
		if(_leftExpression.canExecute() == false || _rightExpression.canExecute() == false) {
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
		
		Object targetObj = _leftExpression.execute();
		Object compObj = _rightExpression.execute();
	
		if(_type.getOp().equals("==")) { 
			
			if(targetObj.equals(compObj)) { 
				return new Boolean(true);
			} else {
				return new Boolean(false);
			}
	
		}
		
		if(_type.getOp().equals("!=")) { 
			if(targetObj.equals(compObj)) {
				return new Boolean(false);
		
			} else {
				return new Boolean(true);
			
			}
						
		}
		
	
		return new Boolean(false);
		
		
	}
	
	@Override
	public void dispose() {
		_leftExpression.dispose();
		_rightExpression.dispose();
		
	}

	
}
