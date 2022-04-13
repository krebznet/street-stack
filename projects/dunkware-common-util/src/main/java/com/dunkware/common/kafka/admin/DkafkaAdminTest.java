package com.dunkware.common.kafka.admin;

import java.util.Collection;

import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.common.internals.Topic;

public class DkafkaAdminTest {
	
	public static void main(String[] args) {
		DKafkaAdmin admin = null; 
		try {
		admin = DKafkaAdmin.newInstance("localhost:9091,localhost:9092,localhost:9032");	
		} catch (Exception e) {
			System.err.println("exception creating new admin instance ");
			System.exit(-1);
			// TODO: handle exception
		}
		try {
			Collection<TopicListing> topics = admin.getTopics();
			for (TopicListing topicListing : topics) {
				System.out.println(topicListing.name());
			}
			admin.close();
		} catch (Exception e) {
			System.err.println("exception getting topics"); 
			e.printStackTrace();
			System.exit(-1);
			// TODO: handle exception
		}
		
		
	}

}
