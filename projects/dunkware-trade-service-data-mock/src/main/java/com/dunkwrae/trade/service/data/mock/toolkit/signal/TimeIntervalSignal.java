package com.dunkwrae.trade.service.data.mock.toolkit.signal;

import java.util.concurrent.TimeUnit;

import com.dunkwrae.trade.service.data.mock.input.MockInputSignal;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionSignalFactory;

public class TimeIntervalSignal extends MockInputSignal {

	private int interval;
	private TimeUnit timeUnit; 
	

	public TimeIntervalSignal(int interval, TimeUnit timeUnit) {
		this.interval = interval;
		this.timeUnit = timeUnit; 
		
	}
	

	public int getInterval() {
		return interval;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	@Override
	public MockSessionSignalFactory createSignalFactory() {
		return new TimeIntervalSignalFactory(this);
	}
	
	
	



	
	
	

}
