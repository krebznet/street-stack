package com.dunkware.trade.net.data.server.stream.entitystats.impl;

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
import org.springframework.stereotype.Service;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.trade.net.data.server.stream.entitystats.EntityStatsService;
import com.dunkware.trade.net.data.server.stream.entitystats.EntityStreamStats;
import com.dunkware.trade.net.data.server.stream.provider.StreamDataProvider;
import com.dunkware.trade.net.data.server.stream.provider.StreamDataProviders;


@Service
public class EntityStatsServiceImpl implements EntityStatsService  {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityStats");
	
	@Autowired
	private StreamDataProviders streamProviders;
	

	private List<EntityStreamStats> streamStats = new ArrayList<EntityStreamStats>();
	
	
	private MySqlConnectionPool connectionPool; 
	

	
	@Autowired
	private ApplicationContext ac; 
	
	@PostConstruct
	private void init() { 
		logger.info(marker, "Starting Entity Stats Service");
		
		for (StreamDataProvider provider: streamProviders.getProviders()) {
			EntityStreamStatsImpl streamStats = new EntityStreamStatsImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(streamStats);
			try {
				streamStats.init(provider.getDescriptor(),this);
				this.streamStats.add(streamStats);
			} catch (Exception e) {
				logger.error(marker, "Exception Initializaing Stream Stats {} Exception {}",provider.getDescriptor().getIdentifier(), e.toString());
			}
		}
		
	}
	
	
	
	
	@Override
	public EntityStreamStats getStream(String streamIdentifier) throws Exception {
		for (EntityStreamStats stats : streamStats) {
			if(stats.getStreamIdentifier().equals(streamIdentifier)) { 
				return stats;
			}
		}
		throw new Exception("Entity Stream Stats " + streamIdentifier + " not found");
		
		
	}

	@Override
	public Collection<EntityStreamStats> getStreams() {
		return streamStats;
	}
	
	

	
	
}
