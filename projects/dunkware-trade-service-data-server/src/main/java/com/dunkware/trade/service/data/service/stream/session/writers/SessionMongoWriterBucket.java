package com.dunkware.trade.service.data.service.stream.session.writers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.net.proto.stream.GEntitySnapshot;

public class SessionMongoWriterBucket {

	private DStopWatch stopWatch = DStopWatch.create();
	private LocalDateTime start; 
	private LocalDateTime stop; 
	private int id;
	private String identifier;
	private List<GEntitySnapshot> snapshots = new ArrayList<GEntitySnapshot>();
	
	public void addSnapshot(GEntitySnapshot snapshot) { 
		this.snapshots.add(snapshot);
	}
	
	public int getSize() { 
		return snapshots.size();
	}

	public DStopWatch getStopWatch() {
		return stopWatch;
	}

	public void setStopWatch(DStopWatch stopWatch) {
		this.stopWatch = stopWatch;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getStop() {
		return stop;
	}

	public void setStop(LocalDateTime stop) {
		this.stop = stop;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public List<GEntitySnapshot> getSnapshots() {
		return snapshots;
	}

	public void setSnapshots(List<GEntitySnapshot> snapshots) {
		this.snapshots = snapshots;
	}
	
	
	
	
	 
}
