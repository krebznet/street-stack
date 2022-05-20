package com.dunkware.trade.service.data.worker.stream.net.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.data.json.worker.container.DataStreamWorkerContainerStartReq;
import com.dunkware.trade.service.data.json.worker.container.DataStreamWorkerContainerStartResp;

@Service()
public class WorkerContainerService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Map<String,WorkerContainer> containers = new ConcurrentHashMap<String,WorkerContainer>();
	
	public DataStreamWorkerContainerStartResp createContainer(DataStreamWorkerContainerStartReq req) {
		
		DataStreamWorkerContainerStartResp resp = new DataStreamWorkerContainerStartResp();
		return resp;
	}
	
	public WorkerContainer getContainer(String workerId) {
		return containers.get(workerId);
	}
	
}
