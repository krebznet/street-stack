package com.dunkware.trade.service.stream.server.snapshot;

public class SnapshotService {

	// it will have -> a cache split over to like 5 nodes, 
	// SnapshotCapture
	// SessionSnapshotStream
		// getSnapshot(Entity, this time) 
		// getSnapshot from this time to this time at 5 second intervals 
		// 
	
	// snapshot query --> load the snapshot data. 
	// getElements where date/time is > 9:13:/23 & Entity is = THis
	// SnapshotDocument
		// SnapshotSecond
			// getTime
			// getVariables
	
	// Decompress Snapshot data into the cache. 
	
}
