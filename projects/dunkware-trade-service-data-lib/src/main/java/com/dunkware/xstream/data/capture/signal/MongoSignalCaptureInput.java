package com.dunkware.xstream.data.capture.signal;

import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2;
import com.dunkware.common.util.dtime.DTimeZone;

public class MongoSignalCaptureInput {
	
	private DKafkaConsumerSpec2 kafkaSpec; 
	private String mongoURL; 
	private String mongoDatabase; 
	private String mongoCollection;
	private String captureId; 
	private String stream; 
	private DTimeZone timeZone;
	private int signalQueueSizeLimit = 10000; 
	private boolean debugLogging = false;
	
	public DKafkaConsumerSpec2 getKafkaSpec() {
		return kafkaSpec;
	}
	public void setKafkaSpec(DKafkaConsumerSpec2 kafkaSpec) {
		this.kafkaSpec = kafkaSpec;
	}
	public String getMongoURL() {
		return mongoURL;
	}
	public void setMongoURL(String mongoURL) {
		this.mongoURL = mongoURL;
	}
	
	public String getMongoDatabase() {
		return mongoDatabase;
	}
	public void setMongoDatabase(String mongoDatabase) {
		this.mongoDatabase = mongoDatabase;
	}
	public String getMongoCollection() {
		return mongoCollection;
	}
	public void setMongoCollection(String mongoCollection) {
		this.mongoCollection = mongoCollection;
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
	public DTimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
	}
	
	
	public int getSignalQueueSizeLimit() {
		return signalQueueSizeLimit;
	}
	public void setSignalQueueSizeLimit(int signalQueueSizeLimit) {
		this.signalQueueSizeLimit = signalQueueSizeLimit;
	}
	public boolean isDebugLogging() {
		return debugLogging;
	}
	public void setDebugLogging(boolean debugLogging) {
		this.debugLogging = debugLogging;
	} 
	
	

}
