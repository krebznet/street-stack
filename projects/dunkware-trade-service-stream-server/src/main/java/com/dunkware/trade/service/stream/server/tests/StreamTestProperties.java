package com.dunkware.trade.service.stream.server.tests;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("StreamTest")
public class StreamTestProperties {

	@Value("${streamtest.script.directory.equity}")
	private String equityScriptDirectory; 
	
	@Value("${streamtest.tickservice.url}")
	private String tickServiceUrl;
	
	
	public String getEquityScriptDirectory() { 
		return equityScriptDirectory;
	}
	
	public String getTickServiceUrl() { 
		return tickServiceUrl;
	}
}
