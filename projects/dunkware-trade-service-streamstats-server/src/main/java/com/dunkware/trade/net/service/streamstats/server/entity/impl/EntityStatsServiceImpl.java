package com.dunkware.trade.net.service.streamstats.server.entity.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.trade.net.service.streamstats.server.config.StreamStatsConfig;
import com.dunkware.trade.net.service.streamstats.server.config.StreamStatsConfigStream;
import com.dunkware.trade.net.service.streamstats.server.entity.EntityStatsService;
import com.dunkware.trade.net.service.streamstats.server.entity.EntityStreamStats;
import com.dunkware.trade.net.service.streamstats.server.entity.impl.stream.EntityStreamStatsImpl;


@Service
public class EntityStatsServiceImpl implements EntityStatsService  {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityStats");
	
	@Autowired
	private StreamStatsConfig statsConfig;
	

	private Map<String,EntityStreamStats> streamStats = new ConcurrentHashMap<String, EntityStreamStats>();
	
	
	private MySqlConnectionPool connectionPool; 
	

	
	@Autowired
	private ApplicationContext ac; 
	
	@PostConstruct
	private void init() { 
		logger.info(marker, "Starting Entity Stats Service");
		try {
			logger.info(marker, "Creating MySQL Connection Pool host {}  db {} prt {} user {} ",statsConfig.getDbHost(),statsConfig.getDbName(),statsConfig.getDbPort(),statsConfig.getDbUsername());
			connectionPool = new MySqlConnectionPool(statsConfig.getDbHost(), statsConfig.getDbName(), statsConfig.getDbPort(), statsConfig.getDbUsername(), statsConfig.getDbPassword(), statsConfig.getDbPoolSize());
		} catch (Exception e) {
			logger.error(marker, "Stats MySQL Connnection Pool Exception Fatal " + e.toString(),e);
			System.exit(-1);
		}
		
		for (StreamStatsConfigStream stream : statsConfig.configStreams()) {
			EntityStreamStatsImpl streamStats = new EntityStreamStatsImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(streamStats);
			try {
				streamStats.init(stream.getId(), stream.getIdenttifier(), this);
				this.streamStats.put(stream.getIdenttifier(), streamStats);
			} catch (Exception e) {
				logger.error(marker, "Exception Initializaing Stream Stats {} Exception {}",stream.getIdenttifier(), e.toString());
			}
		}
		
	}
	
	
	
	@Override
	public MySqlConnectionPool getConnectionPool() {
		return connectionPool;
	}



	@Override
	public EntityStreamStats getStream(String streamIdentifier) throws Exception {
		EntityStreamStats stats = streamStats.get(streamIdentifier);
		if(stats == null) { 
			throw new Exception("Entity Stream Stats " + streamIdentifier + " not found");
		}
		return stats;
	}

	@Override
	public Collection<EntityStreamStats> getStreams() {
		return streamStats.values();
	}

	
	
}
