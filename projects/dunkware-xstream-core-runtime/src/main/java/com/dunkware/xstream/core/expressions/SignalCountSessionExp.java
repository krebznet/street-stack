package com.dunkware.xstream.core.expressions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.SignalCountSession;
import com.dunkware.xstream.xScript.VarAggSessionType;

@AXStreamExpression(type = SignalCountSession.class)
public class SignalCountSessionExp extends XStreamExpressionImpl {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private SignalCountSession myType;
	private XStreamRow row;
	
	@Override
	public void init(XStreamRow row, ExpressionType type) {
		myType = (SignalCountSession)type;
		this.row = row; 
		
		// think about session time range 
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
