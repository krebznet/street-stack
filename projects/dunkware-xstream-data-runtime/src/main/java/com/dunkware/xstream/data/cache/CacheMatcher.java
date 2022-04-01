package com.dunkware.xstream.data.cache;

public interface CacheMatcher {
	
	default int getOrder() { 
		return 100;
	}

}
