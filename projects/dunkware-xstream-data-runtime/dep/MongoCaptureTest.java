package com.dunkware.xstream.data.capture;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.xstream.data.model.capture.CaptureServiceConfig;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoCaptureTest {
	
	
	public static void main(String[] args) {
		try {
			ConnectionString connectionString = new ConnectionString("mongodb+srv://root:st@rt_N0w@genesis.2cmut.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
			MongoClientSettings settings = MongoClientSettings.builder()
			        .applyConnectionString(connectionString)
			        .serverApi(ServerApi.builder()
			            .version(ServerApiVersion.V1)
			            .build())
			        .build();
			MongoClient mongoClient = MongoClients.create(settings);
			MongoDatabase database = mongoClient.getDatabase("alpha");
			
				mongoClient.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public static void fuck(String[] args) {
		MongoCaptureRunner runner = new MongoCaptureRunner();
		CaptureServiceConfig c = new CaptureServiceConfig();
		c.setKafkaBrokers("172.16.16.55:31090");
		c.setKafkaClientId("TestCapture1");
		c.setKafkaGroupId("TestCaptureGroup");
		c.setKafkaTopics("us_equity_2");
		c.setMongoDatabase("Alpha");
		//c.setMongoURL("mongodb://root:password@172.16.16.55:32700");
		//c.setMongoURL("mongodb://root:password@172.16.16.55:32700,172.16.16.55:32701/?replicaset=rs0");
		c.setMongoURL("mongodb://root:password@172.16.16.55:32700");
		//ac.setMongoURL("mongodb://root:password@172.16.16.55:32700/?replicaSet=rs0");
	//	c.setMongoURL("mongodb://root:password@172.16.16.55:32700,root:password@172.16.16.555:32701/?replicaSet=rs0");
		c.setTimeZone(DTimeZone.NewYork);
		c.setSnapshotBucketSize(60);
		c.setSignalCollection("poop_singnal");
		c.setSnapshotCollection("poop_snapshot");
		
//mongodb://root@172.16.16.55:32700/?serverSelectionTimeoutMS=5000&connectTimeoutMS=10000&authSource=admin&authMechanism=SCRAM-SHA-
		try {
			runner.start(c);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		;
	
	}
		
}