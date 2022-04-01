package com.dunkwrae.trade.service.data.mock.stream;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkwrae.trade.service.data.mock.input.MockInput;
import com.dunkwrae.trade.service.data.mock.util.MockMarker;

@Service
public class MockStreamService {

	
	private Logger logger = LoggerFactory.getLogger(MockStreamService.class.getName());
	
	private ConcurrentHashMap<String,MockStream> streams = new ConcurrentHashMap<String, MockStream>();
	
	
	@Autowired
	private ApplicationContext ac;
 

	@PostConstruct
	public void postConstruct() { 
		
	}
	
	public void newStream(MockInput input) throws Exception {
		if(streams.get(input.getStreamIdentifier()) != null) { 
			throw new Exception("Stream " + input.getStreamIdentifier() + " already exists");
		}
		MockStream stream = new MockStream();
		ac.getAutowireCapableBeanFactory().autowireBean(stream);
		try {
			stream.init(input);	
			logger.info(MockMarker.getMarker(), "Mock Stream {} Initialized",input.getStreamIdentifier());
			streams.put(input.getStreamIdentifier(), stream);
			streams.put(input.getStreamIdentifier(), stream);
		} catch (Exception e) {
			logger.error(MockMarker.getMarker(), "Exception Mock Stream Init {} Error {}",input.getStreamIdentifier(),e.toString(),e);;
		}
	}
	
	
	public Collection<MockStream> getStreams() { 
		return streams.values();
	}
	
	
	public MockStream getStream(String stream) { 
		return streams.get(stream);
		// NPE? 
	}
}


