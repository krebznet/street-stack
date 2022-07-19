package com.dunkware.xstream.net.core.container.core;

import java.time.LocalDateTime;

import com.dunkware.xstream.net.core.container.ContainerEntityVarStats;

public class ContainerEntityVarStatsImpl implements ContainerEntityVarStats {
	
	private LocalDateTime highTime;
	private LocalDateTime lowTime; 
	private Object low; 
	private Object high; 
	

	@Override
	public LocalDateTime getHighTime() {
		return highTime;
	}

	@Override
	public Object getHigh() {
		return high;
	}

	@Override
	public LocalDateTime getLowTime() {
		return lowTime;
	}

	@Override
	public Object getLow() {
		return low;
	}

	
}
