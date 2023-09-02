package com.dunkware.trade.net.data.server.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

@Service
public class MongoProvider {

	private Logger logger = LoggerFactory.getLogger(getClass());
	

	
	@Value("${config.mongo.url}")
	private String mongoDatabase;

	private String mongoPassword; 
	
	private String mongoAuthDatabase;
	
	
	
	private MongoClient client;
	
	@PostConstruct
	public void init() { 
		try {
			 client = MongoClients.create("mongodb://root:rootpassword@localhost:27017/?authSource=admin");
			for (String db : client.listDatabaseNames()) {
				System.out.println(db);
			}
			
		} catch (Exception e) {
			logger.error("Exception connecting to mongo {}",e.toString());

		}
	}
	
	public MongoClient getClient() { 
		return client;
	}
	
	public MongoDatabase getDatabase(String database) throws Exception { 
		return client.getDatabase(database);
	}
}
