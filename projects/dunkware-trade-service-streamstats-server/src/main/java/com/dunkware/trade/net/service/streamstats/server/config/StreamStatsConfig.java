package com.dunkware.trade.net.service.streamstats.server.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StreamStatsConfig {

	@Value("${config.stats.streams.idents}")
	private String streamIdents;
	@Value("${config.stats.streams.ids}")
	private String streamIds;
	

	@Value("${config.stats.db.host}")
	private String dbHost;
	@Value("${config.stats.db.schema}")
	private String dbName;
	@Value("${config.stats.db.port}")
	private int dbPort;
	@Value("${config.stats.db.user}")
	private String dbUsername;
	@Value("${config.stats.db.pass}")
	private String dbPassword;
	@Value("${config.stats.db.pool.size}")
	private int dbPoolSize; 
	
	@Value("${config.kafka.brokers}")
	private String kafkaBrokers;
	
	@Value("${config.kafka.consumer.id}")
	private String kafkaConsumerId; 
	
	@Value("${config.kafka.group.id}")
	private String kafkaGroupId; 
	
	private List<StreamStatsConfigStream> configStreams = new ArrayList<StreamStatsConfigStream>();

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("streamstatsconfig");
	
	@PostConstruct
	private void init() { 
		String[] idents = streamIdents.split(",");
		String[] ids = streamIds.split(",");
		int i = 0;
		try {
			while(i < idents.length) { 
				StreamStatsConfigStream cs = new StreamStatsConfigStream();
				cs.setIdenttifier(idents[i]);
				cs.setId(Integer.valueOf(ids[i]));
				configStreams.add(cs);
				i++;
			}	
		} catch (RuntimeException e) {
			logger.error(marker, "Exception parsing config streams " + e.toString());
			System.exit(1);
			
		} 	
		
	}
	
	public List<StreamStatsConfigStream> configStreams() { 
		return configStreams;
	}

	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public int getDbPort() {
		return dbPort;
	}

	public void setDbPort(int dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}
	
	

	public String getKafkaBrokers() {
		return kafkaBrokers;
	}

	public String getKafkaConsumerId() {
		return kafkaConsumerId;
	}

	public String getKafkaGroupId() {
		return kafkaGroupId;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public int getDbPoolSize() {
		return dbPoolSize;
	}

	public void setDbPoolSize(int dbPoolSize) {
		this.dbPoolSize = dbPoolSize;
	}
	
	
	
	
	
	
	
}
