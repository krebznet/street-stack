package com.dunkware.spring.cluster.core.request;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelClient;
import com.dunkware.spring.cluster.DunkNetNode;

public class DunkNetChannelRequest implements Future<DunkNetChannel> {
	
	private String requestId; 
	private DunkNetNode node;
	
	private DunkNetChannel channel;
	
	private boolean cancelled = false;
	private boolean done = false;
	
	private boolean error = false;
	private String errorMessage = null;
	
	private BlockingQueue<Integer> getQueue = new LinkedBlockingDeque<Integer>();
	private BlockingQueue<Integer> getTimeoutQueue = new LinkedBlockingDeque<Integer>();
	
	
	public DunkNetChannelRequest(String requestId, DunkNetNode node) { 
		this.node = node;
		this.requestId = requestId;
	}
	
	public void setChannel(DunkNetChannel channel) { 
		this.channel = channel;
		this.done = true;
		getQueue.add(1);
		getTimeoutQueue.add(1);
	}
	
	public void setError(String error) { 
		this.errorMessage = error;
		this.error = true;
		this.done = true; 
		getQueue.add(1);
		getTimeoutQueue.add(1);
	}
	
	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return false;
	}

	@Override
	public boolean isCancelled() {
		return false;
	}

	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public DunkNetChannel get() throws InterruptedException, ExecutionException {
		if(!done) { 
			Object result = getQueue.poll(60, TimeUnit.SECONDS);
			if(result == null) { 
				throw new ExecutionException(new Exception("Timeout after 60 seconds for channel something is wrong"));
			}	
		}
		if(error && done) { 
			throw new ExecutionException("Server Channel Returned Error", new Exception(errorMessage));
		}
		return channel;
	}

	@Override
	public DunkNetChannel get(long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException, TimeoutException {
		if(!done) { 
			Object result = getTimeoutQueue.poll(timeout, unit);
			if(result == null) { 
				throw new ExecutionException(new Exception("Timeout after " + timeout + " " + unit.name() + " for channel"));
			}	
		}
		if(error && done) { 
			throw new ExecutionException("Server Channel Returned Error", new Exception(errorMessage));
		}
		return channel;
	}

}
