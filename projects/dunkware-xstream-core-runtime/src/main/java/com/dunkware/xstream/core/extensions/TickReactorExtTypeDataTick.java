package com.dunkware.xstream.core.extensions;

public class TickReactorExtTypeDataTick {

	private int type;
	private int key;

	public TickReactorExtTypeDataTick() {

	}

	public TickReactorExtTypeDataTick(int tickType, int keyField) {
		this.type = tickType;
		this.key = keyField;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

}
