package com.dunkware.trade.tick.service.server.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.tick.service.protocol.service.spec.TickServiceStatusSpec;

@RestController
public class TickMonitorWebService {

	@Autowired
	private TickMonitorService statusService; 
	
	
	
	@RequestMapping(path = "/monitor/status")
	public @ResponseBody TickServiceStatusSpec getStatus() { 
		TickServiceStatusSpec spec = new TickServiceStatusSpec();
		spec.setAvailable(true);
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return spec;
	}
}
