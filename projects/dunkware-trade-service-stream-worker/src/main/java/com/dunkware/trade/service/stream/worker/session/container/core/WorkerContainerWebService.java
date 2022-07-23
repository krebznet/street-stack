package com.dunkware.trade.service.stream.worker.session.container.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.trade.service.stream.container.worker.WorkerContainerInput;
import com.dunkware.trade.service.stream.container.worker.WorkerContainerStartReq;
import com.dunkware.trade.service.stream.container.worker.WorkerContainerStartResp;

@RestController
public class WorkerContainerWebService {

	@Autowired
	private Cluster cluster;
	
	@Autowired
	private ApplicationContext ac; 
	
	@PostMapping(path = "/worker/stream/container/start")
	public @ResponseBody WorkerContainerStartResp startWorkerContainer(@RequestBody() WorkerContainerStartReq req) throws Exception {
		WorkerContainerStartResp resp = new WorkerContainerStartResp();
		try {
			WorkerContainerInput input = req.getInput();
			WorkerContainerImpl container = new WorkerContainerImpl(); 
			ac.getAutowireCapableBeanFactory().autowireBean(container);
			container.start(input);
			resp.setSuccessfull(true);
			return resp; 
			
		} catch (Exception e) {
			resp.setException(e.toString());
			resp.setSuccessfull(false);
			return resp; 
		}
		
	}
}
