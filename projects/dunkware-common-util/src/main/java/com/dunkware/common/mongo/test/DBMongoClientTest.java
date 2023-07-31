package com.dunkware.common.mongo.test;

import java.time.LocalDateTime;

import org.bson.Document;

import com.dunkware.common.mongo.DMongoClient;
import com.dunkware.common.mongo.DMongoCollection;
import com.dunkware.common.mongo.DMongoDatabase;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;

public class DBMongoClientTest {

	public static void main(String[] args) {
		try {
			
			
			
			
			DMongoClient client = DMongoClient.connect("mongodb://data2.dunkware.net:27017");
			DMongoDatabase db = client.getDatabase("dunkstreet");
			String collectionName = "stream_stats_entity_session";
			DMongoCollection collection = db.getCollection(collectionName);
			 BasicDBObject query = new BasicDBObject();
		        query.put("ident", "AAPL");
		        DStopWatch watch = DStopWatch.create();
				watch.start();
			FindIterable<Document> docs = collection.get().find(query);
			int count = 0; 
			
			System.out.println(LocalDateTime.now().toString());
			for (Document document : docs) {
				if(count == 1) { 
					//System.out.println(document.toJson());
				}
				//System.out.println(document.toJson());
				System.out.println(count);
				count++;
			}
			watch.stop();
			
			System.out.println(watch.getCompletedSeconds());
			System.out.println(collection.get().countDocuments());
			
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
