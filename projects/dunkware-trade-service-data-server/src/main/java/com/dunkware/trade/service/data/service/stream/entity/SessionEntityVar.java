package com.dunkware.trade.service.data.service.stream.entity;

import java.time.LocalDateTime;

import com.dunkware.net.proto.stream.GEntityVarSnapshot;

public interface SessionEntityVar {
	
	public String getHigh();
	
	public String getLow();
	
	public LocalDateTime getHighTime();
	
	public LocalDateTime getLowTime();
	
	public String getIdentifier();
	
	public int getId();
	
	public void snapshot(GEntityVarSnapshot snapshot);

}
