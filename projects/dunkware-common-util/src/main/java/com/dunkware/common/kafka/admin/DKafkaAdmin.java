package com.dunkware.common.kafka.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.common.KafkaFuture;

import com.dunkware.common.kafka.DKafkaException;
import com.dunkware.common.kafka.properties.DKafkaProperties;
import com.dunkware.common.kafka.properties.DKafkaPropertiesValidator;
import com.dunkware.common.util.properties.DProperties;
import com.dunkware.common.util.properties.DPropertiesBuilder;
import com.dunkware.common.util.properties.DPropertiesException;

public class DKafkaAdmin {
	
	public static DKafkaAdmin newInstance(String brokers) throws DKafkaException,DPropertiesException { 
		return newInstance(DPropertiesBuilder.newBuilder().addProperty(DKafkaProperties.BOOTSTRAP_SERVERS, brokers).build());
	}
	
	public static DKafkaAdmin newInstance(String brokers, String zookeeper) throws DKafkaException,DPropertiesException { 
		return newInstance(DPropertiesBuilder.newBuilder().
				addProperty("zookeeper.connect", zookeeper).
				addProperty(DKafkaProperties.BOOTSTRAP_SERVERS, brokers).build());
	}
	
	public static DKafkaAdmin newInstance(DProperties props) throws DKafkaException,DPropertiesException {
		props.validate(DKafkaPropertiesValidator.newInstance());	
		return new DKafkaAdmin(props);
		
	}
	
	private DProperties props;
	private AdminClient client;
	
	private DKafkaAdmin(DProperties props) throws DKafkaException { 
		this.props = null;
		Properties javaProps = props.toJavaProperties();
		client = KafkaAdminClient.create(javaProps);
	}
	
	public Collection<TopicListing> getTopics() throws DKafkaException {
		try {
			return doGetTopics();
		} catch (Exception e) {
			try {
				client = KafkaAdminClient.create(props.toJavaProperties());
				return doGetTopics();
			} catch (Exception e2) {
				throw new DKafkaException("Exception getting topics in kafka manager " + e.toString(),e2);
			}
		}
	}
	
	public void deleteTopicWildcard(String wildcard) throws DKafkaException { 
		List<String> deletes = new ArrayList<String>();
		for (TopicListing listing : getTopics()) {
			if(listing.name().startsWith(wildcard)) { 
				deletes.add(listing.name());
			}
		}
		deleteTopics(deletes.toArray(new String[deletes.size()]));
	}
	
	public void deleteTopics(String...topics) throws DKafkaException {
		try {

			List<String> test = Arrays.asList(topics);
			
			DeleteTopicsResult result = client.deleteTopics(test);
			KafkaFuture<Void> results = result.all();
			if(results.isCompletedExceptionally()) {
		
				int i = 0;
				while(i < 5) { 
					try {
						i++;
						KafkaFuture<Void> again = client.deleteTopics(test).all();
						if(again.isCompletedExceptionally()) { 
							Thread.sleep(1000);
							
						} else { 
							return;
						}
					} catch (Exception e) {
						
					}
				}
			}
			// okay this is not working here result.values().get(0);
			result.values();
			
			} catch (Exception e) {
			throw new DKafkaException("Exception deleting topic " + e.toString());
		}
	}
	
	
	private Collection<TopicListing> doGetTopics() throws Exception { 
		Collection<TopicListing> listings = client.listTopics().listings().get();
		return listings;
	}
	
	public void close() { 
		client.close();
	}
	
	public boolean topicExists(String name) throws DKafkaException { 
		Collection<TopicListing> topics = getTopics();
		for (TopicListing topicListing : topics) {
			if(topicListing.name().equals(name)) { 
				return true;
			}
		}
		return false; 
	}
	//TODO: Implement Topic Lifespan Properties
	public void createTopic(final String topicName, final int partitions, final short replicationFactor, Map<String, String> configs)
			throws DKafkaException {
		try {
			if(topicExists(topicName)) {
				throw new DKafkaException("Topic " + topicName + " already exists, cannot create it");
			}
			// Define topic
			final NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);
			if (configs != null) {
				newTopic.configs(configs);
			}
			// Create topic, which is async call.
			final CreateTopicsResult createTopicsResult = client.createTopics(Collections.singleton(newTopic));

			// Since the call is Async, Lets wait for it to complete.
			createTopicsResult.values().get(topicName).get();
		
		} catch (Exception e) {
			throw new DKafkaException("Create Topic Server Exception " + e.toString());
		} 
		

	}

// you could do poor mans audit
// get a list of topics and try to delete again 
	
}
