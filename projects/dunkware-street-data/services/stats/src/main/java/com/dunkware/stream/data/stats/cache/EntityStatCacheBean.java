package com.dunkware.stream.data.stats.cache;

public class EntityStatCacheBean {
	
	private int sessions; 
	private double loaLocalTime; 
	private int loadsecond;
	private int errorcount;
	private int keycount; 
	private int stream;
	
	
	public int getSessions() {
		return sessions;
	}
	public void setSessions(int sessions) {
		this.sessions = sessions;
	}
	public double getLoaLocalTime() {
		return loaLocalTime;
	}
	public void setLoaLocalTime(double loaLocalTime) {
		this.loaLocalTime = loaLocalTime;
	}
	public int getLoadsecond() {
		return loadsecond;
	}
	public void setLoadsecond(int loadsecond) {
		this.loadsecond = loadsecond;
	}
	public int getErrorcount() {
		return errorcount;
	}
	public void setErrorcount(int errorcount) {
		this.errorcount = errorcount;
	}
	public int getKeycount() {
		return keycount;
	}
	public void setKeycount(int keycount) {
		this.keycount = keycount;
	}
	public int getStream() {
		return stream;
	}
	public void setStream(int stream) {
		this.stream = stream;
	} 
	
	
	

}
