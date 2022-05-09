package com.dunkware.learn.server.logging;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service()
@Profile("TestLogger")
public class LogginService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	private void load() { 
		MakeLogs make = new MakeLogs();
		make.start();
	}
	
	private class MakeLogs extends Thread { 
		public void run() { 
			while(!interrupted()) { 
				logger.debug("Hello Debug");
				logger.error("No Errror!");
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
}
