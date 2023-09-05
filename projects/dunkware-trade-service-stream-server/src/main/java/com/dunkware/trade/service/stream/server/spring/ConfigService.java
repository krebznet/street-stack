package com.dunkware.trade.service.stream.server.spring;

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
	

	@Value("${kafka.brokers}")
	private String kafkaBrokers; 
	
	@Value("${tick.service.endpoint}")
	private String tickServiceURL;
	
	@Value("${streams.schedule.enable}")
	private boolean scheduleStreams;
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

	
	
	
	
	

}
