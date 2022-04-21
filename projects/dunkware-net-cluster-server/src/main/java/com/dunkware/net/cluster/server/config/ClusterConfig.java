package com.dunkware.net.cluster.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ClusterConfig {
	
	@Value("${kafkaBrokers}")
	private String kafkaBrokers;

	
	public String getKafkaBrokers() {
		return kafkaBrokers;
	}

	

}
