package com.dunkware.trade.service.stream.json.worker.stream;

import java.util.ArrayList;
import java.util.List;

public class StreamSessionWorkersStats {

	private List<StreamSessionWorkerStats> workers = new ArrayList<StreamSessionWorkerStats>();

	public List<StreamSessionWorkerStats> getWorkers() {
		return workers;
	}

	public void setWorkers(List<StreamSessionWorkerStats> workers) {
		this.workers = workers;
	}
	
	
}
