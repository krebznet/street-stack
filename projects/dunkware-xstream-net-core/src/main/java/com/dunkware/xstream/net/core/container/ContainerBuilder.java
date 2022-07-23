package com.dunkware.xstream.net.core.container;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.xstream.container.ContainerExtType;
import com.dunkware.xstream.net.core.container.core.ContainerImpl;

public class ContainerBuilder {

	public static ContainerBuilder newInstance() { 
		return new ContainerBuilder();
	}
	
	
	private DExecutor executor; 
	private DTimeZone timeZone; 
	private List<ContainerExtType> extensions = new ArrayList<ContainerExtType>();
	private String identifier; 
	
	private ContainerBuilder() { 
		
	}
	
	public ContainerBuilder executor(DExecutor executor) {
		this.executor = executor;
		return this;
	}
	
	public ContainerBuilder timezone(DTimeZone timeZone) { 
		this.timeZone = timeZone;
		return this; 
	}
	
	public ContainerBuilder extensions(List<ContainerExtType> extensionTypes) {
		this.extensions.addAll(extensionTypes);
		return this;
	}
	
	public ContainerBuilder identifier(String identifier) { 
		this.identifier = identifier; 
		return this;
	}
	
	public Container build() throws Exception { 
		ContainerInput input = new ContainerInput(extensions, executor, timeZone, identifier);
		ContainerImpl container = new ContainerImpl();
		container.start(input);;
		return container;
	}
}
