package com.dunkware.common.util.connection;

public interface DConnection {
	
	DConnectionStatus getConnectionStatus();
	
	public void addConnectionListener(DConnectionListener listener);
	
	public void removeConnectionListener(DConnectionListener listener);
	
	public String getConnectionName();
	
	public String getConnectionError();
}
