package com.dunkware.trade.net.data.server.stream.signals;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.net.data.server.stream.streamprovider.StreamDataProvider;
import com.dunkware.trade.net.data.server.stream.streamprovider.StreamDataProviders;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;

@Service
public class StreamSignalServiceImpl implements StreamSignalService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamSignals");
	
	@Autowired
	private ApplicationContext ac; 
	
	@Autowired
	private StreamDataProviders streamProviders; 
	
	private Map<String,StreamSignalProvider> streamSignals = new ConcurrentHashMap<String,StreamSignalProvider>();
	
	@Autowired
	private ExecutorService executor;
	
	@PostConstruct
	public void init() { 
		logger.info(marker, "Starting Stream Signals Provider");
		for (StreamDataProvider dataProvider : streamProviders.getProviders()) {	
			StreamSignalProviderImpl streamSignals = new StreamSignalProviderImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(streamSignals);
			try {
				streamSignals.init(dataProvider);
				this.streamSignals.put(dataProvider.getDescriptor().getIdentifier(), streamSignals);
				logger.info(marker, "Initialized StreamSignals for stream {}",dataProvider.getDescriptor().getIdentifier());
			} catch (Exception e) {
				logger.error(marker, "StreamSignalsImpl init failed " + e.toString(),e);
			}
		}
		
		
	}

	@Override
	public StreamSignalProvider getProvider(String streamIdentifier) throws Exception {
		StreamSignalProvider signals = streamSignals.get(streamIdentifier);
		if(signals == null) { 
			throw new Exception("Stream Signals not found for stream idnentifier " + streamIdentifier);
		}
		return signals;
	}

	@Override
	public Collection<StreamSignalProvider> getProviders() {
		return streamSignals.values();
	}

	
}
