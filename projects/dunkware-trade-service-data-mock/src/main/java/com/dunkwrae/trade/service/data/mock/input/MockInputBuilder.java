package com.dunkwrae.trade.service.data.mock.input;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.net.proto.core.GDataType;

public class MockInputBuilder {
	
	public static MockInputBuilder newBuilder(String identifier, int entityCount, int updateInterval, double streamVersion) {
		return new MockInputBuilder(identifier, entityCount,updateInterval, streamVersion);
	}

	private MockInput input;
	
	private MockInputBuilder(String identifier, int entityCount, int updateInterval, double streamVersion) { 
		input = new MockInput(identifier, entityCount, updateInterval,streamVersion);
	}
	
	public MockInputBuilder schedule(DTimeZone timeZone, LocalTime start, LocalTime stop,String... days) { 
		MockInputSchedule sched = new MockInputSchedule();
		sched.setTimeZone(timeZone);
		sched.setStart(start);
		sched.setStop(stop);
		sched.setDays(days);
		input.setSchedule(sched);
		return this;
	}
	
	public MockInputBuilder var(String identifier, String name, int id, GDataType dataType, MockInputVar inputVar) { 
		inputVar.init(identifier, name, id, dataType);
		input.getVars().add(inputVar);
		return this;
		
	}
	
	public MockInputBuilder signalFactory(String identifier, String name, int id, MockInputSignal signal) {
		signal.setStreamSignalType(identifier, name, id);
		input.getSignalFactories().add(signal);
		return this;
	}
	
	public MockInputBuilder signalFactory(String identifier, String name, int id, MockInputSignal signal, String...entities) throws Exception {
		signal.setStreamSignalType(identifier, name, id);
		List<String> entList = new ArrayList<String>();
		for (String string : entities) {
			if(input.hasEntity(string) == false) { 
				throw new Exception("Entity " + string + " does not exist");
			}
			entList.add(string);
		}
		signal.setEntities(entList);
		input.getSignalFactories().add(signal);
		return this;
	}
	
	
	public MockInput build() throws Exception { 
		return input;
	}
	
	

	
	
	
}
