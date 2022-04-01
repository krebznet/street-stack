package com.dunkware.xstream.data.capture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.xstream.util.XStreamEventConsumer;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoEventCapture2 extends Thread {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private MongoEventCaptureConfig config;
	private XStreamEventConsumer eventConsumer;
	private BlockingQueue<GStreamEvent> eventQueue;

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> signalCollection;
	private MongoCollection<Document> snapshotCollection;


	private AtomicInteger consumeCount = new AtomicInteger(0);
	
	private KafkaConsumer<String, byte[]> kafkaConsumer;
	

	public static MongoEventCapture2 newInstance(MongoEventCaptureConfig config) {
		return new MongoEventCapture2(config);
	}

	private MongoEventCapture2(MongoEventCaptureConfig config) {
		this.config = config;
	}

	public void run() {

		try {

			mongoClient = MongoClients.create(config.getMongoURL());
			System.out.println("created mongo client");

			// MongoDatabase database = mongoClient.getDatabase(config.getMongoDatabase());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception connecting to mongo db " + config.getMongoURL() + e.toString());
			return;
		}

		try {
			mongoDatabase = mongoClient.getDatabase(config.getMongoDatabase());
			snapshotCollection = mongoDatabase.getCollection(config.getMongoSnapshotCollection());
			signalCollection = mongoDatabase.getCollection(config.getMongoSignalCollection());
		} catch (Exception e) {
			logger.error("Exceptoon getting mongo database " + e.toString());
			e.printStackTrace();
			return;
			// TODO: handle exception
		}

		try {
			eventConsumer = new XStreamEventConsumer();
			String[] topics = new String[1];
			topics[0] = config.getKafkaTopics();
			eventQueue = new LinkedBlockingQueue<GStreamEvent>();
			eventConsumer.start(config.getKafkaBrokers(), topics, "testmongoclient", "testgroupid", eventQueue);
		} catch (Exception e) {
			logger.error("Exception starting stream event consumer " + e.toString());
			return;
		}


	}

	public void dispose() {
		mongoClient.close();
		
		eventConsumer.dipose();
	}
}


