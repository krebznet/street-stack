package com.dunkware.trade.service.stream.worker.session.container.core;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.stream.container.worker.WorkerContainerStartReq;
import com.dunkware.trade.service.stream.container.worker.WorkerContainerStartResp;

@RestController
public class WorkerContainerWebService {

	@Autowired
	private WorkerContainerService service; 
	
	@PostConstruct
	public void test() { 
		
	}
	
	@PostMapping(path = "/worker/stream/container/start")
	public @ResponseBody WorkerContainerStartResp startWorkerContainer(@RequestBody() WorkerContainerStartReq req) { 
		return service.createContainer(req);
	}
	
	@GetMapping(path = "/worker/stream/container/stop")
	public void stopWorkerContainer(@RequestParam() String workerId) { 
		
	}
	
	@GetMapping(path = "/worker/ping")
	public @ResponseBody String ping(@RequestParam String ping) { 
		return ping;
	}
	
	
}
