package com.dunkware.xstream.core.expressions;

import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.IntConstantType;

@AXStreamExpression(type = IntConstantType.class)
public class IntExpression extends XStreamExpressionImpl {

	private IntConstantType type;
	private XStreamRow row; 
	@Override
	public void init(XStreamRow row, ExpressionType type) {
		this.type = (IntConstantType)type;
		
	}

	@Override
	public void start() {
		setValue(type.getValue());
	}

	@Override
	public void dispose() {
		
		
	}

	@Override
	public ExpressionType getExpType() {
		return type;
	}

	@Override
	public boolean execute() {
		return false;
	}

	@Override
	public boolean canExecute() {
		return false; 
	}

	
}
