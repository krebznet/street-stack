package com.dunkware.logger;

import com.dunkware.logger.core.DLoggerRuntime;

public interface DLoggerExtension {
	
	public void init(DLoggerRuntime runtime); 
	
	public void dispose();

}
