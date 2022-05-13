package com.dunkware.common.mongo;

import org.bson.Document;

import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DMongoClient {

	
	public static DMongoClient connect(String url) throws DMongoClientException { 
		return new DMongoClient(url);
	}
	
	private MongoClient mongoClient;
	
	private DMongoClient(String url) throws DMongoClientException { 
		try {
			mongoClient = MongoClients.create(url);
		} catch (Exception e) {
			throw new DMongoClientException("Exception connecting " + e.toString(),e);
		}
		
		
	}
	
	public MongoClient get() { 
		return mongoClient;
	}
	
	public void close() { 
		mongoClient.close();
	}
	
	public DMongoDatabase getDatabase(String name) throws Exception { 
		MongoDatabase db =  mongoClient.getDatabase(name);
		return new DMongoDatabase(db, this);
	}
	
	
	
	
	
	
}
