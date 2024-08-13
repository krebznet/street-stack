package com.dunkware.time.stream.model.admin.settings;

import com.dunkware.utils.core.scheduler.simple.SimpleEventSchedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreamSettings {
	
	private SimpleEventSchedule schedul;
	private String name;
	private String type;
	private String script;
	private String[] entityLists;
	
	

}
