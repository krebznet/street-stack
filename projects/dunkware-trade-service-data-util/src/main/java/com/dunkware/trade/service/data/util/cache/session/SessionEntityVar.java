package com.dunkware.trade.service.data.util.cache.session;

import java.time.LocalDateTime;

public interface SessionEntityVar {

	String getIdentifier();
	
	int getId();
	
	Object getHigh();
	
	LocalDateTime getHighTime();
	
	Object getLow();
	
	LocalDateTime getLowTime();
	
	Object getValue();
	
	SessionEntity getEntity();
	
	void varUpdate(Object value);
	
	public void addListener(SessionEntityVarListener listener);
	
	public void removeListener(SessionEntityVarListener listener);
	
}
