package com.dunkware.trade.service.stream.worker.session.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.data.json.worker.container.DataStreamWorkerContainerStartReq;
import com.dunkware.trade.service.data.json.worker.container.DataStreamWorkerContainerStartResp;

@Service()
public class WorkerContainerService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Map<String,WorkerContainer> containers = new ConcurrentHashMap<String,WorkerContainer>();
	
	@Autowired
	private ApplicationContext ac; 
	
	public DataStreamWorkerContainerStartResp createContainer(DataStreamWorkerContainerStartReq req) {
		DataStreamWorkerContainerStartResp resp = new DataStreamWorkerContainerStartResp();
		WorkerContainer container = new WorkerContainer();
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
	
	public WorkerContainer getContainer(String workerId) {
		return containers.get(workerId);
	}
	
}
