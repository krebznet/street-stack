package com.dunkware.common.util.json;

import org.msgpack.jackson.dataformat.MessagePackFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class DJsonPack {

	private static ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());

	private static boolean initialized = false;

	private static void init() {
		if (!initialized) {
			objectMapper = new ObjectMapper(new MessagePackFactory());
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			objectMapper.registerModule(new JavaTimeModule());
			initialized = true;
		}
	}
	
	public static ObjectMapper getMapper() { 
		init();
		return objectMapper;
	}
	
	public static byte[] serializeBytes(Object pojo) throws JsonProcessingException{ 
		return objectMapper.writeValueAsBytes(pojo);
	}

}
