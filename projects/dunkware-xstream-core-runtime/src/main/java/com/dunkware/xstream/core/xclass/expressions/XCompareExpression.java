/**
 * 
 */
package com.dunkware.xstream.core.xclass.expressions;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.core.annotations.AXObjectExpression;
import com.dunkware.xstream.xScript.XComparisonType;
import com.dunkware.xstream.xScript.XExpressionType;

/**
 * @author Duncan Krebs
 * @date Oct 28, 2015
 * @category M10-Comcast
 */
@AXObjectExpression(type = XComparisonType.class)
public class XCompareExpression implements XObjectExpression {

	private XComparisonType _type;
	private XObject _xObject; 
	
	private XObjectExpression _leftExpression;
	private XObjectExpression _rightExpression; 
	Double _targetValue = null;
	Double _compareValue = null; 
	@Override
	public void init(XExpressionType type, XObject object)  {
		_type = (XComparisonType)type;
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
	try {
		Object targetObj = _leftExpression.execute();
		Object compObj = _rightExpression.execute();

		if (targetObj instanceof Double) {
			_targetValue = (Double) targetObj;
		}
		if (compObj instanceof Double) {
			 _compareValue = (Double) compObj;
		}
		if (compObj instanceof Integer) {
			Integer intValue = (Integer) compObj;
			_compareValue = Double.valueOf(String.valueOf(intValue));
		}
		if (targetObj instanceof Integer) {
			Integer intValue = (Integer) targetObj;
			_targetValue = Double.valueOf(String.valueOf(intValue));
		}
		if (compObj instanceof Long) {
			Long intValue = (Long) compObj;
			_compareValue = Double.valueOf(String.valueOf(intValue));
		}
		if (targetObj instanceof Long) {
			Long intValue = (Long) targetObj;
			_targetValue = Double.valueOf(String.valueOf(intValue));
		}
		
		if(_targetValue == null || _compareValue == null) {
			throw new XStreamRuntimeException("Error in XCompare target/compare expression is null");
		}
		
		
		if(_type.getOp().equals(">")) { 
			
			if(_targetValue > _compareValue) { 
				return new Boolean(true);
			} else {
				return new Boolean(false);
			}
	
		}
		
		if(_type.getOp().equals("<")) { 
			if(_targetValue < _compareValue) {
				return new Boolean(true);
		
			} else {
				return new Boolean(false);
			
			}
						
		}
		
		if(_type.getOp().equals("==")) { 
			if(_targetValue.equals(_compareValue)) 
				return new Boolean(true);
			return new Boolean(false);
			
		}

		return new Boolean(false);
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
		return new Boolean(false);
		
	}	
		
		
	}
	
	@Override
	public void dispose() {
		_leftExpression.dispose();
		_rightExpression.dispose();
		
	}

	
}
