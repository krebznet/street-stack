package com.dunkware.trade.net.data.server.stream.streamprovider;

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
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.stream.cluster.proto.controller.GetStreamDescriptors;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptors;

@Service
@Profile("Live")
public class StreamDataProvidersImpl implements StreamDataProviders {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamDataProviders");
	
	@Autowired
	private DunkNet dunkNet; 
	
	@Autowired
	private ApplicationContext ac; 
	
	private Map<String,StreamDataProvider> dataProviders = new ConcurrentHashMap<String,StreamDataProvider>();
	
	private StreamDescriptors descriptors; 
	
	@PostConstruct
	private void init() { 
		try {
			logger.info(null);
			boolean error = true; 
			int count = 0; 
			while(error) { 
				try {
					descriptors = (StreamDescriptors)dunkNet.serviceBlocking(new GetStreamDescriptors());
					break;
				} catch (Exception e) {
					try {
						Thread.sleep(1000);
						count++;
						if(count > 120) { 
							// 20 seconds no one picking up fuck it throw it a day. 
							logger.error(marker, "Exception getting stream descriptors " + e.toString());
							System.exit(-1);
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		} catch (Exception e) {
			logger.error(marker, "WTF INIT " + e.toString());
		}
		
		for (StreamDescriptor d : descriptors.getDescriptors()) {
			StreamDataProvider provider = new StreamDataProvider();
			ac.getAutowireCapableBeanFactory().autowireBean(provider);
			provider.init(d);
			dataProviders.put(d.getIdentifier(), provider);
		}
	}
	
	public StreamDataProvider getDataProvider(String identifier) { 
		return dataProviders.get(identifier);
	}
	
	public StreamDescriptors getStreamDescriptors() { 
		return descriptors;
	}
	
	public Collection<StreamDataProvider> getProviders() { 
		return dataProviders.values();
	}
}
