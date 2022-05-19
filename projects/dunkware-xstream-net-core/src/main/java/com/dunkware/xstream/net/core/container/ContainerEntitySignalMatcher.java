package com.dunkware.xstream.net.core.container;

public interface ContainerEntitySignalMatcher extends ContainerMatcher {
	
	default void update(Container stream) { 
		
	}

	boolean match(ContainerEntitySignal signal) throws ContainerException;
}
