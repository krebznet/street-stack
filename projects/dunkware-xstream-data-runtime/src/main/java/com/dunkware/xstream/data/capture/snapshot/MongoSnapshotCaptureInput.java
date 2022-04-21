package com.dunkware.xstream.data.capture.snapshot;

import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.util.dtime.DTimeZone;

public class MongoSnapshotCaptureInput {
	
	private DKafkaByteConsumer2Spec kafkaSpec; 
	private String mongoURL; 
	private int batchSize = 200; 
	private String mongoDatabase; 
	private String mongoCollection;
	private String captureId; 
	private String stream; 
	private DTimeZone timeZone;
	private int writeQueueSizeLimit = 25000; 
	private boolean debugLogging = false; 
	
	
	public String getMongoURL() {
		return mongoURL;
	}
	public void setMongoURL(String mongoURL) {
		this.mongoURL = mongoURL;
	}
	public int getBatchSize() {
		return batchSize;
	}
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
	public String getMongoDatabase() {
		return mongoDatabase;
	}
	public void setMongoDatabase(String mongoDatabase) {
		this.mongoDatabase = mongoDatabase;
	}
	
	public String getCaptureId() {
		return captureId;
	}
	public void setCaptureId(String captureId) {
		this.captureId = captureId;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getMongoCollection() {
		return mongoCollection;
	}
	public void setMongoCollection(String mongoCollection) {
		this.mongoCollection = mongoCollection;
	}
	public DTimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
	}
	public DKafkaByteConsumer2Spec getKafkaSpec() {
		return kafkaSpec;
	}
	public void setKafkaSpec(DKafkaByteConsumer2Spec kafkaSpec) {
		this.kafkaSpec = kafkaSpec;
	}
	public int getWriteQueueSizeLimit() {
		return writeQueueSizeLimit;
	}
	public void setWriteQueueSizeLimit(int writeQueueSizeLimit) {
		this.writeQueueSizeLimit = writeQueueSizeLimit;
	}
	public boolean isDebugLogging() {
		return debugLogging;
	}
	public void setDebugLogging(boolean debugLogging) {
		this.debugLogging = debugLogging;
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
