package com.dunkware.trade.service.stream.server.tick;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.dunkware.trade.service.tick.client.TickServiceClient;
import com.dunkware.trade.service.tick.client.TickServiceClientFactory;

@Component
public class StreamTickService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private TickServiceClient client; 
	
	@Value("${tick.service.endpoint}")
	private String endpoint; 
	
	private boolean init = false;
	
	@PostConstruct
	public void load() { 
		init = true;
		if(endpoint == null) { 
			logger.error("Tick Service Endpoint not configured, existing");
			System.exit(-1);
		}
		try {
			System.err.println("connecting to tick server client " + endpoint);
			client = TickServiceClientFactory.connect(endpoint);
		} catch (Exception e) {
			logger.error("Exception connecting to tick server endpoint " + e.toString());
			// some kind of event notification
		}
	}
	
	
	/**
	 * this method is fuckin borken NPE somewhere
	 * @return
	 * @throws Exception
	 */
	public boolean isOnline() throws Exception { 
		// fuck you 
		if(1 == 1) return true;
		try {
			logger.debug("isOnline Calling");
			if(client == null) { 
				throw new Exception("Tick service client is null mother fucker");
			}
			client.getStatus();
			logger.debug("isOnline Called");
			return true; 
		} catch (Exception e) {
			throw new Exception("Tick Service isOnline throws Exception " + e.fillInStackTrace().getStackTrace().toString());
		}
	}
	
	public TickServiceClient getClient() { 
		return client;
	}
}
