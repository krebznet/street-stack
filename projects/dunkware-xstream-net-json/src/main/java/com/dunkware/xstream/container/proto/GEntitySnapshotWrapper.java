package com.dunkware.xstream.container.proto;

/**
 * Wraps a GStreamEvent message from google protcol buffers
 * as a JSON serialiable message; 
 * 
 * @author duncankrebs
 *
 */
public class GEntitySnapshotWrapper {

	private byte[] bytes;

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	} 
	
	
}
