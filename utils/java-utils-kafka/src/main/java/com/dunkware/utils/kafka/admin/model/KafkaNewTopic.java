package com.dunkware.utils.kafka.admin.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;

public class KafkaNewTopic {

	public static Builder builder() { 
		return new Builder();
	}
	public static class Builder {

		private Long retentionTime;
		private Long retentionSize;
		private Integer partitions;
		private Short replicas;
		private String cleanupPolicy;
		private String name;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder cleanupPolicy(String cleanupPolicy) {
			this.cleanupPolicy = cleanupPolicy;
			return this;
		}

		public Builder retentionSize(long megabytes) {
			retentionSize = megabytes * 1000000;
			return this;
		}

		public Builder retentionTime(long value, TimeUnit timeUnit) {
			retentionTime = timeUnit.toMillis(value);

			return this;
		}

		public Builder paritions(int value) {
			this.partitions = value;
			return this;
		}

		public Builder replicas(Short value) {
			this.replicas = value;
			return this;
		}

		public KafkaNewTopic build() {
			NewTopic topic = new NewTopic(name, partitions, (short) replicas);
			Map<String, String> configs = new HashMap<String, String>();
			if (retentionTime != null) {
				configs.put(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(retentionTime));
			}
			if (retentionSize != null) {
				configs.put(TopicConfig.RETENTION_BYTES_CONFIG, String.valueOf(retentionSize));
			}
			if (cleanupPolicy != null) {
				configs.put(TopicConfig.CLEANUP_POLICY_CONFIG, cleanupPolicy);
			}
			return new KafkaNewTopic(topic, configs);
		}

	}

	private NewTopic topic;
	private Map<String, String> configs;

	public KafkaNewTopic(NewTopic topic, Map<String, String> configs) {
		this.topic = topic;
		this.configs = configs;
	}

	public NewTopic getTopic() {
		return topic;
	}

	public void setTopic(NewTopic topic) {
		this.topic = topic;
	}

	public Map<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(Map<String, String> configs) {
		this.configs = configs;
	}
	
	

}
