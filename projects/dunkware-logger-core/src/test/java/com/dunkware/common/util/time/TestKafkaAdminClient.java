package com.dunkware.common.util.time;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.AlterConfigsResult;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.apache.kafka.clients.admin.CreateTopicsOptions;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.TopicConfig;
import org.junit.After;
import org.junit.Test;

public class TestKafkaAdminClient {

	private AdminClient client = null;

	private boolean setup = false;
	
	
	public void setup() {
		Map<String, Object> conf = new HashMap<>();
		conf.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9091");
		conf.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "5000");
		try {
			
			client = AdminClient.create(conf);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		

	}

	@After
	public void teardown() {
		client.close();
	}

	@Test
	public void testCreateTopic() {
		if(!setup) { 
			setup = true;
			setup();
		}
		int partitions = 8;
		short replicationFactor = 2;
		try {
			KafkaFuture<Void> future = client
					.createTopics(Collections.singleton(new NewTopic("tweet", partitions, replicationFactor)),
							new CreateTopicsOptions().timeoutMs(10000))
					.all();
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




}