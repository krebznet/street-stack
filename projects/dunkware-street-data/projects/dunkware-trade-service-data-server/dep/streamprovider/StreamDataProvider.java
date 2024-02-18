package com.dunkware.trade.net.data.server.stream.streamprovider;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintChannelClient;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintChannelRequest;
import com.dunkware.trade.net.data.server.stream.converters.MongoStreamConverter;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.xstream.model.signal.StreamEntitySignal;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;

/**
 * A Stream data service is in scope of a single stream in the cluster. it only works with one stream
 * it setsup the snapshot and entity consumers it will provide other data services. 
 * 
 * @author duncankrebs
 *
 */

public class StreamDataProvider   {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamDataProvider");


	private MongoClient coreClient; 
	
	private MongoDatabase coreDatabase;
	private MongoCollection<Document> signalCollection;

	private StreamDescriptor descriptor;
	
	private StreamBlueprintChannelClient blueprint;
	
	@Autowired
	private DunkNet dunkNet; 

	private boolean initialized = false; 
	
	public void init(StreamDescriptor descriptor) {
		this.descriptor = descriptor;
		try {
			coreClient = MongoClients.create(descriptor.getCoreDatabaseURL());
		} catch (Exception e) {
			logger.error(marker, "Exception creating to core mongo database on URL {} exception {}",descriptor.getCoreDatabaseURL(),e.toString());
		}
		try {
			this.descriptor = descriptor;
			String collectionname = descriptor.getSignalMongoCollection();
			coreDatabase  = coreClient.getDatabase(descriptor.getCoreDatabase()); 
					
			for (String col : coreDatabase.listCollectionNames()) {
				if (col.equals(collectionname)) {
					this.signalCollection = coreDatabase.getCollection(collectionname);
				}
			}
			if (signalCollection == null) {
				coreDatabase.createCollection(collectionname);
				signalCollection = coreDatabase.getCollection(collectionname);
				signalCollection.createIndex(Indexes.ascending("id", "ident","dateTime"));
				
			}
			 
		} catch (Exception e) {
			logger.error(marker, "Excpption init Stream Data Provider " + e.toString());
		}
		
		// 
		StreamBlueprintChannelRequest req = new StreamBlueprintChannelRequest();;
		req.setStreamIdentifier(descriptor.getIdentifier());
		try {
			DunkNetChannelRequest channelRequest = dunkNet.channel(req);
			DunkNetChannel channel = channelRequest.get(35, TimeUnit.SECONDS);
			this.blueprint = new  StreamBlueprintChannelClient();
			channel.setHandler(blueprint);
			
		} catch (Exception e) {
			logger.error(marker, "Exception creating stream blueprint channel " + e.toString());
			return;
		}
		initialized = true;
		
		
	}
	
	public StreamBlueprintChannelClient getBlueprint() { 
		return blueprint;
	}
	
	public StreamDescriptor getDescriptor() { 
		return descriptor;
	}
	
	public MongoCollection<Document> getSignalCollection() { 
		return signalCollection;
	}


	public List<StreamEntitySignal> getSignals() { 
		try {
			MongoCursor<Document> documents = signalCollection.find().batchSize(5).cursor();
			while(documents.hasNext()) { 
				Document doc = documents.next();
				// this is the hard part converting doc vars to map 
				
				Object date = doc.getDate("date");
				// convert a Date to a LocalDate? 
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public void insertSignal(StreamEntitySignal signal) throws Exception { 
		Document document = MongoStreamConverter.signalToDocument(signal);
		try {
			signalCollection.insertOne(document);
		} catch (Exception e) {
			throw e;
		}
	}

}
