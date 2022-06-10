package com.dunkware.xstream.net.core.container.core;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.xstream.net.core.container.ContainerDataType;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerEntityVar;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.util.ContainerHelper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public class ContainerEntityVarImpl implements ContainerEntityVar {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Cache<LocalDateTime,Object> values;

	
	private ContainerEntityImpl entity; 
	private String ident; 
	private int id; 
	private LocalDateTime firstValueTime;
	private LocalDateTime lastValueTime; 
	private ContainerDataType dataType; 
	
	private AtomicInteger valueCount = new AtomicInteger();
	
	private volatile Object lastValue = null;
	
	
	public ContainerEntityVarImpl(ContainerEntityImpl entity, String ident, int id, Object value, LocalDateTime time) { 
		values = Caffeine.newBuilder().expireAfterWrite(23, TimeUnit.HOURS).build();
		this.id = id;
		this.ident = ident; 
		this.entity = entity;
		try {
			// it should do the casting once up front
			dataType = ContainerHelper.getDataType(value);
		} catch (Exception e) {
			logger.error("Unknown data type for variable value name " + ident  + " value type " + value.getClass().getName());;
			dataType = ContainerDataType.UNKOWNN;
		}
		this.firstValueTime = time;
		this.lastValueTime = time;
		this.values.put(time, value);
		this.entity = entity;
		lastValue = value;
		valueCount.incrementAndGet();
	}
	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getIdent() {
		return ident;
	}

	@Override
	public Object getLastValue() {
		return lastValue;
		
	}

	@Override
	public int getValueCount() {
		return valueCount.get();
	}
	@Override
	public void setValue(LocalDateTime time, Object value) {
		lastValue = value; 
		this.lastValueTime = time;
		values.put(time, value);
	}
	@Override
	public LocalDateTime getFirstValueTime() {
		return firstValueTime;
	}
	@Override
	public LocalDateTime getLastValueTime() {
		return lastValueTime;
	}
	@Override
	public Object getValue(LocalDateTime time) throws ContainerSearchException {
		Object value = values.getIfPresent(time);
		if(value == null) { 
			throw new ContainerSearchException("Value on variable " + ident + " for time " + DunkTime.toStringTimeStamp(time) + " not found");
		}
		return value;
	}
	@Override
	public ContainerDataType getDataType() {
		return dataType;
	}
	@Override
	public ContainerEntity getEntity() {
		return entity;
	}
	
	
	

	
}
