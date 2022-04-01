package com.dunkwrae.trade.service.data.mock.toolkit.var;

import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionVar;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionVarUpdater;

public class IntegerSequenceVarUpdater implements MockSessionVarUpdater {
	
	
	private IntegerSequenceVar input;
	int nextIndex = 0;
	public IntegerSequenceVarUpdater(IntegerSequenceVar input) { 
		this.input = input;
	
	}
	

	@Override
	public void update(MockSessionVar var) {
		var.setValue(Integer.valueOf(input.getList()[nextIndex]));
		if(nextIndex + 1 == input.getList().length) { 
			nextIndex = 0;
		} else { 
			nextIndex++;
		}
	}



	
	
	
	
	

}
