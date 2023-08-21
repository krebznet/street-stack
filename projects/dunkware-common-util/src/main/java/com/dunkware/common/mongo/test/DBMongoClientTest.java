package com.dunkware.common.mongo.test;

import static com.mongodb.client.model.Filters.and;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.dunkware.common.mongo.DMongoClient;
import com.dunkware.common.mongo.DMongoCollection;
import com.dunkware.common.mongo.DMongoDatabase;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.mongodb.BasicDBObject;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

public class DBMongoClientTest {

	private static volatile int totalCount = 0;
	private static AtomicInteger secondCounter = new AtomicInteger(0);
	private static AtomicInteger totalCounter = new AtomicInteger(0);
	public static void main(String[] args) {
		try {
			for (String string : args) {
				System.out.println(string);
			}
			int batchsize = 1000;
			if(args.length > 0) { 
				batchsize = Integer.valueOf(args[0]);
			}
			
			
			DMongoClient client = DMongoClient.connect("mongodb://data2.dunkware.net:27017");
			DMongoDatabase db = client.getDatabase("dunkstreet");
			String collectionName = "stream_stats_entity_session";
			System.out.println("start disting");
			DMongoCollection collection = db.getCollection(collectionName);
			DStopWatch watch = DStopWatch.create();
			watch.start();
			DistinctIterable<String> results = collection.get().distinct("ident", String.class);
		
			//Bson filter = and(gt("date", new Date().getTime());
			//MongoCursor<Document> result3 = collection.get().find(filter).batchSize(499).iterator();
			
			if(true)return;
			for (String string : results) {
				System.out.println(string);
				 BasicDBObject query = new BasicDBObject();
				 query.put("stream", "us_equity");
			        query.put("ident", string);
			        
			        
					
					FindIterable<Document> docs = 	collection.get().find(query).batchSize(100);
					int count = 0;
					for (Document document : docs) {
						
						count++;
					}
					System.out.println(string);
				
			}
			watch.stop();
			System.out.println("bam " + watch.getCompletedSeconds());
			System.out.println("end distinct");
			if(1 == 1) { 
				return;
			}
			DStopWatch over = DStopWatch.create();
			over.start();
			for (String string : Arrays.asList("BAC","JPM","SPY","GRUB","BAC","SCHW","BAC","JPM","SPY","GRUB","BAC","SCHW")) {
				 BasicDBObject query = new BasicDBObject();
				 query.put("stream", "us_equity");
			        query.put("ident", string);
			        
			       
					watch.start();
					
					FindIterable<Document> docs = 	collection.get().find(query).batchSize(100);
					int count = 0;
					for (Document document : docs) {
						count++;
					}
					watch.stop();
					System.out.println(count);
					System.out.println(string);
					System.out.println(watch.getCompletedSeconds());	
			}
			
			
			over.stop();
			System.out.println(over.getCompletedSeconds());
				client.close();
			if(true) return;
			MongoCursor<Document> cursor = collection.get().find().batchSize(batchsize).cursor();
			
			DStopWatch w2 = DStopWatch.create();
			System.out.println(LocalDateTime.now().toString());
		//count = 0;
			w2.start();
			
			while (cursor.hasNext()) {
			   Document obj = cursor.next();
			   String hmm = obj.toJson();
			   System.out.println(hmm);
			   
			   totalCounter.incrementAndGet();
			   secondCounter.incrementAndGet();

			  // if(count == 1000) { 
				//	w2.stop();
				//	System.out.println(w2.getCompletedSeconds());
				//	System.out.println(totalCount);
				//	count = 0;
					//w2.start();
					//System.out.println(document.toJson());
				//}
				//System.out.println(document.toJson());
				totalCount++;
				
			//	count++;
			   
			}
			
			
			
			//System.out.println(watch.getCompletedSeconds());
			//System.out.println(collection.get().countDocuments());
			w2.stop();
			System.out.println(w2.getCompletedSeconds());
			client.close();
		//	p.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	
}
