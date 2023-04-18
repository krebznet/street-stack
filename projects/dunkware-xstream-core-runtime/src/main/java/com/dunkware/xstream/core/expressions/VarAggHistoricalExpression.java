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
	
	private Number aggValue = null; 
	private boolean resolved = false; 
	// we need an aggregation service implemented in here 
	

	@Override
	public void init(XStreamRow row, ExpressionType type) {
		this.row = row; 
		this.myType = (VarAggHistoryType)type;
		
		// get the # of days; 
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		//SignalQuery(SmaRoc1x2min(0) > varAggSession(SmaRoc1x2min,HIGH,realtiveDays(5)), throttle 4 SEC, limit -1, execute 1 SEC)
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
		return false;
	}

	@Override
	public boolean canExecute() {
		return resolved;
	}
	
	

}
