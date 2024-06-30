package com.dunkware.xstream.core.expressions;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.BoolConstantType;
import com.dunkware.xstream.xScript.ExpressionType;

@AXStreamExpression(type = BoolConstantType.class)
public class BooleanExp extends XStreamExpressionImpl  {

	private BoolConstantType myType; 
	private Boolean value; 
	private boolean valueSet = false; 
	
	@Override
	public void init(XStreamEntity row, ExpressionType type) {
		this.myType = (BoolConstantType)type;
		if(myType.getValue().equals("true")) { 
			value = Boolean.TRUE;
		} else { 
			value = Boolean.FALSE;
		}
		
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
