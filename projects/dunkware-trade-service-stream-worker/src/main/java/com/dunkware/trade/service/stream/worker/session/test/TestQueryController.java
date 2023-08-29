package com.dunkware.trade.service.stream.worker.session.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestQueryController {
	
	@Autowired
	private TestQueryService service; 
	
	@GetMapping(path = "/builder/test/1")
	public void test1() { 
		try {
			service.test1();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
