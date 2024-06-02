package com.dunkware.spring.test.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.spring.test.server.model.ExchangeRef;
import com.dunkware.spring.test.server.model.Person;
import com.dunkware.spring.test.server.model.PersonResponse;

@RestController
@CrossOrigin("*")
public class Test {
	
	
	@GetMapping(path = "/api/exchanges")
	public List<ExchangeRef> exchanges() { 
		List<ExchangeRef> results = new ArrayList<ExchangeRef>();
		results.add(ExchangeRef.builder().id(1).identifier("NYSE/NASDAQ").name("US Stock Market").status("Open").tickers(4).build());
		results.add(ExchangeRef.builder().id(1).identifier("NYSE/CBOE").name("US Options Market").status("Open").tickers(4).build());
		return results;
	}

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
