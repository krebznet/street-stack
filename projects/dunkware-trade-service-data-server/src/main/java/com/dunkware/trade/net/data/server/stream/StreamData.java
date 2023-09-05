package com.dunkware.trade.net.data.server.stream;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.trade.net.data.server.config.MongoProvider;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.xstream.model.signal.StreamEntitySignal;
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

public class StreamData implements IStreamDataController {

	@Autowired
	private MongoProvider mongoProvider;

	
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> signalCollection;

	private StreamDescriptor descriptor;

	public void init(StreamDescriptor descriptor) {
		try {
			this.descriptor = descriptor;
			String collectionname = descriptor.getIdentifier() + "_data_signal";
			mongoDatabase = mongoProvider.getDatabase("dunkstreet");
			for (String col : mongoDatabase.listCollectionNames()) {
				if (col.equals(collectionname)) {
					this.signalCollection = mongoDatabase.getCollection(collectionname);
				}
			}
			if (signalCollection == null) {
				mongoDatabase.createCollection(collectionname);
				signalCollection = mongoDatabase.getCollection(collectionname);
				signalCollection.createIndex(Indexes.ascending("date"));
				signalCollection.createIndex(Indexes.ascending("entity"));
				signalCollection.createIndex(Indexes.ascending("type"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Override
	public StreamDescriptor getDescriptor() {
		return descriptor;
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
		Document document = new Document();
		document.append("timestamp", signal.getDateTime());
		document.append("type", signal.getType());
		document.append("entity", signal.getEntity());
		Document varDoc = new Document();
		for (Integer varKey : signal.getVars().keySet()) {
			varDoc.append(String.valueOf(varKey), signal.getVars().get(varKey));
		}
		document.append("vars", varDoc);
		try {
			signalCollection.insertOne(document);
		} catch (Exception e) {
			throw e;
		}
	}

}
