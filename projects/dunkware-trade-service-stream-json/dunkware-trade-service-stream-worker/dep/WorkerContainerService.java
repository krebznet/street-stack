package com.dunkware.trade.service.stream.worker.session.container.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.stream.container.worker.WorkerContainerStartReq;
import com.dunkware.trade.service.stream.container.worker.WorkerContainerStartResp;

@Service()
public class WorkerContainerService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Map<String,WorkerContainerImpl> containers = new ConcurrentHashMap<String,WorkerContainerImpl>();
	
	@Autowired
	private ApplicationContext ac; 
	
	public WorkerContainerStartResp createContainer(WorkerContainerStartReq req) {
		WorkerContainerStartResp resp = new WorkerContainerStartResp();
		WorkerContainerImpl container = new WorkerContainerImpl();
		ac.getAutowireCapableBeanFactory().autowireBean(container);
		try {
			container.start(req);
		} catch (Exception e) {
			resp.setSuccessfull(false);
			resp.setException(e.toString());
			logger.error("Exception starting worker container " + e.toString());
			return resp;
		}
		resp.setSuccessfull(true);
		return resp;
	}
	
	public WorkerContainerImpl getContainer(String workerId) {
		return containers.get(workerId);
	}
	
}
