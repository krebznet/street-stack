package com.dunkware.trade.tick.service.server.polygon;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.trade.tick.service.server.ticker.TickerService;

import jakarta.annotation.PostConstruct;

@Service()
public class PolygonService {
	
	@Autowired
	private TickerService tickerService; 
	
	private Map<String,PolygonTicker> tickers = new ConcurrentHashMap<String,PolygonTicker>();
	
	@PostConstruct
	public void start() { 
		
		
	}

}
