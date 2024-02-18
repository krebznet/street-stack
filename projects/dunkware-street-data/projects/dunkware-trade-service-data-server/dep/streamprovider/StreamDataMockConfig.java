package com.dunkware.trade.net.data.server.stream.streamprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("Mock")
public class StreamDataMockConfig {

	@Value("${mock.warehouse.database}")
	private String warehouseDatabase; 
	
	@Value("${mock.warehouse.url}")
	private String warehouseURL; 
	
	@Value("${mock.core.database}")
	private String coreDatabase; 
	
	@Value("${mock.core.url}")
	private String coreDatabaseURL; 
	

	@Value("${mock.kafka.brokers}")
	private String kafkaBrokers; 

	@Value("${mock.stats.db.host}")
	private String statsDbHost;
	@Value("${mock.stats.db.schema}")
	private String statsDbName;
	@Value("${mock.stats.db.port}")
	private int statsDbPort;
	@Value("${mock.stats.db.user}")
	private String statsDbUser;
	@Value("${mock.stats.db.pass}")
	private String statsDbPass;
	@Value("${mock.stats.db.pool.size}")
	private int statsDbPoolSize;
	
	@Value("${mock.stream.id}")
	private int streamId; 
	
	@Value("${mock.stream.identifier}")
	private String streamIdentifier;

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

	public String getKafkaBrokers() {
		return kafkaBrokers;
	}

	public void setKafkaBrokers(String kafkaBrokers) {
		this.kafkaBrokers = kafkaBrokers;
	}

	public String getStatsDbHost() {
		return statsDbHost;
	}

	public void setStatsDbHost(String statsDbHost) {
		this.statsDbHost = statsDbHost;
	}

	public String getStatsDbName() {
		return statsDbName;
	}

	public void setStatsDbName(String statsDbName) {
		this.statsDbName = statsDbName;
	}

	public int getStatsDbPort() {
		return statsDbPort;
	}

	public void setStatsDbPort(int statsDbPort) {
		this.statsDbPort = statsDbPort;
	}

	public String getStatsDbUser() {
		return statsDbUser;
	}

	public void setStatsDbUser(String statsDbUser) {
		this.statsDbUser = statsDbUser;
	}

	public String getStatsDbPass() {
		return statsDbPass;
	}

	public void setStatsDbPass(String statsDbPass) {
		this.statsDbPass = statsDbPass;
	}

	public int getStatsDbPoolSize() {
		return statsDbPoolSize;
	}

	public void setStatsDbPoolSize(int statsDbPoolSize) {
		this.statsDbPoolSize = statsDbPoolSize;
	}

	public int getStreamId() {
		return streamId;
	}

	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}

	public String getStreamIdentifier() {
		return streamIdentifier;
	}

	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	} 
	
	
	
}
