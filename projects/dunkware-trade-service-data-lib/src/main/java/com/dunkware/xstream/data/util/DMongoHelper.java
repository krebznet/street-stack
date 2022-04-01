package com.dunkware.xstream.data.util;

import com.mongodb.client.MongoDatabase;

public class DMongoHelper {
	
	
	public static boolean collectionExists(MongoDatabase db, String collection) { 
		for (String name : db.listCollectionNames()) {
			if(name.equals(collection)) { 
				return true;
			}
		}
		return false; 
	}
	
	

}
