package com.dunkware.xstream.net.client.core.scanner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.net.proto.netstream.GEntityMatcher;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.xstream.net.client.StreamClient;
import com.dunkware.xstream.net.client.StreamClientException;
import com.dunkware.xstream.net.client.StreamClientMessageHandler;
import com.dunkware.xstream.net.core.scanner.StreamEntityScanner;
import com.dunkware.xstream.net.core.scanner.StreamEntityScannerItem;
import com.dunkware.xstream.net.core.scanner.StreamEntityScannerListener;
import com.dunkware.xstream.net.core.util.GNetProto;

public class StreamClientEntityScanner implements StreamEntityScanner, StreamClientMessageHandler  {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	

	private String scannerIdentifier;
	
	
	private List<StreamEntityScannerListener> handlers = new ArrayList<StreamEntityScannerListener>();
	private Semaphore handlerLock = new Semaphore(1);
	
	private boolean running = false; 
	
	private Map<String,StreamEntityScannerItem> items = new ConcurrentHashMap<String,StreamEntityScannerItem>();
	
	private StreamClient client; 
	private GEntityMatcher matcher; 
	
	
	public void start(StreamClient client,GEntityMatcher matcher, String scannerIdentifier) throws StreamClientException { 
		this.client = client;
		this.scannerIdentifier = scannerIdentifier;
		this.matcher = matcher;

		try {
			client.addMessageHandler(this);
			client.sendMessage(GNetProto.scannerStartRequestMessage(scannerIdentifier, matcher));			
		} catch (Exception e) {
			client.removeMessageHandler(this);
		}
		
	}
	@Override
	public void addListener(StreamEntityScannerListener listener) {
		try {
			handlerLock.acquire();
			handlers.add(listener);
		} catch (Exception e) {
			logger.error("Exception adding listener " + e.toString());
		} finally { 
			handlerLock.release();
		}
	}

	@Override
	public void removeListener(StreamEntityScannerListener listener) {
		try {
			handlerLock.acquire();
			handlers.remove(listener);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			handlerLock.release();
		}
	}

	@Override
	public Collection<StreamEntityScannerItem> getItems() {
		return items.values();
		
	}
	
	@Override
	public String getIdentifier() {
		return scannerIdentifier;
		
	}
	
	@Override
	public void dispose() {
		// send message 

	}
	@Override
	public void onMessage(GNetServerMessage message) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
