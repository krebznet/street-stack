package com.dunkware.common.mongo;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class DMongoCollection {

	private MongoCollection<Document> collection; 
	private DMongoDatabase database; 
	
	public DMongoCollection(MongoCollection<Document> collection, DMongoDatabase db) { 
		this.collection = collection;
		this.database = db; 
	}
	
	public MongoCollection<Document> get() { 
		return collection;
	}
	
	public DMongoDatabase getDatabase() { 
		return database;
	}
}
