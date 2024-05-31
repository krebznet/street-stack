package com.dunkware.spring.test.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.spring.test.server.model.Person;
import com.dunkware.spring.test.server.model.PersonResponse;

@RestController
@CrossOrigin("*")
public class Test {

	@CrossOrigin("*")
	@PostMapping(path = "/person")
	public ResponseEntity<PersonResponse> get(@RequestBody Person p) {
		PersonResponse resp = new PersonResponse();
		resp.setMessage("man");
		return  ResponseEntity.ok(resp);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String appoitments() { 
		return "apts";
	}
}
