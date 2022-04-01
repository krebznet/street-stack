package com.dunkware.common.kafka.tests;

import com.dunkware.common.kafka.admin.DKafkaAdmin;
import com.dunkware.common.kafka.properties.DKafkaProperties;
import com.dunkware.common.util.properties.DProperties;
import com.dunkware.common.util.properties.DPropertiesBuilder;

public class DKafkaAdminTest {

	public static void main(String[] args) {
		DProperties props = DPropertiesBuilder.newBuilder()
				.addProperty("topics", "MyDestination")
		.addProperty(DKafkaProperties.BOOTSTRAP_SERVERS, "192.168.5.8:9093").build();
		try {
			DKafkaAdmin admin = DKafkaAdmin.newInstance(props);
			
			//
			//Collection<TopicListing> topics = admin.getTopics();
			//for (TopicListing topicListing : topics) {
			//	System.out.println(topicListing.name());
			//}
			
			admin.deleteTopicWildcard("dunktrade_stream_capture");
			
			
		} catch (Exception e) {
			
			//System.err.println(e.toString());
			//e.printStackTrace();
		}
	}
}
