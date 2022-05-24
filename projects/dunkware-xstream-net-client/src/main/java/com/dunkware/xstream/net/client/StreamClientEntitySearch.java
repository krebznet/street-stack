package com.dunkware.xstream.net.client;

import java.util.List;

import com.dunkware.net.proto.netstream.GNetEntity;

public interface StreamClientEntitySearch {
	
	int getSearchId(); 
	
	String getException();
	
	List<GNetEntity> getResults();

	StreamClientEntitySearchStatus getStatus();

	
}
