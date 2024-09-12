package com.dunkware.trade.service.stream.serverd.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {
	
	
	
	@Value("${config.warehouse.database}")
	private String warehouseDatabase; 
	
	@Value("${config.warehouse.url}")
	private String warehouseURL; 
	
	@Value("${config.core.database}")
	private String coreDatabase; 
	
	@Value("${config.core.url}")
	private String coreDatabaseURL; 
	

	@Value("${dunknet.brokers}")
	private String kafkaBrokers; 
	
	@Value("${tick.service.endpoint}")
	private String tickServiceURL;
	
	@Value("${streams.schedule.enable}")
	private boolean scheduleStreams;
	

	@Value("${config.stats.db.host}")
	private String statsDbHost;
	@Value("${config.stats.db.schema}")
	private String statsDbName;
	@Value("${config.stats.db.port}")
	private int statsDbPort;
	@Value("${config.stats.db.user}")
	private String statsDbUser;
	@Value("${config.stats.db.pass}")
	private String statsDbPass;
	@Value("${config.stats.db.pool.size}")
	private int statsDbPoolSize;
	
	
	public String getKafkaBrokers() {
		return kafkaBrokers;
	}
	
	
	public String getTickServiceURL() {
		return tickServiceURL;
	}
	public boolean isScheduleStreams() {
		return scheduleStreams;
	}


	public String getWarehouseDatabase() {
		return warehouseDatabase;
	}


	public void setWarehouseDatabase(String warehouseDatabase) {
		this.warehouseDatabase = warehouseDatabase;
	}


	public String getWarehouseURL() {
		return warehouseURL;
	}


	public void setWarehouseURL(String warehouseURL) {
		this.warehouseURL = warehouseURL;
	}


	public String getCoreDatabase() {
		return coreDatabase;
	}


	public void setCoreDatabase(String coreDatabase) {
		this.coreDatabase = coreDatabase;
	}


	public String getCoreDatabaseURL() {
		return coreDatabaseURL;
	}


	public void setCoreDatabaseURL(String coreDatabaseURL) {
		this.coreDatabaseURL = coreDatabaseURL;
	}


	public void setKafkaBrokers(String kafkaBrokers) {
		this.kafkaBrokers = kafkaBrokers;
	}


	public void setTickServiceURL(String tickServiceURL) {
		this.tickServiceURL = tickServiceURL;
	}


	public void setScheduleStreams(boolean scheduleStreams) {
		this.scheduleStreams = scheduleStreams;
	}


	public String getStatsDbHost() {
		return statsDbHost;
	}


	public String getStatsDbName() {
		return statsDbName;
	}

	

	public int getStatsDbPort() {
		return statsDbPort;
	}


	public String getStatsDbUser() {
		return statsDbUser;
	}


	public String getStatsDbPass() {
		return statsDbPass;
	}


	public int getStatsDbPoolSize() {
		return statsDbPoolSize;
	}
	
	
	

	
	
	
	
	

}
