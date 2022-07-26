package com.dunkware.trade.service.beach.server.web.streams;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.spring.streaming.AbstractStreamingAdapter;
import com.dunkware.spring.streaming.StreamingAdapter;
import com.dunkware.trade.service.beach.server.trade.BeachTradeService;

public class BeachAccountStream extends AbstractStreamingAdapter {
	
	
	private StreamingAdapter adapter;
	
	@Autowired
	private BeachTradeService tradeService; 
	
	public void start() throws Exception { 
		
	}

	@Override
	public void onServerDisconnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClientDisconnect() {
		// TODO Auto-generated method stub
		
	} 
	
	
	
	
	
	

}
