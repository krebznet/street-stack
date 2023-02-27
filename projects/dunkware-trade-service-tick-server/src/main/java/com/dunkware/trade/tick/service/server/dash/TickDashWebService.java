package com.dunkware.trade.tick.service.server.dash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
public class TickDashWebService {

	@Autowired
	private TickDashService service; 
	
	@GetMapping(path = "/tick/dash/subscriptions")
	public ResponseEntity<StreamingResponseBody> subscriptionsStream() { 
		TickDashSubscriptionStream stream = service.subscriptionStream();
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(stream.getStreamingAdapter());
	}
}
