package com.dunkwrae.trade.service.data.mock.toolkit.var;

import com.dunkwrae.trade.service.data.mock.input.MockInputVar;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionVarUpdater;

public class IntegerSequenceVar extends MockInputVar {

	private Integer[] list;
	private int nextIndex; 
	private int listSize; 
	
	public IntegerSequenceVar(Integer... list) { 
		this.list = list;
		this.nextIndex = 0;
	}
	
	public Integer[] getList() {
		return list;
	}
	
	@Override
	public MockSessionVarUpdater buildVarUpdater() {
		return new IntegerSequenceVarUpdater(this);
	}

	
	
}
