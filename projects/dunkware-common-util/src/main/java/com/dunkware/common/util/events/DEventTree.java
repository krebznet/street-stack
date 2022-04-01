package com.dunkware.common.util.events;

import com.dunkware.common.util.executor.DExecutor;

public class DEventTree {
	
	public static DEventTree newInstance(DExecutor executor)  { 
		return new DEventTree(executor);
	}

	private DEventNode rootNode;
	private DExecutor executor;
	
	private DEventTree(DExecutor executor) { 
		rootNode = new DEventNode(null, "/",this);
		this.executor = executor;
	}
	
	public DEventNode getRoot() { 
		return rootNode;
	}
	
	public DExecutor getExecutor() { 
		return executor;
	}
}
