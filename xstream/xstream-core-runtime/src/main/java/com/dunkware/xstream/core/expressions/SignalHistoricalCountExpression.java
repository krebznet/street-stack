package com.dunkware.xstream.core.expressions;

import com.dunkware.utils.core.concurrent.DunkExecutor;
import com.dunkware.xstream.core.XStreamExecutorImpl;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.xScript.SignalHistoricalCountType;

@AXStreamExpression(type = SignalHistoricalCountType.class)
public class SignalHistoricalCountExpression extends XStreamExecutorImpl {

	public SignalHistoricalCountExpression(DunkExecutor executor) {
		super(executor);
		// TODO Auto-generated constructor stub
	}
	
	

}
