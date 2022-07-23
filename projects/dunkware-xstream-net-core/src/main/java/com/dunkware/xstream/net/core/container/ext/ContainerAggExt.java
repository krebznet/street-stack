package com.dunkware.xstream.net.core.container.ext;

import com.dunkware.xstream.container.ContainerExtType;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerException;
import com.dunkware.xstream.net.core.container.ContainerExtension;
import com.dunkware.xstream.net.core.container.anot.ACacheExtension;

@ACacheExtension(type = ContainerAggExtType.class)
public class ContainerAggExt implements ContainerExtension {

	@Override
	public void init(Container container, ContainerExtType type) throws ContainerException {
		// TODO Auto-generated method stub
		// need an aggg interface
		// just the model 
	}

	@Override
	public void containerStarting(Container stash) throws ContainerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void containerStarted(Container stash) throws ContainerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void containerDisposing(Container stash) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void containerDisposed(Container stash) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newSession(Container container) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
