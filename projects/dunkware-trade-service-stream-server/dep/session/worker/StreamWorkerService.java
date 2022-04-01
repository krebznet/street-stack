package com.dunkware.trade.service.stream.server.controller.session.worker;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.dunkware.xstream.model.XStreamBundle;

@Component
@Profile("StreamWorkerService")
public class StreamWorkerService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private List<StreamWorker> workers = new ArrayList<StreamWorker>();
	
	@Autowired
	private ApplicationContext ac;
	
	@PostConstruct
	private void load() { 
		logger.info("Starting Stream Worker Service");
	}
	
	public StreamWorker startWorker(XStreamBundle streamBundle, String streamName) throws Exception { 
		StreamWorker worker = new StreamWorker();
		ac.getAutowireCapableBeanFactory().autowireBean(worker);
		worker.start(streamBundle, streamName);
		workers.add(worker);
		return worker;
	}

	public void stopWorker(String identifier) throws Exception { 
		for (StreamWorker worker : workers) {
			if(worker.getIdentifier().equals(identifier)) { 
				worker.stop();
				return;
			}
		}
		throw new Exception("Worker with Identifier " + identifier + " not found");
	}
	
	
	public StreamWorker getWorker(String identifier) throws StreamWorkerException { 
		for (StreamWorker streamWorker : workers) {
			if(streamWorker.getIdentifier().equals(identifier)) { 
				return streamWorker;
			}
		}
		throw new StreamWorkerException("Stream Identifier not found in worker service" + identifier);
	}
	
	
	public boolean hasWorker(String identifier) { 
		try {
			getWorker(identifier);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
