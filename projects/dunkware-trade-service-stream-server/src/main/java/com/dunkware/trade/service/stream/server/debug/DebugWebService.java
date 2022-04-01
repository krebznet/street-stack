package com.dunkware.trade.service.stream.server.debug;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.tick.client.TickServiceClient;
import com.dunkware.trade.service.tick.client.TickServiceClientFactory;
import com.dunkware.trade.tick.service.protocol.service.spec.TickServiceStatusSpec;

@RestController()
public class DebugWebService {
	
	@Value("${tick.service.endpoint}")
	private String tickServiceEndpoint; 

	
	@GetMapping(path = "/debug/tickservice/config") 
	public String getTickServiceEndpoint() { 
		return tickServiceEndpoint;
	}
	
	@GetMapping(path = "/debug/tickservice/test") 
	public String testTickService() { 
		try {
			TickServiceClient client = TickServiceClientFactory.connect(tickServiceEndpoint);
			TickServiceStatusSpec spec = client.getStatus();
			return "got status okay " + spec.toString();
		} catch (Exception e) {
			return e.toString();
		}
	}
}
