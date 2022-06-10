package com.dunkware.xstream.net.core.container;

public interface ContainerExtension {
	
	void init(Container container, ContainerExtensionType type) throws ContainerException;
	
	void containerStarting(Container stash) throws ContainerException;
	
	void containerStarted(Container stash) throws ContainerException;
	
	void containerDisposing(Container stash);
	
	void containerDisposed(Container stash);
	
	void newSession(Container container);

}
