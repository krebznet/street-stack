package com.dunkware.common.kafka.admin;

import java.util.concurrent.TimeUnit;

import org.apache.kafka.common.config.TopicConfig;

import com.dunkware.common.kafka.admin.model.DKafkaDeleteTopicResult;
import com.dunkware.common.kafka.admin.model.DKafkaDeleteTopicResults;
import com.dunkware.common.kafka.admin.model.DKafkaNewTopic;
import com.dunkware.common.kafka.admin.model.DKafkaNewTopicResult;

public class DKafkaAdmin2Test {

	
	public static void main(String[] args) {
		try {
			DKafkaAdminClient admin = DKafkaAdminClient.newInstance("172.16.16.55:30100");
			DKafkaNewTopic newTopic = 
			DKafkaNewTopic.builder().name("dunkware_pjkijng_me_babjy").cleanupPolicy(TopicConfig.CLEANUP_POLICY_DELETE).
			replicas((short)1).retentionSize(5).retentionTime(60, TimeUnit.SECONDS).paritions(1).build();
			DKafkaNewTopicResult result = admin.createTopic(newTopic);
			if(result.isException()) { 
				System.out.println("New Topic Creation failed " + result.getCause());;
			} 
			if(result.isTimeout()) { 
				System.out.println("Result Timeout");
			}
			if(result.isException() == false && result.isTimeout() == false) { 
				System.out.println("new topic " + newTopic.getTopic().name() + "created in " + result.getTime() + " seconds");
			}
			admin.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
			// TODO: handle exception
		}
		
		
	}
	/*
	 * public static void main3(String[] args) { try {
	 * 
	 * if(admin.topicExists("hello")) { System.out.println("topic hello exists"); }
	 * else { System.out.println("topic hello does not exist"); }
	 * 
	 * 
	 * try { DKafkaDeleteTopicResults results = admin.deleteTopics(false);
	 * if(results.isTimeout()) { System.out.println("Timeout Returned " +
	 * results.getTime() + " seconds"); } System.out.println("Deleted Topics...");
	 * for (DKafkaDeleteTopicResult result : results.getDeletes()) {
	 * System.out.println(result.getTopic()); }
	 * System.out.println("Exception Deletes..."); for (DKafkaDeleteTopicResult
	 * result : results.getExceptions()) { System.out.println(result.getTopic() +
	 * " " + result.getCause());
	 * 
	 * } admin.close(); } catch (Exception e) { e.printStackTrace(); }
	 * admin.close(); } catch (Exception e) { e.printStackTrace(); // TODO: handle
	 * exception } }
	 */
	
	public DKafkaNewTopicResult createTopic(DKafkaNewTopic topic, DKafkaAdminClient admin) { 
		return admin.createTopic(topic);
	}
}
