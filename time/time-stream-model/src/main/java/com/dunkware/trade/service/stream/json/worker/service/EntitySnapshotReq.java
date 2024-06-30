package com.dunkware.trade.service.stream.json.worker.service;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntitySnapshotReq {
	
	private int streamId; 
	private int entityId; 
	private String entityIdent; 
	private LocalDateTime timestamp; 
	private boolean latest = true; 
	
	
	public static EntitySnapshotReq latest(int streamId, int entityId) { 
		EntitySnapshotReq req = new EntitySnapshotReq();
		req.setStreamId(streamId);
		req.setEntityId(entityId);
		return req;
	}

}
