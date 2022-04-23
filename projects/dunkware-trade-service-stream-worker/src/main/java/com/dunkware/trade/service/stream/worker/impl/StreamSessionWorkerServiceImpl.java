package com.dunkware.trade.service.stream.worker.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.worker.StreamSessionWorker;
import com.dunkware.trade.service.stream.worker.StreamSessionWorkerService;

@Service
public class StreamSessionWorkerServiceImpl implements StreamSessionWorkerService {

	@Autowired
	private ApplicationContext ac;

	private Map<String,StreamSessionWorker> workers = new ConcurrentHashMap<String,StreamSessionWorker>();
	
	@PostConstruct
	public void start() { 
		System.out.println("break here");
	}
	
	@Override
	public StreamSessionWorker getWorker(String workerId) throws Exception {
		if(workers.containsKey(workerId) == false) { 
			throw new Exception("Stream Worker ID " + workerId + " does not exist");
		}
		return workers.get(workerId);
	}

	@Override
	public StreamSessionWorker startWorker(StreamSessionWorkerStartReq req) throws Exception {
		StreamSessionWorkerImpl worker = new StreamSessionWorkerImpl();
		ac.getAutowireCapableBeanFactory().autowireBean(worker);
		worker.start(req);
		workers.put(req.getWorkerId(), worker);
		return worker;
	}

	@Override
	public void stopWorker(String workerId) throws Exception {
		if(workers.containsKey(workerId) == false) { 
			throw new Exception("Worker " + workerId + " not found, check yourself before your wreck yourself");
		}
		workers.get(workerId).stop();
		workers.remove(workerId);
	}
	
	
	
	

	

}
