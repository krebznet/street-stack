package com.dunkwrae.trade.service.data.mock.toolkit.signal;

import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionEntity;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionSignalFactory;

public class TimeSignalFactory implements MockSessionSignalFactory  {

	private TimeSignal input;
	private int counter = 0;
	private int interval = 0;
	public TimeSignalFactory(TimeSignal input) { 
		this.input = input;
		
	}
	@Override
	public void update(MockSessionEntity entity) {
		if(entity.getSession().getTime().equals(input.getTIme())) {
			entity.signal(input.getStreamSignalType());;
		}
		
	}
	
	

}
