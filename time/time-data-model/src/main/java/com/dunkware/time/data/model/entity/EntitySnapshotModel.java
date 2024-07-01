package com.dunkware.time.data.model.entity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntitySnapshotModel {

	private int streamId;  
	private int entityId;  
	private String entityIdent;
	private LocalDateTime timestamp; 
	
	private Map<Integer,Number> variables = new ConcurrentHashMap<Integer,Number>();
	
	
	
}
