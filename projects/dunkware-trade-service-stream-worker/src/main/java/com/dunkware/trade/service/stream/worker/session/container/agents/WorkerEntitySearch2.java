package com.dunkware.trade.service.stream.worker.session.container.agents;

import org.slf4j.Logger;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.net.proto.netstream.GNetEntitySearchRequest2;
import com.dunkware.trade.service.stream.worker.session.container.WorkerContainer;
import com.dunkware.xstream.net.core.container.ContainerEntityScanner;

public class WorkerEntitySearch2 implements Runnable {
	
	private WorkerContainer container; 
	private int searchId; 
	private Logger logger; 
	private GNetEntitySearchRequest2 request;
	private DStopWatch watch;
	
	private ContainerEntityScanner entityScanner; 
	
	public WorkerEntitySearch2(GNetEntitySearchRequest2 req, WorkerContainer container) { 
		this.container = container; 
		this.request = req;
		this.searchId = request.getSearchId();
	}

	@Override
	public void run() {
		
		// TODO: LOL
	}
	
	

}
