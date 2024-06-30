package com.dunkware.trade.service.stream.json.worker.service;

import com.dunkware.time.data.model.entity.EntitySnapshot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntitySnapshotRep {

	private EntitySnapshot snapshot; 
	private boolean error = false; 
	private String errorMessage; 
}
