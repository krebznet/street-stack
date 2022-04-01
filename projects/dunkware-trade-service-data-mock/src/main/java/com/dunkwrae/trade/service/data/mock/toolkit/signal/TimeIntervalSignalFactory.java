package com.dunkwrae.trade.service.data.mock.toolkit.signal;

import java.util.concurrent.TimeUnit;

import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionEntity;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionSignalFactory;

public class TimeIntervalSignalFactory implements MockSessionSignalFactory  {

	private TimeIntervalSignal input;
	private int counter = 0;
	private int interval = 0;
	public TimeIntervalSignalFactory(TimeIntervalSignal input) { 
		this.input = input;
		if(input.getTimeUnit() == TimeUnit.SECONDS) { 
			interval = input.getInterval();
		}
		if(input.getTimeUnit() == TimeUnit.MINUTES) { 
			interval = input.getInterval() * 60;
		}
	}
	@Override
	public void update(MockSessionEntity entity) {
		if(counter == interval) { 
			entity.signal(input.getStreamSignalType());
			counter = 0;
			return;
		}
		counter++;
		
	}
	
	

}
