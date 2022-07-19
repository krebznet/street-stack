package com.dunkware.xstream.net.core.container;

import java.time.LocalDateTime;

public interface ContainerEntityVar {

	int getId();
	
	String getIdent();
	
	public Object getLastValue();
	
	public int getValueCount();
	
	public void setValue(LocalDateTime time, Object value);
	
	public LocalDateTime getFirstValueTime();
	
	public LocalDateTime getLastValueTime();
	
	public Object getValue(LocalDateTime time) throws ContainerSearchException;
	
	public ContainerDataType getDataType();
	
	public ContainerEntity getEntity();
	
	public ContainerEntityVarStats getStats();
}
