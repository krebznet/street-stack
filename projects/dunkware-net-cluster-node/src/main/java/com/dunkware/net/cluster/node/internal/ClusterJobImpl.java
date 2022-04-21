package com.dunkware.net.cluster.node.internal;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.net.cluster.json.job.ClusterJobState;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterJob;
import com.dunkware.net.cluster.node.ClusterJobRunner;

public class ClusterJobImpl implements ClusterJob {

	@Autowired
	private Cluster cluster; 
	
	private String type; 
	private String name; 
	private ClusterJobRunner runner;
	
	private LocalDateTime startTime; 
	private LocalDateTime completeTime;
	private LocalDateTime exceptionTime;
	
	private String exceptionMessage;
	private boolean exception = false;
	private boolean completed = false; 
	
	private ClusterJobState state = null;
	
	private AtomicLong executions = new AtomicLong(); 
	
	public ClusterJobImpl() { 
		
	}
	
	public void start(ClusterJobRunner runner, String type, String name) throws Exception { 
		this.runner = runner;
		this.type = type;
		this.name = name;
		startTime = LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork));
		try {
			runner.startJob(this);
		} catch (Exception e) {
			state = ClusterJobState.Exception;
			exceptionTime = cluster.now();
			exceptionMessage = "Runner Exception " + e.toString();
			throw e;
		}
		
		state = ClusterJobState.Running;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	
	public ClusterJobState getState() { 
		return state;
	}
	
	@Override
	public ClusterJobRunner getRunner() {
		return runner;
	}

	@Override
	public void jobException(String error) {
		exceptionMessage = error;
		exception = true;
		exceptionTime = cluster.now();
		state = ClusterJobState.Exception;
		//todo: event
		
	}

	@Override
	public void jobComplete() {
		completed = true;
		completeTime = cluster.now();
		
	}

	public void execute(Runnable runnable) { 
		executions.incrementAndGet();
		cluster.getExecutor().execute(runnable);
	}
	
	

}
