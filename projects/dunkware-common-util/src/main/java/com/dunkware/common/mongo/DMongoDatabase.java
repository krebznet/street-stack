package com.dunkware.common.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.TimeSeriesGranularity;
import com.mongodb.client.model.TimeSeriesOptions;

public class DMongoDatabase {

	private MongoDatabase database;
	private DMongoClient client;

	public DMongoDatabase(MongoDatabase mongoDatabase, DMongoClient client) {
		this.database = mongoDatabase;
		this.client = client;

	}

	public MongoDatabase get() {
		return database;
	}

	public List<String> collectionNames() {
		List<String> results = new ArrayList<String>();
		MongoIterable<String> names = database.listCollectionNames();
		for (String string : names) {
			results.add(string);
		}
		return results;
	}

	public boolean collectionExists(String name) {
		List<String> names = collectionNames();
		for (String string : names) {
			if (string.equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public DMongoCollection getCollection(String name) throws Exception {
		MongoCollection<Document> collection = database.getCollection(name);
		return new DMongoCollection(collection, this);
	}

	public void createTimeSerriesCollection(String name, String timeField, String metaField,
			TimeSeriesGranularity granularity) {
		TimeSeriesOptions tsOptions = new TimeSeriesOptions(timeField);
		tsOptions.granularity(granularity);
		tsOptions.metaField(metaField);

		CreateCollectionOptions colOptions = new CreateCollectionOptions().timeSeriesOptions(tsOptions);
		database.createCollection(name, colOptions);

	}

}
