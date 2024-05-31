package com.dunkware.xstream.core;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.xScript.SignalType;

public class XStreamSignalImpl implements XStreamRowSignal {
	
	private XStreamEntity row; 
	private SignalType signalType; 
	private long timestamp; 
	private DTime time; 
	private LocalDateTime localDateTime;
	private Map<Integer,Object> vars = new ConcurrentHashMap<Integer,Object>();
	
	public XStreamSignalImpl(XStreamEntity row, SignalType signalType,  long timestamp,  DTime time, LocalDateTime dateTime) { 
		this.row = row; 
		this.localDateTime = dateTime;
		this.signalType = signalType; 
		this.timestamp = timestamp; 
		this.time = time; 
		
		for (XStreamEntityVar var : row.getVars()) {
			if(var.getSize() == 0) { 
				vars.put(var.getVarType().getId(), "null");
			} else { 
				vars.put(var.getVarType().getId(), var.getValue(0));
			}
		}
	}

	@Override
	public XStreamEntity getRow() {
		return row; 
	}

	@Override
	public SignalType getSignalType() {
		return signalType; 
	}


	@Override
	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public DTime getTime() {
		return time;
	}

	@Override
	public Map<Integer, Object> getVars() {
		return vars;
	}

	@Override
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	
	
	
	
	// search methods here; 
	
	
	

}
