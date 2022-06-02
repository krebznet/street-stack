package com.dunkware.net.core.messaging;

public interface DRequestService {
	
	public void service(DMessage request, DMessage response) throws DException;

}
