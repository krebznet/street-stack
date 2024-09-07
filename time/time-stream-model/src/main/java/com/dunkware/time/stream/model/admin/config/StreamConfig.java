package com.dunkware.time.stream.model.admin.config;

import com.dunkware.utils.core.scheduler.simple.SimpleEventSchedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreamConfig {
	
	private SimpleEventSchedule schedule;
	private String name;
	private String type;
	private String scriptRepo;
	private String[] entityLists;
	
	

}
