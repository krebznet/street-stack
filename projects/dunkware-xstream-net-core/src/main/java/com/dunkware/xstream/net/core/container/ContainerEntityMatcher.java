package com.dunkware.xstream.net.core.container;

public interface ContainerEntityMatcher {
	
	boolean match(ContainerEntity entity) throws ContainerException;

}
