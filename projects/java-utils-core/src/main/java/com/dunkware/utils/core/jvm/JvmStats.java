package com.dunkware.utils.core.jvm;

public class JvmStats {
	
	public static JvmStats newInstance() { 
		return new JvmStats();
	}
	
	private long freeMemory;
	private long maxMemory;
	private long totalMemory; 
	
	private JvmStats() { 
			freeMemory = Runtime.getRuntime().freeMemory();
			maxMemory = Runtime.getRuntime().maxMemory();
			totalMemory = Runtime.getRuntime().totalMemory();
	}

	public long getFreeMemory() {
		return freeMemory;
	}

	public long getMaxMemory() {
		return maxMemory;
	}

	public long getTotalMemory() {
		return totalMemory;
	}
	

}
