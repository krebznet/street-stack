package com.dunkware.xstream.core.expressions;

import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.VarAggHistoryType;

@AXStreamExpression(type = VarAggHistoryType.class)
public class VarAggHistoricalExpression extends XStreamExpressionImpl {
	
	private XStreamRow row; 
	private VarAggHistoryType myType; 

	@Override
	public void init(XStreamRow row, ExpressionType type) {
		this.row = row; 
		this.myType = (VarAggHistoryType)type;
		
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ExpressionType getExpType() {
		return myType; 
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExecute() {
		// TODO Auto-generated method stub
		return false;
	}

}
