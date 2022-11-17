package com.dunkware.xstream.core.expressions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.SignalCountHistory;

@AXStreamExpression(type = SignalCountHistory.class)
public class SignalCountHistorical extends XStreamExpressionImpl {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private SignalCountHistory myType; 
	private XStreamRow row; 
	
	@Override
	public void init(XStreamRow row, ExpressionType type) {
		this.row = row; 
		this.myType = (SignalCountHistory)type;
		
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
		// TODO Auto-generated method stub
		return null;
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
