package com.dunkware.trade.service.stream.server.tests;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("LogTest1")
public class LogTest1 {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	@PostConstruct
	public void start() { 
		LogThread log = new LogThread();
		log.start();
	}
	
	private class LogThread extends Thread { 
		
		public void run() { 
			try {
				int count = 0;
				while(!interrupted()) { 
					Thread.sleep(1000); { 
						logger.info("Info Log " + count );
						logger.error("Error Log " + count);
						logger.debug("Debug Log " + count);
						count++;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
