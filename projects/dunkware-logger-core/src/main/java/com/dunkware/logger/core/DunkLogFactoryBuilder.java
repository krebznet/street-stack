package com.dunkware.logger.core;

public class DunkLogFactoryBuilder {
	
	public DunkLogFactoryBuilder type(String type) { 
		return this;
	}
	
	public DunkLogFactoryBuilder targets(String...targets) {
		return this;
	}
	
	public DunkLogFactory build() { 
		return new DunkLogFactory();
	}

}
