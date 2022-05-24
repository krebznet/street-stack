package com.dunkware.xstream.net.core.container;

import java.util.ArrayList;
import java.util.List;

public class ContainerSearchContext {
	
	private Container container; 
	private List<String> errors = new ArrayList<String>();
	
	public ContainerSearchContext(Container container) { 
		this.container = container;
	}
	
	public Container getContainer() { 
		return container;
	}
	
	public void logError(String error) { 
		errors.add(error);
	}

}
