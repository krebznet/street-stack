package com.dunkware.xstream.core.expressions;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.IntConstantType;

@AXStreamExpression(type = IntConstantType.class)
public class IntegerExp extends XStreamExpressionImpl  {

	private IntConstantType myType; 
	private Integer value; 
	private boolean valueSet = false; 
	
	@Override
	public void init(XStreamEntity row, ExpressionType type) {
		this.myType = (IntConstantType)type;
		value = myType.getValue();
		
	}

	@Override
	public void start() {
	
		
	}

	@Override
	public void dispose() {
	
		
	}

	@Override
	public ExpressionType getExpType() {
		return myType; 
	}

	@Override
	public boolean execute() {
		if(!valueSet) { 
			setValue(value);
			valueSet = true; 
			return true; 
		}
		return false; 
	}

	@Override
	public boolean canExecute() {
		return true; 
	}

	
	
}
