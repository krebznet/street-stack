package com.dunkware.logger;

public interface DLogger {

	public void setLevel(int level);
	
	public int getLevel();
	
	public void info(String message, Object...params);
	
	public void info(String message); 
	
	public void debug(String message, Object...params);
	
	public void debug(String message); 
	
	public void warn(String message, Object...params);
	
	public void warn(String message); 
	
	public void addLabel(String key, String value);
	
	public void addTag(String tag); 
	

}
