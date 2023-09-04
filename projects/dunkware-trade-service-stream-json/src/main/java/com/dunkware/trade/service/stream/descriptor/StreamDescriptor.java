package com.dunkware.trade.service.stream.descriptor;

import java.util.List;

import com.dunkware.common.util.dtime.DTimeZone;

public class StreamDescriptor {

	private int id; 
	private String identifier; 
	private double version; 
	private String kafkaBrokers;
	private String signalTopic;
	private String snapshotTopic; 
	private int entityCount; 
	private DTimeZone timeZone; 
	private String mongoDatabase; 
	private String mongoURL; 
	private String snapshotDatabase; 
	private String snapshotURL;
	
	
	
	private List<StreamSignalDescriptor> entityVars;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIdentifier() {
		return identifier;
	}


	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}


	public double getVersion() {
		return version;
	}


	public void setVersion(double version) {
		this.version = version;
	}


	public String getKafkaBrokers() {
		return kafkaBrokers;
	}


	public void setKafkaBrokers(String kafkaBrokers) {
		this.kafkaBrokers = kafkaBrokers;
	}


	public String getSignalTopic() {
		return signalTopic;
	}


	public void setSignalTopic(String signalTopic) {
		this.signalTopic = signalTopic;
	}


	public String getSnapshotTopic() {
		return snapshotTopic;
	}


	public void setSnapshotTopic(String snapshotTopic) {
		this.snapshotTopic = snapshotTopic;
	}


	public int getEntityCount() {
		return entityCount;
	}


	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}


	public List<StreamSignalDescriptor> getEntityVars() {
		return entityVars;
	}


	public void setEntityVars(List<StreamSignalDescriptor> entityVars) {
		this.entityVars = entityVars;
	}


	public DTimeZone getTimeZone() {
		return timeZone;
	}


	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
	}


	public String getMongoDatabase() {
		return mongoDatabase;
	}


	public void setMongoDatabase(String mongoDatabase) {
		this.mongoDatabase = mongoDatabase;
	}


	public String getMongoURL() {
		return mongoURL;
	}


	public void setMongoURL(String mongoURL) {
		this.mongoURL = mongoURL;
	}


	public String getSnapshotDatabase() {
		return snapshotDatabase;
	}


	public void setSnapshotDatabase(String snapshotDatabase) {
		this.snapshotDatabase = snapshotDatabase;
	}


	public String getSnapshotURL() {
		return snapshotURL;
	}


	public void setSnapshotURL(String snapshotURL) {
		this.snapshotURL = snapshotURL;
	}
	
	
	
	


	

	

	
	
	
	
}
