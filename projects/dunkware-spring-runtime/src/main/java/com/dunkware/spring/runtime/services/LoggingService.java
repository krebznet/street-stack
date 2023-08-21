package com.dunkware.spring.runtime.services;

import java.io.File;
import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

@Service
public class LoggingService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	public void configure() { 
		String loggingConfig = System.getenv("LOGGING_CONFIG");
		if(loggingConfig == null) { 
			logger.info("LOGGING_CONFIG ENV NOT SET");
			return;
		}
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		loggerContext.reset();
		JoranConfigurator configurator = new JoranConfigurator();
		
		try {
			FileInputStream configStream = new FileInputStream(new File(loggingConfig));
			configurator.setContext(loggerContext);
			configurator.doConfigure(configStream); // loads logback file
			configStream.close();
			logger.info("Logging Configuration loaded from " + loggingConfig);
		} catch (Exception e) {
			logger.error(MarkerFactory.getMarker("Crash"),"Exception loading logging configure " + e.toString());
			System.exit(-1);
		}
		
	}
}
