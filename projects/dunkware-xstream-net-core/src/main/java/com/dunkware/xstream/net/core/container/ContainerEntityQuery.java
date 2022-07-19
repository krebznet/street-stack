package com.dunkware.xstream.net.core.container;

import com.dunkware.xstream.model.search.SessionEntitySearch;

public interface ContainerEntityQuery {

	public void init(Container container, SessionEntitySearch search) throws ContainerSearchException;
	
	void dispose(); 
	
	public ContainerSearchResults<ContainerEntity> execute() throws ContainerSearchException;
	
	

}
