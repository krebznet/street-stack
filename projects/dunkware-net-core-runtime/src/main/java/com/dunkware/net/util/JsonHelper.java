package com.dunkware.net.util;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {
	
	private static ObjectMapper objectMapper = null;
	
	private static boolean initialized = false; 
	
	private static void init() { 
		if(!initialized) { 
			objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			initialized = true;
		}
	}
	public static String serialize(Object object) throws JsonMappingException,JsonGenerationException,IOException { 
		init();
		StringWriter writer = new StringWriter();
		objectMapper.writeValue(writer, object);
		return writer.toString();
	}

	public static String serializePretty(Object object) throws JsonMappingException,JsonGenerationException,IOException { 
		init();
		String output = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		return output;
	}

	
	

	public static ObjectMapper getObjectMapper() { 
		init();
		return objectMapper;
	}

}
