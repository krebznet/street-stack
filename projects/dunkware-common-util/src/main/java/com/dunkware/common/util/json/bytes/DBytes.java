package com.dunkware.common.util.json.bytes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = DBytesSerializer.class)
@JsonDeserialize(using = DBytesDeserializer.class)
public class DBytes {

	private byte[] bytes;

	public DBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public byte[] getBytes() {
		return bytes;
	}

}
