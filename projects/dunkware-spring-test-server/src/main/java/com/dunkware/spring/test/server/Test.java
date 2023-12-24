package com.dunkware.spring.test.server;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("test")
public class Test {

	@RequestMapping(path = "/hello")
	public String get() { 
		return "hello";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String appoitments() { 
		return "apts";
	}
}
