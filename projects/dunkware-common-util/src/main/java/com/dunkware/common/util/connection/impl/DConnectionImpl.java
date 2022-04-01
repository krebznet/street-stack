package com.dunkware.common.util.connection.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.dunkware.common.util.connection.DConnection;
import com.dunkware.common.util.connection.DConnectionListener;
import com.dunkware.common.util.connection.DConnectionStatus;

public abstract class DConnectionImpl implements DConnection {
	
	protected DConnectionStatus connectionStatus = DConnectionStatus.Disconnected;
	private List<DConnectionListener> listeners = new ArrayList<DConnectionListener>();
	private String connectionName = null;
	private Semaphore listenerLock = new Semaphore(1);
	private String connectionError = null;
	

	@Override
	public DConnectionStatus getConnectionStatus() {
		return connectionStatus;
	}

	@Override
	public void addConnectionListener(DConnectionListener listener) {
		try {
			listenerLock.acquire();
			listeners.add(listener);
		} catch (Exception e) {
		} finally { 
			listenerLock.release();
		}
	}

	@Override
	public void removeConnectionListener(DConnectionListener listener) {
		try {
			listenerLock.acquire();
			listeners.remove(listener);
		} catch (Exception e) {
		
		} finally { 
			listenerLock.release();
		}
	}

	@Override
	public String getConnectionError() {
		return connectionError;
	}
	

	@Override
	public String getConnectionName() {
		return connectionName;
	}
	
	protected void setConnectionName(String cnName) { 
		this.connectionName = cnName;
	}
	
	protected void setConnectionError(String error) {
		this.connectionError = error;
		notifyConnectionListeners();
	}

	protected void setConnectionStatus(DConnectionStatus status) {
		if(this.connectionStatus != status) { 
			this.connectionStatus = status;
			notifyConnectionListeners();
		}
	}
	
	
	private void notifyConnectionListeners() { 
		try {
			listenerLock.acquire();
			for (DConnectionListener listener : listeners) {
				listener.connectionUpdate(this);
			}
		} catch (Exception e) {
		} finally { 
			listenerLock.release();
		}
	}

	
}
