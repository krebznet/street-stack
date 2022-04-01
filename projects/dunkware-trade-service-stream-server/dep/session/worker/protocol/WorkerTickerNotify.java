package com.dunkware.trade.service.stream.server.controller.session.worker.protocol;

import com.dunkware.common.util.dtime.DTime;

public class WorkerTickerNotify {
	
	private String symbol;
	private DTime time; 
	private String node;
	private String stream; 
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public DTime getTime() {
		return time;
	}
	public void setTime(DTime time) {
		this.time = time;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	} 
	
	
	
	

}
