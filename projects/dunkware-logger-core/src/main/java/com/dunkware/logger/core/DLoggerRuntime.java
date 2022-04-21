package com.dunkware.logger.core;

import com.dunkware.logger.DLog;
import com.dunkware.logger.DLogListener;

public class DLoggerRuntime {
	
	private static DLoggerRuntime instance; 
	
	public static DLoggerRuntime get() { 
		if(instance == null) { 
			instance = new DLoggerRuntime();
		}
		return instance; 
	}

	public void log(DLog log) { 
		
	}
	
	public void addListener(String matcher, DLogListener lisener) { 
		
	}
	
	public void removeListener(DLogListener listener) { 
		
	}
	
	private class RuntimeAppender { 
		
		private String pattern;
		private DLogListener appender; 
	}
}
