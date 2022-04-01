package com.dunkwrae.trade.service.data.mock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {
	
	//@Value("${mongoURL}")
	//private String mongoURL; 
	
	@Value("${kafkaBrokers}")
	private String kafkaBrokers;

	
	public String getKafkaBrokers() {
		return kafkaBrokers;
	}

	

}
