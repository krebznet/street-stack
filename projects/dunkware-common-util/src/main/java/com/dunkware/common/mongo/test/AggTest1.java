package com.dunkware.common.mongo.test;

import static com.mongodb.client.model.Accumulators.max;
import static com.mongodb.client.model.Accumulators.min;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.eq;

import java.time.LocalTime;
//import static com.mongodb.client.Pr
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.dunkware.common.mongo.DMongoClient;
import com.dunkware.common.mongo.DMongoCollection;
import com.dunkware.common.mongo.DMongoDatabase;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;

public class AggTest1 {

	public static void main(String[] args) {
		new AggTest1();
	}
	
	private DMongoClient client; 
	private DMongoDatabase database; 
	private DMongoCollection collection;
	public AggTest1() { 
		try {
			 client = DMongoClient.connect("mongodb://data2.dunkware.net:27017");
			 database = client.getDatabase("street");
			 collection = database.getCollection("snapshot_us_equity_220601");
			 test1();
			 ///coundDocumentsByIdent();
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	private void test1() { 
			try {
				
				
				Bson match = match(eq("ident","DIS"));
				
				Bson group = group("$ident",max("max", "$vars"), min("min","$vars"));
			
				DStopWatch sw = DStopWatch.create();
				sw.start();
				System.out.println("starting aggregation " + DunkTime.toStringTimeStamp(LocalTime.now()));
				List<Document> results = collection.get().
						aggregate(Arrays.asList(match,group)).into(new ArrayList<>());		
				sw.stop();
				System.out.println("Agg Time " + sw.getCompletedSeconds());
				System.out.println("completed aggregation " + DunkTime.toStringTimeStamp(LocalTime.now()));
				for (Document document : results) {
					System.out.println(document.toJson());
				}
				 
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
	
			
	}
	
	private void coundDocumentsByIdent() { 
		try {
			//Bson match = match(eq("ident","CVAC"));
			Bson filter = eq("ident", "CVAC");
			long count = collection.get().countDocuments(filter);
			System.out.println("There are " + count + " documents with ident CVAC");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		
}

	
}
