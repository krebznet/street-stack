package com.dunkware.spring.test.server.time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class TimeNetController {

	@Autowired
	private TimeService service; 
	
	@GetMapping(path = "/event")
	public void sendTime() { 
		service.sendEvent();
	}
}
