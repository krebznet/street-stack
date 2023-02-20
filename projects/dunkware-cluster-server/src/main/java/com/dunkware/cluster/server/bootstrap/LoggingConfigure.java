package com.dunkware.cluster.server.bootstrap;

import java.io.File;
import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

@Component
@Profile("LoggingConfig")
public class LoggingConfigure {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	public void configure() { 
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		loggerContext.reset();
		JoranConfigurator configurator = new JoranConfigurator();
		String loggingConfig = System.getenv("LOGGING_CONFIG");
		if(loggingConfig == null) { 
			logger.error("Logging Config Env Variable Not Found!! ");
			return;
		}
		try {
			FileInputStream configStream = new FileInputStream(new File(loggingConfig));
			configurator.setContext(loggerContext);
			configurator.doConfigure(configStream); // loads logback file
			configStream.close();
			System.out.println("Logging Configured with " + loggingConfig);
		} catch (Exception e) {
			logger.error("Exception loading logging configure " + e.toString());
			System.err.println("logging config error " + e.toString());
			e.printStackTrace();
		}
		
	}
}
