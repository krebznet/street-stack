package com.dunkware.spring.cluster;

public interface DunkNetChannelListener {
	
	public void onChannelStart();
	
	public void onChannelClose();
	
	public void onChannelError(Throwable t);
	
	

}
