package com.dunkware.xstream.core.extensions;

import com.dunkware.xstream.xproject.model.XStreamExtensionType;

public class VarSnapshotPrinterType extends XStreamExtensionType {

	private int interval;

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	
}
