package com.dunkware.common.util.enums;

public enum RunStatus {
	
	STOPPED(0), RUNNING(2), STARTING(3), STOPPING(4), RUNFAIL(5);

	private int value;

	RunStatus(final int newValue) {
		value = newValue;
	}

	public int getValue() {
		return value;
	}


}
