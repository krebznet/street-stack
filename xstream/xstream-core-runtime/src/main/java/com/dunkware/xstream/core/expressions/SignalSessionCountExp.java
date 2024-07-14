package com.dunkware.xstream.core.expressions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.core.XStreamExpressionImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.SignalSesionCountType;

@AXStreamExpression(type = SignalSesionCountType.class)
public class SignalSessionCountExp extends XStreamExpressionImpl {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private SignalSesionCountType myType;
	private XStreamEntity row;
	
	@Override
	public void init(XStreamEntity row, ExpressionType type) {
		myType = (SignalSesionCountType)type;
		this.row = row; 
		
		// think about session time range 
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
		
		return false;
	}

	@Override
	public boolean canExecute() {
		
		return false;
	}

	
}
