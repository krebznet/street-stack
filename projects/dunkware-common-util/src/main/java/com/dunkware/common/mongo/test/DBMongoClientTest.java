package com.dunkware.common.mongo.test;

import java.util.List;

import com.dunkware.common.mongo.DMongoClient;
import com.dunkware.common.mongo.DMongoDatabase;
import com.mongodb.client.model.TimeSeriesGranularity;

public class DBMongoClientTest {

	public static void main(String[] args) {
		try {
			DMongoClient client = DMongoClient.connect("mongodb://192.168.23.100:27017");
			DMongoDatabase db = client.getDatabase("street");
			String collectionName = "snapshot_us_equity_220513"; 
			db.createTimeSerriesCollection(collectionName, "time", "vars", TimeSeriesGranularity.SECONDS);
			List<String> collections = db.collectionNames();
			for (String string : collections) {
				System.out.println(string);
			}
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
