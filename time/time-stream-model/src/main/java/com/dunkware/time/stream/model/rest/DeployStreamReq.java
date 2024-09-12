package com.dunkware.time.stream.model.rest;

import java.util.List;

import com.dunkware.utils.core.scheduler.simple.SimpleEventSchedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeployStreamReq {
	
	private SimpleEventSchedule schedule; 
	private String ident; 
	private String name; 
	private String type; 
	private String script; 
	private List<String> entities; // groups 
	

}
