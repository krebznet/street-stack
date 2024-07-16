package com.dunkware.utils.core.eventlogger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.text.html.HTML.Tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventLog {
	
	private LocalDateTime timestamp; 
	private List<Tag> tags = new ArrayList<Tag>();
	private Map<String,Object> values = new ConcurrentHashMap<String,Object>();
	private String logger; 
	private String type; 
	private String message; 
	
	
	
	

}
