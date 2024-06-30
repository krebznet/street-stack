package com.dunkware.utils.kafka.admin;

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

import com.dunkware.utils.core.stopwatch.StopWatch;
import com.dunkware.utils.kafka.admin.model.KafkaDeleteTopicResult;
import com.dunkware.utils.kafka.admin.model.KafkaDeleteTopicResults;
import com.dunkware.utils.kafka.admin.model.KafkaNewTopic;
import com.dunkware.utils.kafka.admin.model.KafkaNewTopicResult;

public class DunkKafkaAdmin {

	public static DunkKafkaAdmin newInstance(String brokers) throws Exception {
		return new DunkKafkaAdmin(brokers);
	}

	private AdminClient client;

	private DunkKafkaAdmin(String brokers) throws Exception {

		Map<String, Object> conf = new HashMap<>();
		conf.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
		conf.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "10000");
		try {

			client = AdminClient.create(conf);
			Thread.sleep(500);
		} catch (Exception e) {
			throw new Exception("Exception Connection to AdminClient using brokers " + brokers + e.toString(), e);
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
	
	
	public KafkaNewTopicResult createPersistentTopic(String topicName, int partitionCount, int replicaCount) {
		Optional<Integer> pCount = Optional.of(partitionCount);
		Optional<Short> rFactor = Optional.of((short)replicaCount);
		NewTopic newTopic = new NewTopic(topicName, pCount, rFactor);
		Map<String, String> configs = new HashMap<String,String>();
		configs.put(TopicConfig.DELETE_RETENTION_MS_CONFIG, "100000000000");
		configs.put(TopicConfig.RETENTION_MS_CONFIG, "100000000000");
		KafkaNewTopic dNewTopic = new KafkaNewTopic(newTopic, configs);
		return createTopic(dNewTopic);
		
		
	}

	public KafkaNewTopicResult createTopic(KafkaNewTopic dTopic) {
		KafkaNewTopicResult result = new KafkaNewTopicResult();
		result.setTopic(dTopic.getTopic().name());
		StopWatch timer = StopWatch.newInstance();
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
					result.setTime(timer.seconds());
					return result;
				} else {
					result.setTime(timer.seconds());
					return result;
				}
			}
			try {
				Thread.sleep(250);;
				waitTime = waitTime + 250;
				if (TimeUnit.MILLISECONDS.toSeconds(waitTime) > 30) {
					result.setTime(timer.seconds());
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

	public List<String> getTopicNames(boolean includeInternal) throws Exception {

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
			throw new Exception("Get topic listings timeout after 20 seconds");
		}

	}
	
	public KafkaDeleteTopicResults deleteTopics(Collection<String> topics) throws Exception { 
		StopWatch timer = StopWatch.newInstance();
		timer.start();
		DeleteTopicsResult result = client.deleteTopics(topics);
		KafkaDeleteTopicResults returnResults = new KafkaDeleteTopicResults();
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
						KafkaDeleteTopicResult deleteResult = callback.getResult();
						returnResults.addResult(deleteResult);
						callbackTopics.remove(key);
					}
					if (callbackTopics.size() == 0) {
						return returnResults;
					}
					try {
						Thread.sleep(250);
					} catch (Exception e) {
						throw new Exception("Interrupted Exceptiond deleting topics");
					}
					duration = duration + 250;
					if (TimeUnit.MILLISECONDS.toSeconds(duration) > 30) {
						returnResults.setTime(timer.seconds());
						returnResults.setTimeout(true);
						returnResults.setTimeoutTopics(callbackTopics);
						return returnResults;
					}
				}
			}
		}
		return returnResults;
	}
	
	
	

	public KafkaDeleteTopicResults deleteTopicsDangerous(boolean deleteInternalTopics) throws Exception {
		StopWatch timer = StopWatch.newInstance();
		timer.start();
		List<String> topics = getTopicNames(deleteInternalTopics);
		DeleteTopicsResult result = client.deleteTopics(topics);
		KafkaDeleteTopicResults returnResults = new KafkaDeleteTopicResults();
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
						KafkaDeleteTopicResult deleteResult = callback.getResult();
						returnResults.addResult(deleteResult);
						callbackTopics.remove(key);
					}
					if (callbackTopics.size() == 0) {
						return returnResults;
					}
					try {
						Thread.sleep(250);
					} catch (Exception e) {
						throw new Exception("Interrupted Exceptiond deleting topics");
					}
					duration = duration + 250;
					if (TimeUnit.MILLISECONDS.toSeconds(duration) > 30) {
						returnResults.setTime(timer.seconds());
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

		private KafkaDeleteTopicResult result = new KafkaDeleteTopicResult();
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

		public KafkaDeleteTopicResult getResult() {
			return result;
		}
	}

}
