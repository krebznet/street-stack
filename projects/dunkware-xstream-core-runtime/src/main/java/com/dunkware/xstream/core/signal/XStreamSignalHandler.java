package com.dunkware.xstream.core.signal;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityQuery;
import com.dunkware.xstream.api.XStreamEntityQueryRun;
import com.dunkware.xstream.model.signal.type.XStreamSignalType;

public class XStreamSignalHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("SignalHandler");
	
	private XStream stream; 
	private XStreamSignalType signalType; 
	private XStreamEntityQuery query; 
	private XStreamSignalsImpl signals;
	
	private int errorLogCount = 0; 
	
	private Map<Integer,LocalTime> lastTriggers = new ConcurrentHashMap<Integer,LocalTime>();
	
	private QueryRunner queryRunner; 
	
	public void start(XStreamSignalsImpl signals, XStream stream, XStreamEntityQuery query, XStreamSignalType signalType) throws Exception { 
		this.stream = stream;
		this.signalType = signalType;
		this.query = query;
		this.signals  = signals;
		queryRunner = new QueryRunner();
		Thread thread = new Thread(queryRunner); 
		thread.start();
				
		//stream.getClock().scheduleRunnable(queryRunner,signalType.getRunInterval());
	}
	
	public void stop() { 
		stream.getClock().unscheduleRunnable(queryRunner);
	}
	
	public XStreamSignalType getType() { 
		return signalType;
	}
	
	private class QueryRunner implements Runnable {

		@Override
		public void run() {
			XStreamEntityQueryRun queryRun = query.execute();
			if(queryRun.getReturnCode() == XStreamEntityQueryRun.ERRORS) {
				errorLogCount++;
				if(errorLogCount < 10) { 
					StringBuilder errors = new StringBuilder();
					for (String error : queryRun.getExceptions()) {
						errors.append(":");
						errors.append(error);
					}
					stream.runtimeError("SignalHandler", "Signal ID " + signalType.getId() + " Query Run Exception Count " + queryRun.getExceptionCount() + " errors " + errors.toString());			
				}
				return;
			}
			List<XStreamEntity> results = queryRun.getResults();
			LocalTime time = stream.getClock().getLocalTime();
			for (XStreamEntity entity : results) {
				if(signalType.isEnableEntityThrottle()) { 
					LocalTime lastTrigger = lastTriggers.get(entity.getIdentifier());
					if(lastTrigger != null) { 
						long difference = DunkTime.secondsBetween(lastTrigger,time );
						if(difference > signalType.getEntityThrottle()) {
							lastTriggers.put(entity.getIdentifier(), time);
							signals.entitySignal(XStreamSignalHandler.this, entity);
						}
						continue;
					} else { 
						lastTriggers.put(entity.getIdentifier(), time);
						signals.entitySignal(XStreamSignalHandler.this, entity);
					}
				}
				if(signalType.isEnableEntityLimit()) { 
					// TODO: 
				}
				signals.entitySignal(XStreamSignalHandler.this, entity);
			}
		} 
		
		
	}
	
}
