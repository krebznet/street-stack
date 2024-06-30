package com.dunkware.utils.core.json.bytes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = JsonBytesSerializer.class)
@JsonDeserialize(using = JsonBytesDeserializer.class)
public class JsonBytes {

	private byte[] bytes;

	public JsonBytes() { 
		
	}
	public JsonBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public byte[] getBytes() {
		return bytes;
	}

}
