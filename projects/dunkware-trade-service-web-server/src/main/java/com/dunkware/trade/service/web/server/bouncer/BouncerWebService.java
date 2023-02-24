package com.dunkware.trade.service.web.server.bouncer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BouncerWebService {

	@Autowired
	BouncerService service; 
	
	@GetMapping(path = "/web/bounce")
	public void bounce() { 
		service.bounce();
		
	}
	
}
