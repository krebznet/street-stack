/**
 * 
 */
package com.dunkware.xstream.core.xclass.expressions;

import com.dunkware.xstream.api.XObject;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.api.XStreamExpression;
import com.dunkware.xstream.core.annotations.AXObjectExpression;
import com.dunkware.xstream.xScript.XExpressionType;
import com.dunkware.xstream.xScript.XStreamWrapperExpType;

/**
 * @author Duncan Krebs
 * @date Oct 26, 2015
 * @category M10-Comcast
 */
@AXObjectExpression(type = XStreamWrapperExpType.class)
public class XStreamWrapperExp implements XObjectExpression {
	private XStreamWrapperExpType _type;
	private XObject _xObject;
	private XStreamExpression _expression;

	@Override
	public void init(XExpressionType type, XObject object) {
		_xObject = object;
		_type = (XStreamWrapperExpType) type;
		_expression = _xObject.getRow().getStream().getInput().getRegistry().createVarExpression(_type.getWrapperExp());
		_expression.init(_xObject.getRow(), _type.getWrapperExp());
		_expression.start();

	}

	@Override
	public boolean canExecute() {
		return _expression.canExecute();
	}

	@Override
	public XExpressionType getType() {
		return _type;
	}

	@Override
	public Object execute() {
		_expression.execute();
		return _expression.getValue();
	}

	@Override
	public void dispose() {
		_expression.dispose();
	}

}
