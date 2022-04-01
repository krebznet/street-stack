package com.dunkware.common.util.enums;

public enum ConnectionStatus {

	DISCONNECTED(0), CONNECTING(2), CONNECTED(1),FAILED(3);

	private int value;

	ConnectionStatus(final int newValue) {
		value = newValue;
	}

	public int getValue() {
		return value;
	}

}
