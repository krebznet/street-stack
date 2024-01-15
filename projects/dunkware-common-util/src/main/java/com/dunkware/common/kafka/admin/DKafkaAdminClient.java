package com.dunkware.common.kafka.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.config.TopicConfig;

import com.dunkware.common.kafka.DKafkaException;
import com.dunkware.common.kafka.admin.model.DKafkaDeleteTopicResult;
import com.dunkware.common.kafka.admin.model.DKafkaDeleteTopicResults;
import com.dunkware.common.kafka.admin.model.DKafkaNewTopic;
import com.dunkware.common.kafka.admin.model.DKafkaNewTopicResult;
import com.dunkware.common.util.stopwatch.DStopWatch;

public class DKafkaAdminClient {

	public static DKafkaAdminClient newInstance(String brokers) throws DKafkaException {
		return new DKafkaAdminClient(brokers);
	}

	private AdminClient client;

	private DKafkaAdminClient(String brokers) throws DKafkaException {

		Map<String, Object> conf = new HashMap<>();
		conf.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
		conf.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "10000");
		try {

			client = AdminClient.create(conf);
			Thread.sleep(500);
		} catch (Exception e) {
			throw new DKafkaException("Exception Connection to AdminClient using brokers " + brokers + e.toString(), e);
		}

	}

	public void close() {
		client.close();

	}

	public boolean topicExists(String topic)   {
		try {
			if (getTopicNames(false).contains(topic)) {
				return true;
			}	
		} catch (Exception e) {
			
			return false; 
		}
		
		return false;
	}
	
	
	public DKafkaNewTopicResult createPersistentTopic(String topicName, int partitionCount, int replicaCount) {
		Optional<Integer> pCount = Optional.of(partitionCount);
		Optional<Short> rFactor = Optional.of((short)replicaCount);
		NewTopic newTopic = new NewTopic(topicName, pCount, rFactor);
		Map<String, String> configs = new HashMap<String,String>();
		configs.put(TopicConfig.DELETE_RETENTION_MS_CONFIG, "100000000000");
		configs.put(TopicConfig.RETENTION_MS_CONFIG, "100000000000");
		DKafkaNewTopic dNewTopic = new DKafkaNewTopic(newTopic, configs);
		return createTopic(dNewTopic);
		
		
	}

	public DKafkaNewTopicResult createTopic(DKafkaNewTopic dTopic) {
		DKafkaNewTopicResult result = new DKafkaNewTopicResult();
		result.setTopic(dTopic.getTopic().name());
		DStopWatch timer = DStopWatch.create();
		timer.start();
		NewTopic newTopic = dTopic.getTopic().configs(dTopic.getConfigs());
		CreateTopicsResult createResult = client.createTopics(Arrays.asList(newTopic));

		VoidFutureWrapper wrapper = new VoidFutureWrapper(newTopic.name(), createResult.all());
		long waitTime = 0;
		while (true) {
			if (wrapper.isDone()) {
				timer.stop();
				if (wrapper.getResult().isException()) {
					result.setException(true);
					result.setCause(wrapper.getResult().getCause());
					result.setTime(timer.getCompletedSeconds());
					return result;
				} else {
					result.setTime(timer.getCompletedSeconds());
					return result;
				}
			}
			try {
				Thread.sleep(250);;
				waitTime = waitTime + 250;
				if (TimeUnit.MILLISECONDS.toSeconds(waitTime) > 30) {
					result.setTime(timer.getRunningSeconds());
					result.setTimeout(true);
					return result;
				}
			} catch (InterruptedException e) {
				result.setException(true);;
				result.setCause(e.toString());;
				return result;
			}

		}
		

	}

	public List<String> getTopicNames(boolean includeInternal) throws DKafkaException {

		ListTopicsOptions options = new ListTopicsOptions();
		if (includeInternal)
			options.listInternal(true);
		options.listInternal(false);

		ListTopicsResult result = client.listTopics(options);
		KafkaFuture<Collection<TopicListing>> future = result.listings();
		Collection<TopicListing> topicListings = null;
		try {
			List<String> names = new ArrayList<String>();
			topicListings = future.get(20, TimeUnit.SECONDS);
			for (TopicListing topicListing : topicListings) {
				names.add(topicListing.name());
			}
			return names;
		} catch (Exception e) {
			throw new DKafkaException("Get topic listings timeout after 20 seconds");
		}

	}
	
	public DKafkaDeleteTopicResults deleteTopics(Collection<String> topics) throws DKafkaException { 
		DStopWatch timer = DStopWatch.create();
		timer.start();
		DeleteTopicsResult result = client.deleteTopics(topics);
		DKafkaDeleteTopicResults returnResults = new DKafkaDeleteTopicResults();
		Map<String, VoidFutureWrapper> callbacks = new ConcurrentHashMap<String, VoidFutureWrapper>();
		Map<String, KafkaFuture<Void>> futures = result.values();
		List<String> callbackTopics = new ArrayList<String>();
		for (String string : futures.keySet()) {
			callbacks.put(string, new VoidFutureWrapper(string, futures.get(string)));
			callbackTopics.add(string);
		}
		boolean done = false;
		long duration = 0;
		while (!done) {
			for (String key : callbacks.keySet()) {
				if (callbackTopics.contains(key)) {
					VoidFutureWrapper callback = callbacks.get(key);
					if (callback.isDone()) {
						DKafkaDeleteTopicResult deleteResult = callback.getResult();
						returnResults.addResult(deleteResult);
						callbackTopics.remove(key);
					}
					if (callbackTopics.size() == 0) {
						return returnResults;
					}
					try {
						Thread.sleep(250);
					} catch (Exception e) {
						throw new DKafkaException("Interrupted Exceptiond deleting topics");
					}
					duration = duration + 250;
					if (TimeUnit.MILLISECONDS.toSeconds(duration) > 30) {
						returnResults.setTime(timer.getRunningSeconds());
						returnResults.setTimeout(true);
						returnResults.setTimeoutTopics(callbackTopics);
						return returnResults;
					}
				}
			}
		}
		return returnResults;
	}
	
	
	

	public DKafkaDeleteTopicResults deleteTopicsDangerous(boolean deleteInternalTopics) throws DKafkaException {
		DStopWatch timer = DStopWatch.create();
		timer.start();
		List<String> topics = getTopicNames(deleteInternalTopics);
		DeleteTopicsResult result = client.deleteTopics(topics);
		DKafkaDeleteTopicResults returnResults = new DKafkaDeleteTopicResults();
		Map<String, VoidFutureWrapper> callbacks = new ConcurrentHashMap<String, VoidFutureWrapper>();
		Map<String, KafkaFuture<Void>> futures = result.values();
		List<String> callbackTopics = new ArrayList<String>();
		for (String string : futures.keySet()) {
			callbacks.put(string, new VoidFutureWrapper(string, futures.get(string)));
			callbackTopics.add(string);
		}
		boolean done = false;
		long duration = 0;
		while (!done) {
			for (String key : callbacks.keySet()) {
				if (callbackTopics.contains(key)) {
					VoidFutureWrapper callback = callbacks.get(key);
					if (callback.isDone()) {
						DKafkaDeleteTopicResult deleteResult = callback.getResult();
						returnResults.addResult(deleteResult);
						callbackTopics.remove(key);
					}
					if (callbackTopics.size() == 0) {
						return returnResults;
					}
					try {
						Thread.sleep(250);
					} catch (Exception e) {
						throw new DKafkaException("Interrupted Exceptiond deleting topics");
					}
					duration = duration + 250;
					if (TimeUnit.MILLISECONDS.toSeconds(duration) > 30) {
						returnResults.setTime(timer.getRunningSeconds());
						returnResults.setTimeout(true);
						returnResults.setTimeoutTopics(callbackTopics);
						return returnResults;
					}
				}
			}
		}
		return returnResults;

	}

	private class VoidFutureWrapper {

		private DKafkaDeleteTopicResult result = new DKafkaDeleteTopicResult();
		private boolean done = false;

		public VoidFutureWrapper(String topic, KafkaFuture<Void> future) {
			result.setTopic(topic);
			future.whenComplete((v, t) -> {
				if (t != null) {
					result.setCause(t.getMessage());
					result.setException(true);
					done = true;
				} else {
					result.setException(false);
					done = true;
				}
			});
		}

		public boolean isDone() {
			return done;
		}

		public DKafkaDeleteTopicResult getResult() {
			return result;
		}
	}

}
