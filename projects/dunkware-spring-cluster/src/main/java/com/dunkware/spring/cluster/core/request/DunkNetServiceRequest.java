package com.dunkware.spring.cluster.core.request;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.cluster.message.DunkNetMessage;
import com.dunkware.spring.cluster.protocol.DunkNetNodeService;

public class DunkNetServiceRequest {

	private DunkNetNode node;
	private Object payload;
	// private volatile T result = null;
	private volatile Throwable throwable = null;
	private volatile boolean completed = false;
	private volatile boolean success = false;
	private volatile boolean error = false;
	private CountDownLatch completionLatch = new CountDownLatch(1);
	private String requestId;
	private DunkNetNodeService serviceDescriptor;
	private short waiters;
	private String responseErrror = null;
	private static final Object SUCCESS = new Object();
	private static final Object UNCANCELLABLE = new Object();
	
	private BlockingQueue<String> wait = new LinkedBlockingQueue<String>();

	private volatile Object result;

	public DunkNetServiceRequest(DunkNetNode node, Object payload) throws DunkNetException {
		this.node = node;
		this.serviceDescriptor = node.getServiceDescriptor(payload);
		if (serviceDescriptor == null) {
			throw new DunkNetException("Service Descriptor on node " + node.getId() + " not found for class "
					+ payload.getClass().getName());
		}

	}

	public boolean isSuccess() {
		if (completed && success) {
			return true;
		}
		return false;
	}

	public boolean isFailure() {
		if (completed && error) {
			return true;
		}
		return false;
	}

	public void response(DunkNetMessage message) {
		Integer respCode = (Integer) message.getHeader(DunkNetMessage.KEY_RESPONSE_CODE);
		if (respCode == DunkNetMessage.RESPONSE_ERROR) {
			String error = message.getHeader(DunkNetMessage.KEY_RESPONSE_ERROR).toString();
			setError(error);
		}
		if (respCode == DunkNetMessage.RESPONSE_SUCCESS) {
			result = message.getPayload();
			setSuccess(result);
		}
	}

	public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
		Object result = wait.poll(timeout, unit);
		if(result == null) { 
			return false;
		}
		return true;
	}

	private void setError(String error) {
		responseErrror = error;
		this.error = true;
		this.completed = true;
		wait.add("error");
	}

	public void setSuccess(Object result) {
		this.result = result;
		this.completed = true;
		this.success = true;
		wait.add("success");

	}

	public boolean isDone() {
		return isDone0(result);
	}

	private static boolean isDone0(Object result) {
		return result != null && result != UNCANCELLABLE;
	}

	private boolean await0(long timeoutNanos, boolean interruptable) throws InterruptedException {
		if (isDone()) {
			return true;
		}

		if (timeoutNanos <= 0) {
			return isDone();
		}

		if (interruptable && Thread.interrupted()) {
			throw new InterruptedException(toString());
		}

		// checkDeadLock();

		long startTime = System.nanoTime();
		long waitTime = timeoutNanos;
		boolean interrupted = false;
		try {
			for (;;) {
				synchronized (this) {
					if (isDone()) {
						return true;
					}
					incWaiters();
					try {
						wait(waitTime / 1000000, (int) (waitTime % 1000000));
					} catch (InterruptedException e) {
						if (interruptable) {
							throw e;
						} else {
							interrupted = true;
						}
					} finally {
						decWaiters();
					}
				}
				if (isDone()) {
					return true;
				} else {
					waitTime = timeoutNanos - (System.nanoTime() - startTime);
					if (waitTime <= 0) {
						return isDone();
					}
				}
			}
		} finally {
			if (interrupted) {
				Thread.currentThread().interrupt();
			}
		}
	}

	private void incWaiters() {
		if (waiters == Short.MAX_VALUE) {
			throw new IllegalStateException("too many waiters: " + this);
		}
		++waiters;
	}

	private void decWaiters() {
		--waiters;
	}
	
	public Object getResponse() throws DunkNetException { 
		return result;
	}

	public <T> T getResponse(Class<T> clazz) throws DunkNetException {
		if (result == null) {
			throw new DunkNetException("Result is null");
		}
		if (!clazz.isInstance(result)) {
			throw new DunkNetException("Invalid Response Type Expected " + clazz.getName() + " but returned "
					+ result.getClass().getName());
		}
		return (T) result;

	}
	
	public String getError() { 
		return responseErrror;
	}

}
