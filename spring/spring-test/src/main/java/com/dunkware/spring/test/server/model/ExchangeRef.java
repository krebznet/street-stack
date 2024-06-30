package com.dunkware.spring.test.server.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExchangeRef {

	private int id; 
	private String name; 
	private String identifier;
	private int tickers; 
	private String status;
	
	
	
}
