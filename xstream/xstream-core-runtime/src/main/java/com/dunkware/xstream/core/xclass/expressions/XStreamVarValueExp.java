/**
 * 
 */
package com.dunkware.xstream.core.xclass.expressions;

import com.dunkware.utils.core.helpers.DunkNumber;
import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.core.annotations.AXObjectExpression;
import com.dunkware.xstream.xScript.XExpressionType;
import com.dunkware.xstream.xScript.XStreamVarValueExpType;

/**
 * @author Duncan Krebs
 * @date Oct 26, 2015
 * @category M10-Comcast
 */
@AXObjectExpression(type = XStreamVarValueExpType.class)
public class XStreamVarValueExp implements XObjectExpression {
	private XStreamVarValueExpType _type;
	private XObject _xObject; 
	private XStreamEntityVar _refVar = null;
	private XObjectExpression _expression; 
	private Integer _index = null;
	@Override
	public void init(XExpressionType type, XObject object)
			 {
		_xObject = object;
		_type = (XStreamVarValueExpType)type;
		_refVar = _xObject.getRow().getVar(_type.getVar().getName());
		_expression = _xObject.getContext().createXExpression(_type.getExpressionValue());
	
	}

	
	@Override
	public boolean canExecute()  {
		if(_expression.canExecute()) {
			Object value = _expression.execute();
			try {
				_index = DunkNumber.toInteger(value);
			} catch (Exception e) {
				throw new XStreamRuntimeException("Error getting int inex value " + e.toString());
			}
			if(_refVar.getSize() > _index) {
				return true; 
			}
			return false; 
		}
		return false; 
	}


	@Override
	public XExpressionType getType() {
		return _type;
	}

	
	@Override
	public Object execute()  {
		return _refVar.getValue(_index);
	}


	@Override
	public void dispose() {
		
		
	}
	
	
}
