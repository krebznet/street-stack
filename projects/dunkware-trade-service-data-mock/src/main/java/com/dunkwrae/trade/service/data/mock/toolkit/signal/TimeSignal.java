package com.dunkwrae.trade.service.data.mock.toolkit.signal;

import java.time.LocalTime;

import com.dunkwrae.trade.service.data.mock.input.MockInputSignal;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionSignalFactory;

public class TimeSignal extends MockInputSignal {

	private LocalTime time;

	public TimeSignal(LocalTime time) {
		this.time = time;
		
	}
	
	public LocalTime getTIme() { 
		return time;
	}

	@Override
	public MockSessionSignalFactory createSignalFactory() {
		return new TimeSignalFactory(this);
	}
	
	
	

}
