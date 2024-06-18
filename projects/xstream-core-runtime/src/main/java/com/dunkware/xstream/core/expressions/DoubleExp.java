package com.dunkware.xstream.core.expressions;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.DoubleConstantType;
import com.dunkware.xstream.xScript.ExpressionType;

@AXStreamExpression(type = DoubleConstantType.class)
public class DoubleExp extends XStreamExpressionImpl  {

	private DoubleConstantType myType; 
	private Double value; 
	private boolean valueSet = false; 
	
	@Override
	public void init(XStreamEntity row, ExpressionType type) {
		this.myType = (DoubleConstantType)type;
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
