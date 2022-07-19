package com.dunkware.trade.service.stream.worker.session.container;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.data.json.worker.container.DataStreamWorkerContainerStartReq;
import com.dunkware.trade.service.data.json.worker.container.DataStreamWorkerContainerStartResp;

@RestController
public class WorkerContainerWebService {

	@Autowired
	private WorkerContainerService service; 
	
	@PostConstruct
	public void test() { 
		System.out.println("loading worker container web service");
	}
	
	@PostMapping(path = "/worker/stream/container/start")
	public @ResponseBody DataStreamWorkerContainerStartResp startWorkerContainer(@RequestBody() DataStreamWorkerContainerStartReq req) { 
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
