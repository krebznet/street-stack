package com.dunkware.trade.service.stream.server.session.capture.worker;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.stream.server.session.capture.protocol.SessionEventCaptureWorkerStartReq;
import com.dunkware.trade.service.stream.server.session.capture.protocol.SessionEventCaptureWorkerStartResp;

@Profile("MongoEventCapture")
@RestController
public class SessionEventCaptureWorkerWebService {
	
	
	@PostMapping(path = "/stream/event/capture/start")
	public @ResponseBody SessionEventCaptureWorkerStartResp startWorker(@RequestBody()SessionEventCaptureWorkerStartReq req) {
		return null;
	}
	

	@PostMapping(path = "/stream/event/capture/stop")
	public @ResponseBody SessionEventCaptureWorkerStartResp stopWorker(@RequestParam() String workderId) {
		return null;
	}
	
	

}
