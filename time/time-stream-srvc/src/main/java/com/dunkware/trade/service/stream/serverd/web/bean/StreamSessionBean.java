package com.dunkware.trade.service.stream.serverd.web.bean;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class StreamSessionBean {

	
	private String date; 
	private String startTime; 
	private String stopTime; 
	private Number entityCount; 
	private Number errorCount; 
	private String status; // ERROR = 
	private double version; // 23.2893  /  $32.323 
	
	
	
	
	
	
}
