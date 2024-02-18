package com.dunkware.trade.net.data.server.stream.streamprovider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptors;

@Service()
@Profile("Mock")
public class StreamDataMockProviders implements StreamDataProviders {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamData");
	
	
	@Autowired
	private StreamDataMockConfig config;
	
	private List<StreamDataProvider> dataProviders = new ArrayList<StreamDataProvider>();
	
	private StreamDescriptors descriptors = new StreamDescriptors();
	
	@Autowired
	private ApplicationContext ac;
	
	@PostConstruct
	private void init() { 
		descriptors = new StreamDescriptors();
		StreamDescriptor desc = new StreamDescriptor();
		desc.setId(config.getStreamId());
		desc.setIdentifier(config.getStreamIdentifier());
		desc.setCoreDatabase(config.getCoreDatabase());
		desc.setCoreDatabaseURL(config.getCoreDatabaseURL());
		desc.setWarehouseDatabase(config.getWarehouseDatabase());
		desc.setWarehouseURL(config.getWarehouseURL());
		desc.setKafkaBrokers(config.getKafkaBrokers());
		desc.setStatDbHost(config.getStatsDbHost());
		desc.setStatDbName(config.getStatsDbName());
		desc.setStatDbPass(config.getStatsDbPass());
		desc.setStatDbPoolSize(config.getStatsDbPoolSize());
		desc.setStatDbPort(config.getStatsDbPort());
		desc.setTimeZone(DTimeZone.NewYork);
		desc.setStatDbUser(config.getStatsDbUser());
		descriptors.getDescriptors().add(desc);
		
		StreamDataProvider provider = new StreamDataProvider();
		try {
			ac.getAutowireCapableBeanFactory().autowireBean(provider);
			provider.init(descriptors.getDescriptors().get(0));
			dataProviders.add(provider);
		} catch (Exception e) {
			logger.error(marker, "Fatal Shit, Mock Properties are not right " + e.toString());
			System.exit(-1);
		}
		
		
	}
	
	@Override
	public StreamDataProvider getDataProvider(String provider) throws Exception {
		for (StreamDataProvider streamDataProvider : dataProviders) {
			if(streamDataProvider.getDescriptor().getIdentifier().equals(provider)) { 
				return streamDataProvider;
			}
		}
		throw new Exception("Cannot find stream provider " + provider);
	}

	@Override
	public Collection<StreamDataProvider> getProviders() {
		return dataProviders;
	}

	@Override
	public StreamDescriptors getStreamDescriptors() {
		return descriptors;
	}
	
	

}
