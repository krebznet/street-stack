package com.dunkware.xstream.net.core.container;

import com.dunkware.xstream.container.ContainerExtType;

public interface ContainerExtension {
	
	void init(Container container, ContainerExtType type) throws ContainerException;
	
	void containerStarting(Container stash) throws ContainerException;
	
	void containerStarted(Container stash) throws ContainerException;
	
	void containerDisposing(Container stash);
	
	void containerDisposed(Container stash);
	
	void newSession(Container container);

}
