package com.dunkware.trade.service.data.service.tests;

import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoTest {
	
	public static void main(String[] args) {
		try {
			MongoClient mongoClient = MongoClients.create("mongodb://192.168.23.100:27017");
			WriteConcern wc = new WriteConcern(0).withJournal(false);
			MongoDatabase database = mongoClient.getDatabase("test");
			for (String col : database.listCollectionNames()) {
				System.out.println(col);
			}
			mongoClient.close();
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
