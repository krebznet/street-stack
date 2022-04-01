/**
 * 
 */
package com.dunkware.xstream.core.xclass.expressions;

import org.apache.commons.beanutils.ConvertUtils;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.core.annotations.AXObjectExpression;
import com.dunkware.xstream.xScript.XExpressionType;
import com.dunkware.xstream.xScript.XSubExpType;

/**
 * @author Duncan Krebs
 * @date Oct 26, 2015
 * @category M10-Comcast
 */
@AXObjectExpression(type = XSubExpType.class)
public class XSubExp implements XObjectExpression {

	private XSubExpType _type;
	private XObject _xObject; 
	
	private XObjectExpression _value1Exp;
	private XObjectExpression _value2Exp;

	@Override
	public void init(XExpressionType type, XObject object)
			 {
		_type = (XSubExpType)type;
		_xObject = object;
		_value1Exp = _xObject.getContext().createXExpression(_type.getValue1());
		_value2Exp = _xObject.getContext().createXExpression(_type.getValue2());
	}

	/* (non-Javadoc)
	 * @see com.kntrade.sdk.xclass.api.runtime.XObjectExpression#canExecute()
	 */
	@Override
	public boolean canExecute()  {
		if(_value1Exp.canExecute() == false || _value2Exp.canExecute() == false) {
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
		Object v1 = _value1Exp.execute();
		Object v2 = _value2Exp.execute();
		if (v1 instanceof Integer) {
			Integer v1Int = (Integer) v1;
			if (v2 instanceof Integer) {
				Integer v2Int = (Integer) v2;
				return v1Int - v2Int;
			}
		}
		if (v1 instanceof Double) {
			Double v1Dub = (Double) v1;
			if (v2 instanceof Double) {
				Double v2Dub = (Double) v2;
				return v1Dub - v2Dub;
			}
		}
		double v1dub = (Double)ConvertUtils.convert(v1,Double.class);
		double v2dub = (Double)ConvertUtils.convert(v2,Double.class);
		double results = v1dub - v1dub;

		return v1dub - v2dub; 

	}


	@Override
	public void dispose() {
		_value1Exp.dispose();
		_value2Exp.dispose();
	}
	
	

}
