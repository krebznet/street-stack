package com.dunkware.trade.service.stream.descriptor;

import com.dunkware.common.util.dtime.DTimeZone;

public class StreamDescriptor {

	private long id; 
	private String identifier; 
	private String kafkaBrokers;
	private DTimeZone timeZone; 
	private String warehouseDatabase; 
	private String warehouseURL; 
	private String coreDatabase; 
	private String coreDatabaseURL; 
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getKafkaBrokers() {
		return kafkaBrokers;
	}

	public void setKafkaBrokers(String kafkaBrokers) {
		this.kafkaBrokers = kafkaBrokers;
	}

	public DTimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
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
	
	public String getSnapshotEventTopic() { 
		return "stream_" + getIdentifier() + "_feed_snapshots";
	}
	
	public String getSnapshotMongoCollection() { 
		return "stream_" + getIdentifier() + "_snapshot_second";
	}
	
	public String getSignalEventTopic() { 
		return "stream_" + getIdentifier() + "_feed_signals";
	}
	
	public String getSignalMongoCollection() { 
		return "stream_" + getIdentifier() + "_signal_repo";
	}
	
	
	
}
