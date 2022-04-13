package com.dunkware.trade.service.data.service.repository;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import com.dunkware.trade.service.stream.json.controller.session.StreamSessionState;

public class DataStreamSnapshotSessionEntity {
	
	@Id
	private ObjectId _id;
	private String stream;
	private String sessionId; 
	private LocalDateTime startDateTime;
	private LocalDateTime stopDateTime; 
	private String snapshotCollection; 
	private int entityCount; 
	private double scriptVersion; 
	private String timeZone;
	private long snapshotCount; 
	private StreamSessionState state; 

}
