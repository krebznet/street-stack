package com.dunkware.xstream.net.core.container;

public interface ContainerMatcher {
	
	default int getOrder() { 
		return 100;
	}

}
