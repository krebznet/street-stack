package com.dunkware.trade.net.data.server.stream.signals.session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.trade.service.data.model.signals.StreamSessionSignalTypeBean;
import com.dunkware.xstream.model.signal.StreamEntitySignal;

public class StreamSessionSignalType {
	
	private int signalId; 
	private volatile int count; 
	private LocalDate lastDate; 
	private LocalTime lastTime; 
	private Map<Integer,AtomicInteger> entityCount = new HashMap<Integer,AtomicInteger>();
	private volatile int lastEntity; 
	
	
	private StreamSessionSignalTypeBean bean = new StreamSessionSignalTypeBean();
	
	public StreamSessionSignalType(int signalId) { 
		this.signalId = signalId; 
		bean = new StreamSessionSignalTypeBean();
		bean.setSignalType(signalId);
		
	}
	public void signal(StreamEntitySignal signal) { 
		count++;
		bean.setSignalCount(count);
		lastDate = signal.getDateTime().toLocalDate();
		bean.setLastDateTime(signal.getDateTime());
		bean.setLastEntity(signal.getEntity());
		lastTime = signal.getDateTime().toLocalTime();
		lastEntity = signal.getEntity();
		if(entityCount.containsKey(lastEntity) == false) { 
			AtomicInteger co = new AtomicInteger(1);
			entityCount.put(lastEntity, co);
		} else { 
			entityCount.get(lastEntity).incrementAndGet();
		}
	}
	
	
	public StreamSessionSignalTypeBean getBean() { 
		return bean;
	}
	
	

}
