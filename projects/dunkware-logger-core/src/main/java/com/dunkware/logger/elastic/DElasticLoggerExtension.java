package com.dunkware.logger.elastic;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.dunkware.logger.DLog;
import com.dunkware.logger.DLoggerExtension;
import com.dunkware.logger.core.DLoggerRuntime;

public class DElasticLoggerExtension implements DLoggerExtension {

	private BlockingQueue<DLog> writeQueue = new LinkedBlockingQueue<DLog>();
	private DLoggerRuntime runtime;
	
	// we need elastic search properties. 
	// we need to bulk send if possible; 
	// or multile send threads. 
	
	// need to convert to Json. 
	
	@Override
	public void init(DLoggerRuntime runtime) {
		this.runtime = runtime;
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
}
