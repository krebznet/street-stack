package com.dunkware.common.mongo.test;

import java.time.LocalDateTime;

import org.bson.Document;

import com.dunkware.common.mongo.DMongoClient;
import com.dunkware.common.mongo.DMongoCollection;
import com.dunkware.common.mongo.DMongoDatabase;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.mongodb.BasicDBObject;
import com.mongodb.CursorType;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

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
		//	FindIterable<Document> docs = 
			//		collection.get().find().batchSize(250).allowDiskUse(true).cursorType(CursorType.NonTailable);
			
			
			MongoCursor<Document> cursor = collection.get().find().batchSize(500).cursorType(CursorType.NonTailable).cursor();
			DStopWatch w2 = DStopWatch.create();
			System.out.println(LocalDateTime.now().toString());
			int totalCount  = 0;
			int count = 0;
			w2.start();
			while (cursor.hasNext()) {
			   Document obj = cursor.next();
			   if(count == 1000) { 
					w2.stop();
					System.out.println(w2.getCompletedSeconds());
					System.out.println(totalCount);
					count = 0;
					w2.start();
					//System.out.println(document.toJson());
				}
				//System.out.println(document.toJson());
				totalCount++;
				
				count++;
			   
			}
			
			
			
			System.out.println(watch.getCompletedSeconds());
			System.out.println(collection.get().countDocuments());
			
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
