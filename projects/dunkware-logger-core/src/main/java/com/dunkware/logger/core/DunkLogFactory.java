package com.dunkware.logger.core;

public class DunkLogFactory {


	public DunkLogBuilder log() { 
		return new DunkLogBuilder(this);
	}
}
